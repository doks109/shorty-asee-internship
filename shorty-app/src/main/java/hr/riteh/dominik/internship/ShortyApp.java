package hr.riteh.dominik.internship;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication

public class ShortyApp {
    private static final Logger logger = LogManager.getLogger(ShortyApp.class);
    public static void main(String[] args) {

        logger.debug("Hello from Log4j 2");
        logger.debug("This is a Debug Message!");
        logger.info("This is an Info Message!");
        logger.error("And here comes the Error Message!", new RuntimeException("RunRunRun"));

        SpringApplication.run(ShortyApp.class, args);
    }
}
