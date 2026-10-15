package com.example.demo.actuator;



import com.example.demo.config.DataProperties;
import com.example.demo.service.ProductRepository;
import org.springframework.boot.actuate.info.Info;
import org.springframework.boot.actuate.info.InfoContributor;
import org.springframework.stereotype.Component;
import java.util.Map;

@Component
public class DataSourceInfoContributor implements InfoContributor {

    private final DataProperties props;
    private final ProductRepository repo;

    public DataSourceInfoContributor(DataProperties props, ProductRepository repo) {
        this.props = props;
        this.repo = repo;
    }

    @Override
    public void contribute(Info.Builder builder) {
        builder.withDetail("dataSource", Map.of(
                "path", props.path(),
                "format", props.format(),
                "recordsLoaded", repo.count()));
    }
}