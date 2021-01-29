package com.hotelautomation.domain;

public class Ac implements IDomain{

    private Integer ConsumptionUnit;
    private Integer totalConsumedPower;
    private Boolean switchButton;

    public Integer getConsumptionUnit() {
        return ConsumptionUnit;
    }

    public Integer getTotalConsumedPower() {
        return totalConsumedPower;
    }

    public Boolean getSwitchButton() {
        return switchButton;
    }

    public void setConsumptionUnit(Integer consumptionUnit) {
        ConsumptionUnit = consumptionUnit;
    }

    public void setTotalConsumedPower(Integer totalConsumedPower) {
        this.totalConsumedPower = totalConsumedPower;
    }

    public void setSwitchButton(Boolean switchButton) {
        this.switchButton = switchButton;
    }

}

