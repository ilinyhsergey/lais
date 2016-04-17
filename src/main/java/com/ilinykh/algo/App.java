package com.ilinykh.algo;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {

        System.out.println("Longest Almost-Increasing Subsequenc.");

        if (args.length < 1)
            throw new RuntimeException("Unknown data file name.");

        File file = new File(args[0]);

        System.out.println("Source data file: " + file);

        LinkedList<Long> sourceData = new LinkedList<>();

        try {
            Scanner scanner = new Scanner(file);

            while (scanner.hasNextInt()) {
                sourceData.add(scanner.nextLong());
            }

            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }


        List<Long> laIS = new DataProcessor(sourceData).getLaIS();

        laIS.iterator().forEachRemaining(System.out::println);

    }

}
