package hr.riteh.dominik.internship.controller;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Objects;
import java.util.Properties;

@RestController
public class RedirectController {
    @GetMapping("/{shortenedUrl}")
    @ResponseBody
    public RedirectView getShortenedUrl(@PathVariable String shortenedUrl) throws IOException {
        Properties prop = new Properties();
        FileInputStream ip = new FileInputStream("application.yaml");
        prop.load(ip);


        File file = new File(prop.getProperty("excelFile"));
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
        return new RedirectView("/error");
    }
}
