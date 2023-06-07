package hr.riteh.dominik.internship.util;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.io.*;

public class WorkbookUtil {
    public static XSSFWorkbook openWorkbookMethod () throws IOException {
        XSSFWorkbook xssfworkbook;
        Sheet firstSheet;
        File file = new File("shorty_entries.xlsx");
        if(file.exists() == false){
            xssfworkbook = new XSSFWorkbook();
            firstSheet = xssfworkbook.createSheet("Sheet 1");
            OutputStream fileOut = new FileOutputStream("shorty_entries.xlsx");
            xssfworkbook.write(fileOut);
            fileOut.close();
        }
        FileInputStream inputStream = new FileInputStream(file);
        xssfworkbook = new XSSFWorkbook(inputStream);
        return xssfworkbook;
    }
}
