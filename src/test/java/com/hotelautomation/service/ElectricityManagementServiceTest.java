package com.hotelautomation.service;

import com.hotelautomation.builder.impl.FloorBuilderImpl;
import com.hotelautomation.domain.*;
import com.hotelautomation.domain.request.MotionDetectionInput;
import com.hotelautomation.exception.HotelAutomationException;
import com.hotelautomation.service.impl.ElectricityManagerServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Matchers.*;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ElectricityManagementServiceTest {

    @InjectMocks
    ElectricityManagerServiceImpl service;
    @Mock
    FloorBuilderImpl floorBuilder;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    private Floor getFloorData() {
        Floor floor = new Floor();
        Corridor corridor = new Corridor();
        corridor.setType(CorridorType.MAIN);
        corridor.setCorridorNumber(1);
        List<Light> lights = new ArrayList<>();
        Light light = new Light();
        light.setSwitchButton(true);
        lights.add(light);
        List<Ac> acs = new ArrayList<>();
        Ac ac = new Ac();
        ac.setSwitchButton(true);
        acs.add(ac);
        corridor.setLights(lights);
        corridor.setAcs(acs);

        Corridor subCorridor = new Corridor();
        subCorridor.setType(CorridorType.SUB);
        subCorridor.setCorridorNumber(2);
        List<Light> subLights = new ArrayList<>();
        Light subLight = new Light();
        subLight.setSwitchButton(false);
        subLights.add(light);
        List<Ac> subAcs = new ArrayList<>();
        Ac subAc = new Ac();
        ac.setSwitchButton(true);
        subAcs.add(subAc);
        subCorridor.setLights(lights);
        subCorridor.setAcs(acs);

        List<Corridor> corridors = new ArrayList<>();
        corridors.add(corridor);
        corridors.add(subCorridor);

        floor.setCorridors(corridors);
        floor.setNumber(1);

        return floor;
    }

    @Test
    public void shouldProcessMotionDetectionWithoutException() throws HotelAutomationException {

        when(floorBuilder.getFloor(1)).thenReturn(getFloorData());
        MotionDetectionInput input = new MotionDetectionInput();
        input.setMotionDetected(true);
        input.setType(CorridorType.SUB);
        input.setCorridorNumber(2);
        input.setFloorNumber(1);
        service.processMotionDetection(input);

    }

    @Test(expected = HotelAutomationException.class)
    public void shouldThrowHotelAutomationExceptionOnInvalidInputWhileProcessMotionDetection() throws HotelAutomationException {
        MotionDetectionInput input = new MotionDetectionInput();
        input.setMotionDetected(true);
        input.setType(CorridorType.SUB);
        input.setCorridorNumber(2);
        input.setFloorNumber(10);
        service.processMotionDetection(input);

    }

}
