package openWokbook;

import hr.riteh.dominik.internship.util.WorkbookUtil;
import org.junit.jupiter.api.Test;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

public class OpenWorkbookTest {
    @Test
    public void openWorkbookTestMethod() throws IOException {
        File testFile = File.createTempFile("test_workbook", ".xlsx");
        testFile.deleteOnExit();

        XSSFWorkbook workbook = WorkbookUtil.openWorkbookMethod();
        assertNotNull(workbook);
        Sheet sheet = workbook.getSheetAt(0);
        assertNotNull(sheet);
        assertEquals("Sheet 1", sheet.getSheetName());

        assertTrue(testFile.delete());
    }
}
