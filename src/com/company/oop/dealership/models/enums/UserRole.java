package com.company.oop.dealership.models.enums;

public enum UserRole {
    NORMAL,
    VIP,
    ADMIN;

    @Override
    public String toString() {
        return switch (this) {
            case ADMIN -> "Admin";
            case NORMAL -> "Normal";
            case VIP -> "VIP";
            default -> "";
        };
    }
}
