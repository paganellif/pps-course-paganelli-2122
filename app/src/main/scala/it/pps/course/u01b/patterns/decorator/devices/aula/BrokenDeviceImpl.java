package it.pps.course.u01b.patterns.decorator.devices.aula;

/**
 * Created by mirko on 11/6/16.
 */
public class BrokenDeviceImpl extends DeviceDecorator {


    public BrokenDeviceImpl(Device device) {
        super(device);
    }

}