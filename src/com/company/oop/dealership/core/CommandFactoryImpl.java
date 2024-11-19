package com.company.oop.dealership.core;

import com.company.oop.dealership.commands.*;
import com.company.oop.dealership.commands.contracts.Command;
import com.company.oop.dealership.commands.enums.CommandType;
import com.company.oop.dealership.core.contracts.CommandFactory;
import com.company.oop.dealership.core.contracts.VehicleDealershipRepository;
import com.company.oop.dealership.utils.ParsingHelpers;

public class CommandFactoryImpl implements CommandFactory {

    @Override
    public Command createCommandFromCommandName(String commandTypeAsString, VehicleDealershipRepository vehicleDealershipRepository) {
        CommandType commandType = ParsingHelpers.tryParseEnum(commandTypeAsString, CommandType.class);
        switch (commandType) {
            case LOGIN:
                return new LoginCommand(vehicleDealershipRepository);
            case LOGOUT:
                return new LogoutCommand(vehicleDealershipRepository);
            case SHOWUSERS:
                //TODO
                return new ShowUsersCommand(vehicleDealershipRepository);
            case ADDCOMMENT:
                return new AddCommentCommand(vehicleDealershipRepository);
            case ADDVEHICLE:
                return new AddVehicleCommand(vehicleDealershipRepository);
            case REGISTERUSER:
                return new RegisterUserCommand(vehicleDealershipRepository);
            case SHOWVEHICLES:
                return new ShowVehiclesCommand(vehicleDealershipRepository);
            case REMOVECOMMENT:
                return new RemoveCommentCommand(vehicleDealershipRepository);
            case REMOVEVEHICLE:
                return new RemoveVehicleCommand(vehicleDealershipRepository);
            default:
                throw new IllegalArgumentException();
        }
    }

}
