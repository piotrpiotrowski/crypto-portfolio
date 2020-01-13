package com.crypto.portfolio.distribution.file;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import com.crypto.portfolio.distribution.exception.ApplicationException;

public class FileReader {

    public String readAsString(String pathAsString) {
        try {
            return Files.readString(Path.of(pathAsString));
        } catch (IOException e) {
            throw new ApplicationException("Cannot read file with path " + pathAsString, e);
        }
    }
}
