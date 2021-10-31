package eu.vanyamihova.employees.core.csv;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Model, which is taking care about all read lines from the file
 *
 * @author Vanya Mihova - vanya.mihova89@gmail.com
 * @since 30.10.2021
 */
@Component
public final class CsvContent {

    private final List<CsvLine> lines = new ArrayList<>();

    void addLine(CsvLine newLine) {
        if (newLine == null || newLine.isEmpty()) {
            return;
        }
        this.lines.add(newLine);
    }

    void clear() {
        this.lines.clear();
    }

    public List<CsvLine> getLines() {
        return lines;
    }

}
