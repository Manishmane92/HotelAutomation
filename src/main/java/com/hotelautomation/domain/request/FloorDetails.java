package com.hotelautomation.domain.request;

import com.hotelautomation.domain.IDomain;

public class FloorDetails implements IDomain {

    private Integer numberOfMainsCorridors;
    private Integer numberOfSubCorridors;
    private Integer floorNumber;


    public Integer getFloorNumber() {
        return floorNumber;
    }

    public void setFloorNumber(Integer floorNumber) {
        this.floorNumber = floorNumber;
    }

    public Integer getNumberOfMainCorridors() {
        return numberOfMainsCorridors;
    }

    public void setNumberOfMainCorridors(Integer numberOfMainCorridors) {
        this.numberOfMainsCorridors = numberOfMainCorridors;
    }

    public Integer getNumberOfSubCorridors() {
        return numberOfSubCorridors;
    }

    public void setNumberOfSubCorridors(Integer numberOfSubCorridors) {
        this.numberOfSubCorridors = numberOfSubCorridors;
    }
}
