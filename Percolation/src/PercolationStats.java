import java.util.stream.DoubleStream;

/**
 * Created by jay on 2017/8/26.
 */
public class PercolationStats {
    static double[] p_vals;
    static double[] stopwatch_times;

    //Random fast process, return p*
    public static double randomProcessFast(Percolation p) {
        while (!p.percolates()) {
            p.randomOpen();
        }
        double p_val = (double) p.countOpenCell() / (double) p.getGridSize();
        return p_val;
    }
    //Random slow process, return p*
    public static double randomProcessSlow(Percolation_slow p) {
        while (!p.percolates()) {
            p.randomOpen();
        }
        double p_val = (double) p.countOpenCell() / (double) p.getGridSize();
        return p_val;
    }

    public static void main(String[] args) {
        int size = Integer.parseInt(args[0]); //N
        int times = Integer.parseInt(args[1]); //T
        p_vals = new double[times];
        stopwatch_times = new double[times];
        String type = args[2]; //Type
//        Create the percolation grid
        for (int i = 0; i < times; i++) {
            Stopwatch stopwatch = new Stopwatch();
            if (type.equals("fast")) {

                Percolation p = new Percolation(size);
                double p_value = randomProcessFast(p);
//                System.out.println(p_value);
                p_vals[i] = p_value;


            } else if (type.equals("slow")) {
                Percolation_slow p = new Percolation_slow(size);
                double p_value = randomProcessSlow(p);
//                System.out.println(p_value);
                p_vals[i] = p_value;
            }
            stopwatch_times[i] = stopwatch.elapsedTime();
        }

//        Print result
        System.out.println("mean threshold=" + StdStats.mean(p_vals));
        System.out.println("std dev="+StdStats.stddev(p_vals));
        System.out.println("time="+ DoubleStream.of(stopwatch_times).sum());
        System.out.println("mean time="+ StdStats.mean(stopwatch_times));
        System.out.println("stddev time="+ StdStats.stddev(stopwatch_times));


    }


}
