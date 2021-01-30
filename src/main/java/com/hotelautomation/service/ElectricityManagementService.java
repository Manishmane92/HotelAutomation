package com.hotelautomation.service;

import com.hotelautomation.domain.request.MotionDetectionInput;
import com.hotelautomation.exception.HotelAutomationException;

public interface ElectricityManagementService {

    void processMotionDetection(MotionDetectionInput input) throws HotelAutomationException;
}
