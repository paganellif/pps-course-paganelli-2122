package it.pps.course.u01b.patterns.strategy.devices.aula;

public class Launcher {

    public static void main(String[] args) {
        DeviceManagerImpl dm = new DeviceManagerImpl();
        dm.addDevice(new DeviceImpl("a"));
        dm.addDevice(new DeviceImpl("b"));
        dm.addDevice(new DeviceImpl("a"));

        for (Device d: dm.devicesByName("a")){
            System.out.println(d);
        }

        for (Device d: dm.devicesByChooser(d -> d.getName().equals("a"))){
            System.out.println(d);
        }
    }
}
