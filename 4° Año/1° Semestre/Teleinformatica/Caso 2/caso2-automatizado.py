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


class Caso2:

    def __init__(self, numberOfNetworks, firstNetwork='192.168.100.0', mask=29, insideNetwork='10.0'):
        self.net = Mininet(topo=None, build=False, ipBase='10.0.0.0/8')
        self.numberOfNetworks = numberOfNetworks
        self.firstNetwork = firstNetwork
        self.mask = mask
        self.IPs = None
        self.insideNetwork = insideNetwork
        self.rc = self.net.addHost('rc', cls=Node, defaultRoute=None)
        self.rc.cmd('sysctl -w net.ipv4.ip_forward=1')


    def networkIPs(self):
        bitsIPs = 32 - self.mask
        self.IPs = 2 ** bitsIPs
        nets = self.firstNetwork.rsplit('.', 1)
        networks = []
        for network in range(self.numberOfNetworks):
            networks.append(nets[0] + '.' + str(int(nets[1]) + self.IPs * network))
        return networks


    def createRouterSuc(self, number, networkIP):
        name = 'rsuc' + str(number)
        prefix = networkIP.rsplit('.', 1)
        IP = prefix[0] + '.' + str(int(prefix[1]) + 1)
        rcIP = prefix[0] + '.' + str(int(prefix[1]) + self.IPs - 2)
        rsucIP = self.insideNetwork + '.' + str(number) + '.1'
        hname = 'hsuc' + str(number)
        hsucIP = self.insideNetwork + '.' + str(number) + '.254'
        allt = [name, IP, rcIP, rsucIP, hname, hsucIP]
        r = self.net.addHost(name, cls=Node, defaultRoute=None)
        r.cmd('sysctl -w net.ipv4.ip_forward=1')
        return(allt)


    def myNetwork(self):
        networks = self.networkIPs()
        nets = []
        for router in range(len(networks)):
            nets.append(self.createRouterSuc(router + 1, networks[router]))
        for net in nets:
            
        # r.setIP(IP, prefixLen=29, intf=name + "-eth0")
        # r.setIP(rsucIP, prefixLen=24, intf=name + "-eth1")
        # hsuc = self.net.addHost(hname, cls=Host, ip=hsucIP, defaultRoute='via ' + rsucIP)




        info( '* Starting network\n')
        self.net.build()
        info( '* Starting controllers\n')
        for controller in self.net.controllers:
            controller.start()

        info( '* Post configure switches and hosts\n')

        CLI(self.net)
        self.net.stop()


if __name__ == '__main__':
    setLogLevel( 'info' )
    numberOfNetworks = int(input('How many networks would you like? '))
    caso2 = Caso2(numberOfNetworks)
    caso2.myNetwork()
