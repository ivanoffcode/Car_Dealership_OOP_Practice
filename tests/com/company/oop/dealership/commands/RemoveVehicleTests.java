package com.company.oop.dealership.commands;

import com.company.oop.dealership.core.VehicleDealershipRepositoryImpl;
import com.company.oop.dealership.core.contracts.VehicleDealershipRepository;
import com.company.oop.dealership.models.CarImplTests;
import com.company.oop.dealership.models.vehicles.contracts.Car;
import com.company.oop.dealership.models.users.contracts.User;
import com.company.oop.dealership.utils.TestUtilities;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

public class RemoveVehicleTests {

    public static final int EXPECTED_NUMBER_OF_ARGUMENTS = 1;

    private RemoveVehicleCommand removeVehicleCommand;
    private User user;

    @BeforeEach
    public void before() {
        VehicleDealershipRepository repository = new VehicleDealershipRepositoryImpl();
        removeVehicleCommand = new RemoveVehicleCommand(repository);
        user = LoginTests.loginInitializedUserToRepository(repository);
    }

    @Test
    public void should_ThrowException_When_ArgumentCountDifferentThanExpected() {
        // Arrange
        List<String> params = TestUtilities.getList(EXPECTED_NUMBER_OF_ARGUMENTS - 1);

        // Act, Assert
        Assertions.assertThrows(IllegalArgumentException.class, () -> removeVehicleCommand.execute(params));
    }

    @Test
    public void should_ThrowException_When_VehicleIndexIsInvalid() {
        // Arrange
        List<String> params = List.of(
                "INVALID_INDEX");

        // Act, Assert
        Assertions.assertThrows(IllegalArgumentException.class, () -> removeVehicleCommand.execute(params));
    }

    @Test
    public void should_ThrowException_When_VehicleDoesNotExist() {
        // Arrange
        List<String> params = List.of(
                "1");

        // Act, Assert
        Assertions.assertThrows(IllegalArgumentException.class, () -> removeVehicleCommand.execute(params));
    }

    @Test
    public void should_RemoveCommentFromUser_When_ArgumentsAreValid() {
        // Arrange
        Car testCar = CarImplTests.initializeTestCar();
        user.addVehicle(testCar);

        // Act
        removeVehicleCommand.executeCommand(List.of(
                "1"));

        // Act, Assert
        Assertions.assertEquals(0, user.getVehicles().size());
    }
}