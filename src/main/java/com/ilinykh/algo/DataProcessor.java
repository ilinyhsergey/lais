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

    private TreeMap<Long, Integer> z;

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

            Map.Entry<Long, Integer> pred = z.lowerEntry(xi);
            if (pred != null) {
                p.add(i, pred.getValue());// store index of predecessor of xi
            } else {
                p.add(i, i);
            }
            z.put(xi, i); // key: xi; value: i - index of xi;


            Long s = z.ceilingKey(xi + c);
            if (s != null) {
                z.remove(s);
            }

            System.out.println("\n>>> i=" + i);
            showMeZ(z);
        }
        // todo


        showMeZ(z);
        showMeP(p);

        return lais;
    }

    private void showMeZ(TreeMap<Long, Integer> z) {
        System.out.println("____ z tree: ____");
        for (Map.Entry<Long, Integer> zi : z.entrySet()) {
            System.out.println(zi.getKey() + "\t| " + zi.getValue());
        }
    }

    private void showMeP(List<Integer> p) {
        System.out.println("____ p list: ____");
        for (int pi : p) {
            System.out.println(pi + "/!");
        }
    }
}
