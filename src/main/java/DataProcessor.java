import java.util.*;

public class DataProcessor {

    /**
     * Resulted Longest Almost-Increasing Subsequence.
     */
    long[] lais;

    // We have to store multiple indexes for one 'x' value.
    private RedBlackBST<Long, Stack<Integer>> z;

    /**
     * When we add a new element 'x' in 'z' tree we store (in 'p' list)
     * index (the index in the original data) of an element that precedes
     * the element (that we add).
     */
    private List<Integer> p;

    private List<Long> data;
    private long c;

    public DataProcessor(List<Long> data, long c) {
        this.data = data;
        this.c = c;

        z = new RedBlackBST<>();
        p = new ArrayList<>(data.size());
    }

    public long[] getLaIS() {
        int n = data.size();

        for (int i = 0; i < n; ++i) {

            Counter.increment();

            long xi = data.get(i);

            RedBlackBST<Long, Stack<Integer>>.Node pred = z.lowerNode(xi);
            if (pred != null)
                p.add(i, pred.getVal().get(0));// store index of predecessor of xi
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
            RedBlackBST<Long, Stack<Integer>>.Node s = z.ceilingNode(xi + c);
            if (s != null) {
                Stack<Integer> stack1 = s.getVal();
                if (stack1.size() < 2)
                    z.delete(s.getKey());
                else
                    stack1.pop();
            }

        }


        // count length(LaIS)
        int l = countLaisLength(z);

        // m <- tail_node().index
        int m = z.maxNode().getVal().get(0);
        long xm = data.get(m);

        lais = new long[l];
        // A position in LaIS to put element in it.
        int positionInLais = l;

        for (int i = n - 1; i > m; --i) {

            Counter.increment();

            long xi = data.get(i);
            if (xm - c < xi && xi <= xm) {
                lais[--positionInLais] = xi;
            }
        }

        lais[--positionInLais] = xm;

        int t = m;
        int pt = p.get(t);
        while (t != pt) {

            Counter.increment();

            long xpt = data.get(pt);
            for (int i = t - 1; i > pt; --i) {

                Counter.increment();

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

    private int countLaisLength(RedBlackBST<Long, Stack<Integer>> z) {
        int l = 0;
        for (Long zi : z.keys()) {

            Counter.increment();

            l += z.get(zi).size();
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