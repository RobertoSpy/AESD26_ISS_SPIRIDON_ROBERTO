package com.example.demo.reader;

import com.example.demo.model.Product;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import tools.jackson.core.type.TypeReference;
import tools.jackson.databind.ObjectMapper;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;


@Component
@Profile("prod")
@ConditionalOnExpression("'${app.data.format}'.equalsIgnoreCase('json')")
public class JsonProductReader implements ProductReader {

    private final ObjectMapper mapper;

    public JsonProductReader(ObjectMapper mapper) { this.mapper = mapper; }

    @Override
    public List<Product> read(InputStream in) throws IOException {
        return mapper.readValue(in, new TypeReference<List<Product>>() {});
    }

    @Override
    public String format() { return "json"; }
}