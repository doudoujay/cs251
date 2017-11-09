import java.util.Collection;
import java.util.Iterator;

public class Utils {
    public static int FindSmallest (int [] arr1){//start method

        int index = 0;
        int min = arr1[index];
        for (int i=1; i<arr1.length; i++){

            if (arr1[i] < min ){
                min = arr1[i];
                index = i;
            }


        }
        return index ;

    }
    public static int iterableSize(Iterable<?> it) {
        if (it instanceof Collection)
            return ((Collection<?>)it).size();

        // else iterate

        int i = 0;
        for (Object obj : it) i++;
        return i;
    }

}
