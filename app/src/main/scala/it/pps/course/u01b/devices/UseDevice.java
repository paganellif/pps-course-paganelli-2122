package it.pps.course.u01b.devices;

public class UseDevice {

    public static void main(String[] args) {
        Device d = new DeviceImpl();
        System.out.println(d.isOn());
    }
}
