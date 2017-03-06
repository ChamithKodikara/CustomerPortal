package com.vgates.sample;

import net.lingala.zip4j.core.ZipFile;
import net.lingala.zip4j.exception.ZipException;
import net.lingala.zip4j.model.ZipParameters;
import net.lingala.zip4j.util.Zip4jConstants;

import java.io.File;
import java.util.ArrayList;

/**
 * Created by Chamith on 11/23/2016.
 */
public class CreatePasswordProtectedZipExample {
    public static void main(String[] args)
    {
        try {
            //This is name and path of zip file to be created
            ZipFile zipFile = new ZipFile("E:/ZIPTest/test.zip");

            //Add files to be archived into zip file
            ArrayList<File> filesToAdd = new ArrayList<File>();
            filesToAdd.add(new File("E:/ZIPTest/test1.txt"));
            filesToAdd.add(new File("E:/ZIPTest/test2.txt"));

            //Initiate Zip Parameters which define various properties
            ZipParameters parameters = new ZipParameters();
            parameters.setCompressionMethod(Zip4jConstants.COMP_DEFLATE); // set compression method to deflate compression

            //DEFLATE_LEVEL_FASTEST     - Lowest compression level but higher speed of compression
            //DEFLATE_LEVEL_FAST        - Low compression level but higher speed of compression
            //DEFLATE_LEVEL_NORMAL  - Optimal balance between compression level/speed
            //DEFLATE_LEVEL_MAXIMUM     - High compression level with a compromise of speed
            //DEFLATE_LEVEL_ULTRA       - Highest compression level but low speed
            parameters.setCompressionLevel(Zip4jConstants.DEFLATE_LEVEL_NORMAL);

            //Set the encryption flag to true
            parameters.setEncryptFiles(true);

            //Set the encryption method to AES Zip Encryption
            parameters.setEncryptionMethod(Zip4jConstants.ENC_METHOD_AES);

            //AES_STRENGTH_128 - For both encryption and decryption
            //AES_STRENGTH_192 - For decryption only
            //AES_STRENGTH_256 - For both encryption and decryption
            //Key strength 192 cannot be used for encryption. But if a zip file already has a
            //file encrypted with key strength of 192, then Zip4j can decrypt this file
            parameters.setAesKeyStrength(Zip4jConstants.AES_STRENGTH_256);

            //Set password
            parameters.setPassword("testpwd");

            //Now add files to the zip file
            zipFile.addFiles(filesToAdd, parameters);
        }
        catch (ZipException e)
        {
            e.printStackTrace();
        }
    }
}
