package com.company.oop.dealership.models.vehicles.contracts;

import com.company.oop.dealership.models.enums.VehicleType;

public interface Vehicle extends Priceable, Commentable {

    int getWheels();

    VehicleType getType();

    String getMake();

    String getModel();

    void addComment(Comment comment);

    void removeComment(Comment comment);

    String toString();

    String printComment();

}
