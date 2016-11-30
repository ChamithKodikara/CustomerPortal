package com.vgates.customerportal.encrypter;

import org.apache.log4j.Logger;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by Chamith on 11/30/2016.
 */
public class PasswordEncrypter {

    final static Logger LOGGER = Logger.getLogger(PasswordEncrypter.class);

    public String getEncryptedPassword(String password) {
        return encryptPassword(password);
    }

    private String encryptPassword(String password) {
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
            LOGGER.error("Password Encryption Error ! -- " + ex);
        }
        return generatedPassword;
    }

}
