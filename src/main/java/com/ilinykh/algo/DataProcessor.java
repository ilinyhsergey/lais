package com.ilinykh.algo;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class DataProcessor {

    private File file;
    private List<Long> p;
    private TreeMap<Long, Node> z;

    private Comparator<Long> comparator = new Comparator<Long>() {
        @Override
        public int compare(Long o1, Long o2) {
            long l1 = (o1 == null) ? 0L : o1;
            long l2 = (o2 == null) ? 0L : o2;

            if (l1 < l2)
                return -1;
            else if (l1 == l2)
                return 0;
            else
                return 1;
        }
    };

    public DataProcessor(File file) {
        this.file = file;
        p = new LinkedList<Long>();
        z = new TreeMap<Long, Node>(comparator);
    }

    public List<Long> getLaIS() {
        LinkedList<Long> lais = new LinkedList<>();

        try {
            Scanner scanner = new Scanner(file);

            long i = 0;// All indexes start from 0.
            while (scanner.hasNextLong()) {
                long x = scanner.nextLong();

                System.out.println(x);// todo

                processElement(x, i++);

            }

            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return lais;
    }


    private void processElement(long x, long i) {

    }


    public static class Node {
        final long value;
        final long index;

        public Node(long value, long index) {
            this.value = value;
            this.index = index;
        }
    }
}
