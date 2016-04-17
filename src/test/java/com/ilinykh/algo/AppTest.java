package com.ilinykh.algo;


import org.junit.Test;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.TreeMap;

import static org.junit.Assert.*;

public class AppTest {

    @Test
    public void validateLais() {
        long[] data = {7, 15, 2, 14, 14, 6, 8, 11, 17, 15, 14, 13};
        long c = 2;
        int length = data.length;
        ArrayList<Long> dataTyped = new ArrayList<>(length);
        for (int i = 0; i < length; ++i) {
            dataTyped.add(data[i]);
        }
        long[] laIS = new DataProcessor(dataTyped).getLaIS(c);
        assertArrayEquals(laIS, new long[]{2, 6, 8, 11, 14, 13});
        assertTrue(validateLais(laIS, c));
    }



    @Test
    public void checkComplexity() {
        long c = 2;
        int length = 1000;
        int times = 5000;
        List<Long> dataTyped;
        long start, fin, delta;
        int l;
        long[] laIS;
        Random generator = new Random();
        PrintWriter writer;

        int[] k = new int[times];
        long[] time = new long[times];


        TreeMap<Integer, Long> avg = new TreeMap<>();
        TreeMap<Integer, Long> cnt = new TreeMap<>();
        TreeMap<Integer, Double> std = new TreeMap<>();




        try {
            writer = new PrintWriter("result-5000.csv", "UTF-8");
            writer.println("length(LaIS), Time in nanos");

            for (int j = 0; j < times; ++j) {
                dataTyped = new ArrayList<>(length);

                for (int i = 0; i < length; ++i) {
                    dataTyped.add((long) generator.nextInt(1000));
                }

                DataProcessor dataProcessor = new DataProcessor(dataTyped);

                start = System.nanoTime();
                laIS = dataProcessor.getLaIS(c);
                fin = System.nanoTime();

                assertTrue(validateLais(laIS, c));




                delta = fin - start;
                l = laIS.length;

                k[j] = l;
                time[j] = delta;

                Long cntEl = cnt.get(l);
                if (cntEl == null){
                    cnt.put(l, 1L);
                    avg.put(l, delta);
                } else {
                    cnt.put(l, cntEl + 1);
                    avg.put(l, (avg.get(l) * cntEl + delta) / (cntEl + 1));
                }
                writer.println("" + l + ", " + delta);
            }

            writer.close();

            TreeMap<Integer, Long> d2Map = new TreeMap<>();

            for (int j = 0; j < times; ++j) {
                int kEl = k[j];
                long d = avg.get(kEl) - kEl;
                Long d2Val = d2Map.get(kEl);
                if (d2Val == null){
                    d2Map.put(kEl, d*d);
                } else {
                    d2Map.put(kEl, d2Val + d*d);
                }
            }

            for (int kEl: avg.keySet()){
                std.put(kEl, Math.sqrt(d2Map.get(kEl) / avg.get(kEl)));
            }



            writer = new PrintWriter("result-5000-avg-std.csv", "UTF-8");
            writer.println("length(LaIS), Time in nanos, STD");

            for (int kEl : avg.keySet()){
                writer.println("" + kEl + ", " + avg.get(kEl)+ ", " + std.get(kEl));
            }



            writer.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }


    }

    public boolean validateLais(long[] laIS, long c){
        int l = laIS.length;
        long max = laIS[0];
        long el;

        for (int i = 1; i < l; ++i){
            el = laIS[i];

            if (el <= max - c)
                return false;

            if (el > max)
                max = el;
        }
        return true;
    }

}
