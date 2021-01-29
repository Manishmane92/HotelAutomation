package com.hotelautomation.domain.request;

import com.hotelautomation.domain.CorridorType;

public class MotionDetectionInput {
    private Integer floorNumber;
    private Integer corridorNumber;
    private CorridorType type;
    private Boolean isMotionDetected;

    public Integer getFloorNumber() {
        return floorNumber;
    }

    public void setFloorNumber(Integer floorNumber) {
        this.floorNumber = floorNumber;
    }

    public Integer getCorridorNumber() {
        return corridorNumber;
    }

    public void setCorridorNumber(Integer corridorNumber) {
        this.corridorNumber = corridorNumber;
    }

    public CorridorType getType() {
        return type;
    }

    public void setType(CorridorType type) {
        this.type = type;
    }

    public Boolean getMotionDetected() {
        return isMotionDetected;
    }

    public void setMotionDetected(Boolean motionDetected) {
        isMotionDetected = motionDetected;
    }
}
