package com.hotelautomation.service;

import com.hotelautomation.domain.request.MotionDetectionInput;

public interface ElectricityManagementService {

    void processMotionDetection(MotionDetectionInput input);
}
