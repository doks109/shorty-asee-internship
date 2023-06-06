package hr.riteh.dominik.internship.excel;

import java.io.*;
import java.util.Iterator;
import java.util.Objects;
import java.util.Scanner;
import hr.riteh.dominik.internship.util.RandomGenerator;
import hr.riteh.dominik.internship.util.WorkbookUtil;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFCell;

public class ExcelManager implements ExcelHandler {
    public void enterNewLinksMethod() throws IOException {
        String userInputString;
        Scanner inputString = new Scanner(System.in);
        System.out.println("Now you can enter your link: ");
        userInputString = inputString.nextLine();

        XSSFWorkbook xssfworkbook = WorkbookUtil.openWorkbookMethod();
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
                        cell.setCellValue(RandomGenerator.getAlphaNumericString());
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
    public void deleteLinksMethod() throws IOException {
        Scanner inputID = new Scanner(System.in);
        int IdToDelete, flag = 1;
        System.out.println("Please enter ID of the link you want to delete");
        IdToDelete = inputID.nextInt();

        XSSFWorkbook xssfworkbook = WorkbookUtil.openWorkbookMethod();
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
    public void viewLinksMethod() throws IOException {
        String helper, ID = "ID", originalUrl = "Original URL", shortenedUrl = "Shortened URL";
        int maxWidth, flag = 0;

        XSSFWorkbook xssfworkbook = WorkbookUtil.openWorkbookMethod();
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
        System.out.print("|---------|");
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
        System.out.println();
    }
}
