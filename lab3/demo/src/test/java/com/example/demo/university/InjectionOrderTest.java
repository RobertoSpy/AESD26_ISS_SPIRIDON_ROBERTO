package com.example.demo.university;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class InjectionOrderTest {

    @Autowired
    InjectionTracker tracker;

    @Test
    void constructorThenFieldThenSetterAndMethod() {
        var steps = tracker.steps();
        // constructor runs first: nothing else is injected yet
        assertThat(steps.get(0)).startsWith("CONSTRUCTOR")
                .contains("classroom=null", "library=null", "gradingPolicy=null");
        // the field is already set when the setter runs, so field injection came between them
        assertThat(steps.get(1)).startsWith("SETTER")
                .contains("classroom=SET", "library=SET", "gradingPolicy=null");
        assertThat(steps.get(2)).startsWith("METHOD")
                .contains("classroom=SET", "library=SET", "gradingPolicy=SET");
        assertThat(steps.get(3)).startsWith("POSTCONSTRUCT");
    }
}
