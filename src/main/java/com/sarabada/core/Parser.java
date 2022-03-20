package com.sarabada.core;

import com.sarabada.entities.ClientUserInfo;

import java.util.ArrayList;
import java.util.List;

public class Parser {
    private static Parser ourInstance = new Parser();

    public static Parser getInstance() {
        return ourInstance;
    }

    private Parser() { }

    public List<List<String>> parseGames (List<String> logArray) {
        List<List<String>> gamesArray = new ArrayList<List<String>>();
        List<String> currentGame = new ArrayList<String>();
        for (int i = 0; i < logArray.size(); i++) {
            if (!logArray.get(i).matches(
                    "^(.*)(\\d*\\d:\\d\\d)(\\s-+)(\\s*)$")) {
                currentGame.add(logArray.get(i));
                if(logArray.get(i).matches(
                        "^(.*)(\\d*\\d:\\d\\d\\sShutdownGame:)(.*)$")
                        || (logArray.size() > (i+1)
                        && logArray.get(i+1).matches(
                        "^(.*)(\\d*\\d:\\d\\d)(\\s-+)(\\s*)$"))) {
                    gamesArray.add(currentGame);
                    currentGame = new ArrayList<String>();
                }
            }
        }
        return gamesArray;
    }

    public List<Integer> parseClientIds (List<String> gameArray) {
        List<Integer> clientIds = new ArrayList<Integer>();
        for (String line : gameArray) {
            if (line.matches(
                    "^(.*)(\\d*\\d:\\d\\d\\sClientConnect:\\s\\d+)(.*)$")) {
                int clientId = Integer.parseInt(
                        line.split(
                                "(.*)(\\d*\\d:\\d\\d\\sClientConnect:\\s)")[1]);
                if (!clientIds.contains(clientId))
                    clientIds.add(clientId);
            }
        }
        return clientIds;
    }

    public ClientUserInfo parseClientUserInfo (String gameInfoLine, int clientId,
                                               ClientUserInfo clientUserInfo) {
        if (gameInfoLine.matches(
                "^(.*)(\\d*\\d:\\d\\d\\sClientUserinfoChanged:\\s"
                +clientId+"\\s)(.*)")) {
            String[] clientUserInfoArray = gameInfoLine.replaceAll(
                    "\\d+\\d:\\d\\d\\sClientUserinfoChanged:\\s"
                            + clientId + "\\s", "").trim().split("\\\\");
            clientUserInfo.setN(clientUserInfoArray[1]);
            clientUserInfo.setT(Integer.parseInt(clientUserInfoArray[3]));
            clientUserInfo.setModel(clientUserInfoArray[5]);
            clientUserInfo.sethModel(clientUserInfoArray[7]);
            clientUserInfo.setgRedTeam(clientUserInfoArray[9]);
            clientUserInfo.setgBlueTeam(clientUserInfoArray[11]);
            clientUserInfo.setC1(Integer.parseInt(clientUserInfoArray[13]));
            clientUserInfo.setC2(Integer.parseInt(clientUserInfoArray[15]));
            clientUserInfo.setHc(Integer.parseInt(clientUserInfoArray[17]));
            clientUserInfo.setW(Integer.parseInt(clientUserInfoArray[19]));
            clientUserInfo.setL(Integer.parseInt(clientUserInfoArray[21]));
            clientUserInfo.setTt(Integer.parseInt(clientUserInfoArray[23]));
            clientUserInfo.setTl(Integer.parseInt(clientUserInfoArray[25]));
        }
        return clientUserInfo;
    }

    public ClientUserInfo parseClientKills (String gameInfoLine, int clientId,
                                               ClientUserInfo clientUserInfo) {
        if (gameInfoLine.matches("^(.*)(\\d*\\d:\\d\\d\\sKill:\\s"
                +clientId+")(.*)$"))
            clientUserInfo.setKills(clientUserInfo.getKills()+1);
        else if (gameInfoLine.matches("^(.*)(\\d*\\d:\\d\\d\\sKill:\\s1022\\s"
                +clientId+")(.*)$"))
            clientUserInfo.setWorldDeaths(clientUserInfo.getWorldDeaths()+1);
        return clientUserInfo;
    }

    public List<ClientUserInfo> parseClientActivities (List<String> gameArray,
                                                       int clientId) {
        List<ClientUserInfo> clientConnections =
                new ArrayList<ClientUserInfo>();
        for (int i = 0; i < gameArray.size(); i++) {
            int j = i;
            ClientUserInfo clientUserInfo = new ClientUserInfo();
            while (j < gameArray.size()) {
                if(gameArray.get(j).matches(
                        "^(.*)(\\d*\\d:\\d\\d\\sClientDisconnect:\\s"+
                                clientId+")(.*)$"))
                    break;
                this.parseClientUserInfo(gameArray.get(j), clientId
                        , clientUserInfo);
                this.parseClientKills(gameArray.get(j), clientId
                        , clientUserInfo);
                j++;
            }
            i = j;
            if (!clientUserInfo.equals(new ClientUserInfo()))
                clientConnections.add(clientUserInfo);
        }
        return clientConnections;
    }
}