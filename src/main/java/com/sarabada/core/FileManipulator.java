package com.sarabada.core;

import com.sarabada.exceptions.WrongFileTypeException;
import org.springframework.stereotype.Service;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@Service
public class FileManipulator {
    private static FileManipulator ourInstance = new FileManipulator();
    private File[] files = new File(this.getClass().getClassLoader()
            .getResource("logs")
            .getPath())
            .listFiles();

    public static FileManipulator getInstance() {
        return ourInstance;
    }

    private FileManipulator() {}

    public List<String> readGameLog (String path)
            throws IOException, WrongFileTypeException {
        for (File file : files) {
            if (file.getName().equals(path)) {
                if (file.getName().matches(".+\\.log"))
                    return Files.readAllLines(Paths.get(file.getPath()));
                else
                    throw new WrongFileTypeException(".log",
                            "."+file.getName().replaceAll(".+\\.", ""));
            }
        }
        throw new FileNotFoundException();
    }

    public List<String> readLogsFolder () {

        List<String> fileNames = new ArrayList<String>();
        for (File file : this.files)
            if (file.isFile() && file.getName().matches(".+\\.log"))
                fileNames.add(file.getName());
        return fileNames;

    }
}
