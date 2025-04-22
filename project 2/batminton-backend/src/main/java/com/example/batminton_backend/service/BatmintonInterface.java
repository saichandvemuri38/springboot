package com.example.batminton_backend.service;


import com.example.batminton_backend.entity.Batminton;
import com.example.batminton_backend.model.SaveBatmintonPlayers;
import org.springframework.stereotype.Component;

public interface BatmintonInterface {
    Batminton saveBatmintonPlayers(SaveBatmintonPlayers saveBatmintonPlayers);
}
