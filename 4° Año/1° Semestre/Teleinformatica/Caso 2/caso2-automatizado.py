#!/usr/bin/python
from mininet.net import Mininet
from mininet.node import Host, Node
from mininet.node import OVSKernelSwitch
from mininet.cli import CLI
from mininet.log import setLogLevel, info


class Star:

    def __init__(self, numberOfNetworks):
        self.numberOfNetworks = numberOfNetworks
        self.net = Mininet(topo=None, build=False, ipBase='10.0.0.0/8')
        self.rc = None

    def networkIPs(self):
        IPs = 8
        nets = '192.168.100'
        networks = []
        for network in range(self.numberOfNetworks):
            networks.append(nets + '.' + str(int(IPs * network)))
        return networks

    def genSwitch(self, where, number):
        name = 's' + where + str(number)
        self.net.addSwitch(name, cls=OVSKernelSwitch, failMode='standalone')

    def genRouter(self, number, networkIP):
        name = 'rsuc' + str(number)
        r = self.net.addHost(name, cls=Node)
        r.cmd('sysctl -w net.ipv4.ip_forward=1')

    def genHost(self, number):
        name = 'h1suc' + str(number)
        prefix = '10.0.' + str(number)
        IP = prefix + '.254/24'
        IPr = prefix + '.1'
        default = 'via ' + IPr
        self.net.addHost(name, cls=Host, ip=IP, defaultRoute=default)

    def genLinks(self, number, networkIP):
        sc = self.net.get('sc' + str(number))
        rs = self.net.get('rsuc' + str(number))
        ss = self.net.get('ssuc' + str(number))
        h = self.net.get('h1suc' + str(number))
        self.net.addLink(self.rc, sc)
        self.net.addLink(sc, rs)
        self.net.addLink(rs, ss)
        self.net.addLink(ss, h)

    def genNetwork(self):
        networks = self.networkIPs()
        info('*Adding switches\n')
        for index in range(len(networks)):
            self.genSwitch('c', index+1)
            self.genSwitch('suc', index+1)
        info('*Adding routers and hosts\n')
        self.rc = self.net.addHost('rc', cls=Node, defaultRoute=None)
        self.rc.cmd('sysctl -w net.ipv4.ip_forward=1')
        for index in range(len(networks)):
            self.genRouter(index+1, networks[index])
            self.genHost(index+1)
        info('*Adding links\n')
        for index in range(len(networks)):
            self.genLinks(index+1, networks[index])
        info('* Starting network\n')
        self.net.build()
        info('* Starting switches\n')
        for switch in self.net.switches:
            switch.start([])
        info('* Post configure switches and hosts\n')
        for index in range(len(networks)):
            rs = self.net.get('rsuc' + str(index+1))
            #  rc's IP for rsuc's network
            network = networks[index].rsplit('.', 1)
            IP = network[0] + '.' + str(int(network[1]) + 6)
            intfrc = 'rc-eth' + str(index)
            self.rc.setIP(IP, prefixLen=29, intf=intfrc)
            # rsuc's IPs for its networks
            IPout = network[0] + '.' + str(int(network[1]) + 1)
            intf0 = 'rsuc' + str(index + 1) + '-eth0'
            IPin = '10.0.' + str(index + 1) + '.1'
            IPinNet = '10.0.' + str(index + 1) + '.0/24'
            intf1 = 'rsuc' + str(index + 1) + '-eth1'
            rs.setIP(IPout, prefixLen=29, intf=intf0)
            rs.setIP(IPin, prefixLen=24, intf=intf1)
            # rc's route to rsuc
            cmd = 'ip route add ' + IPinNet + ' via ' + IPout + ' dev rc-eth' + str(index)
            self.rc.cmd(cmd)
            # rsuc's route to the other networks
            # cmd = 'ip route add 0.0.0.0/0 via ' + IP + ' dev ' + intf0
            cmd = 'ip route add 10.0.0.0/18 via ' + IP + ' dev ' + intf0
            rs.cmd(cmd)
            cmd = 'ip route add 192.168.100.0/24 via ' + IP + ' dev ' + intf0
            rs.cmd(cmd)
        CLI(self.net)
        self.net.stop()


def main():
    while True:
        while True:
            try:
                numberOfNetworks = int(input('How many networks would you like (up to 32)? '))
                break
            except Exception:
                print("Please enter a number")
        if numberOfNetworks > 0 and numberOfNetworks < 33:
            break
        else:
            print("Please enter a number between 1 and 32")
    caso2 = Star(numberOfNetworks)
    caso2.genNetwork()


if __name__ == '__main__':
    setLogLevel('info')
    main()
