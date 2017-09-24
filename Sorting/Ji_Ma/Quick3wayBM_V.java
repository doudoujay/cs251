import edu.princeton.cs.algs4.*;
import edu.princeton.cs.algs4.StdIn;

import java.util.Arrays;

/**
 * Created by jay on 2017/9/13.
 */
public class Quick3wayBM_V{

    public static void main(String[] args) {
        try {
            int size = edu.princeton.cs.algs4.StdIn.readInt(); //set size
            Integer[] a = new Integer[size];
            a = Arrays.stream( StdIn.readAllInts() ).boxed().toArray( Integer[]::new );
            System.out.println("(<Improvement_used>, <lo>, <v>, <p>, <i>, <j>, <q>, <hi>)");
            Quick3wayBM.sort(a,true);




        } catch (ArrayIndexOutOfBoundsException e) {
            e.printStackTrace();
        }
    }
}
