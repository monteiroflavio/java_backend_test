package core;

import com.sarabada.core.FileManipulator;
import com.sarabada.exceptions.WrongFileTypeException;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

public class FileManipulatorTester {

    private FileManipulator fileManipulator = FileManipulator.getInstance();

    @Test
    public void readGameLogShouldReturnAnArray () {
        try {
            assertThat(fileManipulator.readGameLog("games.log")
                    , hasSize(5306));
        } catch (IOException e) {
            e.printStackTrace();
        } catch (WrongFileTypeException wrongFileTypeException) {
            wrongFileTypeException.printStackTrace();
        }
    }

    @Test
    public void readGameLogShouldReturnIOException () {
        try {
            fileManipulator.readGameLog("ames.log");
        } catch (IOException e) {
            assertThat(e, is(instanceOf(IOException.class)));
        } catch (WrongFileTypeException wrongFileTypeException) {
            wrongFileTypeException.printStackTrace();
        }
    }

    @Test
    public void readGameLogShouldReturnWrongFileTypeException () {
        try {
            fileManipulator.readGameLog("test.txt");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (WrongFileTypeException wrongFileTypeException) {
            assertThat(wrongFileTypeException.getMessage()
                    , is("File type not expected. " +
                            "Required: .log, found: .txt."));
        }
    }

    @Test
    public void readLogsFolderShouldReturnAnArray () {
        List<String> result = fileManipulator.readLogsFolder();
        assertThat(result, hasSize(1));
        assertEquals("games.log", result.get(0));
    }
}
