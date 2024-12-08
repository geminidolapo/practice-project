package com.practice.project.service.sample;

import java.util.List;

public class Wildcards {
    public void unboundedWildcard(List<?> list) {
        //....
    }

    public void upperBoundedWildcards(List<? extends Number> numbers) {
        //  ....
    }

    public void lowerBoundedWildcards(List<? super Integer> list) {
        // ...
    }
}
