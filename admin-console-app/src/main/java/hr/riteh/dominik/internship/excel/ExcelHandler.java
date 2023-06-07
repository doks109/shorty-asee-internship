package hr.riteh.dominik.internship.excel;

import java.io.IOException;

public interface ExcelHandler {
    void enterNewLinks() throws IOException;
    void deleteLinks() throws IOException;
    void viewLinks() throws IOException;
}
