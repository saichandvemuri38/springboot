package com.example.batminton_backend.service;

import com.example.batminton_backend.entity.Batminton;
import com.example.batminton_backend.entity.TeamA;
import com.example.batminton_backend.entity.TeamB;
import com.example.batminton_backend.model.SaveBatmintonPlayers;
import com.example.batminton_backend.repository.BatmintonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class BatmintonService implements BatmintonInterface {

    @Autowired
    private BatmintonRepository batmintonRepository;

    @Override
    public Batminton saveBatmintonPlayers(SaveBatmintonPlayers saveBatmintonPlayers) {
        System.out.println(saveBatmintonPlayers.TeamB.player1);
        Batminton batminton = new Batminton();
        TeamA teamA = new TeamA();
        TeamB teamB = new TeamB();
        teamA.setPlayer1(saveBatmintonPlayers.TeamA.player1);
        teamA.setPlayer2(saveBatmintonPlayers.TeamA.player2);
        teamB.setPlayer1(saveBatmintonPlayers.TeamB.player1);
        teamB.setPlayer2(saveBatmintonPlayers.TeamB.player2);
        batminton.setTeamAplayers(teamA);
        batminton.setTeamBplayers(teamB);
        batmintonRepository.save(batminton);
        return batmintonRepository.save(batminton);
    }

}
