package com.core;

import java.awt.*;

public class Gbc extends GridBagConstraints {


    public Gbc(int gridX, int gridY) {
        super.gridx = gridX;
        super.gridy = gridY;
    }

    public Gbc(int gridX, int gridY, int gridWidth, int gridHeight) {
        super.gridx = gridX;
        super.gridy = gridY;
        super.gridwidth = gridWidth;
        super.gridheight = gridHeight;
    }

    public static Gbc of(int gridX, int gridY) {
        return new Gbc(gridX, gridY);
    }

    public static Gbc of(int gridX, int gridY, int gridWidth, int gridHeight) {
        return new Gbc(gridX, gridY, gridWidth, gridHeight);
    }

    public static Gbc of(int gridX, int gridY, float weightX, float weightY) {
        return new Gbc(gridX, gridY).setWeights(weightX, weightY);
    }

    public static Gbc of(int gridX, int gridY, double weightX, double weightY, int anchor) {
        return new Gbc(gridX, gridY).setWeights(weightX, weightY).setAnchor(anchor);
    }

    public static Gbc of(int gridX, int gridY, double weightX, double weightY, int fill, int anchor) {
        return new Gbc(gridX, gridY).setWeights(weightX, weightY).setFill(fill).setAnchor(anchor);
    }

    public static Gbc of(int gridX, int gridY, double weightX, double weightY, int anchor, float insets) {
        return new Gbc(gridX, gridY).setWeights(weightX, weightY).setAnchor(anchor).setInsets((int) insets);
    }

    public static Gbc of(int gridX, int gridY, double weightX, double weightY, int anchor, int fill, float insets) {
        return new Gbc(gridX, gridY).setWeights(weightX, weightY)
                .setAnchor(anchor).setFill(fill).setInsets((int) insets);
    }

    public Gbc setAnchor(int anchor) {
        super.anchor = anchor;
        return this;
    }

    public Gbc setWeights(double weightX, double weightY) {
        super.weightx = weightX;
        super.weighty = weightY;
        return this;
    }

    public Gbc setFill(int fill) {
        super.fill = fill;
        return this;
    }

    public Gbc setInsets(int k) {
        this.insets = new Insets(k, k, k, k);
        return this;
    }

}
