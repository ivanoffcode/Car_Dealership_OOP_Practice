package com.company.oop.dealership.commands;

import com.company.oop.dealership.core.VehicleDealershipRepositoryImpl;
import com.company.oop.dealership.core.contracts.VehicleDealershipRepository;
import com.company.oop.dealership.models.UserImplTests;
import com.company.oop.dealership.models.users.contracts.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

public class LogoutTests {

    private VehicleDealershipRepository repository;
    private LogoutCommand logoutCommand;

    @BeforeEach
    public void before() {
        repository = new VehicleDealershipRepositoryImpl();
        logoutCommand = new LogoutCommand(repository);
    }

    @Test
    public void should_Throw_When_UserNotLoggedIn() {

        // Arrange, Act, Assert
        Assertions.assertThrows(IllegalArgumentException.class, () -> logoutCommand.execute(new ArrayList<>()));
    }

    @Test
    public void should_LogoutUser() {
        // Arrange
        User userToLogIn = UserImplTests.initializeTestUser();
        repository.login(userToLogIn);

        // Act
        logoutCommand.execute(new ArrayList<>());

        // Assert
        Assertions.assertThrows(IllegalArgumentException.class, () -> repository.getLoggedInUser());
    }
}