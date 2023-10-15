package com.example;

import org.springframework.stereotype.Component;


@Component
public class BackgroundProc implements Runnable {
    private volatile boolean running = false;
    Reader reader = new Reader();
    Writer writer = new Writer();


    public void start() {
        running = true;
        new Thread(this).start();
    }

    public void stop() {
        running = false;
    }

    @Override
    public void run() {
        while (running) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            int current = reader.readConfig().getColorNumber();
            if (current == 2) {
                writer.updateConfigFile(true, 0);
            } else {
                writer.updateConfigFile(true, ++current);
            }
        }

    }
}

