package it.pps.course.u01b.devices;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


/**
 * Created by mirko on 11/7/16.
 */
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class DeviceManagerImplTest {

    public static final int SIZE = 3;
    private DeviceManager manager;
    private List<Device> devices;

    @BeforeAll
    public void init() {
        this.manager = new DeviceManagerImpl();
        this.devices = new ArrayList<>();
        for (int i = 0; i< SIZE; i++) {
            final Device d = new DeviceImpl();
            d.on();
            this.devices.add(d);
            this.manager.addDevice(d);
        }
    }

    @Test
    public void devices() {
        assertEquals(this.devices,this.manager.devices());
    }

    @Test
    public void switchAllOn() {
        this.init();
        this.manager.devices().get(0).off();
        this.manager.devices().get(SIZE-1).off();
        this.manager.switchAllOn();
        assertTrue(this.manager.allOn());
    }

    @Test
    public void switchAllOff(){
        this.manager.devices().get(SIZE-1).off();
        this.manager.switchAllOff();
        for (final Device device: this.manager.devices()){
            assertFalse(device.isOn());
        }
    }

    @Test
    public void allOn(){
        for (final Device device: this.manager.devices()){
            assertTrue(device.isOn());
        }
    }

    @Test
    public void allWorking(){
        for (final Device device: this.manager.devices()){
            assertTrue(this.manager.allWorking());
        }
    }

    @Test
    public void someNotWorking(){
        final DeviceImpl device = new DeviceImpl();
        device.stopWorking();
        this.manager.addDevice(device);
        assertFalse(this.manager.allWorking());
    }

}