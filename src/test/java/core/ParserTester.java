package core;

import com.sarabada.core.FileManipulator;
import com.sarabada.core.Parser;
import com.sarabada.entities.ClientUserInfo;
import com.sarabada.exceptions.WrongFileTypeException;
import org.junit.Test;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class ParserTester {

    private Parser parser = Parser.getInstance();
    private FileManipulator fileManipulator = FileManipulator.getInstance();

    @Test
    public void parseGamesShouldReturnAnArray () {
        try {
            List<String> logArray = FileManipulator.getInstance()
                    .readGameLog("games.log");
            assertThat(parser.parseGames(logArray), hasSize(21));
        } catch (IOException e) {
            e.printStackTrace();
        } catch (WrongFileTypeException wrongFileTypeException) {
            wrongFileTypeException.printStackTrace();
        }
    }

    @Test
    public void parseClientIdsShouldReturnAnArray () {
        try {
            List<Integer> expected = Arrays.asList(2, 3);
            List<String> logArray = FileManipulator.getInstance()
                    .readGameLog("games.log");
            List<String> gameTwo = parser.parseGames(logArray).get(1);
            List<Integer> actual = parser.parseClientIds(gameTwo);
            assertThat(actual, is(expected));
            assertThat(actual, hasSize(2));
        } catch (IOException e) {
            e.printStackTrace();
        } catch (WrongFileTypeException wrongFileTypeException) {
            wrongFileTypeException.printStackTrace();
        }
    }

    @Test
    public void parseClientUserInfoShouldReturnExpectedClientUserInfo () {
        String line = "21:17 ClientUserinfoChanged: 2 n\\Isgalamido\\" +
                "t\\0\\model\\uriel/zael\\hmodel\\uriel/zael\\g_redteam\\\\" +
                "g_blueteam\\\\c1\\5\\c2\\5\\hc\\100\\w\\0\\l\\0\\tt\\0\\tl\\0";
        ClientUserInfo result = parser.parseClientUserInfo(
                line, 2, new ClientUserInfo());
        assertThat(result.getN(), is("Isgalamido"));
        assertThat(result.getT(), is(0));
        assertThat(result.getModel(), is("uriel/zael"));
        assertThat(result.gethModel(), is("uriel/zael"));
        assertThat(result.getgRedTeam(), is(""));
        assertThat(result.getgBlueTeam(), is(""));
        assertThat(result.getC1(), is(5));
        assertThat(result.getC2(), is(5));
        assertThat(result.getHc(), is(100));
        assertThat(result.getW(), is(0));
        assertThat(result.getL(), is(0));
        assertThat(result.getTt(), is(0));
        assertThat(result.getTl(), is(0));
    }

    @Test
    public void parseClientUserInfoShouldReturnEmptyClientUserInfo () {
        String line = "21:17 ClientUserinfoChanged: 2 n\\Isgalamido\\" +
                "t\\0\\model\\uriel/zael\\hmodel\\uriel/zael\\g_redteam\\\\" +
                "g_blueteam\\\\c1\\5\\c2\\5\\hc\\100\\w\\0\\l\\0\\tt\\0\\tl\\0";
        ClientUserInfo expected = new ClientUserInfo();
        ClientUserInfo result = parser.parseClientUserInfo(line, 3, expected);
        assertThat(result, is(expected));
    }

    @Test
    public void parseClientKillShouldUpdateKillInfo () {
        String line = "22:06 Kill: 2 3 7: Isgalamido killed Mocinha by " +
                "MOD_ROCKET_SPLASH";
        ClientUserInfo result = parser.parseClientKills(line, 2
                , new ClientUserInfo());
        assertThat(result.getKills(), is(1));
        assertThat(result.getWorldDeaths(), is(0));
    }

    @Test
    public void parseClientKillShouldNotUpdateKillInfo () {
        String line = "22:06 Kill: 2 3 7: Isgalamido killed Mocinha by " +
                "MOD_ROCKET_SPLASH";
        ClientUserInfo expected = new ClientUserInfo();
        ClientUserInfo result = parser.parseClientKills(line, 3, expected);
        assertThat(result, is(expected));
    }

    @Test
    public void parseClientKillShouldUpdateWorldDeathInfo () {
        String line = "21:07 Kill: 1022 2 22: <world> killed Isgalamido by " +
                "MOD_TRIGGER_HURT";
        ClientUserInfo result = parser.parseClientKills(line, 2
                , new ClientUserInfo());
        assertThat(result.getKills(), is(0));
        assertThat(result.getWorldDeaths(), is(1));
    }

    @Test
    public void parseClientKillShouldNotUpdateWorldDeathInfo () {
        String line = "21:07 Kill: 1022 2 22: <world> killed Isgalamido by " +
                "MOD_TRIGGER_HURT";
        ClientUserInfo expected = new ClientUserInfo();
        ClientUserInfo result = parser.parseClientKills(line, 3, expected);
        assertThat(result, is(expected));
    }

    @Test
    public void parseClientActivitiesShouldReturnAnArray () {
        try {
            List<String> logArray = FileManipulator.getInstance()
                    .readGameLog("games.log");
            List<String> gameTwo = parser.parseGames(logArray).get(1);
            List<ClientUserInfo> result = parser.parseClientActivities(gameTwo, 2);

            assertThat(result.get(0).getKills(), is(0));
            assertThat(result.get(0).getWorldDeaths(), is(2));
            assertThat(result.get(0).getN(), is("Isgalamido"));
            assertThat(result.get(0).getT(), is(0));
            assertThat(result.get(0).getModel(), is("uriel/zael"));
            assertThat(result.get(0).gethModel(), is("uriel/zael"));
            assertThat(result.get(0).getgRedTeam(), is(""));
            assertThat(result.get(0).getgBlueTeam(), is(""));
            assertThat(result.get(0).getC1(), is(5));
            assertThat(result.get(0).getC2(), is(5));
            assertThat(result.get(0).getHc(), is(100));
            assertThat(result.get(0).getW(), is(0));
            assertThat(result.get(0).getL(), is(0));
            assertThat(result.get(0).getTt(), is(0));
            assertThat(result.get(0).getTl(), is(0));

            assertThat(result.get(1).getKills(), is(3));
            assertThat(result.get(1).getWorldDeaths(), is(6));
            assertThat(result.get(1).getN(), is("Isgalamido"));
            assertThat(result.get(1).getT(), is(0));
            assertThat(result.get(1).getModel(), is("uriel/zael"));
            assertThat(result.get(1).gethModel(), is("uriel/zael"));
            assertThat(result.get(1).getgRedTeam(), is(""));
            assertThat(result.get(1).getgBlueTeam(), is(""));
            assertThat(result.get(1).getC1(), is(5));
            assertThat(result.get(1).getC2(), is(5));
            assertThat(result.get(1).getHc(), is(100));
            assertThat(result.get(1).getW(), is(0));
            assertThat(result.get(1).getL(), is(0));
            assertThat(result.get(1).getTt(), is(0));
            assertThat(result.get(1).getTl(), is(0));
        } catch (IOException e) {
            e.printStackTrace();
        } catch (WrongFileTypeException wrongFileTypeException) {
            wrongFileTypeException.printStackTrace();
        }
    }
}