package model;

public class Car extends Vehicle {

    public Car(String vehicleId, int speed) {
        super(vehicleId, "Car", speed, false);
    }

    @Override
    public void move() {
        System.out.println("Car " + getVehicleId() + " is moving.");
    }
}