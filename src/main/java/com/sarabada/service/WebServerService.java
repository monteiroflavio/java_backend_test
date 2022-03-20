package com.sarabada.service;

import com.sarabada.core.FileManipulator;
import com.sarabada.core.Generator;
import com.sarabada.exceptions.GameNotExistsException;
import com.sarabada.exceptions.WrongFileTypeException;
import com.sarabada.responses.GameResponse;
import com.sarabada.responses.LogGamesResponse;
import com.sarabada.responses.LogResponse;
import com.sarabada.responses.LogsResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Map;

@Service
public class WebServerService {
    @Autowired
    private Generator generator;
    @Autowired
    private FileManipulator fileManipulator;

    public LogsResponse getLogs() {
        return new LogsResponse(fileManipulator.readLogsFolder());
    }

    public LogResponse getLog (String logPath)
            throws IOException, WrongFileTypeException {
        return generator.generateLog(fileManipulator.readGameLog(logPath));
    }

    public LogGamesResponse getLogGames (String logPath)
            throws IOException, WrongFileTypeException {
        return new LogGamesResponse(generator.generateLog(fileManipulator
                .readGameLog(logPath)).getLog().keySet());
    }

    public GameResponse getLogGame (String logPath, String game)
            throws IOException, WrongFileTypeException, GameNotExistsException {
        Map<String, GameResponse> games = generator.generateLog(fileManipulator
                .readGameLog(logPath)).getLog();
        if (!games.containsKey(game))
            throw new GameNotExistsException(game, games.keySet());
        return generator.generateLog(fileManipulator
                .readGameLog(logPath)).getLog().get(game);
    }
}
