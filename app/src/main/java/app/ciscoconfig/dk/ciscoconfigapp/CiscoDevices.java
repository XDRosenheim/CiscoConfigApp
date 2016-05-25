package app.ciscoconfig.dk.ciscoconfigapp;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by torso
 * on 5/25/2016.
 */
public class CiscoDevices {

    List<String[]> DeviceInfo;
    public String[] DeviceModel;
    public String[] FastEthernetInterfaces;
    public String[] GigabitInterfaces;
    public String[] SerialInterfaces;

    public ArrayList Router1900(){
        DeviceModel = new String[]{"Router 1900"};
        for (int i=0;i<1;i++) {
            GigabitInterfaces = new String[]{"GigabitEthernet 0/"+i};
        }
        for (int i=0;i<1;i++) {
            SerialInterfaces = new String[]{"Serial 0/0/"+i};
        }

        DeviceInfo.add(DeviceModel);
        DeviceInfo.add(GigabitInterfaces);
        DeviceInfo.add(SerialInterfaces);
        return (ArrayList) DeviceInfo;
    }

}
