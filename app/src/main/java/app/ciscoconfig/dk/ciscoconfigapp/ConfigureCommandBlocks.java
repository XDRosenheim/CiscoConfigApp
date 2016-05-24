package app.ciscoconfig.dk.ciscoconfigapp;

import java.sql.Struct;
import java.util.ArrayList;

/**
 * Created by torso on 20-05-2016.
 *
 */
public class ConfigureCommandBlocks {

    public ArrayList<String> array;

    //configure Rip Protocol // not done yet
    public ArrayList configureRIPv2(String SerialInterface){
        array.clear();
        array.add("Version 2");
        array.add("No Auto-Summary");
        array.add("passive interface "+ SerialInterface);
        return array;
    }

    //Configure Serieal interface.
    public ArrayList setSerialInterface(String Clock ,String Interface,String Description, String ip, String subnetmask){
        array.clear();
        array.add("conf t");
        array.add("int serial "+Interface);
        if (Description.toString() == ""){}
        else{array.add("description "+Description);}
        array.add("ip address "+ip+" "+subnetmask);
        if (Clock.toString() == ""){}
        else{array.add("clock rate "+Clock);}
        array.add("no shut");
        array.add("end");
        return array;
    }

    //no ip domain-lookup
    public ArrayList noIpDomainLookUp(){
        array.clear();
        array.add("No ip domain-lookup");
        array.add("end");
        return array;
    }
    // sethostname
    public ArrayList setHostName (String name){
        array.clear();
        array.add("conf t");
        array.add("hostname "+ name);
        array.add("end");
        return array;
    }
    // Set ip on FastEthernet interface                     x/y
    public ArrayList setIpFeastEthernetInterface(String Interface,String Description, String ip, String subnetmask){
        array.clear();
        array.add("conf t");
        array.add("int fa "+Interface);
        if (Description.toString() == ""){}
        else{array.add("description "+Description);}
        array.add("ip address "+ip+" "+subnetmask);
        array.add("no shut");
        array.add("end");
        return array;
    }
    // Set Messages Of the Day Banner.
    public ArrayList setMOTD(String msg){
        array.clear();
        array.add("conf t");
        array.add("banner motd "+ msg);
        array.add("end");
        return array;
    }
    // Save the Running configuration to the startup.
    public ArrayList copyRunToStart(){
        array.clear();
        array.add("copy run start");
        array.add(""); // confirm copy
        array.add("end");
        return array;
    }
}
