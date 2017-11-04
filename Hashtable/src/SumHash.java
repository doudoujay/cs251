import edu.princeton.cs.algs4.Stopwatch;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class SumHash {
    //    N^2
    public static void main(String[] args) {
        Stopwatch stopwatch = new Stopwatch();
        //    Stdin Workaround
        if (args.length > 1)
            try {
                System.setIn(new FileInputStream(args[1]));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        int size = StdIn.readInt(); //read a1
        ArrayList<Num> numArrayList = new ArrayList<Num>(size);
        for (int i = 0; i < size; i++) {
            numArrayList.add(new Num(i, StdIn.readInt()));
        }
        find(numArrayList);
        System.out.println(stopwatch.elapsedTime());
    }

    public static void find(ArrayList<Num> numArrayList) {
//Generate all the pairwise sums in O(N^2)
//        Stopwatch stopwatch = new Stopwatch();
        ArrayList<Quadruple> result = new ArrayList<Quadruple>();
        Map<Integer, ArrayList<Sum>> map = new HashMap<>(); // the arrayList contains the same sum
        for (int i = 0; i < numArrayList.size(); i++) {
            for (int j = i + 1; j < numArrayList.size(); j++) {
//                n^2 here
                int sumKey = numArrayList.get(i).getValue() + numArrayList.get(j).getValue();
                Sum sum = new Sum(numArrayList.get(i).getIndex(), numArrayList.get(j).getIndex(), numArrayList.get(i).getValue() + numArrayList.get(j).getValue());
                if (map.containsKey(sumKey)) {
//                    if key exist, append sum to sumList, which also means that >=2 sums
                    for (Sum tempSum : map.get(sumKey)
                            ) {
                        int i1 = tempSum.getIndex_1();
                        int i2 = tempSum.getIndex_2();
                        int i3 = sum.getIndex_1();
                        int i4 = sum.getIndex_2();
                        //                        Preprocess to satisfy the restriction
                        int s1 = Math.min(i1, i2);
                        int s2 = Math.max(i1, i2);
                        int s3 = Math.min(i3, i4);
                        int s4 = Math.max(i3, i4);
                        if (!(s1 < s3)) {
//                            Fix it!
                            int temp1 = s3;
                            int temp2 = s4;
                            s3 = s1;
                            s4 = s2;
                            s1 = temp1;
                            s2 = temp2;
                        }
                        if (!(s1 == s3 && s2 == s4) && (s1 < s2 && s1 < s3 && s1 < s4)) {
                            result.add(new Quadruple(s1, s2, s3, s4));
                        }
                    }
                    map.get(sumKey).add(sum); //final add this new sum to existing entry
                } else {
//                    no key existed, create a new sum list
                    ArrayList<Sum> sumArrayList = new ArrayList<Sum>();
                    sumArrayList.add(sum);
//                    pass in the hash
                    map.put(sumKey, sumArrayList);

                }

            }

        }
//        double time = stopwatch.elapsedTime();
        Collections.sort(result); //nlogn
        System.out.println(result.size());
        for (Quadruple q : result
                ) {
            System.out.println(q);
        }
//        System.out.println(time);
    }
}
