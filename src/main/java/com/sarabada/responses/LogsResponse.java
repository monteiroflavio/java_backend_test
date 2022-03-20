package com.sarabada.responses;

import java.util.ArrayList;
import java.util.List;

public class LogsResponse extends Response {

    private List<String> logs = new ArrayList<String>();

    public LogsResponse(List<String> logs) {
        super("normal");
        this.logs = logs;
    }

    public List<String> getLogs() {
        return logs;
    }

    public void setLogs(List<String> logs) {
        this.logs = logs;
    }
}
