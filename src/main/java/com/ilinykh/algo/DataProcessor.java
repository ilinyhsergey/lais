package com.ilinykh.algo;

import java.util.*;

/**
 * Longest Almost-Increasing Subsequenc
 */
public class DataProcessor {

    /**
     * Resulted Longest Almost-Increasing Subsequence.
     */
    long[] lais;

    // We have to store multiple indexes for one 'x' value.
    private TreeMap<Long, Stack<Integer>> z;

    /**
     * When we add a new element 'x' in 'z' tree we store (in 'p' list)
     * index (the index in the original data) of an element that precedes
     * the element (that we add).
     */
    private List<Integer> p;

    private Comparator<Long> comparator = new Comparator<Long>() {
        @Override
        public int compare(Long o1, Long o2) {
            long l1 = (o1 == null) ? 0L : o1;
            long l2 = (o2 == null) ? 0L : o2;

            if (l1 < l2)
                return -1;
            else if (l1 > l2)
                return 1;
            else
                return 0;
        }
    };

    private List<Long> data;
    private long c;

    public DataProcessor(List<Long> data, long c) {
        this.data = data;
        this.c = c;

        z = new TreeMap<>(comparator);
        p = new ArrayList<>(data.size());
    }

    public long[] getLaIS() {
        int n = data.size();

        for (int i = 0; i < n; ++i) {
            long xi = data.get(i);

            Map.Entry<Long, Stack<Integer>> pred = z.lowerEntry(xi);
            if (pred != null)
                p.add(i, pred.getValue().get(0));// store index of predecessor of xi
            else
                p.add(i, i);

            // insert()
            // key: xi; value: i - index of xi;
            Stack<Integer> stack = z.get(xi);
            if (stack == null) {
                stack = new Stack<>();
                z.put(xi, stack);
            }
            stack.push(i);

            // delete()
            Map.Entry<Long, Stack<Integer>> s = z.ceilingEntry(xi + c);
            if (s != null) {
                Stack<Integer> stack1 = s.getValue();
                if (stack1.size() < 2)
                    z.remove(s.getKey());
                else
                    stack1.pop();
            }

//            System.out.println("\n>>> i=" + i);
//            showMeZ(z);
        }

//        showMeZ(z);
//        showMeP(p);


        // count length(LaIS)
        int l = countLaisLength(z);
//        System.out.println("> length(LaIS) = " + l);

        // m <- tail_node().index
        int m = z.lastEntry().getValue().get(0);
        long xm = data.get(m);
//        System.out.println("> m=" + m + "    x[m]=" + xm);

        lais = new long[l];
        // A position in LaIS to put element in it.
        int positionInLais = l;

        for (int i = n - 1; i > m; --i) {
            long xi = data.get(i);
            if (xm - c < xi && xi <= xm) {
                lais[--positionInLais] = xi;
            }
        }

        lais[--positionInLais] = xm;

        int t = m;
        int pt = p.get(t);
        while (t != pt) {
            long xpt = data.get(pt);
            for (int i = t - 1; i > pt; --i) {
                long xi = data.get(i);
                if (xpt - c < xi && xi <= xpt) {
                    lais[--positionInLais] = xi;
                }
            }
            lais[--positionInLais] = xpt;
            t = pt;
            pt = p.get(t);
        }

        return lais;
    }

    private int countLaisLength(TreeMap<Long, Stack<Integer>> z) {
        int l = 0;
        for (Map.Entry<Long, Stack<Integer>> zi : z.entrySet()) {
            l += zi.getValue().size();
        }
        return l;
    }

    private void showMeZ(TreeMap<Long, Stack<Integer>> z) {
        System.out.println("____ z tree: ____");
        for (Map.Entry<Long, Stack<Integer>> zi : z.entrySet()) {
            System.out.println(zi.getKey() + "\t| " + stackToString(zi.getValue()));
        }
    }

    private String stackToString(Stack<Integer> stack) {
        int size = stack.size();
        StringBuilder builder = new StringBuilder("[");
        for (int j = 0; j < size; ++j) {
            if (j > 0)
                builder.append(", ");
            builder.append(stack.get(j));
        }
        builder.append("]");
        return builder.toString();
    }

    private void showMeP(List<Integer> p) {
        System.out.println("____ p list: ____");
        for (int pi : p) {
            System.out.println(pi + "/!");
        }
    }
}
