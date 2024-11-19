package com.company.oop.dealership.commands;

import com.company.oop.dealership.core.contracts.VehicleDealershipRepository;
import com.company.oop.dealership.models.users.contracts.User;

import java.util.Iterator;
import java.util.List;

import static java.lang.String.format;

public class ShowUsersCommand extends BaseCommand {
    public static final String NOT_ADMIN_ERR = "You are not an admin!";
    //TODO

    public ShowUsersCommand(VehicleDealershipRepository vehicleDealershipRepository) {
        super(vehicleDealershipRepository);
    }

    @Override
    protected String executeCommand(List<String> parameters) {
        if(!getVehicleDealershipRepository().getLoggedInUser().isAdmin()){
            throw new IllegalArgumentException(NOT_ADMIN_ERR);
        }
        return showUsersCommand();
    }

    private String showUsersCommand() {
        List<User> users = getVehicleDealershipRepository().getUsers();
        StringBuilder output = new StringBuilder();
        output.append("--USERS--\n");
        for (int i = 0; i < users.size(); i++) {
            output.append(format("%d. ", i + 1));
            output.append(users.get(i).toString());
            if (i < users.size() - 1) {
                output.append(System.lineSeparator());
            }
        }
        return output.toString().trim();
    }

    @Override
    protected boolean requiresLogin() {
        return true;
    }

}
