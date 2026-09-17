package model;

import java.util.ArrayList;
import java.util.List;

public class Junction {

    private String junctionId;
    private String name;
    private List<Road> roads;
    private TrafficSignal signal;

    public Junction(String junctionId, String name) {
        this.junctionId = junctionId;
        this.name = name;
        this.roads = new ArrayList<>();
        this.signal = new TrafficSignal(junctionId + "-S1");
    }

    public String getJunctionId() {
        return junctionId;
    }

    public String getName() {
        return name;
    }

    public List<Road> getRoads() {
        return roads;
    }

    public TrafficSignal getSignal() {
        return signal;
    }

    public void addRoad(Road road) {
        roads.add(road);
    }

    public int getTotalVehicles() {
        int total = 0;

        for (Road road : roads) {
            total += road.getVehicleCount();
        }

        return total;
    }

    public void displayJunctionStatus() {
        System.out.println("\nJunction: " + name);
        System.out.println("Total Vehicles: " + getTotalVehicles());
        signal.displaySignal();
    }
}