package com.welcome;
import java.util.Scanner;

public class Welcome {
    public static void main(String[] args) {
        Scanner myObj = new Scanner(System.in);
        int userInput;
        do {
            System.out.println("Welcome to Shorty - your link shortening tool!\n\nPlease choose an option:");
            System.out.println("1. View existing shortened links");
            System.out.println("2. Enter a new link");
            System.out.println("3. Delete an existing link");
            System.out.println("0. Exit");
            userInput = myObj.nextInt();
        } while(userInput != 0);

    }
}
