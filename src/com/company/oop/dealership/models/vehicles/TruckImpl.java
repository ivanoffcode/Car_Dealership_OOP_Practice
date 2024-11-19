package com.company.oop.dealership.models.vehicles;

import com.company.oop.dealership.models.enums.VehicleType;
import com.company.oop.dealership.models.vehicles.contracts.Truck;


import static com.company.oop.dealership.utils.ValidationHelpers.validateIntRange;
import static java.lang.String.format;

public class TruckImpl extends VehicleBase implements Truck {

    public static final int WEIGHT_CAP_MIN = 1;
    public static final int WEIGHT_CAP_MAX = 100;
    private static final String WEIGHT_CAP_ERR = format(
            "Weight capacity must be between %d and %d!",
            WEIGHT_CAP_MIN,
            WEIGHT_CAP_MAX);

    private int weightCapacity;
    private final int WHEELS = 8;

    public TruckImpl(String make, String model, double price, int weightCapacity) {
        super(make, model, price);
        setWeightCapacity(weightCapacity);
    }

    public int getWeightCapacity() {
        return weightCapacity;
    }

    private void setWeightCapacity(int weightCapacity) {
        validateIntRange(weightCapacity, WEIGHT_CAP_MIN, WEIGHT_CAP_MAX, WEIGHT_CAP_ERR);
        this.weightCapacity = weightCapacity;
    }

    @Override
    public int getWheels() {
        return WHEELS;
    }

    @Override
    public VehicleType getType() {
        return VehicleType.TRUCK;
    }

    @Override
    protected String printAdditionalInfo() {
        return String.format("Weight Capacity: %dt%n", getWeightCapacity());
    }

//TODO
}