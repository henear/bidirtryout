package com.example.bidirtryout.constant;

import java.util.List;

public class FirstConstants {
    public static final String HELLO_FIRST = "hello_1";

    public static List<String> findAllStringWithPostfix(List<String> input) {
        return input.stream().filter(ele -> ele.endsWith(SecondConstants.HELLO_TWO)).toList();
    }
}
