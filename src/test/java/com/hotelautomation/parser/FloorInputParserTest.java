package com.hotelautomation.parser;

import com.hotelautomation.domain.request.FloorDetails;
import com.hotelautomation.exception.HotelAutomationException;
import com.hotelautomation.parser.impl.FloorInputParserImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;


@RunWith(MockitoJUnitRunner.class)
public class FloorInputParserTest {

    @Test
    public void shouldReturnListOfFloorDetailsForValidInput() throws HotelAutomationException {
        IFloorInputParser parser = new FloorInputParserImpl();
        Integer numberOfMainsCorridors = 2, numberOfSubCorridors = 2, floorNumber = 1;
        List<FloorDetails> floorDetails = parser.parseFloorData(new ArrayList<String>() {{
            add(floorNumber +"," + numberOfMainsCorridors +"," + numberOfSubCorridors);
        }},1);

        assertEquals(1, floorDetails.size());
        assertEquals(numberOfMainsCorridors, floorDetails.get(0).getNumberOfMainCorridors());
        assertEquals(numberOfSubCorridors, floorDetails.get(0).getNumberOfSubCorridors());
        assertEquals(floorNumber, floorDetails.get(0).getFloorNumber());
    }

    @Test(expected = HotelAutomationException.class)
    public void shouldThrowHotelAutomationExceptionOnInvalidInputFOrFloorDataParsing() throws HotelAutomationException {
        IFloorInputParser parser = new FloorInputParserImpl();
        List<FloorDetails> floorDetails = parser.parseFloorData(new ArrayList<String>() {{
            add("abc,2,2");
        }},1);
    }
}