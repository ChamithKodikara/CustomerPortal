package com.vgates.sample;

import com.vgates.customerportal.util.ReferenceGenerationUtil;

public class ReferenceGenerationTester {
    public static void main(String[] args) {
        System.out.println(ReferenceGenerationUtil.generateCustomerReference("1"));
        System.out.println(ReferenceGenerationUtil.generateInvoiceReference("1"));
    }
}
