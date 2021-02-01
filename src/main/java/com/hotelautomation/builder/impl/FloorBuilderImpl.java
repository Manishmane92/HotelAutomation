package com.hotelautomation.builder.impl;

import com.hotelautomation.builder.IFloorBuilder;
import com.hotelautomation.constants.ConsumptionConstants;
import com.hotelautomation.domain.*;
import com.hotelautomation.domain.request.FloorDetails;
import com.hotelautomation.exception.HotelAutomationException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class FloorBuilderImpl implements IFloorBuilder {

    private static IFloorBuilder builder;

    private List<Floor> floorList;

    private FloorBuilderImpl(){};

    public static IFloorBuilder getInstance(){
        if(builder == null){
            builder = new FloorBuilderImpl();
        }
        return builder;
    }

    public Floor getFloor(final Integer floorNumber){
        Optional<Floor> floor = floorList.stream().filter( f -> f.getNumber().equals(floorNumber)).findFirst();
        if(!floor.isPresent()){
            //throw exception
        }

        return floor.get();
    }

    public List<Floor> getFloorList(){
        return floorList;
    }

    public void buildFloor(List<FloorDetails> details) throws HotelAutomationException {
        floorList = new ArrayList<Floor>();
        List<Integer> floorNumbers = new ArrayList<>();
        for (FloorDetails detail:details) {
            //build floor one by one :
            if(floorNumbers.contains(detail.getFloorNumber())){
                throw new HotelAutomationException("Duplicate data for floor : " + detail.getFloorNumber());
            }
            floorNumbers.add(detail.getFloorNumber());
            Floor floor = new Floor();
            List<Corridor> mainCorridors = buildMainCorridors(detail);
            List<Corridor> subCorridors = buildSubCorridors(detail);
            mainCorridors.addAll(subCorridors);
            floor.setCorridors(mainCorridors);
            floor.setNumber(detail.getFloorNumber());
            floorList.add(floor);
            if(ConsumptionConstants.MAX_CONSUMPTION_PER_FLOOR < calculateCurrentConsumption(floor)){
                throw new HotelAutomationException("Total Electricity Consumption per floor should not exceed 35 unit");
            }
        }
    }

    public Integer calculateCurrentConsumption(Floor floor) {
        Integer consumption = 0;
        for (Corridor corridor : floor.getCorridors()
        ) {
            consumption = consumption + ((getSwitchedOnLightsCountForCorridor(corridor) * ConsumptionConstants.LIGHT_CONSUMPTION) +
                    (getSwitchedOnAcsCountForCorridor(corridor) * ConsumptionConstants.AC_CONSUMPTION));
        }
        return consumption;
    }

    private Integer getSwitchedOnLightsCountForCorridor(Corridor corridor){
       return corridor.getLights().stream().filter(l -> l.getSwitchButton().equals(Boolean.TRUE)).collect(Collectors.toList()).size();
    }

    private Integer getSwitchedOnAcsCountForCorridor(Corridor corridor){
        return corridor.getAcs().stream().filter(l -> l.getSwitchButton().equals(Boolean.TRUE)).collect(Collectors.toList()).size();
    }

    private List<Corridor> buildMainCorridors(FloorDetails detail) {
        List<Corridor> corridors = buildCorridor(detail.getNumberOfMainCorridors(),CorridorType.MAIN,detail.getFloorNumber());
        return corridors;
    }

    private List<Corridor> buildSubCorridors(FloorDetails detail) {
        List<Corridor> corridors = buildCorridor(detail.getNumberOfSubCorridors(),CorridorType.SUB,detail.getFloorNumber());
        return corridors;
    }

    private List<Corridor> buildCorridor(Integer numberOfCorridors, CorridorType type, Integer floorNumber){
        List<Corridor> corridors = new ArrayList<Corridor>();
        for(int i = 0; i < numberOfCorridors;i++){
            Corridor corridor = new Corridor();
            corridor.setCorridorNumber(i+1);
            corridor.setType(type);
            corridor.setFloorNumber(floorNumber);
            List<Ac> acs = getAcs(type);
            corridor.setAcs(acs);

            List<Light> lights = getLights(type);
            corridor.setLights(lights);

            corridors.add(corridor);
        }
        return corridors;
    }



    private List<Light> getLights(CorridorType corridorType) {
        final Light light = new Light();
        if(CorridorType.MAIN.equals(corridorType)) {
            light.setSwitchButton(true);
        }else {
            light.setSwitchButton(false);
        }
        return new ArrayList<Light>(){{add(light);}};
    }

    private List<Ac> getAcs(CorridorType corridorType) {
        final Ac ac = new Ac();
        ac.setSwitchButton(true);
        return new ArrayList<Ac>(){{add(ac);}};
    }


}
