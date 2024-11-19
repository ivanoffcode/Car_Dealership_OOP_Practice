package com.company.oop.dealership.utils;

public class VehicleBaseConstants {

        public static final int MAKE_NAME_LEN_MIN = 2;
        public static final int MODEL_NAME_LEN_MIN = 1;
        public static final double PRICE_VAL_MIN = 0;

        public static final String VALID_MAKE = TestUtilities.getString(MAKE_NAME_LEN_MIN + 1);
        public static final String VALID_MODEL = TestUtilities.getString(MODEL_NAME_LEN_MIN + 1);
        public static final double VALID_PRICE = PRICE_VAL_MIN + 1;
        public static final String INVALID_MAKE = TestUtilities.getString(MAKE_NAME_LEN_MIN - 1);
        public static final String INVALID_MODEL = TestUtilities.getString(MODEL_NAME_LEN_MIN - 1);
}