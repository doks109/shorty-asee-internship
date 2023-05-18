package viewLinks;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


public class ViewLinks {
    public static void viewLinksMethod() throws IOException {
        String excelFilePath = "shorty_entries.xlsx";
        String helper, ID = "ID", originalUrl = "Original URL", shortenedUrl = "Shortened URL";
        int maxWidth, flag = 0;

        FileInputStream inputStream = new FileInputStream(excelFilePath);
        XSSFWorkbook xssfworkbook = new XSSFWorkbook(inputStream);
        Sheet firstSheet = xssfworkbook.getSheetAt(0);
        // finding max width
        maxWidth = originalUrl.length();
        for (int i = 0; i < firstSheet.getPhysicalNumberOfRows(); i++) {
            Row row = firstSheet.getRow(i);
            helper = String.valueOf(row.getCell(1));
            if(helper.length() > maxWidth){
                maxWidth = helper.length();
            }
        }
        // header print
        System.out.format("| %-8s| %-"+(maxWidth + 1)+"s| %-14s |\n", ID, originalUrl, shortenedUrl);
        System.out.printf("|---------|");
        for (int i = 0; i < (maxWidth + 2); i++) {
            System.out.print("-");
        }
        System.out.print("|----------------|\n");
        // table format and print
        for (int i = 0; i < firstSheet.getPhysicalNumberOfRows(); i++) {
            Row row = firstSheet.getRow(i);
            Iterator<Cell> cells = row.cellIterator();
            while (cells.hasNext()) {
                XSSFCell cell = (XSSFCell) cells.next();
                CellType type = cell.getCellType();
                if ((type == CellType.STRING) && flag == 0) {
                    System.out.format("| %-" + (maxWidth + 1) + "s", cell.getStringCellValue());
                    flag = 1;
                } else if ((type == CellType.STRING)) {
                    System.out.format("| %-14s |", cell.getStringCellValue());
                    flag = 0;
                } else if (type == CellType.NUMERIC) {
                    System.out.format("| %-8s", (int)cell.getNumericCellValue());
                }
            }
            System.out.println();
        }
        xssfworkbook.close();
        inputStream.close();
        System.out.println();
    }
}





