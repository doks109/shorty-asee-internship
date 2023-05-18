package com.welcome;
import java.io.IOException;
import java.util.Scanner;

import deleteLinks.DeleteLinks;
import viewLinks.ViewLinks;
import enterNewLinks.EnterNewLinks;

public class Welcome {
    public static void main(String[] args) throws IOException {
        Scanner myObj = new Scanner(System.in);
        int userInput;
        do {
            System.out.println("Welcome to Shorty - your link shortening tool!\n\nPlease choose an option:");
            System.out.println("1. View existing shortened links");
            System.out.println("2. Enter a new link");
            System.out.println("3. Delete an existing link");
            System.out.println("0. Exit");
            userInput = myObj.nextInt();

            switch (userInput){
                case 1:
                    ViewLinks.viewLinksMethod();
                    break;
                case 2:
                    EnterNewLinks.enterNewLinksMethod();
                    break;
                case 3:
                    DeleteLinks.deleteLinksMethod();
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Please insert the correct number.");
            }
        } while(userInput != 0);
    }
}
