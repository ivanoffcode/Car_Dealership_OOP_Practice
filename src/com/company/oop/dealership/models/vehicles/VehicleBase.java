package com.company.oop.dealership.models.vehicles;

import com.company.oop.dealership.models.enums.VehicleType;
import com.company.oop.dealership.models.vehicles.contracts.Comment;
import com.company.oop.dealership.models.vehicles.contracts.Vehicle;

import java.util.ArrayList;
import java.util.List;

import static com.company.oop.dealership.utils.FormattingHelpers.removeTrailingZerosFromDouble;
import static com.company.oop.dealership.utils.ValidationHelpers.validateDecimalRange;
import static com.company.oop.dealership.utils.ValidationHelpers.validateStringLength;
import static java.lang.String.format;

public abstract class VehicleBase implements Vehicle {
    public static final int MAKE_NAME_LEN_MIN = 2;
    public static final int MAKE_NAME_LEN_MAX = 15;
    private static final String MAKE_NAME_LEN_ERR = format(
            "Make must be between %s and %s characters long!",
            MAKE_NAME_LEN_MIN,
            MAKE_NAME_LEN_MAX);
    public static final int MODEL_NAME_LEN_MIN = 1;
    public static final int MODEL_NAME_LEN_MAX = 15;
    private static final String MODEL_NAME_LEN_ERR = format(
            "Model must be between %s and %s characters long!",
            MODEL_NAME_LEN_MIN,
            MODEL_NAME_LEN_MAX);
    public static final double PRICE_VAL_MIN = 0;
    public static final double PRICE_VAL_MAX = 1000000;
    private static final String PRICE_VAL_ERR = format(
            "Price must be between %.1f and %.1f!",
            PRICE_VAL_MIN,
            PRICE_VAL_MAX);
    public final static String NO_COMMENTS_HEADER = "--NO COMMENTS--";
    public final static String COMMENTS_HEADER = "--COMMENTS--";


    private String make;
    private String model;
    private double price;
    private List<Comment> comments;

    public VehicleBase(String make, String model, double price) {
        setMake(make);
        setModel(model);
        setPrice(price);
        this.comments = new ArrayList<>();
    }

    @Override
    public List<Comment> getComments() {
        return new ArrayList<>(comments);
    }

    @Override
    public void addComment(Comment comment) {
        comments.add(comment);
    }

    @Override
    public void removeComment(Comment comment) {
        comments.remove(comment);
    }

    public abstract int getWheels();

    public abstract VehicleType getType();

    @Override
    public double getPrice() {
        return price;
    }

    private void setPrice(double price) {
        validateDecimalRange(price, PRICE_VAL_MIN, PRICE_VAL_MAX, PRICE_VAL_ERR);
        this.price = price;
    }

    @Override
    public String getModel() {
        return model;
    }

    private void setModel(String model) {
        validateStringLength(model, MODEL_NAME_LEN_MIN, MODEL_NAME_LEN_MAX, MODEL_NAME_LEN_ERR);
        this.model = model;
    }

    @Override
    public String getMake() {
        return make;
    }

    private void setMake(String make) {
        validateStringLength(make, MAKE_NAME_LEN_MIN, MAKE_NAME_LEN_MAX, MAKE_NAME_LEN_ERR);
        this.make = make;
    }

    protected abstract String printAdditionalInfo();

    @Override
    public String printComment() {
        StringBuilder builder = new StringBuilder();

        if (getComments().size() <= 0) {
            builder.append(NO_COMMENTS_HEADER);
        } else {
            builder.append(COMMENTS_HEADER);
        }

        for (Comment comment : getComments()) {
            builder.append(comment.toString());
        }

        builder.append(COMMENTS_HEADER);
        return builder.toString();
    }



    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        result.append(String.format(". %s:%n", getType()));
        result.append(String.format("Make: %s%n", getMake()));
        result.append(String.format("Model: %s%n", getModel()));
        result.append(String.format("Wheels: %d%n", getWheels()));
        result.append(String.format("Price: $%s%n", removeTrailingZerosFromDouble(getPrice())));
        result.append(String.format("%s%n", printAdditionalInfo()));

        return result.toString();
    }


}
