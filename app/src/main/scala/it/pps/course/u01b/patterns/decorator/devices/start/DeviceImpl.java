package it.pps.course.u01b.patterns.decorator.devices.start;

/**
 * Created by mirko on 11/6/16.
 */
public class DeviceImpl implements Device {

    private boolean on;
    private boolean working;
    private final String name;

    public DeviceImpl(String name) {
        this.on = false;
        this.working = true;
        this.name = name;
    }

    @Override
    public void on() {
        if (this.isWorking()) {
            this.on = true;
        }
    }

    @Override
    public void off() {
        this.on = false;
    }

    @Override
    public boolean isOn() {
        return this.on;
    }

    @Override
    public boolean isWorking() {
        return this.working;
    }

    @Override
    public String getName() {
        return this.name;
    }
}
