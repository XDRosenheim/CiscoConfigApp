package app.ciscoconfig.dk.ciscoconfigapp;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Created by torso
 * on 5/25/2016.
 */
public class CiscoDevices {

    public Array CiscoDevice;
    public ArrayList<String> DeviceModel;
    public ArrayList<String> FastEthernetInterfaces;
    public ArrayList<String> GigabitInterfaces;
    public ArrayList<String> SerialInterfaces;

    public Array Router1900(){
        DeviceModel.add("Router 1900");
        for (int i=0;i<1;i++) {
            GigabitInterfaces.add("Gigabitethernet 0/"+i);
        }
        for (int i=0;i<1;i++) {
            SerialInterfaces.add("Gigabitethernet 0/0/"+i);
        }
        return CiscoDevice[DeviceModel][GigabitInterfaces][SerialInterfaces];
    }

}
