package controller;

import model.*;

import java.util.*;

public class StartRace {

    public static void main(String[] args) throws InterruptedException {


        CommonObject commonObject = new CommonObject();
        commonObject.start();
    }
}

class CommonObject {
    Preparation preparation = new Preparation();
    Property prop = preparation.getProperty();
    Road road = preparation.createRoad();
    List listPlayers = new ArrayList<Object>();
    int countPlayers = 0;
    Integer marks[];

    CommonObject() {
        int countCar = 0;
        int countTruck = 0;
        int countBike = 0;
        if (prop.getCarRandomCount()) {
            countCar = (1 + (int) (Math.random() * 10));
        } else countCar = prop.getCarCount();
        if (prop.getTruckRandomCount()) {
            countTruck = (1 + (int) (Math.random() * 10));
        } else countTruck = prop.getTruckCount();
        if (prop.getBikeRandomCount()) {
            countBike = (1 + (int) (Math.random() * 10));
        } else countBike = prop.getBikeCount();
        countPlayers = countCar + countTruck + countBike;
        marks = new Integer[countPlayers];
        for (int i = 0; i < countPlayers; i++) {
            marks[i] = i;
        }
        for (int i = 0; i < countCar; i++) {
            listPlayers.add(preparation.createCar(prop));
        }
        for (int i = 0; i < countTruck; i++) {
            listPlayers.add(preparation.createTruck(prop));
        }
        for (int i = 0; i < countBike; i++) {
            listPlayers.add(preparation.createBike(prop));
        }
        Collections.shuffle(listPlayers);

    }

    public void start() {
        RaceObject raceObject = new RaceObject();
        raceObject.countPlayers = countPlayers;
        raceObject.prop = prop;
        raceObject.road = road;
        for (Object obj : marks) {
            raceObject.model = listPlayers.get((Integer) obj);
            String name = raceObject.model.getClass().getSimpleName() + " " + ((Integer) obj + 1);
            RaceThread raceThread = new RaceThread(raceObject);
            raceObject.race.add(raceThread);
            Thread rt = new Thread(raceThread);
            rt.setName(name);
            rt.start();
        }
    }
}

class RaceObject {
    public Property prop;
    public Road road;
    public Map finish = new HashMap<String, String>();
    public List<RaceThread> race = new ArrayList<RaceThread>();
    int counter = 0;
    Long timestart = System.currentTimeMillis();
    Object model;
    int countPlayers;

    public static boolean askYesOrNo(String query) {
        String yesOrNo = null;
        boolean validInput = false;
        while (!validInput) {
            System.out.println(query);

            yesOrNo = getConsoleInput();
            validInput = yesOrNo.toUpperCase().equals("Y")
                    || yesOrNo.toUpperCase().equals("N");
            if (!validInput) {
                System.out
                        .println("Invalid input! Please enter /'Y/' or /'N/'");
            }
        }
        return yesOrNo.toUpperCase().equals("Y");
    }

    public static String getConsoleInput() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }
}

class RaceThread implements Runnable {
    private final String modelName;
    private long maxSpeed;
    private Integer breakdownSleep;
    private int breakdownPoint = 0;
    RaceObject res;
    private double factorySpeed = 1;
    private double factorySleep = 1;
    private boolean breakdown = false;
    double koef = 1;
    private String name;
    private String load;

    RaceThread(RaceObject res) {
        this.res = res;
        modelName = res.model.getClass().getSimpleName();
        if (modelName.equals("Car")) {
            Car model = ((Car) res.model);
            load = model.getAmountPeople()+" чел.";
            koef = 1.1 +0.2* model.getAmountPeople();
            breakdown = model.getBreakdown();
            breakdownSleep = res.prop.getCarBreakdownSleep();
            maxSpeed = model.getMaxSpeed();

        }
        if (modelName.equals("Truck")) {
            Truck model = ((Truck) res.model);
            load = model.getCargoWeight()+" тонн";
            koef = 1.1 +0.2*model.getCargoWeight();
            breakdown = model.getBreakdown();
            breakdownSleep = res.prop.getTruckBreakdownSleep();
            maxSpeed = model.getMaxSpeed();
        }
        if (modelName.equals("Bike")) {
            Bike model = ((Bike) res.model);
            load = "нет";
            if (model.getSidecar()) {
                koef = 1.4;
                load = "коляска";
            }
            breakdown = model.getBreakdown();
            breakdownSleep = res.prop.getBikeBreakdownSleep();
            maxSpeed = model.getMaxSpeed();
        }
        if (koef != 1) {
            factorySpeed = 0.5 + koef;
            factorySleep = koef;
        }
        if (breakdown) {
            breakdownPoint = (1 + (int) (Math.random() * res.road.getRoadLength()));
        }

    }

    @Override
    public void run() {
        name = Thread.currentThread().getName();
        synchronized (res) {
            res.counter++;
            System.out.printf(
                    "П.п.: %d, ТС: %s, Макс. скорость: %d, Прокол: %s, Нагрузка: %s, Коэф. нагрузки: %s\n",
                    res.counter,name, maxSpeed, (breakdown ? "Возможен" : "Нет"), load, factorySpeed
            );
        }
        long time = 0;
        long speed = 0;
        for (int i = 1; i < res.road.getRoadLength(); i++) {
            time = System.currentTimeMillis() - res.timestart;
            speed = (long) (time / i / factorySpeed);
            if (speed > maxSpeed) {
                factorySleep = 1 + (speed - maxSpeed) / 200;
                speed = maxSpeed;
            }

            try {
                Thread.sleep((long) ((breakdownPoint == i) ? breakdownSleep : factorySleep * 200));
            } catch (InterruptedException e) {
                //e.printStackTrace();
            }
            System.out.printf("Игрок: %s Скорость: %d Время: %d Расстояние: %d %s %s\n", name, speed, time, i + 1, (breakdownPoint == i?"Прокол":""),(i == (res.road.getRoadLength() - 1)?("Место: "+(res.finish.size()+1)):""));

            if (i == (res.road.getRoadLength() - 1)) {
                Map results = new HashMap();
                results.put("Прокол", (breakdown ? "Да" : "Нет"));
                results.put("Время", time);
                results.put("Скорость", speed);
                results.put("Место", res.finish.size() + 1);
                res.finish.put("Игрок: " + name, results);
            }
        }
        synchronized (res.finish) {
            if (res.finish.size() == res.countPlayers) {
                System.out.println(res.finish);
                if (res.askYesOrNo("Повторить?")) {
                    res.finish.clear();
                    for (RaceThread th : res.race) {
                        res.timestart = System.currentTimeMillis();
                        Thread rt = new Thread(th);
                        rt.setName(th.name);
                        rt.start();
                    }
                }
            }
        }
    }
}
