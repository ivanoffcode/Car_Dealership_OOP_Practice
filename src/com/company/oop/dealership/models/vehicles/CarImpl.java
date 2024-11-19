package com.company.oop.dealership.models.vehicles;

import com.company.oop.dealership.models.enums.VehicleType;
import com.company.oop.dealership.models.vehicles.contracts.Car;

import static com.company.oop.dealership.utils.ValidationHelpers.validateIntRange;
import static java.lang.String.format;

public class CarImpl extends VehicleBase implements Car {

    public static final int CAR_SEATS_MIN = 1;
    public static final int CAR_SEATS_MAX = 10;
    private static final String CAR_SEATS_ERR = format(
            "Seats must be between %d and %d!",
            CAR_SEATS_MIN,
            CAR_SEATS_MAX);

    private int seats;
    private final int WHEELS = 4;

    public CarImpl(String make, String model, double price, int seats) {
        super(make, model, price);
        setSeats(seats);
    }

    public int getSeats() {
        return seats;
    }

    private void setSeats(int seats) {
        validateIntRange(seats, CAR_SEATS_MIN, CAR_SEATS_MAX, CAR_SEATS_ERR);
        this.seats = seats;
    }

    @Override
    public int getWheels() {
        return WHEELS;
    }

    @Override
    public VehicleType getType() {
        return VehicleType.CAR;
    }

    @Override
    protected String printAdditionalInfo() {
        return String.format("Seats: %d", getSeats());
    }

//TODO
}