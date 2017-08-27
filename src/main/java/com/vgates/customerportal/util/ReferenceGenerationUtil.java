package com.vgates.customerportal.util;

public class ReferenceGenerationUtil {
    /**
     * This is to add padding before string value(Mask Left Side) with zeros
     *
     * @param string base String to pad
     * @param length full length of String after padding added
     */
    public static String leftPadWithZeros(String string, int length) {
        if (string == null) {
            string = "0";
        }
        String format = String.format("%" + length + "." + length + "s", string);
        return String.format("%1$" + length + "s", format).replace(" ", "0");
    }

    public static String generateCustomerReference(String num) {
        return "C-" + leftPadWithZeros(num, 6);
    }

    public static String generateInvoiceReference(String num) {
        return "INV-" + leftPadWithZeros(num, 6);
    }
}
