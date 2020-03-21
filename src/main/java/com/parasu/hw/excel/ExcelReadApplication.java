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
        ReadExcel re = new ReadExcel();
        Map<String, List<String>> values = re.read(args[0]);
        logger.info("{}", values);
    }
}
