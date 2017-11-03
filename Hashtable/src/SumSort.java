import edu.princeton.cs.algs4.Stopwatch;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;

public class SumSort {
    //    N^2LogN
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
//        Sort, cost nlogn
//        Collections.sort(numArrayList);
        find(numArrayList);
        System.out.println(stopwatch.elapsedTime());

    }


    //    public static void find(ArrayList<Num> numArrayList) {
////        startIdx < innerStartIdx < innerEndIdx < endIdx
////        inner sum is alway <= out sum
//        int startIdx =0;
//        int endIdx = numArrayList.size()-1;
//        while(startIdx<endIdx){
//            int outSum = numArrayList.get(startIdx).getValue()+numArrayList.get(endIdx).getValue(); //out sum
//
//            int innerStartIdx = startIdx+1;
//            int innerEndIdx = endIdx-1;
//            while(innerStartIdx<innerEndIdx){
//                int innerSum = numArrayList.get(innerStartIdx).getValue()+numArrayList.get(innerEndIdx).getValue(); //inner sum
//                if(innerSum == outSum){
//                    System.out.printf("%d %d %d %d",numArrayList.get(startIdx).getIndex(), numArrayList.get(innerStartIdx).getIndex() ,numArrayList.get(innerEndIdx).getIndex() ,numArrayList.get(endIdx).getIndex());
//                    innerEndIdx--;
//                    innerStartIdx++;
//                }
//                if(innerSum>outSum){
//                    innerEndIdx--;
//                }
//                if(innerSum<outSum){
//                    innerStartIdx++;
//                }
//
//
//            }
//            return;
//
//        }
//
//    }
    public static void find(ArrayList<Num> numArrayList) {
        Stopwatch stopwatch = new Stopwatch();
        ArrayList<Quadruple> result = new ArrayList<Quadruple>();
        ArrayList<Sum> sums = new ArrayList<Sum>();
        for (int i = 0; i < numArrayList.size(); i++) {
            for (int j = i + 1; j < numArrayList.size(); j++) {
//                n^2 here
                sums.add(new Sum(numArrayList.get(i).getIndex(), numArrayList.get(j).getIndex(), numArrayList.get(i).getValue() + numArrayList.get(j).getValue()));
            }

        }
        Collections.sort(sums);
        int i = 0;
        while (i < sums.size() - 1) {
//            n^2
            if (sums.get(i).sum == sums.get(i + 1).sum) {
                int sameIndexStart = i;
                i++;
                while (sums.get(i).sum == sums.get(i + 1).sum) {
                    i++;
                }
                int sameIndexEnd = i;

                for (int j = sameIndexStart; j < sameIndexEnd; j++) {
                    for (int k = j + 1; k <= sameIndexEnd; k++) {
//                        find repeat sums, loop should only take very small amount of time
                        int i1 = sums.get(j).getIndex_1();
                        int i2 = sums.get(j).getIndex_2();
                        int i3 = sums.get(k).getIndex_1();
                        int i4 = sums.get(k).getIndex_2();
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

                }


            } else {
//                no duplicate
                i++;
            }


        }
        double time = stopwatch.elapsedTime();
        Collections.sort(result); //nlogn
        System.out.println(result.size());
        for (Quadruple q : result
                ) {
            System.out.println(q);
        }
        System.out.println(time);


    }


}
