package com.hotelautomation.builder;

import com.hotelautomation.domain.Floor;
import com.hotelautomation.domain.request.FloorDetails;
import com.hotelautomation.exception.HotelAutomationException;

import java.util.List;

public interface IFloorBuilder {

    void buildFloor(List<FloorDetails> details) throws HotelAutomationException;

    Floor getFloor(Integer floorNumber);

    Integer calculateCurrentConsumption(Floor floor);

    List<Floor> getFloorList();
}
