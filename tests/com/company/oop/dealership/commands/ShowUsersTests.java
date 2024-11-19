package com.company.oop.dealership.commands;

import com.company.oop.dealership.core.VehicleDealershipRepositoryImpl;
import com.company.oop.dealership.core.contracts.VehicleDealershipRepository;
import com.company.oop.dealership.models.UserImplTests;
import com.company.oop.dealership.models.users.contracts.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

public class ShowUsersTests {

    private ShowUsersCommand showUsersCommand;
    private VehicleDealershipRepository repository;

    @BeforeEach
    public void before() {
        repository = new VehicleDealershipRepositoryImpl();
        showUsersCommand = new ShowUsersCommand(repository);
    }

    @Test
    public void should_ThrowException_When_NoUserIsLoggedIn() {
        // Arrange, Act, Assert
        Assertions.assertThrows(IllegalArgumentException.class, () -> showUsersCommand.execute(List.of()));
    }

    @Test
    public void should_ThrowException_When_LoggedInUserIsNotAdmin() {
        // Arrange
        LoginTests.loginInitializedUserToRepository(repository);

        // Act, Assert
        Assertions.assertThrows(IllegalArgumentException.class, () -> showUsersCommand.execute(List.of()));
    }

    @Test
    public void should_ShowCategory_When_ArgumentsAreValid() {
        // Arrange
        User testAdmin = UserImplTests.initializeTestAdmin();
        repository.addUser(testAdmin);
        repository.login(testAdmin);

        // Act, Assert
        Assertions.assertDoesNotThrow(() -> showUsersCommand.execute(List.of()));
    }
}