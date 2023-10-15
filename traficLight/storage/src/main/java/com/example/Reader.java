package com.example;


import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
@Component
public class Reader {
    public Light readConfig() {
        Light light = new Light();
        BufferedReader reader = null;
        try {
            ClassPathResource classPathResource = new ClassPathResource("currentState");
            File file = classPathResource.getFile();
            String filePath = file.getAbsolutePath();
            reader = new BufferedReader(new FileReader(filePath));
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.contains("color=")) {
                    String colorValue = line.replace("color=", "");
                    try {
                        int colorInt = Integer.parseInt(colorValue);
                        light.setColor(colorInt);
                    } catch (NumberFormatException nfe) {
                        System.out.println("Could not parse " + colorValue + " as int, skipping this setting...");
                    }
                } else if (line.contains("auto=")) {
                    line = line.replace("auto=", "");
                    if (line.contains("true")) light.setAuto(true);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (reader != null) {
                    reader.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return light;
    }
}