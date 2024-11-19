package com.company.oop.dealership.commands;

import com.company.oop.dealership.core.contracts.VehicleDealershipRepository;
import com.company.oop.dealership.models.vehicles.contracts.Comment;
import com.company.oop.dealership.models.users.contracts.User;
import com.company.oop.dealership.models.vehicles.contracts.Vehicle;
import com.company.oop.dealership.utils.ParsingHelpers;
import com.company.oop.dealership.utils.ValidationHelpers;

import java.util.List;

public class RemoveCommentCommand extends BaseCommand {

    private final static String COMMENT_INDEX_OUT_OF_BOUNDS = "There is no comment on this index.";
    public final static String INVALID_COMMENT_INDEX = "Invalid comment index. Expected a number.";
    public final static String COMMENT_REMOVED_SUCCESSFULLY = "%s removed comment successfully!";
    private final static String INVALID_VEHICLE_INDEX = "Invalid vehicle index. Expected a number.";
    private final static String VEHICLE_INDEX_OUT_OF_BOUNDS = "There is no vehicle on this index.";

    public static final int EXPECTED_NUMBER_OF_ARGUMENTS = 3;

    public RemoveCommentCommand(VehicleDealershipRepository vehicleDealershipRepository) {
        super(vehicleDealershipRepository);
    }

    @Override
    protected String executeCommand(List<String> parameters) {
        ValidationHelpers.validateArgumentsCount(parameters, EXPECTED_NUMBER_OF_ARGUMENTS);
        int vehicleIndex = ParsingHelpers.tryParseInt(parameters.get(0), INVALID_VEHICLE_INDEX) - 1;
        int commentIndex = ParsingHelpers.tryParseInt(parameters.get(1), INVALID_COMMENT_INDEX) - 1;
        String username = parameters.get(2);

        return removeComment(vehicleIndex, commentIndex, username);
    }

    private String removeComment(int vehicleIndex, int commentIndex, String username) {
        User user = getVehicleDealershipRepository().findUserByUsername(username);

        ValidationHelpers.validateIntRange(vehicleIndex, 0, user.getVehicles().size() - 1, VEHICLE_INDEX_OUT_OF_BOUNDS);
        ValidationHelpers.validateIntRange(commentIndex, 0, user.getVehicles().get(vehicleIndex).getComments().size() - 1, COMMENT_INDEX_OUT_OF_BOUNDS);

        Vehicle vehicle = user.getVehicles().get(vehicleIndex);
        Comment comment = user.getVehicles().get(vehicleIndex).getComments().get(commentIndex);

        getVehicleDealershipRepository().getLoggedInUser().removeComment(comment, vehicle);

        return String.format(COMMENT_REMOVED_SUCCESSFULLY, getVehicleDealershipRepository().getLoggedInUser().getUsername());
    }

    @Override
    protected boolean requiresLogin() {
        return true;
    }
}