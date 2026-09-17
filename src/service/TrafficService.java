package service;

import exception.TrafficException;
import model.Road;
import model.Vehicle;

public class TrafficService {

    public synchronized void addVehicleToRoad(
            Road road,
            Vehicle vehicle)
            throws TrafficException {

        if (!road.isOpen()) {

            throw new TrafficException(
                    "Road " + road.getName()
                            + " is currently closed.");
        }

        if (road.isFull()) {

            throw new TrafficException(
                    "Road " + road.getName()
                            + " is full.");
        }

        road.addVehicle(vehicle);

        System.out.println(
                vehicle.getType()
                        + " "
                        + vehicle.getVehicleId()
                        + " added to "
                        + road.getName());
    }

    public synchronized void removeVehicleFromRoad(
            Road road,
            Vehicle vehicle) {

        if (road.getVehicles().contains(vehicle)) {

            road.removeVehicle(vehicle);

            System.out.println(
                    vehicle.getVehicleId()
                            + " removed from "
                            + road.getName());
        }
    }
}