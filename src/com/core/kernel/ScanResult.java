package com.core.kernel;

import org.json.JSONObject;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ScanResult {

    private String fullName;
    private BufferedImage probabilityMap;
    private BufferedImage mccMap;
    private double resADPercentage;

    public ScanResult(String fullName, BufferedImage probabilityMap,
                      BufferedImage mccMap, double resADPercentage) {
        this.fullName = fullName;
        this.probabilityMap = probabilityMap;
        this.mccMap = mccMap;
        this.resADPercentage = resADPercentage;
    }

    //TODO implement this class
    public JSONObject toJSONAndSaveImages() throws IOException {
        String imagesPath = Kernel.STORAGE_PATH + "\\Media";
        File dir = new File(imagesPath);
        dir.mkdir();
        String time = LocalDateTime.now().format(DateTimeFormatter.ISO_DATE_TIME);
        time = time.replace(':', '-');
        time = time.replace('.', '-');
        String probabilityMapPath = imagesPath + "\\" + trimFullName(fullName) + "_prob_" + time + ".jpg";
        String mccMapPath = imagesPath + "\\" + trimFullName(fullName) + "_mcc_" + time + ".jpg";

        ImageIO.write(probabilityMap, "jpg", createFile(probabilityMapPath));
        ImageIO.write(mccMap, "jpg", createFile(mccMapPath));
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("fullName", fullName);
        jsonObject.put("resADPercentage", resADPercentage);
        jsonObject.put("probabilityMapPath", Path.of(probabilityMapPath));
        jsonObject.put("mccMap", Path.of(mccMapPath));
        jsonObject.put("time", time);
        return jsonObject;
    }


    //TODO make this after toJSON finished
    public static ScanResult ofJSONWithSavedImages(JSONObject jsonObject) throws IOException {
        String fullName = jsonObject.getString("fullName");
        double resADPercentage = jsonObject.getDouble("resADPercentage");
        String probabilityMapPath = jsonObject.getString("probabilityMapPath");
        String mccMapPath = jsonObject.getString("mccMap");
        BufferedImage probabilityMap = ImageIO.read(new File(probabilityMapPath));
        BufferedImage mccMap = ImageIO.read(new File(mccMapPath));
        ScanResult scanResult = new ScanResult(fullName, probabilityMap, mccMap, resADPercentage);
        return scanResult;
    }

    private static File createFile(String path) {
        File newFile = new File(path);
        boolean created;
        try {
            created = newFile.createNewFile();
        } catch (IOException e) {
            created = false;
            e.printStackTrace();
        }
        if (newFile.isFile()) {
            created = true;
        }

        if (created) {
            return newFile;
        } else {
            return null;
        }
    }

    private static String trimFullName(String fullName) {
        String[] tokens = fullName.split(" ");
        StringBuilder builder = new StringBuilder();
        for (String token : tokens) {
            builder.append(token);
        }
        return builder.toString();
    }

    @Override
    public String toString() {
        return "ScanResult{" +
                "fullName='" + fullName + '\'' +
                ", resADPercentage=" + resADPercentage +
                ", probabilityMap=" + probabilityMap +
                ", mccMap=" + mccMap +
                '}';
    }
}
