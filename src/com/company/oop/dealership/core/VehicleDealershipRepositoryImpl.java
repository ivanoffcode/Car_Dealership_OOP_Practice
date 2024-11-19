package com.company.oop.dealership.core;

import com.company.oop.dealership.core.contracts.VehicleDealershipRepository;
import com.company.oop.dealership.models.enums.UserRole;
import com.company.oop.dealership.models.vehicles.CommentImpl;
import com.company.oop.dealership.models.users.UserImpl;
import com.company.oop.dealership.models.vehicles.contracts.Comment;
import com.company.oop.dealership.models.users.contracts.User;
import com.company.oop.dealership.models.vehicles.CarImpl;
import com.company.oop.dealership.models.vehicles.MotorcycleImpl;
import com.company.oop.dealership.models.vehicles.TruckImpl;
import com.company.oop.dealership.models.vehicles.contracts.Car;
import com.company.oop.dealership.models.vehicles.contracts.Motorcycle;
import com.company.oop.dealership.models.vehicles.contracts.Truck;

import java.util.ArrayList;
import java.util.List;

public class VehicleDealershipRepositoryImpl implements VehicleDealershipRepository {

    private static final String NO_LOGGED_IN_USER = "There is no logged in user.";
    private final static String NO_SUCH_USER = "There is no user with username %s!";
    private final static String USER_ALREADY_EXIST = "User %s already exist. Choose a different username!";

    private final List<User> users;
    private User loggedUser;

    public VehicleDealershipRepositoryImpl() {
        this.users = new ArrayList<>();
        this.loggedUser = null;
    }

    @Override
    public List<User> getUsers() {
        return new ArrayList<>(users);
    }

    @Override
    public void addUser(User userToAdd) {
        if (users.contains(userToAdd)) {
            throw new IllegalArgumentException(String.format(USER_ALREADY_EXIST, userToAdd.getUsername()));
        }
        this.users.add(userToAdd);
    }

    @Override
    public User findUserByUsername(String username) {
        User user = users
                .stream()
                .filter(u -> u.getUsername().equalsIgnoreCase(username))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(String.format(NO_SUCH_USER, username)));
        return user;
    }

    @Override
    public User getLoggedInUser() {
        if (loggedUser == null) {
            throw new IllegalArgumentException(NO_LOGGED_IN_USER);
        }
        return loggedUser;
    }

    @Override
    public boolean hasLoggedInUser() {
        return loggedUser != null;
    }

    @Override
    public void login(User user) {
        loggedUser = user;
    }

    @Override
    public void logout() {
        loggedUser = null;
    }

    @Override
    public Car createCar(String make, String model, double price, int seats) {
        return new CarImpl(make, model, price, seats);
    }

    @Override
    public Motorcycle createMotorcycle(String make, String model, double price, String category) {
        return new MotorcycleImpl(make, model, price, category);
    }

    @Override
    public Truck createTruck(String make, String model, double price, int weightCapacity) {
        return new TruckImpl(make, model, price, weightCapacity);
    }

    @Override
    public User createUser(String username, String firstName, String lastName, String password, UserRole userRole) {
        return new UserImpl(username, firstName, lastName, password, userRole);
    }

    @Override
    public Comment createComment(String content, String author) {
        return new CommentImpl(content, author);
    }
}
