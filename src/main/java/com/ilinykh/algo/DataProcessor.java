package com.ilinykh.algo;

import java.util.*;

/**
 * Longest Almost-Increasing Subsequenc
 */
public class DataProcessor {

    /**
     * Resulted Longest Almost-Increasing Subsequence.
     */
    LinkedList<Long> lais;

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

    public DataProcessor(List<Long> data) {
        this.data = data;

        z = new TreeMap<>(comparator);
        p = new ArrayList<>(data.size());
        lais = new LinkedList<>();
    }

    public List<Long> getLaIS(long c) {
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

            System.out.println("\n>>> i=" + i);
            showMeZ(z);
        }
        // todo


        showMeZ(z);
        showMeP(p);

        return lais;
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
