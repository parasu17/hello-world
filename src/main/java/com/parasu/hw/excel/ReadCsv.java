package com.parasu.hw.excel;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ReadCsv {
    private static final Logger logger = LoggerFactory.getLogger(ReadCsv.class);

    private static final char DEFAULT_SEPARATOR = ',';
    private static final char DEFAULT_QUOTE = '"';

    public Map<String, List<String>> read(String fileName, int[] keyIds) {
        if(keyIds == null || keyIds.length == 0) {
            keyIds = new int[1];
            keyIds[0] = 1;
        }
        Map<String, List<String>> values = new HashMap<>();
        try(BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(fileName)))) {
            String line;
            List<String> cells;
            while( (line = br.readLine()) != null) {
                cells = parseLine(line);
                String key = null;
                if(cells.size() > 0) {
                    StringBuilder sb = new StringBuilder();
                    for(int keyId : keyIds) {
                        sb.append(cells.get(keyId - 1)).append("___");
                    }
                    key = sb.toString();
                }
                if(StringUtils.hasText(key)) {
                    values.put(key, cells);
                }
            }
        } catch(Exception exp) {
            logger.error(exp.getMessage(), exp);
        }
        return values;
    }

    private int indexOfChar(char[] chars, int startIndex, char ch) {
        int index = -1;
        for(int i = startIndex; i < chars.length ; i++) {
            if(chars[i] == ch) {
                index = i;
                break;
            }
        }
        return index;
    }

    private List<String> parseLine(String line) {
        char quote = DEFAULT_QUOTE;

        List<String> values = new ArrayList<>();
        char[] chars = line.toCharArray();

        int endQuote;
        int previousSep = 0;
        String str;
        for(int i = 0; i < chars.length ; i++) {
            if(chars[i] == quote) {
                endQuote = indexOfChar(chars, i + 1, quote);
                if(endQuote != -1) {
                    i = endQuote;
                }
            } else if(chars[i] == DEFAULT_SEPARATOR) {
                str = new String(chars, previousSep, i - previousSep);
                if(str.startsWith("\"") && str.substring(1).endsWith("\"")) {
                    str = str.substring(1, str.length() - 1);
                }
                values.add(str);
                previousSep = i + 1;
            }
        }

        if(chars.length > previousSep) {
            values.add(new String(chars, previousSep, chars.length - previousSep));
        } else if(chars.length == previousSep) {
            values.add("");
        }

        return values;
    }

}
