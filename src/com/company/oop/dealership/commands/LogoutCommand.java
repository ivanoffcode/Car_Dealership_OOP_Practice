package com.company.oop.dealership.commands;

import com.company.oop.dealership.core.contracts.VehicleDealershipRepository;

import java.util.List;

public class LogoutCommand extends BaseCommand {

    public final static String USER_LOGGED_OUT = "You logged out!";

    public LogoutCommand(VehicleDealershipRepository vehicleDealershipRepository) {
        super(vehicleDealershipRepository);
    }

    @Override
    protected String executeCommand(List<String> parameters) {
        getVehicleDealershipRepository().logout();
        return USER_LOGGED_OUT;
    }

    @Override
    protected boolean requiresLogin() {
        return true;
    }
}