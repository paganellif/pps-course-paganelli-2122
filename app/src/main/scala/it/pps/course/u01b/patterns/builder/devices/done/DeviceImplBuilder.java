package it.pps.course.u01b.patterns.builder.devices.done;

public class DeviceImplBuilder {
    private String name = "Device";
    private String description = "";

    public DeviceImplBuilder setName(String name) {
        this.name = name;
        return this;
    }

    public DeviceImplBuilder setDescription(String description) {
        this.description = description;
        return this;
    }

    public DeviceImpl createDeviceImpl() {
        return new DeviceImpl(name, description);
    }
}