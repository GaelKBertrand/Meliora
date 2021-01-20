package com.core.kernel;

import org.json.JSONObject;

public class ScanResult {

    private String fullName;
    private double resADPercentage;

    public ScanResult(String fullName, double resADPercentage) {
        this.fullName = fullName;
        this.resADPercentage = resADPercentage;
    }

    //TODO implement this class
    public JSONObject toJSON() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("fullName", fullName);
        jsonObject.put("resADPercentage", resADPercentage);
        return jsonObject;
    }

    //TODO make this after toJSON finished
    public static ScanResult ofJSON(JSONObject jsonObject) {
        String fullName = jsonObject.getString("fullName");
        double resADPercentage = jsonObject.getDouble("resADPercentage");
        ScanResult scanResult = new ScanResult(fullName, resADPercentage);
        return scanResult;
    }

    @Override
    public String toString() {
        return "ScanResult{" +
                "fullName='" + fullName + '\'' +
                ", resADPercentage=" + resADPercentage +
                '}';
    }
}
