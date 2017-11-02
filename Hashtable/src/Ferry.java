import javafx.util.Pair;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

public class Ferry {
    //    maxmize the car you can load, performance nL
    int L = 0; //Max length
    int maxK = 0;
    ArrayList<Integer> list;


    HashMap<Pair<Integer, Integer>, Boolean> visited;

    public static void main(String[] args) {
        //    Stdin Workaround
        if (args.length > 1)
            try {
                System.setIn(new FileInputStream(args[2]));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        int len = Integer.parseInt(args[0]);
        int size = StdIn.readInt(); //read a1
        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            list.add(StdIn.readInt());
        }
        Ferry ferry = new Ferry(len, list);
        ferry.search();
        System.out.println(ferry.getMaxK());


    }

    public Ferry(int length, ArrayList<Integer> list) {
        this.L = length;
        this.visited = new HashMap<>();
        this.list = list;
    }

    public void search() {
        int sumLength = 0; //Sum of the total car added length
        int k = 0; //tracking the internal number of cars
        int lenRight = 0; //tracking the internal size for right lane length
        search(sumLength, list.get(k), lenRight, k);


    }

    public void search(int sumLength, int carLen, int lenRight, int k) {
//        for every search, we mark that searched pair to visited
        Pair pair = new Pair<Integer, Integer>(k, lenRight);
        if (visited.containsKey(pair)) {
            return; //if visited, return
        } else if (lenRight > L) {
            return;
        } else if ((sumLength - lenRight) > L) {
            return;
        } else {
            visited.put(pair, true);
            boolean leftResult = left(sumLength, carLen, lenRight, k);
            boolean rightResult = right(sumLength, carLen, lenRight, k);
//            if (leftResult || rightResult) {
//                updateMaxK(k);
//            }
            updateMaxK(k);
        }


    }

    public boolean left(int sumLength, int carLen, int lenRight, int k) {
        if (sumLength - lenRight <= L) { // left sum smaller than L, and
//            place on the left

            k++; //k+1
//            updateMaxK(k);
            sumLength += carLen;
//            Recsursive call
            search(sumLength, list.get(k), lenRight, k);

            return true;
        } else {
            return false;
        }

    }

    public boolean right(int sumLength, int carLen, int lenRight, int k) {
        if (lenRight + carLen <= L) { //right sum
//            place on the right

            k++; //k+1
//            updateMaxK(k);
            sumLength += carLen;
            lenRight += carLen; //add
            //            Recsursive call
            search(sumLength, list.get(k), lenRight, k);

            return true;


        } else {
            return false;
        }


    }

    public boolean isVisited(Pair pair) {
        return visited.containsKey(pair);
    }

    public int getMaxK() {
        return maxK;
    }

    public void updateMaxK(int k) {
        if (k > maxK) { // found a larger k, update maxK
            maxK = k;
        }

    }
}
