package com.hotelautomation.application;

import com.hotelautomation.builder.IFloorBuilder;
import com.hotelautomation.builder.impl.FloorBuilderImpl;
import com.hotelautomation.domain.Ac;
import com.hotelautomation.domain.Corridor;
import com.hotelautomation.domain.Floor;
import com.hotelautomation.domain.Light;
import com.hotelautomation.domain.request.FloorDetails;
import com.hotelautomation.domain.request.MotionDetectionInput;
import com.hotelautomation.exception.HotelAutomationException;
import com.hotelautomation.parser.IFloorInputParser;
import com.hotelautomation.parser.IMotionDetectionInputParser;
import com.hotelautomation.parser.impl.FloorInputParserImpl;
import com.hotelautomation.parser.impl.MotionDetectionParserImpl;
import com.hotelautomation.service.ElectricityManagementService;
import com.hotelautomation.service.impl.ElectricityManagerServiceImpl;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Application {

    public static void main(String ar[])  {
        Application application = new Application();
        IFloorInputParser floorParser = new FloorInputParserImpl();
        IFloorBuilder builder = FloorBuilderImpl.getInstance();
        IMotionDetectionInputParser motionParser = new MotionDetectionParserImpl();
        ElectricityManagementService electricityManagementService = new ElectricityManagerServiceImpl();

        try {
            String filePath = ar[0];
            List<String> lines = application.readAllLinesFromFile(filePath);
            String totalFloorsStr = lines.get(0).replaceAll("\\s+","");
            int totalFloors = Integer.parseInt(totalFloorsStr);
            initializeFloorSystem(floorParser, builder, lines, totalFloors);
            System.out.println("Initial Floor electricity system :");
            printCurrentFloorStatus(builder.getFloorList());
            processMotionDetectionAndPrintOutput(builder, motionParser, electricityManagementService, lines, totalFloors);

        }catch(HotelAutomationException e){
            System.out.println(e.getMessage());
        }catch(FileNotFoundException e){
            System.out.println("File location or name incorrect");
        }catch(IOException e){
            System.out.println("Error while reading file");
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    private static void processMotionDetectionAndPrintOutput(IFloorBuilder builder, IMotionDetectionInputParser motionParser, ElectricityManagementService electricityManagementService, List<String> lines, int totalFloors) throws HotelAutomationException {
        for(int i = (totalFloors+1) ; i<=(lines.size()-1) ; i++){
            MotionDetectionInput input = motionParser.parseMotionDetectionInputData(lines.get(i));
            electricityManagementService.processMotionDetection(input);
            System.out.println("Output after motion detection :");
            printCurrentFloorStatus(builder.getFloorList());
            System.out.println("");
        }
    }

    private static void initializeFloorSystem(IFloorInputParser floorParser, IFloorBuilder builder, List<String> lines, int totalFloors) throws HotelAutomationException {
        List<String> floorInitialData = new ArrayList<>();
        for(int i=0;i<totalFloors;i++){
            //Array Index out of bound exception handle it.
            floorInitialData.add(lines.get(i+1));
        }
        List<FloorDetails> details = floorParser.parseFloorData(floorInitialData,totalFloors);
        builder.buildFloor(details);
    }

    private  List<String> readAllLinesFromFile(String path) throws IOException {
        File file = new File(path);
        return Files.readAllLines(Paths.get(path),
                Charset.defaultCharset());
    }

    private static void printCurrentFloorStatus(List<Floor> floors){
        System.out.println("**************************************");
        for (Floor floor: floors) {

            System.out.println("                Floor " + floor.getNumber());
            for (Corridor corridor:floor.getCorridors()) {
                int cnt = 0;
                System.out.print(corridor.getType()+ "  Corridor  ");
                List<Light> lights = corridor.getLights();
                for (Light light:lights) {
                    cnt++;
                    System.out.print("Light" + cnt + " " +(light.getSwitchButton().equals(Boolean.TRUE) ? "ON" : "OFF"));
                }
                cnt = 0;
                List<Ac> acs = corridor.getAcs();
                for (Ac ac:acs) {
                    cnt++;
                    System.out.print("  Ac" + cnt + " " +(ac.getSwitchButton().equals(Boolean.TRUE) ? "ON" : "OFF"));
                }
                System.out.println("");
            }
            System.out.println("**************************************");
        }

    }
}
