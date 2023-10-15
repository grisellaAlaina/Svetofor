package com.example;

import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

@Component
public class Writer {
    public Light updateConfigFile(boolean autoUpdate, Integer colorNumber) {
        try {
            ClassPathResource classPathResource = new ClassPathResource("currentState");
            File file = classPathResource.getFile();
            String filePath = file.getAbsolutePath();
            FileWriter fileWriter = new FileWriter(filePath);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.write("color=" + colorNumber + "\n");
            bufferedWriter.write("auto=" + autoUpdate);
            bufferedWriter.close();
        } catch (IOException e) {
            System.out.println("Ошибка при обновлении файла: " + e.getMessage());
        }
        return new Light(autoUpdate, colorNumber);
    }
}
