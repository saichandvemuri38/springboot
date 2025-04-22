package com.example.batminton_backend.controller;

import com.example.batminton_backend.entity.Batminton;
import com.example.batminton_backend.service.BatmintonInterface;
import com.example.batminton_backend.model.SaveBatmintonPlayers;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/batminton")
@Log4j2
public class BatmintonController {

    private final BatmintonInterface batmintonInterface;

    @Autowired
    public BatmintonController(BatmintonInterface batmintonInterface) {
        this.batmintonInterface = batmintonInterface;
    }

    @PostMapping("/save")
    public ResponseEntity<Batminton> saveBatmintonPlayers(@RequestBody SaveBatmintonPlayers saveBatmintonPlayers) {
        return new ResponseEntity<>(batmintonInterface.saveBatmintonPlayers(saveBatmintonPlayers), HttpStatus.OK);
    }
}
