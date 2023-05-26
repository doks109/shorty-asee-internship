package com.welcome;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.io.*;
import java.io.IOException;

public class WelcomeTest {
    @Test
    public void welcomeTestExit() throws IOException {
        String userInput = "0\n";
        InputStream inputStream = new ByteArrayInputStream(userInput.getBytes());
        System.setIn(inputStream);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(outputStream);
        System.setOut(printStream);

        Welcome.main(new String[]{});

        System.setIn(System.in);
        System.setOut(System.out);

        String expectedOutput = "Welcome to Shorty - your link shortening tool!\n\n" +
                "Please choose an option:\n" +
                "1. View existing shortened links\n" +
                "2. Enter a new link\n" +
                "3. Delete an existing link\n" +
                "0. Exit\n";

        String normalizedExpectedOutput = expectedOutput.replaceAll("\r\n", "\n");
        String normalizedActualOutput = outputStream.toString().replaceAll("\r\n", "\n");
        Assertions.assertEquals(normalizedExpectedOutput, normalizedActualOutput.toString());
    }
}
