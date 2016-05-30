package app.ciscoconfig.dk.ciscoconfigapp;

import java.util.ArrayList;

/**
 * Created by torso
 * Creation date 20-05-2016
 */
public class ConfigureCommandBlocks {
    public ArrayList<String> array;

    // Swtich trunking
    public ArrayList SwitchTrunking(String inferfaceRange, String Vlan){
        array.clear();
        array.add("conf t");
        array.add("int range fa "+inferfaceRange);
        array.add("shut");
        array.add("switchport mode trunk");
        array.add("switchport trunknative vlan "+ Vlan);
        return array;
    }


    // Set static route
    public ArrayList setStaticRoute(String Network,String Netmask, String NextHop){
        array.clear();
        array.add("conf t");
        array.add("ip route "+ Network +" "+Netmask +" "+NextHop);
        array.add("end");
        return array;
    }

    // set OSPF link priority
    public ArrayList OSPFLinkPRiority(String EthernetInterface, String priorityValue){
        array.clear();
        array.add("conf t");
        array.add("int "+EthernetInterface);
        array.add("ip ospf priority "+priorityValue);
        array.add("no shut");
        array.add("end");
        return array;
    }

    // configure ospf protocol // not 100% sure on some of the theory with network vs area
    public ArrayList configureOSPF(String processID, String network, String netmask, String SerialInterface, String Speed) {
        array.clear();
        array.add("conf t");
        array.add("Router ospf " + processID);
        array.add("network " + network + " " + netmask + " area " + network);
        array.add("Default-information originate");
        array.add("Auto-cost reference-bandwidth 10000");
        array.add("end");
        array.add("conf t");
        array.add("int " + SerialInterface);
        array.add("no shut");
        array.add("Bandwidth " + Speed);

        array.add("end");
        return array;
    }

    // configure EIGRP protocol // not 100% sure on some of the theory with network vs ipaddress
    public ArrayList configureEIGRP(String VIName, String network, String SerialInterface, String Speed, String ipaddress, String netmask) {
        array.clear();
        array.add("conf t");
        array.add("router eigrp " + VIName);
        array.add("No auto-summary");
        array.add("network " + network);
        array.add("Redistribute static");
        array.add("end");
        array.add("conf t");
        array.add("int " + SerialInterface);
        array.add("bandwidth " + Speed);
        array.add("ip summary-adress eigrp " + VIName + " " + ipaddress + " " + netmask);
        array.add("end");
        return array;
    }

    //configure Rip Protocol
    public ArrayList configureRIPv2(String SerialInterface, String network) {
        array.clear();
        array.add("conf t");
        array.add("router rip");
        array.add("Version 2");
        array.add("No Auto-Summary");
        array.add("network " + network);
        array.add("passive interface " + SerialInterface);
        array.add("default-information originate");
        array.add("clear ip route");
        array.add("end");
        return array;
    }

    //Configure Serieal interface.
    public ArrayList setSerialInterface(String Clock, String SerialInterface, String Description, String ip, String subnetmask) {
        array.clear();
        array.add("conf t");
        array.add("int " + SerialInterface);
        if (!Description.equals("")) {
            array.add("description " + Description);
        }
        array.add("ip address " + ip + " " + subnetmask);
        // Only on DCE side
        if (!Clock.equals("")) {
            array.add("clock rate " + Clock);
        }
        array.add("no shut");
        array.add("end");
        return array;
    }

    //no ip domain-lookup
    public ArrayList noIpDomainLookUp() {
        array.clear();
        array.add("conf t");
        array.add("No ip domain-lookup");
        array.add("end");
        return array;
    }

    // sethostname
    public ArrayList setHostName(String name) {
        array.clear();
        array.add("conf t");
        array.add("hostname " + name);
        array.add("end");
        return array;
    }

    // Set ip on FastEthernet interface          fa/fe/Serial+ x/y
    public ArrayList setIpFeastEthernetInterface(String Interface, String Description, String ip, String subnetmask) {
        array.clear();
        array.add("conf t");
        array.add("int " + Interface);
        if (!Description.equals("")) {
            array.add("description " + Description);
        }
        array.add("ip address " + ip + " " + subnetmask);
        array.add("no shut");
        array.add("end");
        return array;
    }

    // Set Messages Of the Day Banner.
    public ArrayList setMOTD(String msg) {
        array.clear();
        array.add("conf t");
        array.add("banner motd " + msg);
        array.add("end");
        return array;
    }

    // Save the Running configuration to the startup.
    public ArrayList copyRunToStart() {
        array.clear();
        array.add("copy run start");
        array.add(""); // confirm copy
        array.add("end");
        return array;
    }
}
