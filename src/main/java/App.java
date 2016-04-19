import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.LinkedList;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {

        System.out.println("Longest Almost-Increasing Subsequenc.");

        if (args.length < 1 || args[0] == null)
            throw new RuntimeException("Unknown data inputFile name");

        File inputFile = new File(args[0]);
        System.out.println("Source file = " + inputFile);

        long c = 2;
        if (args.length > 1 && args[1] != null)
            c = Long.parseLong(args[1]);
        System.out.println("c constant = " + c);

        String outputFilename = "LaIs-result.csv";
        if (args.length > 2 && args[2] != null)
            outputFilename = args[2];
        File outputFile = new File(outputFilename);
        System.out.println("Result file = " + outputFile);


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


        long[] laIS = new DataProcessor(sourceData, c).getLaIS();

        PrintWriter writer;
        try {
            writer = new PrintWriter(outputFile, "UTF-8");


            StringBuilder builder = new StringBuilder();
            for (long element : laIS) {
                builder.append(element).append(",");
            }
            builder.deleteCharAt(builder.length() - 1);

            writer.println(builder.toString());
            writer.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

    }

}
