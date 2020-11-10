package model;

public class Truck {
    public Integer cargoWeight;
    public Integer maxCargoWeight;
    public Long speed;
    public Long maxSpeed;
    public Boolean breakdown;

    public Integer getCargoWeight() {
        return cargoWeight;
    }

    public void setCargoWeight(Integer cargoWeight) {
        this.cargoWeight = cargoWeight;
    }

    public Integer getMaxCargoWeight() {
        return maxCargoWeight;
    }

    public void setMaxCargoWeight(Integer maxCargoWeight) {
        this.maxCargoWeight = maxCargoWeight;
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
