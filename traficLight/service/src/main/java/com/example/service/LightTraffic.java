package com.example.service;

import com.example.BackgroundProc;
import com.example.Light;
import com.example.Reader;
import com.example.Writer;
import com.example.exeption.BackgroundProcessException;
import com.example.exeption.ConfigReadException;
import com.example.exeption.ConfigUpdateException;
import com.example.exeption.ErrorCode;
import org.springframework.stereotype.Component;

@Component
public class LightTraffic {
    Reader reader;
    Writer writer;
    BackgroundProc backgroundProcess;

    public LightTraffic(Reader reader, Writer writer, BackgroundProc backgroundProcess) {
        this.reader = reader;
        this.writer = writer;
        this.backgroundProcess = backgroundProcess;
    }

    public Light getCurrent() {
        try {
            return reader.readConfig();
        } catch (Exception e) {
            throw new ConfigReadException(ErrorCode.CONFIG_READ_ERROR);
        }
    }

    public Light change() {
        try {
            int current = reader.readConfig().getColorNumber();
            if (current == 2) {
                return writer.updateConfigFile(false, 0);
            }
            return writer.updateConfigFile(false, ++current);
        } catch (Exception e) {
            throw new ConfigUpdateException(ErrorCode.CONFIG_UPDATE_ERROR);
        }
    }

    public void changeMode() {
        try {
            if (reader.readConfig().isAuto()) {
                backgroundProcess.stop();
                writer.updateConfigFile(false, 0);
            } else {
                writer.updateConfigFile(true, 0);
                backgroundProcess.start();
            }
        } catch (Exception e) {
            throw new BackgroundProcessException(ErrorCode.BACKGROUND_PROCESS_ERROR);
        }
    }
}



