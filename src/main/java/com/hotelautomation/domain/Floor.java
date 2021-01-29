package com.hotelautomation.domain;

import java.util.List;

public class Floor implements IDomain{

    private Integer number;
    private List<Corridor> corridors;
    private Integer currentConusmedPower;

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public List<Corridor> getCorridors() {
        return corridors;
    }

    public void setCorridors(List<Corridor> corridors) {
        this.corridors = corridors;
    }
}
