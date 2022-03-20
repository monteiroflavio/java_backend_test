package controller;

import com.sarabada.core.FileManipulator;
import com.sarabada.core.Generator;
import com.sarabada.exceptions.GameNotExistsException;
import com.sarabada.exceptions.WrongFileTypeException;
import com.sarabada.responses.GameResponse;
import com.sarabada.responses.LogGamesResponse;
import com.sarabada.responses.LogResponse;
import com.sarabada.responses.LogsResponse;
import com.sarabada.service.WebServerService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.io.IOException;
import java.util.Arrays;
import java.util.Map;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = com.sarabada.App.class)
@AutoConfigureMockMvc
public class WebServerControllerTester {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private WebServerService wss;

    @Test
    public void getLogsShouldReturnArray() throws Exception{
        when(wss.getLogs()).thenReturn(new LogsResponse(
                Arrays.asList("games.log")));
        this.mockMvc.perform(get("/logs"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.logs", hasSize(1)))
                .andExpect(jsonPath("$.logs[0]", is("games.log")))
                .andExpect(content().contentType(MediaType
                        .APPLICATION_JSON_UTF8));
        verify(wss, times(1)).getLogs();
        verifyNoMoreInteractions(wss);
    }

    @Test
    public void getLogsFileShouldReturnLog() throws Exception{
        LogResponse response = Generator.getInstance().generateLog(
                FileManipulator.getInstance().readGameLog("games.log"));
        when(wss.getLog("games.log")).thenReturn(response);
        this.mockMvc.perform(get("/logs/games.log"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.log.game_9.totalKills", is(67)));
        verify(wss, times(1)).getLog("games.log");
        verifyNoMoreInteractions(wss);
    }

    @Test
    public void getLogsFileShouldIOException() throws Exception{

        given(wss.getLog("ames.log")).willThrow(new IOException());

        this.mockMvc.perform(get("/logs/ames.log"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.type", is("error")))
                .andExpect(jsonPath("$.message"
                        , is("Some I/O error occurred.")));
        verify(wss, times(1)).getLog("ames.log");
        verifyNoMoreInteractions(wss);
    }

    @Test
    public void getLogsFileShouldWrongFileTypeException() throws Exception{

        given(wss.getLog("test.txt")).willThrow(new WrongFileTypeException(".log"
                , ".txt"));

        this.mockMvc.perform(get("/logs/test.txt"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.type", is("error")))
                .andExpect(jsonPath("$.message"
                        , is("File requested has unappropriated type.")));
        verify(wss, times(1)).getLog("test.txt");
        verifyNoMoreInteractions(wss);
    }

    @Test
    public void getLogsFileGamesShouldReturnArray() throws Exception{
        Map<String, GameResponse> test = Generator.getInstance().generateLog(
                FileManipulator.getInstance().readGameLog("games.log")).getLog();
        LogGamesResponse response = new LogGamesResponse(test.keySet());
        when(wss.getLogGames("games.log")).thenReturn(response);
        this.mockMvc.perform(get("/logs/games.log/games"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.games", hasSize(21)));
        verify(wss, times(1)).getLogGames("games.log");
        verifyNoMoreInteractions(wss);
    }

    public void getLogsFileGamesShouldIOException() throws Exception{

        given(wss.getLogGames("ames.log")).willThrow(new IOException());

        this.mockMvc.perform(get("/logs/ames.log/games"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.type", is("error")))
                .andExpect(jsonPath("$.message"
                        , is("Some I/O error occurred.")));
        verify(wss, times(1)).getLogGames("ames.log");
        verifyNoMoreInteractions(wss);
    }

    @Test
    public void getLogsFileGamesShouldWrongFileTypeException()
            throws Exception{

        given(wss.getLogGames("test.txt")).willThrow(
                new WrongFileTypeException(".log", ".txt"));

        this.mockMvc.perform(get("/logs/test.txt/games"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.type", is("error")))
                .andExpect(jsonPath("$.message"
                        , is("File requested has unappropriated type.")));
        verify(wss, times(1)).getLogGames("test.txt");
        verifyNoMoreInteractions(wss);
    }

    @Test
    public void getLogsFileGamesGameShouldReturnGame() throws Exception{
        GameResponse result = Generator.getInstance().generateLog(
                FileManipulator.getInstance().readGameLog("games.log"))
                .getLog().get("game_9");
        when(wss.getLogGame("games.log", "game_9")).thenReturn(result);
        this.mockMvc.perform(get("/logs/games.log/games/game_9"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.totalKills", is(67)));
        verify(wss, times(1)).getLogGame("games.log", "game_9");
        verifyNoMoreInteractions(wss);
    }

    public void getLogsFileGamesGameShouldIOException() throws Exception{

        given(wss.getLogGame("ames.log", "game_9"))
                .willThrow(new IOException());

        this.mockMvc.perform(get("/logs/ames.log/games/game_9"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.type", is("error")))
                .andExpect(jsonPath("$.message"
                        , is("Some I/O error occurred.")));
        verify(wss, times(1))
                .getLogGame("ames.log", "game_9");
        verifyNoMoreInteractions(wss);
    }

    @Test
    public void getLogsFileGamesGameShouldWrongFileTypeException()
            throws Exception {

        given(wss.getLogGame("test.txt", "game_9")).willThrow(
                new WrongFileTypeException(".log", ".txt"));

        this.mockMvc.perform(get("/logs/test.txt/games/game_9"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.type", is("error")))
                .andExpect(jsonPath("$.message"
                        , is("File requested has unappropriated type.")));
        verify(wss, times(1))
                .getLogGame("test.txt", "game_9");
        verifyNoMoreInteractions(wss);
    }

    @Test
    public void getLogsFileGamesGameShouldGameNotExistsException()
        throws Exception {
        Map<String, GameResponse> games = Generator.getInstance()
                .generateLog(FileManipulator.getInstance()
                .readGameLog("games.log")).getLog();

        given(wss.getLogGame("games.log", "game_22")).willThrow(
                new GameNotExistsException("game_22", games.keySet()));

        this.mockMvc.perform(get("/logs/games.log/games/game_22"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.type", is("error")))
                .andExpect(jsonPath("$.message"
                        , is("Game required not found on the log.")));
        verify(wss, times(1))
                .getLogGame("games.log", "game_22");
        verifyNoMoreInteractions(wss);
    }
}