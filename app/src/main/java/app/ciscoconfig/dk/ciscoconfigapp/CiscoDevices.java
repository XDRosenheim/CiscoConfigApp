package app.ciscoconfig.dk.ciscoconfigapp;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by torso
 * on 5/25/2016.
 */
public class CiscoDevices {

    public String[][] DeviceInfo;
    //public String[][] DeviceModel = new String[][];
    public String[][] FastEthernetInterfaces;
    public String[][] GigabitInterfaces;
    public String[][] SerialInterfaces;

    public String[][] Router1900(){
        for (int i=0;i<1;i++) {
            //DeviceInfo[1] = new String[][]{"Gigabitethernet 0/"+i};
            GigabitInterfaces[i] = new String[]{"Gigabitethernet 0/"+i};
        }
        for (int i=0;i<1;i++) {
            //DeviceInfo[2] = new String[][]{"Serial 0/"+i};
            SerialInterfaces[i] = new String[]{"Serial 0/0/"+i};
        }
        DeviceInfo[0] = new String[]{"Router 1900"};
        DeviceInfo[1] = new String[]{GigabitInterfaces.toString()};
        DeviceInfo[2] = new String[]{SerialInterfaces.toString()};

        return DeviceInfo;
    }
}
