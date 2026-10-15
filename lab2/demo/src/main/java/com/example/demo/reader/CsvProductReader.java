package com.example.demo.reader;

import com.example.demo.config.DataProperties;
import com.example.demo.model.Product;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;


@Component
@Profile("dev")
@ConditionalOnExpression("'${app.data.format}'.equalsIgnoreCase('csv')")
public class CsvProductReader implements ProductReader {

    private final DataProperties props;

    public CsvProductReader(DataProperties props) { this.props = props; }

    @Override
    public List<Product> read(InputStream in) throws IOException {
        CSVFormat format = CSVFormat.DEFAULT.builder()
                .setDelimiter(props.delimiter())
                .setIgnoreEmptyLines(true)
                .setTrim(true)
                .get();

        List<Product> result = new ArrayList<>();
        try (Reader reader = new InputStreamReader(in, props.encoding());
             CSVParser parser = format.parse(reader)) {
            boolean first = true;
            for (CSVRecord r : parser) {
                if (first && props.skipHeader()) { first = false; continue; }
                first = false;
                result.add(new Product(
                        Integer.parseInt(r.get(0)),
                        r.get(1),
                        Double.parseDouble(r.get(2))));
            }
        }
        return result;
    }

    @Override
    public String format() { return "csv"; }
}