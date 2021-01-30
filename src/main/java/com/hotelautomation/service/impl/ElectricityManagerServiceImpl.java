package com.hotelautomation.service.impl;

import com.hotelautomation.builder.IFloorBuilder;
import com.hotelautomation.builder.impl.FloorBuilderImpl;
import com.hotelautomation.constants.ConsumptionConstants;
import com.hotelautomation.domain.Ac;
import com.hotelautomation.domain.Corridor;
import com.hotelautomation.domain.CorridorType;
import com.hotelautomation.domain.Floor;
import com.hotelautomation.domain.request.MotionDetectionInput;
import com.hotelautomation.exception.HotelAutomationException;
import com.hotelautomation.service.ElectricityManagementService;

import java.util.List;
import java.util.Optional;

public class ElectricityManagerServiceImpl implements ElectricityManagementService {

    IFloorBuilder builder = FloorBuilderImpl.getInstance();

    public void processMotionDetection(MotionDetectionInput input) throws HotelAutomationException {
        try {

            Floor floor = builder.getFloor(input.getFloorNumber());
            int currentConsumption = builder.calculateCurrentConsumption(floor);
            if (input.getMotionDetected()) {
                processTurnedOnMotion(input, floor, currentConsumption);
            } else {
                processTurnedOffMotion(input, floor);
            }
        }catch (Exception e){
            throw new HotelAutomationException("Error while processing motion detection data");
        }

    }

    private void processTurnedOffMotion(MotionDetectionInput input, Floor floor) {
        Optional<Corridor> corridor = floor.getCorridors().stream().filter(c -> input.getCorridorNumber().equals(c.getCorridorNumber())).findFirst();
        if (corridor.isPresent()) {
            corridor.get().getLights().get(0).setSwitchButton(false);
            int currentConsumption = builder.calculateCurrentConsumption(floor);
        if (currentConsumption < ConsumptionConstants.MAX_CONSUMPTION_PER_FLOOR) {
            int remainingConsumptionUnit = ConsumptionConstants.MAX_CONSUMPTION_PER_FLOOR - currentConsumption;

            Integer numberOfAcsCanTurnedOn = remainingConsumptionUnit % ConsumptionConstants.AC_CONSUMPTION;
            while (numberOfAcsCanTurnedOn >= 0) {
                for(int i = 0; i<floor.getCorridors().size(); i++){
                    Corridor corr = floor.getCorridors().get(i);
                    if(input.getCorridorNumber()!=(i+1)){
                        List<Ac> acs = floor.getCorridors().get(i).getAcs();
                        Optional<Ac> ac = acs.stream().filter(a -> a.getSwitchButton().equals(Boolean.FALSE)).findFirst();
                        if(ac.isPresent()){
                            ac.get().setSwitchButton(Boolean.TRUE);
                            break;
                        }
                    }
                }
                numberOfAcsCanTurnedOn--;
            }
        }
    }
    }

    private void processTurnedOnMotion(MotionDetectionInput input, Floor floor, int currentConsumption) {
        if(currentConsumption >= ConsumptionConstants.MAX_CONSUMPTION_PER_FLOOR){
            for(int i = 0; i<floor.getCorridors().size(); i++){
                Corridor corridor = floor.getCorridors().get(i);
                if(CorridorType.MAIN.equals(corridor.getType()) && input.getCorridorNumber()!=(i+1)){
                   List<Ac> acs = floor.getCorridors().get(i).getAcs();
                   Optional<Ac> ac = acs.stream().filter(a -> a.getSwitchButton().equals(Boolean.TRUE)).findFirst();
                   if(ac.isPresent()){
                       ac.get().setSwitchButton(false);
                       break;
                   }
                }
            }
        }
        // set light on
        Optional<Corridor> corridor = floor.getCorridors().stream().filter(c -> input.getCorridorNumber().equals(c.getCorridorNumber())).findFirst();
        if(corridor.isPresent()) {
            corridor.get().getLights().get(0).setSwitchButton(true);
        }
    }
}
