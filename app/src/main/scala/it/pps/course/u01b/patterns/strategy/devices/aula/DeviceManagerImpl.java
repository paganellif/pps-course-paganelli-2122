package it.pps.course.u01b.patterns.strategy.devices.aula;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

interface DeviceChooser {
    boolean choose(Device device);
}

/**
 * Created by mirko on 11/6/16.
 */
public class DeviceManagerImpl implements DeviceManager {

    private List<Device> devices = new ArrayList<>();

    @Override
    public void addDevice(Device device) {
        this.devices.add(device);
    }

    @Override
    public List<Device> devices() {
        return Collections.unmodifiableList(this.devices);
    }

    public List<Device> devicesByName(String s) {
        return this.devicesByChooser(d -> d.getName().equals(s));
    }

    public List<Device> devicesByNameLength(int l) {
        return this.devicesByChooser(d -> d.getName().length()==l);
    }


    List<Device> devicesByChooser(DeviceChooser chooser){
        List<Device> list = new ArrayList<>();
        for (Device device: devices){
            if (chooser.choose(device)){
                list.add(device);
            }
        }
        return Collections.unmodifiableList(list);
    }

    @Override
    public void switchAllOn() {
        this.devices.forEach(Device::on);
    }

    @Override
    public void switchAllOff() {
        this.devices.forEach(Device::off);
    }

    @Override
    public boolean allOn() {
        for (final Device device: this.devices()){
            if (!device.isOn()){
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean allWorking() {
        for (final Device device: this.devices()){
            if (!device.isWorking()){
                return false;
            }
        }
        return true;
    }
}
