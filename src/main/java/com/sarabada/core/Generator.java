package com.sarabada.core;

import com.sarabada.entities.ClientUserInfo;
import com.sarabada.responses.GameResponse;
import com.sarabada.responses.LogResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class Generator {
    private static Generator ourInstance = new Generator();

    public static Generator getInstance() {
        return ourInstance;
    }

    private Generator() { }

    public GameResponse generateGame(List<String> gameArray) {
        List<Integer> clientIds =
                Parser.getInstance().parseClientIds(gameArray);
        GameResponse gameResponse = new GameResponse();
        for (Integer clientId : clientIds) {
            List<ClientUserInfo> clientConnections =
                    Parser.getInstance()
                            .parseClientActivities(gameArray, clientId);
            for (ClientUserInfo clientUserInfo : clientConnections) {
                if (!gameResponse.getPlayers().contains(clientUserInfo.getN())) {
                    gameResponse.getPlayers().add(clientUserInfo.getN());
                    gameResponse.getKills().put(clientUserInfo.getN()
                            , (clientUserInfo.getKills()
                                    - clientUserInfo.getWorldDeaths()));
                }
                else
                    gameResponse.getKills().replace(clientUserInfo.getN(), (
                            gameResponse.getKills().get(clientUserInfo.getN())
                            + clientUserInfo.getKills()
                            - clientUserInfo.getWorldDeaths()));
                gameResponse.setTotalKills(gameResponse.getTotalKills()
                        + clientUserInfo.getKills()
                        + clientUserInfo.getWorldDeaths());
            }
        }
        return gameResponse;
    }

    public LogResponse generateLog(List<String> logArray) {
        List<List<String>> games = Parser.getInstance().parseGames(logArray);
        System.out.println(games.size());
        LogResponse logResponse = new LogResponse();
        for (int i = 0; i < games.size(); i++) {
            logResponse.getLog().put("game_"+(i+1),
                    this.generateGame(games.get(i)));
        }
        return logResponse;
    }
}
