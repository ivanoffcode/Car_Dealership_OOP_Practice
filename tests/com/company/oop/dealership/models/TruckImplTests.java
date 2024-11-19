package com.company.oop.dealership.models;

import com.company.oop.dealership.models.vehicles.contracts.Truck;
import com.company.oop.dealership.models.vehicles.contracts.Vehicle;
import com.company.oop.dealership.models.vehicles.TruckImpl;
import com.company.oop.dealership.utils.VehicleBaseConstants;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TruckImplTests {
    public static final int WEIGHT_CAP_MIN = 1;
    public static final int VALID_WEIGHT_CAP = WEIGHT_CAP_MIN + 1;

    @Test
    public void truckImpl_Should_ImplementTruckInterface() {
        // Arrange, Act
        TruckImpl truck = initializeTestTruck();
        // Assert
        Assertions.assertTrue(truck instanceof Truck);
    }

    @Test
    public void truckImpl_Should_ImplementVehicleInterface() {
        // Arrange, Act
        TruckImpl truck = initializeTestTruck();
        // Assert
        Assertions.assertTrue(truck instanceof Vehicle);
    }

    @Test
    public void constructor_Should_ThrowException_When_MakeNameLengthOutOfBounds() {
        // Arrange, Act, Assert
        Assertions.assertThrows(IllegalArgumentException.class, () ->
                new TruckImpl(
                        VehicleBaseConstants.INVALID_MAKE,
                        VehicleBaseConstants.VALID_MODEL,
                        VehicleBaseConstants.VALID_PRICE,
                        VALID_WEIGHT_CAP));
    }

    @Test
    public void constructor_Should_ThrowException_When_ModelNameLengthOutOfBounds() {
        // Arrange, Act, Assert
        Assertions.assertThrows(IllegalArgumentException.class, () ->
                new TruckImpl(
                        VehicleBaseConstants.VALID_MAKE,
                        VehicleBaseConstants.INVALID_MODEL,
                        VehicleBaseConstants.VALID_PRICE,
                        VALID_WEIGHT_CAP));
    }

    @Test
    public void constructor_Should_ThrowException_When_PriceIsInvalid() {
        // Arrange, Act, Assert
        Assertions.assertThrows(IllegalArgumentException.class, () ->
                new TruckImpl(
                        VehicleBaseConstants.VALID_MAKE,
                        VehicleBaseConstants.VALID_MODEL,
                        -1,
                        VALID_WEIGHT_CAP));
    }

    @Test
    public void constructor_Should_ThrowException_When_WeightIsInvalid() {
        // Arrange, Act, Assert
        Assertions.assertThrows(IllegalArgumentException.class, () ->
                new TruckImpl(
                        VehicleBaseConstants.VALID_MAKE,
                        VehicleBaseConstants.VALID_MODEL,
                        VehicleBaseConstants.VALID_PRICE,
                        -1));
    }

    @Test
    public void constructor_Should_CreateNewTruck_When_ParametersAreCorrect() {
        // Arrange, Act
        TruckImpl truck = initializeTestTruck();

        // Assert
        Assertions.assertEquals(VehicleBaseConstants.VALID_MAKE, truck.getMake());
    }

    public static TruckImpl initializeTestTruck() {
        return new TruckImpl(
                VehicleBaseConstants.VALID_MAKE,
                VehicleBaseConstants.VALID_MODEL,
                VehicleBaseConstants.VALID_PRICE,
                VALID_WEIGHT_CAP);
    }
}