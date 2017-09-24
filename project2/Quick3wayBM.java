import edu.princeton.cs.algs4.*;
import edu.princeton.cs.algs4.StdIn;

import java.util.ArrayList;
import java.util.Arrays;

public class Quick3wayBM {
    static int insertion_cutoff = 8;
    static int median_of_three_cutoff = 40;
    static boolean print_trace = false;

    public static void sort(Comparable[] a) {
        sort(a, 0, a.length - 1);
    }

    public static void sort(Comparable[] a, int lo, int hi) {
        int length = hi - lo + 1;
        boolean Median_of_3 = false;
        boolean Tukey = false;


        if (length <= insertion_cutoff) {
            // Insetion Sort
            if (!print_trace) {
                System.out.format("(Insertion Sort, %d, %d) %n", lo, hi);
            }
            InsertionX.sort(a);
//            No more operation needed
            return;
        } else if (length <= median_of_three_cutoff) {
            // Median of 3

            Median_of_3 = true;
            int interval = (length - 1) / 2;
            int median = median_of_3(a, lo, lo + interval, lo + 2 * interval); //get median
            swap(a, median, lo);  //swap

        } else {
            // Use Tukey

            Tukey = true;
            int interval = (length - 1) / 8;
            int m_1 = median_of_3(a, lo, lo + interval, lo + 2 * interval);
            int m_2 = median_of_3(a, lo + 3 * interval, lo + 4 * interval, lo + 5 * interval);
            int m_3 = median_of_3(a, lo + 6 * interval, lo + 7 * interval, lo + 8 * interval);
            int final_median = median_of_3(a, m_1, m_2, m_3);
            swap(a, final_median, lo);
        }


        // BM 3 way partitioning
        int i = lo, j = hi + 1, p = lo, q = hi + 1;
        Comparable value = a[lo];


        while (true) {
            while (less(a[++i], value)) { //++ until there is a element is not less than v (skip the less elements)
                if (hi == i) { // the end of increment
                    break;
                }
            }
            while (greater(a[--j], value)) { //-- until there is a element is not greater than v (slip the greater elements)
                if (lo == j) { // the end of decrement
                    break;
                }
            }

            //you should treat (i == j) as a special case where you should just simply advance j by one and break the loop.
            if (i == j) {
                if (equal(a[j], value)) {
                    swap(a, j, --q);
                }
                j--;


                break;
            }
            if (i > j) break; //The partitioning should terminate when (i >= j)


            //swap operations

            swap(a, i, j); //swap i: not less than v and j: not greater than v
            if (equal(a[i], value)) {
                swap(a, i, ++p);
            }
            if (equal(a[j], value)) {
                swap(a, j, --q);
            }
        }

        //            Print log
        if (!print_trace) {
            if (Median_of_3) {
                System.out.format("(Median of 3, %d, %d, %d, %d, %d, %d, %d) %n", lo, value, p, i, j, q, hi);
            } else if (Tukey) {
                System.out.format("(Tukey Ninther, %d, %d, %d, %d, %d, %d, %d) %n", lo, value, p, i, j, q, hi);
            }
        }

//        swap the items with equal keys into position

        for (int index = lo; index <= p; index++) {
            swap(a, index, j--); //swap left side =v with middle side <v
        }
        for (int index = hi; index >= q; index--) {
            swap(a, index, i++); //swap right side =v with middle side >v
        }


//        Debug
//        System.out.println((p-lo)+(hi-q+1));
//        System.out.println(getK(a,value)-1);
//        print_array(a);

        // print trace for V
        if (print_trace) {
            print_array(a);
        }
//        Repeat this process recursively for left <v and right >v
        sort(a, lo, j);
        sort(a, i, hi);
    }

    public static void sort(Comparable[] a, boolean trace_bool) {
        print_trace = trace_bool;
        sort(a);
    }

    public static void main(String[] args) {
        try {
//            if (!args[0].equals("<")){
//                System.out.println("Invalid");
//                return;
//            }
//            String file = args[1];

            int size = StdIn.readInt(); //set size
            Integer[] a = new Integer[size];
            a = Arrays.stream( StdIn.readAllInts() ).boxed().toArray( Integer[]::new );
            System.out.println("(<Improvement_used>, <lo>, <v>, <p>, <i>, <j>, <q>, <hi>)");
            sort(a);




        } catch (ArrayIndexOutOfBoundsException e) {
            e.printStackTrace();
        }
    }

    //    Helper functions :)
    public static boolean less(Comparable a, Comparable b) {
        return a.compareTo(b) < 0;

    }

    public static boolean equal(Comparable a, Comparable b) {
        return a.compareTo(b) == 0;

    }

    public static boolean greater(Comparable a, Comparable b) {
        return a.compareTo(b) > 0;

    }

    public static void swap(Object[] a, int i, int j) {
        Object temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }

    //return the index of median among three elements
    public static int median_of_3(Comparable[] a, int i, int j, int k) {
        return (less(a[i], a[j]) ?
                (less(a[j], a[k]) ? j : less(a[i], a[k]) ? k : i) :
                (less(a[k], a[j]) ? j : less(a[k], a[i]) ? k : i));
    }

    public static void print_array(Comparable[] a) {
        for (int j = 0; j < a.length; j++) {
            System.out.print(a[j] + " ");
            if (j == a.length - 1) {
                System.out.println("");
            }
        }

    }


    public static int getK(Comparable[] as, Comparable v) {
        int temp = 0;
        for (int i = 0; i < as.length; i++) {
            if (as[i].compareTo(v) == 0) {
                temp++;
            }
        }
        return temp;
    }
}
