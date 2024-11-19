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

public class LoginTests {

    public static final int EXPECTED_NUMBER_OF_ARGUMENTS = 2;

    private VehicleDealershipRepository repository;
    private LoginCommand loginCommand;

    @BeforeEach
    public void before() {
        repository = new VehicleDealershipRepositoryImpl();
        loginCommand = new LoginCommand(repository);
    }

    @Test
    public void should_ThrowException_When_ArgumentCountDifferentThanExpected() {
        // Arrange
        List<String> params = TestUtilities.getList(EXPECTED_NUMBER_OF_ARGUMENTS - 1);

        // Act, Assert
        Assertions.assertThrows(IllegalArgumentException.class, () -> loginCommand.execute(params));
    }

    @Test
    public void should_LoginUser_When_UserNotLoggedIn() {
        // Arrange
        User userToLogIn = UserImplTests.initializeTestUser();
        repository.addUser(userToLogIn);
        List<String> params = List.of(userToLogIn.getUsername(), userToLogIn.getPassword());

        // Act
        loginCommand.execute(params);

        // Assert
        Assertions.assertEquals(userToLogIn.getUsername(), repository.getLoggedInUser().getUsername());
    }

    @Test
    public void should_Throw_When_PasswordIsWrong() {
        // Arrange
        User userToLogIn = UserImplTests.initializeTestUser();
        repository.addUser(userToLogIn);
        List<String> params = List.of(userToLogIn.getUsername(), "WRONG PASSWORD");

        // Act, Assert
        Assertions.assertThrows(IllegalArgumentException.class, () -> loginCommand.execute(params));
    }

    @Test
    public void should_Throw_When_UserDoesNotExists() {
        // Arrange
        List<String> params = List.of(
                UserImplTests.VALID_USERNAME,
                UserImplTests.VALID_PASSWORD);

        // Act, Assert
        Assertions.assertThrows(IllegalArgumentException.class, () -> loginCommand.execute(params));
    }

    @Test
    public void should_Throw_When_UserAlreadyLoggedIn() {
        // Arrange
        User userToLogIn = loginInitializedUserToRepository(repository);
        List<String> params = List.of(userToLogIn.getUsername(), userToLogIn.getPassword());

        // Act, Assert
        Assertions.assertThrows(IllegalArgumentException.class, () -> loginCommand.execute(params));
    }

    @Test
    public void should_Login_When_InputIsValid() {
        // Arrange
        User userToLogin = UserImplTests.initializeTestUser();
        repository.addUser(userToLogin);
        List<String> params = List.of(
                userToLogin.getUsername(),
                userToLogin.getPassword());

        // Act
        loginCommand.executeCommand(params);

        // Assert
        Assertions.assertEquals(repository.getLoggedInUser().getUsername(), userToLogin.getUsername());
    }

    public static User loginInitializedUserToRepository(VehicleDealershipRepository repository) {
        User testUser = UserImplTests.initializeTestUser();
        repository.addUser(testUser);
        repository.login(testUser);
        return testUser;
    }
}