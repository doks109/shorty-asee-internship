package viewLinks;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ViewLinks {
    public static void main (String[] args) throws IOException {
        String excelFilePath = "shorty_entries.xlsx";
        FileInputStream inputStream = new FileInputStream(excelFilePath);

        XSSFWorkbook xssfworkbook = new XSSFWorkbook(inputStream);
        Sheet firstSheet = xssfworkbook.getSheetAt(0);
        for (int i = 0; i < 2; i++) {
            Row row = firstSheet.getRow(i);
            for (int j = 0; j < 3; j++) {
                System.out.print(row.getCell(j) + " ");
            }
            System.out.println();
        }
        xssfworkbook.close();
        inputStream.close();
    }
}




