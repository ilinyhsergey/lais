import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.*;

public class App {
    public static void main(String[] args) {

        System.out.println("Longest Almost-Increasing Subsequenc.");

        if (args.length < 1 && args[0] != null)
            throw new RuntimeException("Unknown data inputFile name");

        File inputFile = new File(args[0]);


        System.out.println("Source data inputFile: " + inputFile);

        LinkedList<Long> sourceData = new LinkedList<>();

        try {
            Scanner scanner = new Scanner(inputFile);
            scanner.useDelimiter("[\\s,]+");
            while (scanner.hasNextLong()) {
                sourceData.add(scanner.nextLong());
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }


        if (sourceData.isEmpty())
            throw new RuntimeException("SourceData is empty.");

        long c;
        if (args.length > 1 && args[1] != null)
            c = Long.parseLong(args[1]);
        else
            c = 2;

        long[] laIS = new DataProcessor(sourceData, c).getLaIS();

        PrintWriter writer;
        try {
            if (args.length > 2 && args[2] != null) {
                File outputFile = new File(args[2]);
                writer = new PrintWriter(outputFile, "UTF-8");
            } else {
                writer = new PrintWriter("output.csv", "UTF-8");
            }


            StringBuilder builder = new StringBuilder();
            for (long element : laIS) {
                builder.append(element).append(" ");
            }

            writer.println(builder.toString());
            writer.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

    }

}
