package viewLinks;

import org.junit.jupiter.api.Test;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import static org.junit.jupiter.api.Assertions.*;


public class ViewLinksTest {
    @Test
    public void viewLinksTestMethod() throws IOException {
        XSSFWorkbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Sheet1");
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        ViewLinks.viewLinksMethod();

        System.setOut(System.out);

        String expectedOutput =
                "| ID      | Original URL | Shortened URL  |\n" +
                "|---------|--------------|----------------|\n\n";

        String normalizedExpectedOutput = expectedOutput.replaceAll("\r\n", "\n");
        String normalizedActualOutput = outputStream.toString().replaceAll("\r\n", "\n");
        assertEquals(normalizedExpectedOutput, normalizedActualOutput.toString());
    }
}


