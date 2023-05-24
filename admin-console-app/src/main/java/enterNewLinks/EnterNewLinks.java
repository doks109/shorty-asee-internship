package enterNewLinks;
import java.io.*;
import java.util.Objects;
import java.util.Scanner;

import openWokbook.OpenWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFCell;

public class EnterNewLinks {
    public static void enterNewLinksMethod() throws IOException {
        String userInputString;
        Scanner inputString = new Scanner(System.in);
        System.out.println("Now you can enter your link: ");
        userInputString = inputString.nextLine();

        XSSFWorkbook xssfworkbook = OpenWorkbook.openWorkbookMethod();
        Sheet firstSheet = xssfworkbook.getSheetAt(0);

        // finding duplicates
        for (int i = 0; i < firstSheet.getPhysicalNumberOfRows(); i++) {
            Row row = firstSheet.getRow(i);
            if(Objects.equals(String.valueOf(row.getCell(1)), userInputString)){
                System.out.println("That link already exists in the table! Please try again.\n");
                return;
            }
        }
        // adding new links
        int rowNumber = firstSheet.getPhysicalNumberOfRows();
            Row row = firstSheet.createRow(rowNumber);
            for (int j = 0; j < 3; j++) {
                XSSFCell cell = (XSSFCell) row.createCell(j);
                switch(j){
                    case 0:
                        cell.setCellValue(rowNumber);
                        break;
                    case 1:
                        cell.setCellValue(userInputString);
                        break;
                    case 2:
                        cell.setCellValue(enterNewLinks.randomGenerator.RandomGenerator.getAlphaNumericString());
                        break;
                }
            }
        System.out.println("Link was successfully added!\n");
        File file = new File("shorty_entries.xlsx");
        FileOutputStream os = new FileOutputStream(file);
        xssfworkbook.write(os);
        xssfworkbook.close();
        os.close();
    }
}
