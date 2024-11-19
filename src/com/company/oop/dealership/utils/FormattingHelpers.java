package com.company.oop.dealership.utils;

import java.math.BigDecimal;

public class FormattingHelpers {

    public static String removeTrailingZerosFromDouble(double number) {
        BigDecimal num = BigDecimal.valueOf(number).stripTrailingZeros();
        return num.toPlainString();
    }
}
