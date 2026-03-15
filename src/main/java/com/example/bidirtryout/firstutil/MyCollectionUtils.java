package com.example.bidirtryout.firstutil;

import com.example.bidirtryout.secondutil.MyStringUtils;

import java.util.List;
import java.util.Objects;

public final class MyCollectionUtils {
    private MyCollectionUtils() {
    }

    public static boolean checkCollectionEmpty(List<?> col) {
        return col != null && col.isEmpty();
    }

    public static boolean checkIfArrCustomSorted(List<?> col) {
        List<Integer> strLength = col.stream()
                .map(Objects::toString)
                .map(String::length)
                .toList();
        if (strLength.size() < 2) {
            return true;
        }
        for (int i = 0; i < strLength.size() - 1; i++) {
            if (strLength.get(i) > strLength.get(i + 1)) {
                return false;
            }
        }
        return true;
    }

    public List<Boolean> batchCheckPalindrome(List<?> input) {
        return input.stream().map(Objects::toString).map(MyStringUtils::checkIfStringPalindrome).toList();
    }
}
