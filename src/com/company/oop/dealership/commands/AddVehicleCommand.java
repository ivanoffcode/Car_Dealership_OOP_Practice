package com.company.oop.dealership.commands;

import com.company.oop.dealership.core.contracts.VehicleDealershipRepository;
import com.company.oop.dealership.models.vehicles.contracts.Vehicle;
import com.company.oop.dealership.models.enums.VehicleType;
import com.company.oop.dealership.utils.ParsingHelpers;
import com.company.oop.dealership.utils.ValidationHelpers;

import java.util.List;

public class AddVehicleCommand extends BaseCommand {

    public static final int EXPECTED_NUMBER_OF_ARGUMENTS = 5;
    public static final String INVALID_PRICE = "Invalid value for price. Should be a number.";
    public static final String INVALID_SEATS_MESSAGE = "Invalid seats. Expected a number.";
    public static final String INVALID_WEIGHT_MESSAGE = "Invalid weight capacity. Expected a number.";
    public static final String CANNOT_CREATE_THIS_TYPE_OF_VEHICLE = "Cannot create this type of vehicle.";
    public final static String VEHICLE_ADDED_SUCCESSFULLY = "%s added vehicle successfully!";

    public AddVehicleCommand(VehicleDealershipRepository vehicleDealershipRepository) {
        super(vehicleDealershipRepository);
    }

    @Override
    protected String executeCommand(List<String> parameters) {
        ValidationHelpers.validateArgumentsCount(parameters, EXPECTED_NUMBER_OF_ARGUMENTS);
        VehicleType type = ParsingHelpers.tryParseEnum(parameters.get(0), VehicleType.class);
        String make = parameters.get(1);
        String model = parameters.get(2);
        double price = ParsingHelpers.tryParseDouble(parameters.get(3), INVALID_PRICE);
        String additionalParam = parameters.get(4);

        return addVehicle(type, make, model, price, additionalParam);
    }

    private String addVehicle(VehicleType type, String make, String model, double price, String additionalParam) {
        Vehicle vehicle = createVehicle(type, make, model, price, additionalParam);

        getVehicleDealershipRepository().getLoggedInUser().addVehicle(vehicle);

        return String.format(VEHICLE_ADDED_SUCCESSFULLY, getVehicleDealershipRepository().getLoggedInUser().getUsername());
    }

    private Vehicle createVehicle(VehicleType type, String make, String model, double price, String additionalParam) {
        switch (type) {
            case CAR:
                return getVehicleDealershipRepository().createCar(make, model, price, ParsingHelpers.tryParseInt(additionalParam, INVALID_SEATS_MESSAGE));
            case MOTORCYCLE:
                return getVehicleDealershipRepository().createMotorcycle(make, model, price, additionalParam);
            case TRUCK:
                return getVehicleDealershipRepository().createTruck(make, model, price, ParsingHelpers.tryParseInt(additionalParam, INVALID_WEIGHT_MESSAGE));
            default:
                throw new IllegalArgumentException(CANNOT_CREATE_THIS_TYPE_OF_VEHICLE);
        }
    }

    @Override
    protected boolean requiresLogin() {
        return true;
    }
}