package com.hotelautomation.parser;

import com.hotelautomation.exception.HotelAutomationException;
import com.hotelautomation.parser.impl.FloorInputParserImpl;
import org.junit.Test;

import java.util.ArrayList;

public class FloorInputParserTest {

    @Test
    public void shouldReturnTrueIfInputDataIsValid() throws HotelAutomationException {
        IFloorInputParser parser = new FloorInputParserImpl();

        parser.parseFloorData(new ArrayList<String>(){{add("1,2,2");}});

    }
}
