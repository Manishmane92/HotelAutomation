package com.hotelautomation.parser.impl;

import com.hotelautomation.domain.CorridorType;
import com.hotelautomation.domain.request.MotionDetectionInput;
import com.hotelautomation.exception.HotelAutomationException;
import com.hotelautomation.parser.IMotionDetectionInputParser;

import java.util.List;

public class MotionDetectionParserImpl implements IMotionDetectionInputParser {
    @Override
    public MotionDetectionInput parseMotionDetectionInputData(String line) throws HotelAutomationException {
        MotionDetectionInput input = null;
        try {
                String[] fields = line.split(",");
                input = new MotionDetectionInput();
                input.setFloorNumber(Integer.valueOf(fields[0]));
                input.setType(CorridorType.SUB);
                input.setCorridorNumber(Integer.valueOf(fields[1]));
                input.setMotionDetected(Boolean.valueOf(fields[2]));

        }catch (Exception e){
            throw new HotelAutomationException("Error while parsing motion detection input data");
        }
        return input;
    }
}
