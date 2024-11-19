package com.company.oop.dealership.core;

import com.company.oop.dealership.commands.LoginTests;
import com.company.oop.dealership.core.contracts.VehicleDealershipRepository;
import com.company.oop.dealership.models.*;
import com.company.oop.dealership.models.enums.UserRole;
import com.company.oop.dealership.models.vehicles.contracts.Comment;
import com.company.oop.dealership.models.users.contracts.User;
import com.company.oop.dealership.models.vehicles.contracts.Car;
import com.company.oop.dealership.models.vehicles.contracts.Motorcycle;
import com.company.oop.dealership.models.vehicles.contracts.Truck;
import com.company.oop.dealership.utils.VehicleBaseConstants;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class VehicleDealershipRepositoryTests {

    VehicleDealershipRepository repository;

    @BeforeEach
    public void beforeEach() {
        repository = new VehicleDealershipRepositoryImpl();
    }

    @Test
    public void addUser_Should_ThrowException_When_UserAlreadyExists() {
        // Arrange
        User user = addInitializedUserToRepo(repository);

        // Act, Assert
        Assertions.assertThrows(IllegalArgumentException.class, () -> repository.addUser(user));
    }

    @Test
    public void addUser_Should_AddUser_When_UserDoesNotExist() {
        // Arrange
        User user = UserImplTests.initializeTestUser();

        // Act
        repository.addUser(user);

        // Assert
        Assertions.assertEquals(1, repository.getUsers().size());
    }

    @Test
    public void findUserByUsername_Should_ThrowException_When_UserDoesNotExist() {
        //Arrange, Act, Assert
        Assertions.assertThrows(IllegalArgumentException.class, () -> repository.findUserByUsername(UserImplTests.VALID_USERNAME));
    }

    @Test
    public void findUserByUsername_Should_ReturnUser_When_UserExists() {
        // Arrange
        User user = addInitializedUserToRepo(repository);

        // Act
        User foundUser = repository.findUserByUsername(user.getUsername());

        // Assert
        Assertions.assertAll(
                () -> Assertions.assertEquals(user.getUsername(), foundUser.getUsername()),
                () -> Assertions.assertEquals(user.getFirstName(), foundUser.getFirstName()),
                () -> Assertions.assertEquals(user.getLastName(), foundUser.getLastName()),
                () -> Assertions.assertEquals(user.getRole(), foundUser.getRole()));
    }

    @Test
    public void getLoggedInUser_Should_ThrowException_When_NoUserIsLoggedIn() {
        //Arrange, Act, Assert
        Assertions.assertThrows(IllegalArgumentException.class, () -> repository.getLoggedInUser());
    }

    @Test
    public void getLoggedInUser_Should_ReturnUser_When_UserIsLoggedIn() {
        // Arrange
        User user = LoginTests.loginInitializedUserToRepository(repository);

        // Act
        User loggedInUser = repository.getLoggedInUser();

        // Assert
        Assertions.assertAll(
                () -> Assertions.assertEquals(user.getUsername(), loggedInUser.getUsername()),
                () -> Assertions.assertEquals(user.getFirstName(), loggedInUser.getFirstName()),
                () -> Assertions.assertEquals(user.getLastName(), loggedInUser.getLastName()),
                () -> Assertions.assertEquals(user.getRole(), loggedInUser.getRole()));
    }

    @Test
    public void login_Should_LoginUser_When() {
        // Arrange, Act
        User user = UserImplTests.initializeTestUser();
        repository.login(user);
        User loggedInUser = repository.getLoggedInUser();

        // Assert
        Assertions.assertAll(
                () -> Assertions.assertEquals(user.getUsername(), loggedInUser.getUsername()),
                () -> Assertions.assertEquals(user.getFirstName(), loggedInUser.getFirstName()),
                () -> Assertions.assertEquals(user.getLastName(), loggedInUser.getLastName()),
                () -> Assertions.assertEquals(user.getRole(), loggedInUser.getRole()));
    }

    @Test
    public void hasLoggedInUser_Should_ReturnTrue_When_UserIsLoggedIn() {
        // Arrange, Act
        User user = UserImplTests.initializeTestUser();
        repository.login(user);

        // Assert
        Assertions.assertTrue(repository.hasLoggedInUser());
    }

    @Test
    public void hasLoggedInUser_Should_ReturnFalse_When_NoUserIsLoggedIn() {
        // Arrange, Act, Assert
        Assertions.assertFalse(repository.hasLoggedInUser());
    }

    @Test
    public void logout_Should_LogoutUser_When_UserIsLoggedIn() {
        // Arrange
        User user = UserImplTests.initializeTestUser();
        repository.login(user);

        // Act
        repository.logout();

        // Assert
        Assertions.assertThrows(IllegalArgumentException.class, () -> repository.getLoggedInUser());
    }

    @Test
    public void createCar_Should_ReturnCar_When_InputIsValid() {
        // Arrange, Act
        Car testCar = repository.createCar(
                VehicleBaseConstants.VALID_MAKE,
                VehicleBaseConstants.VALID_MODEL,
                VehicleBaseConstants.VALID_PRICE,
                CarImplTests.VALID_SEATS
        );

        // Assert
        Assertions.assertAll(
                () -> Assertions.assertEquals(testCar.getMake(), VehicleBaseConstants.VALID_MAKE),
                () -> Assertions.assertEquals(testCar.getModel(), VehicleBaseConstants.VALID_MODEL),
                () -> Assertions.assertEquals(testCar.getPrice(), VehicleBaseConstants.VALID_PRICE),
                () -> Assertions.assertEquals(testCar.getSeats(), CarImplTests.VALID_SEATS));
    }

    @Test
    public void createMotorcycle_Should_ReturnMotorcycle_When_InputIsValid() {
        // Arrange, Act
        Motorcycle testMotorcycle = repository.createMotorcycle(
                VehicleBaseConstants.VALID_MAKE,
                VehicleBaseConstants.VALID_MODEL,
                VehicleBaseConstants.VALID_PRICE,
                MotorcycleImplTests.VALID_CATEGORY
        );

        // Assert
        Assertions.assertAll(
                () -> Assertions.assertEquals(testMotorcycle.getMake(), VehicleBaseConstants.VALID_MAKE),
                () -> Assertions.assertEquals(testMotorcycle.getModel(), VehicleBaseConstants.VALID_MODEL),
                () -> Assertions.assertEquals(testMotorcycle.getPrice(), VehicleBaseConstants.VALID_PRICE),
                () -> Assertions.assertEquals(testMotorcycle.getCategory(), MotorcycleImplTests.VALID_CATEGORY));
    }

    @Test
    public void createTruck_Should_ReturnTruck_When_InputIsValid() {
        // Arrange, Act
        Truck testTruck = repository.createTruck(
                VehicleBaseConstants.VALID_MAKE,
                VehicleBaseConstants.VALID_MODEL,
                VehicleBaseConstants.VALID_PRICE,
                TruckImplTests.VALID_WEIGHT_CAP
        );

        // Assert
        Assertions.assertAll(
                () -> Assertions.assertEquals(testTruck.getMake(), VehicleBaseConstants.VALID_MAKE),
                () -> Assertions.assertEquals(testTruck.getModel(), VehicleBaseConstants.VALID_MODEL),
                () -> Assertions.assertEquals(testTruck.getPrice(), VehicleBaseConstants.VALID_PRICE),
                () -> Assertions.assertEquals(testTruck.getWeightCapacity(), TruckImplTests.VALID_WEIGHT_CAP));
    }

    @Test
    public void createUser_Should_ReturnUser_When_InputIsValid() {
        // Arrange, Act
        User testUser = repository.createUser(
                UserImplTests.VALID_USERNAME,
                UserImplTests.VALID_FIRST_NAME,
                UserImplTests.VALID_LAST_NAME,
                UserImplTests.VALID_PASSWORD,
                UserRole.NORMAL
        );

        // Assert
        Assertions.assertAll(
                () -> Assertions.assertEquals(testUser.getUsername(), UserImplTests.VALID_USERNAME),
                () -> Assertions.assertEquals(testUser.getFirstName(), UserImplTests.VALID_FIRST_NAME),
                () -> Assertions.assertEquals(testUser.getLastName(), UserImplTests.VALID_LAST_NAME),
                () -> Assertions.assertEquals(testUser.getRole(), UserRole.NORMAL));
    }

    @Test
    public void createComment_Should_ReturnCar_When_InputIsValid() {
        // Arrange, Act
        Comment testComment = repository.createComment(
                CommentImplTests.VALID_CONTENT,
                CommentImplTests.VALID_AUTHOR
        );

        // Assert
        Assertions.assertAll(
                () -> Assertions.assertEquals(testComment.getContent(), CommentImplTests.VALID_CONTENT),
                () -> Assertions.assertEquals(testComment.getAuthor(), CommentImplTests.VALID_AUTHOR));
    }

    public static User addInitializedUserToRepo(VehicleDealershipRepository repository) {
        User testUser = UserImplTests.initializeTestUser();
        repository.addUser(testUser);
        return testUser;
    }
}