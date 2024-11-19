package com.company.oop.dealership.commands;

import com.company.oop.dealership.core.contracts.VehicleDealershipRepository;
import com.company.oop.dealership.models.vehicles.contracts.Comment;
import com.company.oop.dealership.models.users.contracts.User;
import com.company.oop.dealership.models.vehicles.contracts.Vehicle;
import com.company.oop.dealership.utils.ParsingHelpers;
import com.company.oop.dealership.utils.ValidationHelpers;

import java.util.List;

public class AddCommentCommand extends BaseCommand {

    public static final int EXPECTED_NUMBER_OF_ARGUMENTS = 3;
    public static final String INVALID_INPUT_MESSAGE = "Invalid input. Expected a number.";
    public final static String COMMENT_ADDED_SUCCESSFULLY = "%s added comment successfully!";
    public final static String VEHICLE_DOES_NOT_EXIST = "The vehicle does not exist!";

    public AddCommentCommand(VehicleDealershipRepository vehicleDealershipRepository) {
        super(vehicleDealershipRepository);
    }

    @Override
    protected String executeCommand(List<String> parameters) {
        ValidationHelpers.validateArgumentsCount(parameters, EXPECTED_NUMBER_OF_ARGUMENTS);
        String content = parameters.get(0);
        String author = parameters.get(1);
        int vehicleIndex = ParsingHelpers.tryParseInt(parameters.get(2), INVALID_INPUT_MESSAGE) - 1;
        return addComment(content, vehicleIndex, author);
    }

    private String addComment(String content, int vehicleIndex, String author) {
        User user = getVehicleDealershipRepository().findUserByUsername(author);

        ValidationHelpers.validateIntRange(vehicleIndex, 0, user.getVehicles().size() - 1, VEHICLE_DOES_NOT_EXIST);

        Vehicle vehicle = user.getVehicles().get(vehicleIndex);

        Comment comment = getVehicleDealershipRepository().createComment(content, getVehicleDealershipRepository().getLoggedInUser().getUsername());

        getVehicleDealershipRepository().getLoggedInUser().addComment(comment, vehicle);

        return String.format(COMMENT_ADDED_SUCCESSFULLY, getVehicleDealershipRepository().getLoggedInUser().getUsername());
    }

    @Override
    protected boolean requiresLogin() {
        return true;
    }
}