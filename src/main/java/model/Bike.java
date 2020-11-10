package model;

public class Bike {
    public Boolean sidecar;
    public Long speed;
    public Long maxSpeed;
    public Boolean breakdown;

    public Boolean getSidecar() {
        return sidecar;
    }

    public void setSidecar(Boolean sidecar) {
        this.sidecar = sidecar;
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
