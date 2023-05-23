package deleteLinks;
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
        firstSheet = xssfworkbook.getSheetAt(0);

        for (int i = 0; i < firstSheet.getPhysicalNumberOfRows(); i++) {
            Row row = firstSheet.getRow(i);
            XSSFCell cell = (XSSFCell) row.getCell(0);
            int lastRowNum = firstSheet.getLastRowNum();
            if((int)cell.getNumericCellValue() == IdToDelete){
                flag = 0;
            }
            if(flag == 0 && i < lastRowNum) {
                firstSheet.shiftRows(i + 1, i + 2, -1, true, true);
                break;
            } else if(flag == 0 && i == lastRowNum){
                firstSheet.removeRow(row);
            }
        }

        if(flag == 0){
            for (int i = IdToDelete; i < firstSheet.getPhysicalNumberOfRows(); i++) {
                Row row = firstSheet.getRow(i);
                XSSFCell cell = (XSSFCell) row.getCell(0);
                cell.setCellValue(i);
            }
            System.out.println("Link was successfully deleted!\n");
        }
        if(flag == 1) System.out.println("There is no link with that ID, please try again!\n");

        inputStream.close();
        FileOutputStream os = new FileOutputStream(file);
        xssfworkbook.write(os);
        xssfworkbook.close();
        os.close();
    }
}
