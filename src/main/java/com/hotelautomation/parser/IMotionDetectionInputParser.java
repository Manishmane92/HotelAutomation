package com.hotelautomation.parser;

import com.hotelautomation.domain.request.MotionDetectionInput;
import com.hotelautomation.exception.HotelAutomationException;

import java.util.List;

public interface IMotionDetectionInputParser {

    MotionDetectionInput parseMotionDetectionInputData(String line) throws HotelAutomationException;
}
