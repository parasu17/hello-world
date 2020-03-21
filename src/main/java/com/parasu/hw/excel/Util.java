package com.parasu.hw.excel;

import java.util.ArrayList;
import java.util.List;

public class Util {

    public static boolean isPresent(int[] intArr, int value) {
        boolean b = false;
        for(int id : intArr) {
            if(id == value) {
                b = true;
                break;
            }
        }
        return b;
    }

    public static int[] getIntArr(String intValues) {
        List<Integer> values = new ArrayList<>();
        for(String str : intValues.split(",")) {
            values.add(Integer.parseInt(str));
        }
        int[] v = new int[values.size()];
        int count = 0;
        for(Integer val : values) {
            v[count++] = val;
        }
        return v;
    }

}
