package com.sarabada.responses;

import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class LogResponse extends Response {
    private Map<String, GameResponse> log = new HashMap<String, GameResponse>();

    public LogResponse() {
        super("normal");
    }

    public Map<String, GameResponse> getLog() {
        return log;
    }

    public void setLog(Map<String, GameResponse> log) {
        this.log = log;
    }
}
