package com.core.kernel;

import org.json.JSONStringer;
import org.json.JSONWriter;
import org.junit.jupiter.api.Test;

import java.awt.image.BufferedImage;
import java.io.*;

public class Kernel {

    private static final String STORAGE_PATH;

    static {
        STORAGE_PATH = System.getProperty("user.home") + "\\Appdata\\Local\\Meliora";
    }

    public static ScanResult analyzeScan(BufferedImage image) {
        return null;
    }

    public static void saveScan(ScanResult scanResult) {
        File dir = new File(STORAGE_PATH);
        dir.mkdir();

        File jsonFile = new File(STORAGE_PATH + "\\scans.json");

        boolean created;
        try {
            created = jsonFile.createNewFile();
        } catch (IOException e) {
            created = false;
            e.printStackTrace();
        }
        if (jsonFile.isFile()) {
            created = true;
        }

        if (created) {
            try {
                PrintWriter printWriter =
                        new PrintWriter(new FileOutputStream(jsonFile, true));

                printWriter.println(scanResult.toJSON());

                printWriter.flush();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    @Test
    public void testSaveScan() {
        saveScan(new ScanResult());
    }
}
