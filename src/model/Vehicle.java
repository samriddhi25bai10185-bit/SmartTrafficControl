package model;

public abstract class Vehicle {

    private String vehicleId;
    private String type;
    private int speed;
    private boolean emergency;

    public Vehicle(String vehicleId, String type, int speed, boolean emergency) {
        this.vehicleId = vehicleId;
        this.type = type;
        this.speed = speed;
        this.emergency = emergency;
    }

    public String getVehicleId() {
        return vehicleId;
    }

    public String getType() {
        return type;
    }

    public int getSpeed() {
        return speed;
    }

    public boolean isEmergency() {
        return emergency;
    }
    public abstract void move();
}