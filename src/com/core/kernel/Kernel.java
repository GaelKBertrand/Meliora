package com.core.kernel;

import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Kernel {

    public static final String STORAGE_PATH;

    static {
        STORAGE_PATH = System.getProperty("user.home") + "\\Appdata\\Local\\Meliora";
    }

    public static ScanResult analyzeScan(BufferedImage image) {
        //TODO make analysis
        return null;
    }

    public static void saveScan(ScanResult scanResult) throws IOException {
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
                Scanner s = new Scanner(new FileInputStream(jsonFile));
                boolean hasNextLine = s.hasNextLine();

                PrintWriter printWriter =
                        new PrintWriter(new FileOutputStream(jsonFile, false));
                JSONArray jsonArray;
                if (hasNextLine) {
                    jsonArray = new JSONArray(s.nextLine());
                } else {
                    jsonArray = new JSONArray();
                }
                jsonArray.put(scanResult.toJSONAndSaveImages());
                printWriter.print(jsonArray);

                printWriter.flush();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    public static List<ScanResult> getSavedScans() {
        ArrayList<ScanResult> scanResults = new ArrayList<>();
        File jsonFile = new File(STORAGE_PATH + "\\scans.json");
        if (jsonFile.isFile()) {
            try {
                Scanner s = new Scanner(new FileInputStream(jsonFile));
                if (s.hasNextLine()) {
                    JSONArray jsonArray = new JSONArray(s.nextLine());
                    int num = jsonArray.length();
                    for (int i = 0; i < num; i++) {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        scanResults.add(ScanResult.ofJSONWithSavedImages(jsonObject));
                    }
                    return scanResults;
                } else {
                    return null;
                }
            } catch (IOException ex) {
                ex.printStackTrace();
                return null;
            }
        } else {
            return null;
        }
    }

    @Test
    public void testSaveScan() throws IOException {
        BufferedImage image =
                ImageIO.read(
                        new File("C:\\Users\\Asus\\Pictures\\small-map-of-usa-with-states-and.jpg"));
        saveScan(new ScanResult(
                "Test1 Test13", image, image, 12.0));
    }

    @Test
    public void testGetSavedScans() {
        System.out.println(getSavedScans());
    }
}
