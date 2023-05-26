package com.welcome;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.io.*;
import java.io.IOException;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.InputMismatchException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class WelcomeExceptionTest {
    @Test
    public void welcomeExceptionTest(){
        String userInput = "a\n";
        InputStream inputStream = new ByteArrayInputStream(userInput.getBytes());
        System.setIn(inputStream);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(outputStream);
        System.setOut(printStream);

        assertThrows(InputMismatchException.class, () -> Welcome.main(new String[]{}));

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


