#!/usr/bin/python

from mininet.net import Mininet
from mininet.node import Controller, RemoteController, OVSController
from mininet.node import CPULimitedHost, Host, Node
from mininet.node import OVSKernelSwitch, UserSwitch
from mininet.node import IVSSwitch
from mininet.cli import CLI
from mininet.log import setLogLevel, info
from mininet.link import TCLink, Intf
from subprocess import call

def myNetwork():

    net = Mininet( topo=None,
                   build=False,
                   ipBase='10.0.0.0/8')

    info( '* Adding controller\n' )
    info( '* Add switches\n')
    s3 = net.addSwitch('s3', cls=OVSKernelSwitch, failMode='standalone')
    s2 = net.addSwitch('s2', cls=OVSKernelSwitch, failMode='standalone')
    s4 = net.addSwitch('s4', cls=OVSKernelSwitch, failMode='standalone')
    s1 = net.addSwitch('s1', cls=OVSKernelSwitch, failMode='standalone')

    info( '* Add hosts\n')
    
    rc = net.addHost('rc', cls=Node, ip='192.168.100.6/29', defaultRoute=None)
    rc.cmd('sysctl -w net.ipv4.ip_forward=1')
    
    rsuc1 = net.addHost('rsuc1', cls=Node, ip='192.168.100.1/29', defaultRoute=None)
    rsuc1.cmd('sysctl -w net.ipv4.ip_forward=1')

    rsuc2 = net.addHost('rsuc2', cls=Node, ip='192.168.100.9/29', defaultRoute=None)
    rsuc2.cmd('sysctl -w net.ipv4.ip_forward=1')
    
    hsuc2 = net.addHost('hsuc2', cls=Host, ip='10.0.2.254/24', defaultRoute='via 10.0.2.1')
    
    hsuc1 = net.addHost('hsuc1', cls=Host, ip='10.0.1.254/24', defaultRoute='via 10.0.1.1')

    info( '* Add links\n')
    net.addLink(rc, s1)
    net.addLink(s1, rsuc1)
    net.addLink(rsuc1, s3)
    net.addLink(s3, hsuc1)
    net.addLink(rc, s2)
    net.addLink(s2, rsuc2)
    net.addLink(rsuc2, s4)
    net.addLink(s4, hsuc2)

    rc.setIP("192.168.100.6",prefixLen = 29,intf = "rc-eth0")
    rc.setIP("192.168.100.14",prefixLen = 29,intf = "rc-eth1")
    rsuc1.setIP("192.168.100.1",prefixLen = 29,intf = "rsuc1-eth0")
    rsuc1.setIP("10.0.1.1",prefixLen = 24,intf = "rsuc1-eth1")
    rsuc2.setIP("192.168.100.9",prefixLen = 29,intf = "rsuc2-eth0")
    rsuc2.setIP("10.0.2.1",prefixLen = 24,intf = "rsuc2-eth1")

    
    rc.cmd('ip route add 10.0.1.0/24 via 192.168.100.1 dev rc-eth0')
    rc.cmd('ip route add 10.0.2.0/24 via 192.168.100.9 dev rc-eth1')

    rsuc1.cmd('ip route add 192.168.100.8/29 via 192.168.100.1 dev rsuc1-eth0')
    rsuc1.cmd('ip route add 10.0.2.0/24 via 192.168.100.1 dev rsuc1-eth0')

    rsuc2.cmd('ip route add 192.168.100.0/29 via 192.168.100.9 dev rsuc2-eth0')
    rsuc2.cmd('ip route add 10.0.1.0/24 via 192.168.100.9 dev rsuc2-eth0')

    
    info( '* Starting network\n')
    net.build()
    info( '* Starting controllers\n')
    for controller in net.controllers:
        controller.start()

    info( '* Starting switches\n')
    net.get('s3').start([])
    net.get('s2').start([])
    net.get('s4').start([])
    net.get('s1').start([])

    info( '* Post configure switches and hosts\n')

    CLI(net)
    net.stop()

if __name__ == '__main__':
    setLogLevel( 'info' )
    myNetwork()

