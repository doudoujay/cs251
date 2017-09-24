import edu.princeton.cs.algs4.InsertionX;

/**
 * Created by jay on 2017/9/13.
 */
public class Quick3wayBM_V{

    public static void main(String[] args) {
        try {
            String file = args[0];
            In inFile = new In(file); //read file
            int size = inFile.readInt(); //set size
            Integer[] a = new Integer[size];
            int i = 0;
            while (!inFile.isEmpty()) {
                a[i] = inFile.readInt();
                i++;
            }
            System.out.println("(<Improvement_used>, <lo>, <v>, <p>, <i>, <j>, <q>, <hi>)");
            Quick3wayBM.sort(a,true);




        } catch (ArrayIndexOutOfBoundsException e) {
            e.printStackTrace();
        }
    }
}
