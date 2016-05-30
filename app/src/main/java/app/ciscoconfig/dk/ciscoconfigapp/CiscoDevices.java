package app.ciscoconfig.dk.ciscoconfigapp;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by torso
 * on 5/25/2016.
 */
public class CiscoDevices {

    public ArrayList DeviceInfo;
    public ArrayList<String> FastEthernetInterfaces;
    public ArrayList<String> GigabitInterfaces;
    public ArrayList<String> SerialInterfaces;

    public ArrayList Router1900(){
        GigabitInterfaces.add("Gigabitethernet 0");
//        for (int i=1;i<3;i++) {
//            //DeviceInfo[1] = new String[][]{"Gigabitethernet 0/"+i};
//            GigabitInterfaces.add("Gigabitethernet 0/"+i);
//        }
//        for (int i=1;i<3;i++) {
//            //DeviceInfo[2] = new String[][]{"Serial 0/"+i};
//            SerialInterfaces.add("Serial 0/0/"+i);
//        }
        //DeviceInfo.add("Router 1900");
        DeviceInfo.add(GigabitInterfaces);
        //DeviceInfo.add(SerialInterfaces);

        return DeviceInfo;
    }
}
