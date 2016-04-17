package com.ilinykh.algo;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {

        System.out.println("Longest Almost-Increasing Subsequenc.");

        if (args.length < 2)
            throw new RuntimeException("Unknown data file name and C - constant.");

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

        long c = Long.parseLong(args[1]);
        long[] laIS = new DataProcessor(sourceData).getLaIS(c);

        System.out.println("____ LaIS: ____");
        for (long e : laIS) {
            System.out.println(e);
        }


//        laIS.iterator().forEachRemaining(System.out::println);

    }

}
