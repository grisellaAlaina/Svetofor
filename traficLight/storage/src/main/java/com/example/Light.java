package com.example;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


@Component
public class Light {


    private List<String> colors = new ArrayList<>(Arrays.asList("карсный", "желтый", "зеленый"));

    private boolean auto;
    private int colorNumber;
    public Light() {

    }

    public Light(boolean auto, int colorNumber) {
        this.auto = auto;
        this.colorNumber = colorNumber;
    }

    public void setColorNumber(int colorNumber) {
        this.colorNumber = colorNumber;
    }

    public int getColorNumber() {
        return colorNumber;
    }

    public boolean isAuto() {
        return auto;
    }

    public void setAuto(boolean auto) {
        this.auto = auto;
    }

    public String getColor() {
        return colors.get(colorNumber);
    }

    public void setColor(int colorNumber) {
        this.colorNumber = colorNumber;
    }

    @Override
    public String toString() {
        return "Light{" +
                "colors=" + colors +
                ", auto=" + auto +
                '}';
    }
}
