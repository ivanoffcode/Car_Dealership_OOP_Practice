package com.company.oop.dealership.commands;

import com.company.oop.dealership.commands.contracts.Command;
import com.company.oop.dealership.core.contracts.VehicleDealershipRepository;

import java.util.List;

public abstract class BaseCommand implements Command {

    private final static String USER_NOT_LOGGED = "You are not logged in! Please login first!";

    private final VehicleDealershipRepository vehicleDealershipRepository;

    protected BaseCommand(VehicleDealershipRepository vehicleDealershipRepository) {
        this.vehicleDealershipRepository = vehicleDealershipRepository;
    }

    protected VehicleDealershipRepository getVehicleDealershipRepository() {
        return vehicleDealershipRepository;
    }

    @Override
    public String execute(List<String> parameters) {
        if (requiresLogin() && !vehicleDealershipRepository.hasLoggedInUser()) {
            throw new IllegalArgumentException(USER_NOT_LOGGED);
        }
        return executeCommand(parameters);
    }

    protected abstract boolean requiresLogin();

    protected abstract String executeCommand(List<String> parameters);
}