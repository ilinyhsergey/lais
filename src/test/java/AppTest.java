import org.junit.Test;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

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
        long[] laIS = new DataProcessor(dataTyped, c).getLaIS();
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
        long[] laIS;
        Random generator = new Random();
        PrintWriter writer;

        try {
            writer = new PrintWriter("result-5000.csv", "UTF-8");
            writer.println("LaIS_length,Iteration_count");

            for (int j = 0; j < times; ++j) {

                dataTyped = new ArrayList<>(length);
                for (int i = 0; i < length; ++i) {
                    dataTyped.add((long) generator.nextInt(bound));
                }

                Counter.reset();
                laIS = new DataProcessor(dataTyped, c).getLaIS();
                assertTrue(validateLais(laIS, c));
                writer.println("" + laIS.length + "," + Counter.get());
            }

            writer.close();

        } catch (IOException e) {
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
