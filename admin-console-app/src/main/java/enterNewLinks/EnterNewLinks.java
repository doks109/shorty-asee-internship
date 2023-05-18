package enterNewLinks;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Objects;
import java.util.Scanner;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class EnterNewLinks {
    public static void enterNewLinksMethod() throws IOException {
        String userInputString;
        Scanner inputString = new Scanner(System.in);
        System.out.println("Now you can enter your link: ");
        userInputString = inputString.nextLine();

        String excelFilePath = "shorty_entries.xlsx";
        FileInputStream inputStream = new FileInputStream(excelFilePath);
        XSSFWorkbook xssfworkbook = new XSSFWorkbook(inputStream);
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
                Cell cell = row.createCell(j);
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
        inputStream.close();
        FileOutputStream os = new FileOutputStream(excelFilePath);
        xssfworkbook.write(os);
        xssfworkbook.close();
        os.close();
    }
}
