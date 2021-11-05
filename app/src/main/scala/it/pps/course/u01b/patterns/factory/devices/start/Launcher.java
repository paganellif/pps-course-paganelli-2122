package it.pps.course.u01b.patterns.factory.devices.start;

/**
 * Created by mirko on 3/5/17.
 */
public class Launcher {

    public static void main(String[] args) {
        Device d1 = new DeviceImpl("A");
        Device d2 = new BrokenDeviceImpl("A");
    }
}
