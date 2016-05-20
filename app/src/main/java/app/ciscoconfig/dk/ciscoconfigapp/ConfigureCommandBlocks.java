package app.ciscoconfig.dk.ciscoconfigapp;

import java.util.ArrayList;

/**
 * Created by torso on 20-05-2016.
 *
 */
public class ConfigureCommandBlocks {

    public ArrayList<String> array;

    // Set Messages Of the Day Banner.
    public ArrayList setMOTD(String msg){
        array.clear();
        array.add("conf t");
        array.add("banner motd"+ msg);
        array.add("end");
        return array;
    }

    // Save the Running configuration to the startup.
    public ArrayList copyRunToStart(){
        array.clear();
        array.add("copy run start");
        array.add("");
        array.add("end");
        return array;
    }

}
