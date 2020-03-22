package com.parasu.hw.excel;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;
import java.util.Map;

@SpringBootApplication
public class ExcelReadApplication implements CommandLineRunner {

    private static final Logger logger = LoggerFactory.getLogger(ExcelReadApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(ExcelReadApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        Map<String, List<String>> values = null;
        int[] keys = Util.getIntArr(args[2]);
        if("csv".equalsIgnoreCase(args[1])) {
            ReadCsv csv = new ReadCsv();
            values = csv.read(args[0], keys);
        } else if("excel".equalsIgnoreCase(args[1])) {
            ReadExcel re = new ReadExcel();
            values = re.read(args[0]);
        }

        Util.print(values);
    }
}
