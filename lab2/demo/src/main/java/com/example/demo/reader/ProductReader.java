package com.example.demo.reader;

import com.example.demo.model.Product;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;


public interface ProductReader {
  List<Product> read(InputStream in) throws IOException;
  String format();
}
