package com.hotelautomation.parser.impl;

import com.hotelautomation.domain.Floor;
import com.hotelautomation.domain.request.FloorDetails;
import com.hotelautomation.exception.HotelAutomationException;
import com.hotelautomation.parser.IFloorInputParser;

import java.util.ArrayList;
import java.util.List;

public class FloorInputParserImpl implements IFloorInputParser {
    @Override
    public List<FloorDetails> parseFloorData(List<String> lines) throws HotelAutomationException {
        List<FloorDetails> floorDetails = new ArrayList<>();
        try {
            for (int i = 0; i < lines.size(); i++) {
                String line = lines.get(i);
                String[] fields = line.split(",");
                FloorDetails details = new FloorDetails();
                details.setFloorNumber(Integer.valueOf(fields[0]));
                details.setNumberOfMainCorridors(Integer.valueOf(fields[1]));
                details.setNumberOfSubCorridors(Integer.valueOf(fields[2]));
                floorDetails.add(details);
            }
        }catch (Exception e){
            throw new HotelAutomationException("Error while parsing floor initial data, please make sure the floor data in placed in proper format in the file");
        }
        return floorDetails;
    }
}
