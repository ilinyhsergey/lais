import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class Test {
    public static void main(String[] args) {

        long c = 10;
        int length = 1000;
        int times = 5000;
        int bound = 1000;


        if (args.length > 0 && args[0] != null)
            c = Long.parseLong(args[0]);

        if (args.length > 1 && args[1] != null)
            length = Integer.parseInt(args[1]);

        if (args.length > 2 && args[2] != null)
            times = Integer.parseInt(args[2]);

        if (args.length > 3 && args[3] != null)
            bound = Integer.parseInt(args[3]);

        checkComplexity(c, length, times, bound);
    }

    public static void checkComplexity(long c, int length, int times, int bound) {
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

                if (!validateLais(laIS, c)) {
                    writer.close();
                    throw new RuntimeException("Not a Longest Almost-Increasing Subsequenc");
                }

                writer.println("" + laIS.length + "," + Counter.get());
            }

            writer.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    public static boolean validateLais(long[] laIS, long c) {
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
