package com.company.oop.dealership.models.vehicles;

import com.company.oop.dealership.models.enums.VehicleType;
import com.company.oop.dealership.models.vehicles.contracts.Motorcycle;


import static com.company.oop.dealership.utils.ValidationHelpers.validateStringLength;
import static java.lang.String.format;

public class MotorcycleImpl extends VehicleBase implements Motorcycle {

    public static final int CATEGORY_LEN_MIN = 3;
    public static final int CATEGORY_LEN_MAX = 10;
    private static final String CATEGORY_LEN_ERR = format(
            "Category must be between %d and %d characters long!",
            CATEGORY_LEN_MIN,
            CATEGORY_LEN_MAX);

    private String category;
    private final int WHEELS = 2;

    public MotorcycleImpl(String make, String model, double price, String category) {
        super(make, model, price);
        setCategory(category);
    }

    public String getCategory() {
        return category;
    }

    private void setCategory(String category) {
        validateStringLength(category, CATEGORY_LEN_MIN, CATEGORY_LEN_MAX, CATEGORY_LEN_ERR);
        this.category = category;
    }

    @Override
    public int getWheels() {
        return WHEELS;
    }

    @Override
    public VehicleType getType() {
        return VehicleType.MOTORCYCLE;
    }

    @Override
    protected String printAdditionalInfo() {
        return String.format("Category: %s%n", getCategory());
    }

//TODO
}