package hr.riteh.dominik.internship.excel;

import java.io.IOException;

public interface ExcelHandler {
    void enterNewLinksMethod() throws IOException;
    void deleteLinksMethod() throws IOException;
    void viewLinksMethod() throws IOException;
}
