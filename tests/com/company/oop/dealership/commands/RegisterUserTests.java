package com.company.oop.dealership.commands;

import com.company.oop.dealership.core.VehicleDealershipRepositoryImpl;
import com.company.oop.dealership.core.contracts.VehicleDealershipRepository;
import com.company.oop.dealership.models.UserImplTests;
import com.company.oop.dealership.models.users.contracts.User;
import com.company.oop.dealership.utils.TestUtilities;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

public class RegisterUserTests {

    public static final int EXPECTED_NUMBER_OF_ARGUMENTS = 4;

    private VehicleDealershipRepository repository;
    private RegisterUserCommand registerUserCommand;

    @BeforeEach
    public void before() {
        repository = new VehicleDealershipRepositoryImpl();
        registerUserCommand = new RegisterUserCommand(repository);
    }

    @Test
    public void should_ThrowException_When_ArgumentCountDifferentThanExpected() {
        // Arrange
        List<String> params = TestUtilities.getList(EXPECTED_NUMBER_OF_ARGUMENTS - 1);

        // Act, Assert
        Assertions.assertThrows(IllegalArgumentException.class, () -> registerUserCommand.execute(params));
    }

    @Test
    public void should_Throw_When_UserAlreadyLoggedIn() {
        // Arrange
        User userToLogIn = LoginTests.loginInitializedUserToRepository(repository);
        List<String> params = List.of(userToLogIn.getUsername(), userToLogIn.getPassword());

        // Act, Assert
        Assertions.assertThrows(IllegalArgumentException.class, () -> registerUserCommand.execute(params));
    }

    @Test
    public void should_Throw_When_UserRoleIsNotValid() {
        // Arrange
        repository.addUser(UserImplTests.initializeTestUser());
        List<String> params = List.of(
                UserImplTests.VALID_USERNAME,
                UserImplTests.VALID_FIRST_NAME,
                UserImplTests.VALID_LAST_NAME,
                UserImplTests.VALID_PASSWORD,
                "INVALID_USER_ROLE");

        // Act, Assert
        Assertions.assertThrows(IllegalArgumentException.class, () -> registerUserCommand.execute(params));
    }

    @Test
    public void should_Throw_When_UserAlreadyExist() {
        // Arrange
        repository.addUser(UserImplTests.initializeTestUser());
        List<String> params = List.of(
                UserImplTests.VALID_USERNAME,
                UserImplTests.VALID_FIRST_NAME,
                UserImplTests.VALID_LAST_NAME,
                UserImplTests.VALID_PASSWORD);

        // Act, Assert
        Assertions.assertThrows(IllegalArgumentException.class, () -> registerUserCommand.execute(params));
    }

    @Test
    public void should_Create_When_InputIsValid() {
        // Arrange
        List<String> params = List.of(
                UserImplTests.VALID_USERNAME,
                UserImplTests.VALID_FIRST_NAME,
                UserImplTests.VALID_LAST_NAME,
                UserImplTests.VALID_PASSWORD);

        // Act
        registerUserCommand.execute(params);

        //Assert
        Assertions.assertEquals(1, repository.getUsers().size());
        Assertions.assertEquals(UserImplTests.VALID_USERNAME, repository.getUsers().get(0).getUsername());
    }
}