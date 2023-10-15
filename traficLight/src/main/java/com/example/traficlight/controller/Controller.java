package com.example.traficlight.controller;


import com.example.BackgroundProc;
import com.example.Light;
import com.example.Reader;
import com.example.Writer;
import com.example.service.LightTraffic;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {

    LightTraffic lightTraffic = new LightTraffic(new Reader(), new Writer(), new BackgroundProc());

    @GetMapping("/")
    public ResponseEntity<Light> FirstReturn() {
        return new ResponseEntity<>(lightTraffic.getCurrent(), HttpStatus.OK);
    }

    @GetMapping("/change")
    public ResponseEntity<Light> Changed() {
        return new ResponseEntity<>(lightTraffic.change(), HttpStatus.OK);
    }

    @GetMapping("/mode")
    public ResponseEntity<Light> autoMode() {
        lightTraffic.changeMode();
        return new ResponseEntity<>(lightTraffic.getCurrent(), HttpStatus.OK);
    }


}
