package com.example.bidirtryout.secondutil;

import com.example.bidirtryout.firstutil.MyCollectionUtils;

import java.util.ArrayList;
import java.util.List;

public final class MyStringUtils {
    private MyStringUtils() {
    }

    public static boolean checkStringEmpty(String str) {
        return str == null || str.isEmpty();
    }

    public String helper1() {
        List<String> arr = new ArrayList<>();
        arr.add("hello");
        arr.add("world");
        return MyCollectionUtils.checkIfArrCustomSorted(arr) ? arr.getFirst() : arr.getLast();
    }

    public static boolean checkIfStringPalindrome(String s) {
        if (s == null || s.length() < 2) {
            return true;
        }
        for (int i = 0; i < s.length() / 2; i++) {
            if (s.charAt(i) != s.charAt(s.length() - i - 1)) {
                return false;
            }
        }
        return true;
    }
}
