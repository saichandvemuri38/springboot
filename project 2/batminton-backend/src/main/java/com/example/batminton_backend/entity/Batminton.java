package com.example.batminton_backend.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Batminton {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    public TeamA getTeamAplayers() {
        return teamAplayers;
    }

    public void setTeamAplayers(TeamA teamAplayers) {
        this.teamAplayers = teamAplayers;
    }

    public TeamB getTeamBplayers() {
        return teamBplayers;
    }

    public void setTeamBplayers(TeamB teamBplayers) {
        this.teamBplayers = teamBplayers;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @OneToOne
    @JoinColumn(name = "teamA")
    private TeamA teamAplayers;

    @OneToOne
    @JoinColumn(name = "teamB")
    private TeamB teamBplayers;
}
