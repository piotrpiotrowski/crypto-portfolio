package com.crypto.portfolio.distribution.file;

import java.io.File;

import com.crypto.portfolio.distribution.exception.ApplicationException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class FileReaderTest {

    FileReader fileReader = new FileReader();

    @Test
    public void shouldBeAbleToReadFileByRelativePath() {
        //given
        File file = new File("src/test/resources/testFile.txt");

        //when
        String fileContent = fileReader.readAsString(file.getAbsolutePath());

        //then
        assertEquals("test-file-content", fileContent);
    }

    @Test
    public void shouldThrowApplicationExceptionWhenFileIsNotReachable() {
        //expect
        ApplicationException applicationException = assertThrows(ApplicationException.class, () -> fileReader.readAsString("Not-existing-file"));
        assertEquals("Cannot read file with path Not-existing-file", applicationException.getMessage());
    }
}