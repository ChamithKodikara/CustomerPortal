package com.vgates.sample;

import org.apache.log4j.Logger;

import java.io.*;
import java.util.zip.Deflater;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * Created by Chamith on 11/23/2016.
 */
public class ZipCreateExample {
    private static final Logger LOGGER = Logger.getLogger(ZipCreateExample.class);

    public static void main(String[] args) throws IOException {
        LOGGER.info("Please enter file name to zip : ");
        BufferedReader input = new BufferedReader
                (new InputStreamReader(System.in));
        String filesToZip = input.readLine();
        File f = new File(filesToZip);
        if (!f.exists()) {
            LOGGER.info("File not found.");
            System.exit(0);
        }
        LOGGER.info("Please enter zip file name : ");
        String zipFileName = input.readLine();
        if (!zipFileName.endsWith(".zip"))
            zipFileName = zipFileName + ".zip";
        byte[] buffer = new byte[18024];
        try {
            ZipOutputStream out = new ZipOutputStream
                    (new FileOutputStream(zipFileName));
            out.setLevel(Deflater.DEFAULT_COMPRESSION);
            FileInputStream in = new FileInputStream(filesToZip);
            out.putNextEntry(new ZipEntry(filesToZip));
            int len;
            while ((len = in.read(buffer)) > 0) {
                out.write(buffer, 0, len);
            }
            out.closeEntry();
            in.close();
            out.close();
        } catch (IllegalArgumentException iae) {
            LOGGER.error(iae.getMessage(), iae);
            System.exit(0);
        } catch (FileNotFoundException fnfe) {
            LOGGER.error(fnfe.getMessage(), fnfe);
            System.exit(0);
        } catch (IOException ioe) {
            LOGGER.error(ioe.getMessage(), ioe);
            System.exit(0);
        }
    }
}
