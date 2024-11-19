package com.company.oop.dealership.models.users;

import com.company.oop.dealership.models.enums.UserRole;
import com.company.oop.dealership.models.users.contracts.User;
import com.company.oop.dealership.models.vehicles.contracts.Comment;
import com.company.oop.dealership.models.vehicles.contracts.Vehicle;

import java.util.ArrayList;
import java.util.List;

import static com.company.oop.dealership.models.vehicles.VehicleBase.NO_COMMENTS_HEADER;
import static com.company.oop.dealership.utils.ValidationHelpers.validatePattern;
import static com.company.oop.dealership.utils.ValidationHelpers.validateStringLength;
import static java.lang.String.format;

public class UserImpl implements User {

    public static final int USERNAME_LEN_MIN = 2;
    public static final int USERNAME_LEN_MAX = 20;
    private static final String USERNAME_REGEX_PATTERN = "^[A-Za-z0-9]+$";
    private static final String USERNAME_PATTERN_ERR = "Username contains invalid symbols!";
    private static final String USERNAME_LEN_ERR = format(
            "Username must be between %d and %d characters long!",
            USERNAME_LEN_MIN,
            USERNAME_LEN_MAX);

    public static final int PASSWORD_LEN_MIN = 5;
    public static final int PASSWORD_LEN_MAX = 30;
    private static final String PASSWORD_REGEX_PATTERN = "^[A-Za-z0-9@*_-]+$";
    private static final String PASSWORD_PATTERN_ERR = "Password contains invalid symbols!";
    private static final String PASSWORD_LEN_ERR = format(
            "Password must be between %s and %s characters long!",
            PASSWORD_LEN_MIN,
            PASSWORD_LEN_MAX);

    public static final int LASTNAME_LEN_MIN = 2;
    public static final int LASTNAME_LEN_MAX = 20;
    private static final String LASTNAME_LEN_ERR = format(
            "Lastname must be between %s and %s characters long!",
            LASTNAME_LEN_MIN,
            LASTNAME_LEN_MAX);

    public static final int FIRSTNAME_LEN_MIN = 2;
    public static final int FIRSTNAME_LEN_MAX = 20;
    private static final String FIRSTNAME_LEN_ERR = format(
            "Firstname must be between %s and %s characters long!",
            FIRSTNAME_LEN_MIN,
            FIRSTNAME_LEN_MAX);

    private final static String NOT_AN_VIP_USER_VEHICLES_ADD = "You are not VIP and cannot add more than %d vehicles!";
    private final static String ADMIN_CANNOT_ADD_VEHICLES = "You are an admin and therefore cannot add vehicles!";

    private static final String YOU_ARE_NOT_THE_AUTHOR = "You are not the author of the comment you are trying to remove!";
    private final static String USER_TO_STRING = "Username: %s, FullName: %s %s, Role: %s";
    private final static String NO_VEHICLES_HEADER = "--NO VEHICLES--";
    private final static String USER_HEADER = "--USER %s--\n";
    private static final int NORMAL_ROLE_VEHICLE_LIMIT = 5;

    //TODO
    private String username;
    private String firstName;
    private String lastName;
    private String password;
    private List<Vehicle> vehicles;
    private UserRole userRole;

    public UserImpl(String username, String firstName, String lastName, String password, UserRole userRole) {
        setUsername(username);
        setFirstName(firstName);
        setLastName(lastName);
        setPassword(password);
        setUserRole(userRole);
        vehicles = new ArrayList<>();
    }

    private void setUsername(String username) {
        validateStringLength(username, USERNAME_LEN_MIN, USERNAME_LEN_MAX, USERNAME_LEN_ERR);
        validatePattern(username, USERNAME_REGEX_PATTERN, USERNAME_PATTERN_ERR);
        this.username = username;
    }

    private void setFirstName(String firstName) {
        validateStringLength(firstName, FIRSTNAME_LEN_MIN, FIRSTNAME_LEN_MAX, FIRSTNAME_LEN_ERR);
        this.firstName = firstName;
    }

    private void setLastName(String lastName) {
        validateStringLength(lastName, LASTNAME_LEN_MIN, LASTNAME_LEN_MAX, LASTNAME_LEN_ERR);
        this.lastName = lastName;
    }

    private void setPassword(String password) {
        validateStringLength(password, PASSWORD_LEN_MIN, PASSWORD_LEN_MAX, PASSWORD_LEN_ERR);
        validatePattern(password, PASSWORD_REGEX_PATTERN, PASSWORD_PATTERN_ERR);
        this.password = password;
    }

    private void setUserRole(UserRole userRole) {
        this.userRole = userRole;
    }

    private void setVehicles(List<Vehicle> vehicles) {
        this.vehicles = vehicles;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public String getFirstName() {
        return firstName;
    }

    @Override
    public String getLastName() {
        return lastName;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public UserRole getRole() {
        return userRole;
    }

    @Override
    public List<Vehicle> getVehicles() {
        return new ArrayList<>(vehicles);
    }

    @Override
    public void addVehicle(Vehicle vehicle) {
        if (isAdmin()) {
            throw new IllegalArgumentException(ADMIN_CANNOT_ADD_VEHICLES);
        }
        if (!isVIP() && vehicles.size() >= NORMAL_ROLE_VEHICLE_LIMIT) {
            throw new IllegalArgumentException(
                    format(NOT_AN_VIP_USER_VEHICLES_ADD, NORMAL_ROLE_VEHICLE_LIMIT));
        }
        vehicles.add(vehicle);
    }

    @Override
    public void removeVehicle(Vehicle vehicle) {
        vehicles.remove(vehicle);
    }

    @Override
    public void addComment(Comment commentToAdd, Vehicle vehicleToAddComment) {
        vehicleToAddComment.addComment(commentToAdd);
    }

    @Override
    public void removeComment(Comment commentToRemove, Vehicle vehicleToRemoveComment) {
        if(commentToRemove.getAuthor().equals(getUsername())){
            vehicleToRemoveComment.removeComment(commentToRemove);
        }
        else {
            throw new IllegalArgumentException(YOU_ARE_NOT_THE_AUTHOR);
        }
    }

    @Override
    public String printVehicles() {
        StringBuilder builder = new StringBuilder();

        builder.append(String.format(USER_HEADER, getUsername()));

        if (vehicles.size() == 0) {
            builder.append(NO_VEHICLES_HEADER).append(System.lineSeparator());
        } else {
            int counter = 1;
            for (Vehicle vehicle : getVehicles()) {
                builder.append(String.format("%d. %s", counter, vehicle.toString())).append(System.lineSeparator());
                builder.append(vehicle.printComment());
                counter++;
            }
        }

        return builder.toString().trim();
    }



    @Override
    public boolean isAdmin() {
        return userRole.equals(UserRole.ADMIN);
    }

    @Override
    public boolean isVIP() {
        return userRole.equals(UserRole.VIP);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserImpl user = (UserImpl) o;
        return username.equals(user.username) && firstName.equals(user.firstName)
                && lastName.equals(user.lastName) && userRole == user.userRole;
    }

    @Override
    public String toString() {
        return format(USER_TO_STRING, getUsername(), getFirstName(), getLastName(), getRole());
    }

}