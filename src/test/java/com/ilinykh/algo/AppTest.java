package com.ilinykh.algo;


import org.junit.Test;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.TreeMap;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertTrue;

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
        long[] laIS = new App.DataProcessor(dataTyped, c).getLaIS();
        assertArrayEquals(laIS, new long[]{2, 6, 8, 11, 14, 13});
        assertTrue(validateLais(laIS, c));
    }


    @Test
    public void checkComplexity() {
        long c = 10;
        int length = 1000;
        int times = 5000;
        int bound = 1000;

        List<Long> dataTyped;
        long start, fin, time;
        long[] laIS;
        Random generator = new Random();
        PrintWriter writer;

        long[] lenArr = new long[times];
        long[] timeArr = new long[times];

        try {
            writer = new PrintWriter("result-5000.csv", "UTF-8");
            writer.println("length(LaIS),Time in nanos");

            for (int j = 0; j < times; ++j) {

                dataTyped = new ArrayList<>(length);
                for (int i = 0; i < length; ++i) {
                    dataTyped.add((long) generator.nextInt(bound));
                }
                App.DataProcessor dataProcessor = new App.DataProcessor(dataTyped, c);

                start = System.nanoTime();
                laIS = dataProcessor.getLaIS();
                fin = System.nanoTime();
                time = fin - start;

                assertTrue(validateLais(laIS, c));


                writer.println("" + laIS.length + "," + time);

                lenArr[j] = laIS.length;
                timeArr[j] = time;
            }

            writer.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }


    }

    public boolean validateLais(long[] laIS, long c) {
        int l = laIS.length;
        long max = laIS[0];
        long el;

        for (int i = 1; i < l; ++i) {
            el = laIS[i];

            if (el <= max - c)
                return false;

            if (el > max)
                max = el;
        }
        return true;
    }

}
