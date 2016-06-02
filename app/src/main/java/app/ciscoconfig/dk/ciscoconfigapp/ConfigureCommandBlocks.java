package app.ciscoconfig.dk.ciscoconfigapp;

import java.io.BufferedReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * Created by torso
 * Creation date 20-05-2016
 */
public class ConfigureCommandBlocks {

    private Socket Sock;
    private PrintWriter Out;
    private BufferedReader In;


    public ConfigureCommandBlocks() {
        Sock = Singleton.getSocket();
        Out = Singleton.getOut();
        In = Singleton.getIn();
    }

    // Swtich trunking
    public void SwitchTrunking(String inferfaceRange, String Vlan) {
        Out.println("conf t");
        Out.println("int range fa " + inferfaceRange);
        Out.println("shut");
        Out.println("switchport mode trunk");
        Out.println("switchport trunknative vlan " + Vlan);
    }


    // Set static route
    public void setStaticRoute(String Network, String Netmask, String NextHop) {
        Out.println("conf t");
        Out.println("ip route " + Network + " " + Netmask + " " + NextHop);
        Out.println("end");
    }

    // set OSPF link priority
    public void OSPFLinkPRiority(String EthernetInterface, String priorityValue) {
        Out.println("conf t");
        Out.println("int " + EthernetInterface);
        Out.println("ip ospf priority " + priorityValue);
        Out.println("no shut");
        Out.println("end");
    }

    // configure ospf protocol // not 100% sure on some of the theory with network vs area
    public void configureOSPF(String processID, String network, String netmask, String SerialInterface, String Speed) {
        Out.println("conf t");
        Out.println("Router ospf " + processID);
        Out.println("network " + network + " " + netmask + " area " + network);
        Out.println("Default-information originate");
        Out.println("Auto-cost reference-bandwidth 10000");
        Out.println("end");
        Out.println("conf t");
        Out.println("int " + SerialInterface);
        Out.println("no shut");
        Out.println("Bandwidth " + Speed);

        Out.println("end");
    }

    // configure EIGRP protocol // not 100% sure on some of the theory with network vs ipaddress
    public void configureEIGRP(String VIName, String network, String SerialInterface, String Speed, String ipaddress, String netmask) {
        Out.println("conf t");
        Out.println("router eigrp " + VIName);
        Out.println("No auto-summary");
        Out.println("network " + network);
        Out.println("Redistribute static");
        Out.println("end");
        Out.println("conf t");
        Out.println("int " + SerialInterface);
        Out.println("bandwidth " + Speed);
        Out.println("ip summary-adress eigrp " + VIName + " " + ipaddress + " " + netmask);
        Out.println("end");
    }

    //configure Rip Protocol
    public void configureRIPv2(String SerialInterface, String network) {
        Out.println("conf t");
        Out.println("router rip");
        Out.println("Version 2");
        Out.println("No Auto-Summary");
        Out.println("network " + network);
        Out.println("passive interface " + SerialInterface);
        Out.println("default-information originate");
        Out.println("clear ip route");
        Out.println("end");
    }

    //Configure Serieal interface.
    public void setSerialInterface(String Clock, String SerialInterface, String Description, String ip, String subnetmask) {
        Out.println("conf t");
        Out.println("int " + SerialInterface);
        if (!Description.equals("")) {
            Out.println("description " + Description);
        }
        Out.println("ip address " + ip + " " + subnetmask);
        // Only on DCE side
        if (!Clock.equals("")) {
            Out.println("clock rate " + Clock);
        }
        Out.println("no shut");
        Out.println("end");
    }

    //no ip domain-lookup
    public void noIpDomainLookUp() {
        Out.println("conf t");
        Out.println("No ip domain-lookup");
        Out.println("end");
    }

    // sethostname
    public void setHostName(String name) {
        Out.println("conf t");
        Out.println("hostname " + name);
        Out.println("end");
    }

    // Set ip on FastEthernet interface          fa/fe/Serial+ x/y
    public void setIpFeastEthernetInterface(String Interface, String Description, String ip, String subnetmask) {
        Out.println("conf t");
        Out.println("int " + Interface);
        if (!Description.equals("")) {
            Out.println("description " + Description);
        }
        Out.println("ip address " + ip + " " + subnetmask);
        Out.println("no shut");
        Out.println("end");
    }

    // Set Messages Of the Day Banner.
    public void setMOTD(String msg) {
        Out.println("conf t");
        Out.println("banner motd #" + msg + "#");
        Out.println("end");
    }

    // Save the Running configuration to the startup.
    public void copyRunToStart() {
        Out.println("copy run start");
        Out.println(""); // confirm copy
        Out.println("end");
    }
}
