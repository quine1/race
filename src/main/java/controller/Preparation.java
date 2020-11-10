package controller;

import model.*;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Preparation {
    private static Properties property;


    public static Property getProperty() {
        FileInputStream fis;
        property = new Properties();
        Property prop = new Property();
        try {
            fis = new FileInputStream("src/main/resources/config.properties");
            property.load(fis);
            try {
                prop.setCarRandomAmountPeople(Boolean.valueOf(property.getProperty("car.randomAmountPeople")));
                prop.setCarRandomBreakdown(Boolean.valueOf(property.getProperty("car.randomBreakdown")));
                prop.setCarBreakdownSleep(Integer.valueOf(property.getProperty("car.breakdownSleep")));
                prop.setCarRandomCount(Boolean.valueOf(property.getProperty("car.randomCount")));
                prop.setTruckRandomCargoWeight(Boolean.valueOf(property.getProperty("truck.randomCargoWeight")));
                prop.setTruckRandomBreakdown(Boolean.valueOf(property.getProperty("truck.randomBreakdown")));
                prop.setTruckBreakdownSleep(Integer.valueOf(property.getProperty("truck.breakdownSleep")));
                prop.setTruckRandomCount(Boolean.valueOf(property.getProperty("truck.randomCount")));
                prop.setBikeRandomSidecar(Boolean.valueOf(property.getProperty("bike.randomSidecar")));
                prop.setBikeRandomBreakdown(Boolean.valueOf(property.getProperty("bike.randomBreakdown")));
                prop.setBikeBreakdownSleep(Integer.valueOf(property.getProperty("bike.breakdownSleep")));
                prop.setBikeRandomCount(Boolean.valueOf(property.getProperty("bike.randomCount")));
                prop.setCarMaxCount(Integer.valueOf(property.getProperty("car.maxCount")));
                prop.setTruckMaxCount(Integer.valueOf(property.getProperty("truck.maxCount")));
                prop.setBikeMaxCount(Integer.valueOf(property.getProperty("bike.maxCount")));
                if (prop.getCarRandomCount()) {
                    prop.setCarCount(1 + (int) (Math.random() * prop.getCarMaxCount()));
                } else prop.setCarCount(Integer.valueOf(property.getProperty("car.count")));
                if (prop.getTruckRandomCount()) {
                    prop.setTruckCount(1 + (int) (Math.random() * prop.getTruckMaxCount()));
                } else prop.setTruckCount(Integer.valueOf(property.getProperty("truck.count")));
                if (prop.getBikeRandomCount()) {
                    prop.setBikeCount(1 + (int) (Math.random() * prop.getBikeMaxCount()));
                } else prop.setBikeCount(Integer.valueOf(property.getProperty("bike.count")));
            } catch (Exception e) {
                System.err.println("ОШИБКА: В файле свойств допущена ошибка!");
            }
        } catch (IOException e) {
            System.err.println("ОШИБКА: Файл свойств отсуствует!");
        }
        return prop;
    }

    public static Bike createBike(Property prop) {
        Bike bike = new Bike();
        bike.setMaxSpeed(Long.valueOf(property.getProperty("bike.maxSpeed")));
        if (prop.getBikeRandomSidecar()) {
            bike.setSidecar(Math.random() < 0.5);
        } else
            bike.setSidecar(Boolean.valueOf(property.getProperty("bike.sidecar")));
        if (prop.getBikeRandomBreakdown()) {
            bike.setBreakdown(Math.random() < 0.5);
        } else
            bike.setBreakdown(Boolean.valueOf(property.getProperty("bike.breakdown")));
        return bike;
    }

    public static Truck createTruck(Property prop) {
        Truck truck = new Truck();
        truck.setMaxCargoWeight(Integer.valueOf(property.getProperty("truck.maxCargoWeight")));
        truck.setMaxSpeed(Long.valueOf(property.getProperty("truck.maxSpeed")));
        if (prop.getTruckRandomCargoWeight()) {
            truck.setCargoWeight((int) (Math.random() * truck.getMaxCargoWeight()));
        } else
            truck.setCargoWeight(Integer.valueOf(property.getProperty("truck.cargoWeight")));
        if (prop.getTruckRandomBreakdown()) {
            truck.setBreakdown(Math.random() < 0.5);
        } else
            truck.setBreakdown(Boolean.valueOf(property.getProperty("truck.breakdown")));
        return truck;
    }

    public static Car createCar(Property prop) {
        Car car = new Car();
        car.setMaxAmountPeople(Integer.valueOf(property.getProperty("car.maxAmountPeople")));
        car.setMaxSpeed(Long.valueOf(property.getProperty("car.maxSpeed")));
        if (prop.getCarRandomAmountPeople()) {
            car.setAmountPeople(1 + (int) (Math.random() * car.getMaxAmountPeople()));
        } else
            car.setAmountPeople(Integer.valueOf(property.getProperty("car.amountPeople")));
        if (prop.getCarRandomBreakdown()) {
            car.setBreakdown(Math.random() < 0.5);
        } else
            car.setBreakdown(Boolean.valueOf(property.getProperty("car.breakdown")));
        return car;
    }

    public static Road createRoad() {
        Road road = new Road();
        road.setRoadCount(Integer.valueOf(property.getProperty("road.count")));
        road.setRoadLength(Integer.valueOf(property.getProperty("road.length")));
        return road;
    }
}
