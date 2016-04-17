package com.ilinykh.algo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Scanner;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
        System.out.println("Hello World!");
        if (args.length < 1)
            throw new RuntimeException("Unknown data file name.");

        File file = new File(args[0]);

        System.out.println("data file: " + file);

        DataProcessor processor = new DataProcessor(file);

        List<Long> laIS = processor.getLaIS();

        laIS.iterator().forEachRemaining(System.out::println);

    }


    private void processElement(long x, long i) {


    }
}
