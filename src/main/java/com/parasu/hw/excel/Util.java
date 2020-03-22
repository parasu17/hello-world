package com.parasu.hw.excel;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Util {

    private static final Logger logger = LoggerFactory.getLogger(Util.class);

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

    public static void print(Map<String, List<String>> values) {
        if(values == null || values.size() == 0) {
            logger.info("Empty map");
            return;
        }

        values.forEach((key, list) -> {
            StringBuilder sb = new StringBuilder();
            list.forEach( val -> sb.append(val).append(","));
            logger.info("key={}, value={}", key, sb);
        });

    }
}
