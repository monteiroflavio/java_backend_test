package core;

import com.sarabada.core.FileManipulator;
import com.sarabada.core.Generator;
import com.sarabada.core.Parser;
import com.sarabada.exceptions.WrongFileTypeException;
import com.sarabada.responses.GameResponse;
import com.sarabada.responses.LogResponse;
import org.junit.Test;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

public class GeneratorTester {

    private Generator generator = Generator.getInstance();
    private Parser parser = Parser.getInstance();

    @Test
    public void generateGameShouldReturnGame () {
        try {
            List<String> logArray = FileManipulator.getInstance()
                    .readGameLog("games.log");
            List<String> gameTwo = parser.parseGames(logArray).get(1);

            List<String> players = Arrays.asList("Isgalamido", "Mocinha");
            Map<String, Integer> kills = new HashMap<String, Integer>();

            kills.put("Isgalamido", -5);
            kills.put("Mocinha", 0);

            GameResponse gameResponse = generator.generateGame(gameTwo);

            assertThat(gameResponse.getPlayers(), is(players));
            assertThat(gameResponse.getKills(), is(kills));
            assertThat(gameResponse.getTotalKills(), is(11));

        } catch (IOException e) {
            e.printStackTrace();
        } catch (WrongFileTypeException wrongFileTypeException) {
            wrongFileTypeException.printStackTrace();
        }
    }

    @Test
    public void generateLogShouldReturnLog () {
        try {
            List<String> logArray = FileManipulator.getInstance()
                    .readGameLog("games.log");
            LogResponse logResponse = generator.generateLog(logArray);

            for (String gameName : logResponse.getLog().keySet())
                assertTrue(gameName.matches("^(game_)(\\d+)$"));
        } catch (IOException e) {
            e.printStackTrace();
        } catch (WrongFileTypeException wrongFileTypeException) {
            wrongFileTypeException.printStackTrace();
        }
    }
}
