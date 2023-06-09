package hr.riteh.dominik.internship.shortyapp;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.FileInputStream;
import java.util.Objects;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.servlet.view.RedirectView;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;



@SpringBootApplication
@RestController
public class ShortyAppApplication {
    public static void main(String[] args) {
        SpringApplication.run(ShortyAppApplication.class, args);
    }
    @GetMapping("/{shortenedUrl}")
    @ResponseBody
    public RedirectView getShortenedUrl(@PathVariable String shortenedUrl) throws IOException {
        File file = new File("shorty_entries.xlsx");
        FileInputStream inputStream = new FileInputStream(file);
        XSSFWorkbook xssfWorkbook = new XSSFWorkbook(inputStream);
        Sheet firstSheet = xssfWorkbook.getSheetAt(0);
        for (int i = 0; i < firstSheet.getPhysicalNumberOfRows(); i++) {
            Row row = firstSheet.getRow(i);
            if(Objects.equals(String.valueOf(row.getCell(2)), shortenedUrl)){
                String originalUrl = (String.valueOf(row.getCell(1)));
                return new RedirectView(originalUrl);
            }
        }
        xssfWorkbook.close();
        return new RedirectView("/");
    }

}
