package com.example.demo.university;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class Course {

    private final InjectionTracker tracker;

    private final Professor professor;
    @Autowired
    private Classroom classroom;

    private Library library;

    private GradingPolicy gradingPolicy;

    @Autowired
    public Course(Professor professor, InjectionTracker tracker) {
        this.professor = professor;
        this.tracker = tracker;
        tracker.record("CONSTRUCTOR   professor=" + state(professor) + ", classroom=" + state(classroom)
                + ", library=" + state(library) + ", gradingPolicy=" + state(gradingPolicy));
    }

    @Autowired
    public void setLibrary(Library library) {
        this.library = library;
        tracker.record("SETTER        professor=" + state(professor) + ", classroom=" + state(classroom)
                + ", library=" + state(this.library) + ", gradingPolicy=" + state(gradingPolicy));
    }

    @Autowired
    public void configureGrading(GradingPolicy gradingPolicy) {
        this.gradingPolicy = gradingPolicy;
        tracker.record("METHOD        professor=" + state(professor) + ", classroom=" + state(classroom)
                + ", library=" + state(library) + ", gradingPolicy=" + state(this.gradingPolicy));
    }

    @PostConstruct
    void ready() {
        tracker.record("POSTCONSTRUCT course is fully set up");
    }

    private static String state(Object o) {
        return o == null ? "null" : "SET";
    }
}
