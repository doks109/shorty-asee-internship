package deleteLinks;
import openWokbook.OpenWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFCell;

import java.io.*;
import java.util.Scanner;

public class DeleteLinks {
    public static void deleteLinksMethod() throws IOException {
        Scanner inputID = new Scanner(System.in);
        int IdToDelete, flag = 1;
        System.out.println("Please enter ID of the link you want to delete");
        IdToDelete = inputID.nextInt();

        XSSFWorkbook xssfworkbook = OpenWorkbook.openWorkbookMethod();
        Sheet firstSheet = xssfworkbook.getSheetAt(0);
        // finding and deleting
        for (int i = 0; i < firstSheet.getPhysicalNumberOfRows(); i++) {
            Row row = firstSheet.getRow(i);
            XSSFCell cell = (XSSFCell) row.getCell(0);
            int lastRowNum = firstSheet.getLastRowNum();
            if((int)cell.getNumericCellValue() == IdToDelete){
                flag = 0;
            }
            if(flag == 0 && i < lastRowNum && i >= 0) {
                firstSheet.shiftRows(i + 1, i + 2, -1, true, true);
                break;
            } else if(flag == 0 && i == lastRowNum){
                firstSheet.removeRow(row);
            }
        }
        // adjusting ID numbers
        if(flag == 0){
            for (int i = IdToDelete; i < firstSheet.getPhysicalNumberOfRows(); i++) {
                Row row = firstSheet.getRow(i);
                XSSFCell cell = (XSSFCell) row.getCell(0);
                cell.setCellValue(i);
            }
            System.out.println("Link was successfully deleted!\n");
        }
        if(flag == 1) System.out.println("There is no link with that ID, please try again!\n");

        File file = new File("shorty_entries.xlsx");
        FileOutputStream os = new FileOutputStream(file);
        xssfworkbook.write(os);
        xssfworkbook.close();
        os.close();
    }
}
