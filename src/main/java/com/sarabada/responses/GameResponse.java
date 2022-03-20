package com.sarabada.responses;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class GameResponse extends Response{
    private int totalKills;
    private List<String> players = new ArrayList<String>();
    private Map<String, Integer> kills = new HashMap<String, Integer>();

    public GameResponse() {
        super("normal");
    }

    public int getTotalKills() {
        return totalKills;
    }

    public void setTotalKills(int totalKills) {
        this.totalKills = totalKills;
    }

    public List getPlayers() {
        return players;
    }

    public void setPlayers(List players) {
        this.players = players;
    }

    public Map<String, Integer> getKills() {
        return kills;
    }

    public void setKills(Map<String, Integer> kills) {
        this.kills = kills;
    }
}
