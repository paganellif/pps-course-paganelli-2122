package it.pps.course.u01b.patterns.adapter.devices.done;

import java.util.Arrays;

/**
 * Created by mirko on 3/5/17.
 */
public class Client {

    final MyDeviceManager manager;

    public Client(MyDeviceManager manager) {
        this.manager = manager;
    }

    void prepare(){
        this.manager.addSomeDevices(Arrays.asList(new DeviceImpl("A"), new DeviceImpl("B")));
        this.manager.switchOn(0);
        this.manager.switchOff(1);
    }
}
