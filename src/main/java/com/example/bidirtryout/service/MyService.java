package com.example.bidirtryout.service;

import com.example.bidirtryout.constant.FirstConstants;
import com.example.bidirtryout.constant.SecondConstants;
import com.example.bidirtryout.firstutil.MyCollectionUtils;
import com.example.bidirtryout.secondutil.MyStringUtils;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class MyService {
    public String manipulateInput() {
        List<String> arr = new ArrayList<>();
        arr.add("hello");
        arr.add("world");
        return MyCollectionUtils.checkIfArrCustomSorted(arr) ? arr.getFirst() : arr.getLast();
    }

    public void helper() {
        List<String> temp = new ArrayList<>();
        temp.add("heleh");
        temp.add("dad");
        temp.add("input");
        List<Boolean> result = new ArrayList<>();
        for (String s: temp) {
            result.add(MyStringUtils.checkIfStringPalindrome(s));
        }
        System.out.println(result);
    }

    public void helper2() {
        List<String> helloFirst = List.of(FirstConstants.HELLO_FIRST, SecondConstants.HELLO_TWO);
        System.out.println(helloFirst);
    }
}
