package hr.riteh.dominik.internship.controller;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
public class CustomErrorController implements ErrorController {
    @GetMapping("/error")
    public ResponseEntity<String> handleError() {
        String htmlContent = "<html><head><style>img { max-width: 100%; height: auto; }</style></head><body><img src=\"https://blog.thomasnet.com/hubfs/shutterstock_774749455.jpg\" alt=\"Error Image\"></body></html>";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.TEXT_HTML);

        return new ResponseEntity<>(htmlContent, headers, HttpStatus.NOT_FOUND);
    }
}

