package model;

public class Car {
    public Integer amountPeople;
    public Integer maxAmountPeople;
    public Long speed;
    public Long maxSpeed;
    public Boolean breakdown;

    public Integer getAmountPeople() {
        return amountPeople;
    }

    public void setAmountPeople(Integer amountPeople) {
        this.amountPeople = amountPeople;
    }

    public Integer getMaxAmountPeople() {
        return maxAmountPeople;
    }

    public void setMaxAmountPeople(Integer maxAmountPeople) {
        this.maxAmountPeople = maxAmountPeople;
    }

    public Long getSpeed() {
        return speed;
    }

    public void setSpeed(Long speed) {
        this.speed = speed;
    }

    public Long getMaxSpeed() {
        return maxSpeed;
    }

    public void setMaxSpeed(Long maxSpeed) {
        this.maxSpeed = maxSpeed;
    }

    public Boolean getBreakdown() {
        return breakdown;
    }

    public void setBreakdown(Boolean breakdown) {
        this.breakdown = breakdown;
    }
}
