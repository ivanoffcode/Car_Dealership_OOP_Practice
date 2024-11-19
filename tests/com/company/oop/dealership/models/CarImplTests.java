package com.company.oop.dealership.models;

import com.company.oop.dealership.models.vehicles.contracts.Car;
import com.company.oop.dealership.models.vehicles.contracts.Vehicle;
import com.company.oop.dealership.models.vehicles.CommentImpl;
import com.company.oop.dealership.models.vehicles.CarImpl;
import com.company.oop.dealership.utils.VehicleBaseConstants;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CarImplTests {
    public static final int VALID_SEATS = 4;

    @Test
    public void carImpl_Should_ImplementCarInterface() {
        // Arrange, Act
        CarImpl car = initializeTestCar();
        // Assert
        Assertions.assertTrue(car instanceof Car);
    }

    @Test
    public void carImpl_Should_ImplementVehicleInterface() {
        // Arrange, Act
        CarImpl car = initializeTestCar();
        // Assert
        Assertions.assertTrue(car instanceof Vehicle);
    }

    @Test
    public void constructor_Should_ThrowException_When_MakeNameLengthOutOfBounds() {
        // Arrange, Act, Assert
        Assertions.assertThrows(IllegalArgumentException.class, () ->
                new CarImpl(
                        VehicleBaseConstants.INVALID_MAKE,
                        VehicleBaseConstants.VALID_MODEL,
                        VehicleBaseConstants.VALID_PRICE,
                        VALID_SEATS));
    }

    @Test
    public void constructor_Should_ThrowException_When_ModelNameLengthOutOfBounds() {
        // Arrange, Act, Assert
        Assertions.assertThrows(IllegalArgumentException.class, () ->
                new CarImpl(
                        VehicleBaseConstants.VALID_MAKE,
                        VehicleBaseConstants.INVALID_MODEL,
                        VehicleBaseConstants.VALID_PRICE,
                        VALID_SEATS));
    }

    @Test
    public void constructor_Should_ThrowException_When_PriceIsInvalid() {
        // Arrange, Act, Assert
        Assertions.assertThrows(IllegalArgumentException.class, () ->
                new CarImpl(
                        VehicleBaseConstants.VALID_MAKE,
                        VehicleBaseConstants.VALID_MODEL,
                        -1,
                        VALID_SEATS));
    }

    @Test
    public void constructor_Should_ThrowException_When_SeatsValueIsInvalidOrNegative() {
        // Arrange, Act, Assert
        Assertions.assertThrows(IllegalArgumentException.class, () ->
                new CarImpl(
                        VehicleBaseConstants.VALID_MAKE,
                        VehicleBaseConstants.VALID_MODEL,
                        VehicleBaseConstants.VALID_PRICE,
                        -1));
    }

    @Test
    public void constructor_Should_CreateNewCar_When_ParametersAreCorrect() {
        // Arrange, Act
        CarImpl car = initializeTestCar();

        // Assert
        Assertions.assertAll(
                () -> Assertions.assertEquals(VehicleBaseConstants.VALID_MAKE, car.getMake()),
                () -> Assertions.assertEquals(VehicleBaseConstants.VALID_MODEL, car.getModel()),
                () -> Assertions.assertEquals(VehicleBaseConstants.VALID_PRICE, car.getPrice()),
                () -> Assertions.assertEquals(VALID_SEATS, car.getSeats())
        );
    }

    @Test
    public void getComments_Should_ReturnCopyOfTheCollection() {
        // Arrange
        CarImpl car = initializeTestCar();

        // Act
        car.getComments().add(CommentImplTests.initializeTestComment());

        // Assert
        Assertions.assertEquals(0, car.getComments().size());
    }

    @Test
    public void addComment_Should_AddCommentToTheCollection() {
        // Arrange
        CarImpl car = initializeTestCar();

        // Act
        car.addComment(CommentImplTests.initializeTestComment());

        // Assert
        Assertions.assertEquals(1, car.getComments().size());
        Assertions.assertEquals(CommentImplTests.VALID_CONTENT, car.getComments().get(0).getContent());
    }

    @Test
    public void removeComment_Should_RemoveCommentFromTheCollection() {
        // Arrange
        CarImpl car = initializeTestCar();
        CommentImpl comment = CommentImplTests.initializeTestComment();
        car.addComment(comment);

        // Act
        car.removeComment(comment);

        // Assert
        Assertions.assertEquals(0, car.getComments().size());
    }

    public static CarImpl initializeTestCar() {
        return new CarImpl(
                VehicleBaseConstants.VALID_MAKE,
                VehicleBaseConstants.VALID_MODEL,
                VehicleBaseConstants.VALID_PRICE,
                VALID_SEATS);
    }
}