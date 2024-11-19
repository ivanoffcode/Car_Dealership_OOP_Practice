package com.company.oop.dealership.utils;

import java.util.Arrays;
import java.util.List;

public class TestUtilities {
    /**
     * Returns a new String with size equal to length.
     * Useful when you do not care what the contents of a String are,
     * for example when testing if a String of given size
     * would cause an exception being thrown.
     *
     * @param length the size of the String to be returned.
     * @return a new String with size equal to length
     */
    public static String getString(int length) {
        return "x".repeat(length);
    }


    /**
     * Returns a new List with the passed size param
     * Useful when you do not care for the contents of the List,
     * for example when testing if a List of given size
     * would cause an exception being thrown.
     *
     * @param size the size of List to be returned.
     * @return a new List with size equal to size
     */
    public static List<String> getList(int size) {
        return Arrays.asList(new String[size]);
    }
}
