package com.company.oop.dealership.models.enums;

public enum VehicleType {
    MOTORCYCLE,
    CAR,
    TRUCK;

    @Override
    public String toString() {
        switch (this) {
            case CAR:
                return "Car";
            case MOTORCYCLE:
                return "Motorcycle";
            case TRUCK:
                return "Truck";
            default:
                throw new UnsupportedOperationException("Can't convert vehicle type.");
        }
    }
}


