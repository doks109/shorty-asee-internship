package deleteLinks;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFCell;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Scanner;

public class DeleteLinks {
    public static void deleteLinksMethod() throws IOException {
        Scanner inputID = new Scanner(System.in);
        int IdToDelete, flag = 1;
        System.out.println("Please enter ID of the link you want to delete");
        IdToDelete = inputID.nextInt();

        String excelFilePath = "shorty_entries.xlsx";
        FileInputStream inputStream = new FileInputStream(excelFilePath);
        XSSFWorkbook xssfworkbook = new XSSFWorkbook(inputStream);
        Sheet firstSheet = xssfworkbook.getSheetAt(0);

        for (int i = 0; i < firstSheet.getPhysicalNumberOfRows(); i++) {
            Row row = firstSheet.getRow(i);
            XSSFCell cell = (XSSFCell) row.getCell(0);

            if((int)cell.getNumericCellValue() == IdToDelete){
                firstSheet.removeRow(row);
                flag = 0;
            }
            if(flag == 0) {
                firstSheet.shiftRows(i + 1, i + 2, -1);
            }
        }
        for (int i = IdToDelete; i < firstSheet.getPhysicalNumberOfRows(); i++) {
            Row row = firstSheet.getRow(i);
            XSSFCell cell = (XSSFCell) row.getCell(0);
            cell.setCellValue(i);
        }
        if(flag == 1) System.out.println("There is no link with that ID, please try again!\n");

        inputStream.close();
        FileOutputStream os = new FileOutputStream(excelFilePath);
        xssfworkbook.write(os);
        xssfworkbook.close();
        os.close();
    }
}
