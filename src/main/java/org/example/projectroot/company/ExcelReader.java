package org.example.projectroot.company;


import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


import java.io.InputStream;
import java.io.IOException;
import java.util.List;

@Slf4j
public class ExcelReader {



    public void readExcelFile(String filePath) throws IOException {
        try (InputStream inputStream = getClass().getClassLoader().getResourceAsStream(filePath)) {
            if (inputStream == null) {
                log.error("Файл не найден: {}", filePath);
                throw new IOException("Файл не найден: " + filePath);
            }


            Workbook workbook = new XSSFWorkbook(inputStream);
            Sheet sheet = workbook.getSheetAt(0); // Чтение первого листа


            for (Row row : sheet) {
                for (Cell cell : row) {
                    System.out.print(cell.toString() + "\t");
                }
                System.out.println();
            }
        } catch (IOException e) {
            log.error("Ошибка при чтении Excel файла: {}", filePath, e);
            throw e;
        }
    }

    public void readMultipleFiles(List<String> filePaths) {
        for (String filePath : filePaths) {
            try {
                readExcelFile(filePath);
            } catch (IOException e) {
                log.error("Ошибка при чтении файла: {}", filePath, e);
            }
        }
    }
}
