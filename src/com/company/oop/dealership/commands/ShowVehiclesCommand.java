package com.company.oop.dealership.commands;

import com.company.oop.dealership.core.contracts.VehicleDealershipRepository;
import com.company.oop.dealership.models.users.contracts.User;
import com.company.oop.dealership.utils.ValidationHelpers;

import java.util.List;

public class ShowVehiclesCommand extends BaseCommand {

    public static final int EXPECTED_NUMBER_OF_ARGUMENTS = 1;

    public ShowVehiclesCommand(VehicleDealershipRepository vehicleDealershipRepository) {
        super(vehicleDealershipRepository);
    }

    @Override
    protected String executeCommand(List<String> parameters) {
        ValidationHelpers.validateArgumentsCount(parameters, EXPECTED_NUMBER_OF_ARGUMENTS);
        String username = parameters.get(0);
        return showUserVehicles(username);
    }

    private String showUserVehicles(String username) {
        User user = getVehicleDealershipRepository().findUserByUsername(username);
        return user.printVehicles();
    }

    @Override
    protected boolean requiresLogin() {
        return true;
    }
}