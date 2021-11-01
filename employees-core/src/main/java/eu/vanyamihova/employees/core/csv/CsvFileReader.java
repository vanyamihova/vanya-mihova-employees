package eu.vanyamihova.employees.core.csv;

import com.opencsv.CSVReader;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;

/**
 * Component, responsible for reading specific {@link MultipartFile}
 * and converting the content via specific {@link CsvContentConverter}.
 * The result is collected in the {@link CsvContent}.
 *
 * @author Vanya Mihova - vanya.mihova89@gmail.com
 * @since 30.10.2021
 */
@Slf4j
@Component
public class CsvFileReader {

    @Autowired
    private CsvContent csvContent;

    public CsvContent read(CsvContentConverter csvContentConverter, MultipartFile file) throws Exception {
        log.info("Start reading file {}", file.getOriginalFilename());
        csvContent.clear();

        Reader reader = new InputStreamReader(file.getInputStream());
        CSVReader csvReader = new CSVReader(reader);
        String[] line;
        while ((line = csvReader.readNext()) != null) {
            CsvLine content = csvContentConverter.convert(line);
            csvContent.addLine(content);
        }
        reader.close();
        csvReader.close();
        return csvContent;
    }

}
