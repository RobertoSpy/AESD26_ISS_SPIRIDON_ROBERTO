package com.example.demo.service;

import com.example.demo.config.DataProperties;
import com.example.demo.model.Product;
import com.example.demo.reader.ProductReader;
import jakarta.annotation.PostConstruct;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;



@Service
public class ProductRepository {

    private final DataProperties props;
    private final ProductReader reader;
    private final ResourceLoader resourceLoader;
    private List<Product> products = List.of();

    public ProductRepository(DataProperties props, ProductReader reader, ResourceLoader resourceLoader) {
        this.props = props;
        this.reader = reader;
        this.resourceLoader = resourceLoader;
    }

    @PostConstruct
    void load() throws IOException {
        Resource resource = resourceLoader.getResource(props.path());
        try (InputStream in = resource.getInputStream()) {
            products = reader.read(in);
        }
    }

    public List<Product> findAll() { return products; }
    public int count() { return products.size(); }
}