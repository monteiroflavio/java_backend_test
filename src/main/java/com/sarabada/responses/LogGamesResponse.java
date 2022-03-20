package com.sarabada.responses;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class LogGamesResponse extends Response {
    private List<String> games;

    public LogGamesResponse(Set<String> games) {
        super("normal");
        this.games = new ArrayList<String>();
        this.games.addAll(games);
    }

    public List<String> getGames() {
        return games;
    }

    public void setGames(List<String> games) {
        this.games = games;
    }
}
