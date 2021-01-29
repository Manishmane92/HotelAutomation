package com.hotelautomation.domain;

import java.util.List;

public class Corridor implements IDomain{
    private List<Ac> acs;
    private List<Light> lights;
    private Enum<CorridorType> type;
    private Integer floorNumber;
    private Integer corridorNumber;

    public List<Ac> getAcs() {
        return acs;
    }

    public void setAcs(List<Ac> acs) {
        this.acs = acs;
    }

    public List<Light> getLights() {
        return lights;
    }

    public void setLights(List<Light> lights) {
        this.lights = lights;
    }

    public Enum<CorridorType> getType() {
        return type;
    }

    public void setType(Enum<CorridorType> type) {
        this.type = type;
    }

    public Integer getFloorNumber() {
        return floorNumber;
    }

    public void setFloorNumber(Integer floorNumber) {
        this.floorNumber = floorNumber;
    }

    public Integer getCorridorNumber() {
        return corridorNumber;
    }

    public void setCorridorNumber(Integer corridorNumber) {
        this.corridorNumber = corridorNumber;
    }
}
