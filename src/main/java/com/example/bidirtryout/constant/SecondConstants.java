package com.example.bidirtryout.constant;

import java.util.List;

public class SecondConstants {
    public static final String HELLO_TWO = "hello_2";

    public static List<String> findAllStringWithPrefix(List<String> input) {
        return input.stream().filter(ele -> ele.startsWith(FirstConstants.HELLO_FIRST)).toList();
    }
}
