import edu.princeton.cs.algs4.BST;
import edu.princeton.cs.algs4.BinarySearch;
import edu.princeton.cs.algs4.BinarySearchST;
import edu.princeton.cs.algs4.StdIn;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class Query {
//     O(log N + m log m)
//    N
//    Q
//complete binary tree T
    String noResult = "none";
    public void Query() {

    }
    public void getL(){

    }

    public static void main(String[] args) {
      //    Stdin Workaround
      if (args.length > 1)
        try {
          System.setIn(new FileInputStream(args[1]));
        } catch (FileNotFoundException e) {
          e.printStackTrace();
        }
      int size = StdIn.readInt(); //set size
      for (int i = 0; i < size; i++) {
        int x = StdIn.readInt();
        int y = StdIn.readInt();
        Entry entry = new Entry(x, y);

      }
      size = StdIn.readInt(); //query size
      for (int i = 0; i < size; i++) {
        int x = StdIn.readInt();
        int y = StdIn.readInt();
        Entry entry = new Entry(x, y);

      }

    }

}
