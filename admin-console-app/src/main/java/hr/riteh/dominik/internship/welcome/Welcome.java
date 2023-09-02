package hr.riteh.dominik.internship.welcome;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Objects;
import java.util.Properties;
import java.util.Scanner;
import hr.riteh.dominik.internship.excel.ExcelManager;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.config.Configurator;

public class Welcome {
    private static final Logger logger = LogManager.getLogger(Welcome.class);
    public static void main(String[] args) throws IOException {
        Properties prop = new Properties();
        FileInputStream ip = new FileInputStream("application.yaml");
        prop.load(ip);

        logger.debug("Hello from Log4j 2");
        logger.debug("This is a Debug Message!");
        logger.info("This is an Info Message!");
        logger.error("And here comes the Error Message!", new RuntimeException("RunRunRun"));

        Scanner myObj = new Scanner(System.in);
        ExcelManager excel;
        if(Objects.equals(prop.getProperty("dataSource"), "excel")){
            excel = new ExcelManager();
        } else{
            excel = new ExcelManager();
        }

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
                    excel.viewLinks();
                    break;
                case 2:
                    excel.enterNewLinks();
                    break;
                case 3:
                    excel.deleteLinks();
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Please insert the correct number.");
            }
        } while(userInput != 0);
    }
}
