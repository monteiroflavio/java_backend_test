package com.sarabada.controller;

import com.sarabada.exceptions.GameNotExistsException;
import com.sarabada.exceptions.WrongFileTypeException;
import com.sarabada.responses.ErrorResponse;
import com.sarabada.responses.LogsResponse;
import com.sarabada.responses.Response;
import com.sarabada.service.WebServerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/logs")
public class WebServerController {

    @Autowired
    private WebServerService webServerService;

    @RequestMapping(method = RequestMethod.GET)
    public LogsResponse readLogsFolder () {
        return webServerService.getLogs();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{logPath}"
            , produces = {MediaType.APPLICATION_JSON_VALUE})
    public Response getLog (@PathVariable String logPath){
        try {
            return webServerService.getLog(logPath);
        } catch (IOException e) {
            return new ErrorResponse("Some I/O error occurred.");
        } catch (WrongFileTypeException wrongFileTypeException) {
            return new ErrorResponse("File requested has unappropriated type.");
        }
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{logPath}/games"
            , produces = {MediaType.APPLICATION_JSON_VALUE})
    public Response getLogGames (@PathVariable String logPath){
        try {
            return webServerService.getLogGames(logPath);
        } catch (IOException e) {
            return new ErrorResponse("Some I/O error occurred.");
        } catch (WrongFileTypeException wrongFileTypeException) {
            return new ErrorResponse("File requested has unappropriated type.");
        }
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{logPath}/games/{game}"
            , produces = {MediaType.APPLICATION_JSON_VALUE})
    public Response getLogGame (@PathVariable String logPath,
                                @PathVariable String game){
        try {
            return webServerService.getLogGame(logPath, game);
        } catch (IOException e) {
            return new ErrorResponse("Some I/O error occurred.");
        } catch (WrongFileTypeException wrongFileTypeException) {
            return new ErrorResponse("File requested has unappropriated type.");
        } catch (GameNotExistsException e) {
            return new ErrorResponse("Game required not found on the log.");
        }
    }
}