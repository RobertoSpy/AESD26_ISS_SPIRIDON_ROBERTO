package com.example.demo.university;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.springframework.stereotype.Component;

/** Notes every step in the order it happens, so the injection order can be printed and checked in a test. */
@Component
public class InjectionTracker {

    private final List<String> steps = new ArrayList<>();

    public void record(String step) {
        steps.add(step);
        System.out.println("[" + steps.size() + "] " + step);
    }

    public List<String> steps() {
        return Collections.unmodifiableList(steps);
    }
}
