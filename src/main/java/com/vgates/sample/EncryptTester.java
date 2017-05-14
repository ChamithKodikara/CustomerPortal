package com.vgates.sample;

import org.apache.log4j.Logger;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by Chamith on 12/6/2016.
 */
public class EncryptTester {

    private static final Logger LOGGER = Logger.getLogger(EncryptTester.class);

    public static void main(String[] args) {
        LOGGER.info(encryptPassword("123"));
    }

    private static String encryptPassword(String password) {
        String generatedPassword = null;
        try {

            // Create MessageDigest instance for MD5
            MessageDigest md = MessageDigest.getInstance("MD5");

            //Add password bytes to digest
            md.update(password.getBytes());

            //Get the hash's bytes
            byte[] bytes = md.digest();

            //This bytes[] has bytes in decimal format;
            //Convert it to hexadecimal format
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < bytes.length; i++) {
                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }

            //Get complete hashed password in hex format
            generatedPassword = sb.toString();

        } catch (NoSuchAlgorithmException ex) {
            LOGGER.error(ex.getMessage(), ex);
        }
        return generatedPassword;
    }

}
