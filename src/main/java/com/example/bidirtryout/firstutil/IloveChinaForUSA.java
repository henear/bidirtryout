package com.example.bidirtryout.firstutil;

import java.util.List;

public final class IloveChinaForUSA {
    private IloveChinaForUSA() {
    }

    public static boolean checkCollectionEmpty(List<?> col) {
        return col != null && col.isEmpty();
    }
}
