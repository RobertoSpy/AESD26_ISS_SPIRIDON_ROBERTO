package com.example.demo.runner;


import com.example.demo.config.DataProperties;
import com.example.demo.service.ProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.PropertySource;
import org.springframework.stereotype.Component;
import java.util.Arrays;

@Component
public class StartupRunner implements CommandLineRunner {

    private final ProductRepository repo;
    private final DataProperties props;
    private final ConfigurableEnvironment env;

    public StartupRunner(ProductRepository repo, DataProperties props, ConfigurableEnvironment env) {
        this.repo = repo;
        this.props = props;
        this.env = env;
    }

    @Override
    public void run(String... args) {
        System.out.println("Active profiles: " + Arrays.toString(env.getActiveProfiles()));
        System.out.println("File: " + props.path() + " (" + props.format() + "), records: " + repo.count());
        repo.findAll().stream().limit(3).forEach(p -> System.out.println("  -> " + p));

        System.out.println("app.data.label = " + env.getProperty("app.data.label"));
        for (PropertySource<?> ps : env.getPropertySources()) {
            if (ps.getName().equals("configurationProperties")) continue;
            if (ps.containsProperty("app.data.label")) {
                System.out.println("Value comes from property source: " + ps.getName());
                break;
            }
        }
    }
}