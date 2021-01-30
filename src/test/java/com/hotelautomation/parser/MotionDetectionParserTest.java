package com.hotelautomation.parser;

import com.hotelautomation.domain.CorridorType;
import com.hotelautomation.domain.request.MotionDetectionInput;
import com.hotelautomation.exception.HotelAutomationException;
import com.hotelautomation.parser.impl.MotionDetectionParserImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class MotionDetectionParserTest {


    @Test
    public void shouldParseMotionDetectionInputAndReturnMotionDetectionInputObject() throws HotelAutomationException {
        MotionDetectionParserImpl motionDetectionParser = new MotionDetectionParserImpl();
        MotionDetectionInput motionDetectionInput =motionDetectionParser.parseMotionDetectionInputData("1,2,true");
        assertEquals((Integer) 1,motionDetectionInput.getFloorNumber());
        assertEquals((Integer) 2,motionDetectionInput.getCorridorNumber());
        assertEquals(true,motionDetectionInput.getMotionDetected());
        assertEquals(CorridorType.SUB,motionDetectionInput.getType());
    }

    @Test(expected = HotelAutomationException.class)
    public void shouldThrowHotelAutomationExceptionOnInvalidInputWhileParsingMotionDetectionInput() throws HotelAutomationException {
        MotionDetectionParserImpl motionDetectionParser = new MotionDetectionParserImpl();
        motionDetectionParser.parseMotionDetectionInputData("1,invalid_corridor_number,3");

    }
}
