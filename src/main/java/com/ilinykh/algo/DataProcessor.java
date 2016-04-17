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
    private List<Long> p;

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

    private List<Long> sourceData;

    public DataProcessor(List<Long> sourceData) {
        this.sourceData = sourceData;

        z = new TreeMap<>(comparator);
        p = new LinkedList<>();
        lais = new LinkedList<>();
    }

    public List<Long> getLaIS() {


        // todo
        return lais;
    }

}
