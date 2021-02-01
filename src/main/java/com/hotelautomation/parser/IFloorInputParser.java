package com.hotelautomation.parser;

import com.hotelautomation.domain.request.FloorDetails;
import com.hotelautomation.exception.HotelAutomationException;

import java.util.List;

public interface IFloorInputParser {

    List<FloorDetails> parseFloorData(List<String> lines,Integer totalFloorNumber) throws HotelAutomationException;

}