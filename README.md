# Hotel Automation


## Getting Started
#### Pre-requisite
java 8, maven. 
#### Dependencies
- Application uses junit, mockito for testing.
- All dependencies are handled by ```pom.xml``` file

#### Build
go to project root folder
- Run ```mvn clean package``` to build the project

#### Run
go to (cd to) target folder
- Run ``` java -jar HotelAutomation-1.0-SNAPSHOT.jar /Users/manishmane/HotelAutomation/src/main/resources/Hotel.txt```
- Change base file path as per system path structure in above command.
- You can change input in the file before you build it.
- System out is used for the output.

###FILE INPUT FORMAT
- Input file is places in HotelAutomation/src/main/resources/Hotel.txt
- Use this file to add/update input.

#####FILE FORMAT :
1. First line is floor number(Integer number)
2. Second line is Floor input data having columns floorNumber,numberOfMainCorridors,NumberOfSubCorridors. 
   Example input - 1,1,2 (All should be integer number). Add one input line per floor.
3. Motion detection input format is : floorNumber,Corridor Number,isMotionDetected
   Example input - 1,1,true.
   
#####FILE FORMAT :

- Unit tests are written for testing workflows of applications
- All unit tests are in src/test/java folder.
   
