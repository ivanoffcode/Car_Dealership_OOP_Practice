package com.company.oop.dealership.commands;

import com.company.oop.dealership.core.contracts.VehicleDealershipRepository;
import com.company.oop.dealership.models.users.contracts.User;
import com.company.oop.dealership.utils.ValidationHelpers;

import java.util.List;

public class LoginCommand extends BaseCommand {

    private final static String USER_LOGGED_IN = "User %s successfully logged in!";
    private final static String WRONG_USERNAME_OR_PASSWORD = "Wrong username or password!";
    public final static String USER_LOGGED_IN_ALREADY = "User %s is logged in! Please log out first!";

    public static final int EXPECTED_NUMBER_OF_ARGUMENTS = 2;

    public LoginCommand(VehicleDealershipRepository vehicleDealershipRepository) {
        super(vehicleDealershipRepository);
    }

    @Override
    protected String executeCommand(List<String> parameters) {
        throwIfUserLoggedIn();
        ValidationHelpers.validateArgumentsCount(parameters, EXPECTED_NUMBER_OF_ARGUMENTS);
        String username = parameters.get(0);
        String password = parameters.get(1);

        return login(username, password);
    }

    private String login(String username, String password) {
        User userFound = getVehicleDealershipRepository().findUserByUsername(username);

        if (!userFound.getPassword().equals(password)) {
            throw new IllegalArgumentException(WRONG_USERNAME_OR_PASSWORD);
        }

        getVehicleDealershipRepository().login(userFound);
        return String.format(USER_LOGGED_IN, username);
    }

    @Override
    protected boolean requiresLogin() {
        return false;
    }

    private void throwIfUserLoggedIn() {
        if (getVehicleDealershipRepository().hasLoggedInUser()) {
            throw new IllegalArgumentException(
                    String.format(USER_LOGGED_IN_ALREADY, getVehicleDealershipRepository().getLoggedInUser().getUsername())
            );
        }
    }
}