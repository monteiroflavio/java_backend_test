package com.sarabada.exceptions;

import java.util.Set;

public class GameNotExistsException extends Exception {
    public GameNotExistsException(String game, Set<String> logGames) {
        super("Game "+game+ " does not exist on" +
                "required log. Games found: "+logGames);
    }
}
