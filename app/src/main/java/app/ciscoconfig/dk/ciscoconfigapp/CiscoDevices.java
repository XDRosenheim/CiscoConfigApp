package app.ciscoconfig.dk.ciscoconfigapp;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by torso
 * on 5/25/2016.
 */
public class CiscoDevices {

    public ArrayList DeviceInfo = new ArrayList<String>();
    public ArrayList<String> FastEthernetInterfaces = new ArrayList<String>();
    public ArrayList<String> GigabitInterfaces = new ArrayList<String>();
    public ArrayList<String> SerialInterfaces = new ArrayList<String>();

    public ArrayList Router1900(){
        for (int i=1;i<3;i++) {
            GigabitInterfaces.add("Gigabitethernet 0/"+i);
        }
        for (int i=1;i<3;i++) {
            SerialInterfaces.add("Serial 0/0/"+i);
        }
        DeviceInfo.add("Router 1900");
        DeviceInfo.add(GigabitInterfaces);
        DeviceInfo.add(SerialInterfaces);

        return DeviceInfo;
    }
}
