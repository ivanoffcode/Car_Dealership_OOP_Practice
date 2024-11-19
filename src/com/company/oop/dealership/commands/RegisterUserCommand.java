package com.company.oop.dealership.commands;

import com.company.oop.dealership.core.contracts.VehicleDealershipRepository;
import com.company.oop.dealership.models.users.contracts.User;
import com.company.oop.dealership.models.enums.UserRole;
import com.company.oop.dealership.utils.ParsingHelpers;
import com.company.oop.dealership.utils.ValidationHelpers;

import java.util.List;

public class RegisterUserCommand extends BaseCommand {

    private final static String USER_REGISTERED = "User %s registered successfully!";
    public final static String USER_LOGGED_IN_ALREADY = "User %s is logged in! Please log out first!";
    private final static String USER_ALREADY_EXIST = "User %s already exist. Choose a different username!";
    public static final int EXPECTED_NUMBER_OF_ARGUMENTS = 4;

    public RegisterUserCommand(VehicleDealershipRepository vehicleDealershipRepository) {
        super(vehicleDealershipRepository);
    }

    @Override
    protected String executeCommand(List<String> parameters) {
        throwIfUserLoggedIn();
        ValidationHelpers.validateArgumentsCount(parameters, EXPECTED_NUMBER_OF_ARGUMENTS);
        String username = parameters.get(0);
        String firstName = parameters.get(1);
        String lastName = parameters.get(2);
        String password = parameters.get(3);

        UserRole userRole = UserRole.NORMAL;

        if (parameters.size() == EXPECTED_NUMBER_OF_ARGUMENTS + 1) {
            userRole = ParsingHelpers.tryParseEnum(parameters.get(4), UserRole.class);
        }

        return registerUser(username, firstName, lastName, password, userRole);
    }

    private String registerUser(String username, String firstName, String lastName, String password, UserRole userRole) {
        User user = getVehicleDealershipRepository().createUser(username, firstName, lastName, password, userRole);
        getVehicleDealershipRepository().addUser(user);
        getVehicleDealershipRepository().login(user);

        return String.format(USER_REGISTERED, username);
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