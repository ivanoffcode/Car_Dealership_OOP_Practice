package com.company.oop.dealership.core.contracts;

import com.company.oop.dealership.commands.contracts.Command;

public interface CommandFactory {

    Command createCommandFromCommandName(String commandTypeAsString, VehicleDealershipRepository vehicleDealershipRepository);

}
