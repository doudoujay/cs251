import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.Stopwatch;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.*;

public class Filter {
    ArrayList<Entry> entries;
    int size;
    int maxIndex;

    public Filter(int size) {
        this.size = size;
        this.entries = new ArrayList<Entry>(size);

    }

    public ArrayList<Entry> getEntries() {
        return entries;
    }

    public void setEntries(ArrayList<Entry> entries) {
        this.entries = entries;
    }

    public static void main(String[] args) {
        Stopwatch s = new Stopwatch();
//    Stdin Workaround
//        if (args.length > 1)
//            try {
//                System.setIn(new FileInputStream(args[1]));
//            } catch (FileNotFoundException e) {
//                e.printStackTrace();
//            }

        int size = StdIn.readInt(); //set size
        Filter filter = new Filter(size);
        for (int i = 0; i < size; i++) {
            int x = StdIn.readInt();
            int y = StdIn.readInt();
            Entry entry = new Entry(x, y);
            filter.getEntries().add(entry);
        }
        filter.filt();
//    Print result
        for (Entry e : filter.getEntries()
                ) {
            System.out.println(e);
        }
        System.out.println(s.elapsedTime());


    }

    public void filt() {
        Collections.sort(this.entries, Entry.Comparators.Y_reversed); //Sort along y
        LinkedHashMap<Integer, Entry> m = new LinkedHashMap<Integer, Entry>();
        for (int i = 0; i < this.size; i++) {
            entries.get(i).setY_rank(i);
            m.put(i, entries.get(i));
        }
        Collections.sort(this.entries); //sort along x for entries

        int index = 0;
        this.maxIndex = this.size - 1;

        for (Iterator<Entry> iter = this.entries.iterator(); iter.hasNext(); ) {
            Entry entry = iter.next();
            if (!isValid(entry, m)) {
                iter.remove();
            }
            System.out.println(index + "/" + size);

            index++;
        }
//    Get rid off the inferior
    }

    public boolean isValid(Entry entry, LinkedHashMap<Integer, Entry> m) {
//        from S every record (x[i],y[i]) for which there
// exists another record (x[j],y[j]) having both x[j] > x[i] and y[j] > y[i].
//    applied to sorted entries
//    for (int i = index+1; i < this.entries.size(); i++) {
//      if(entry.getY() < this.entries.get(i).getY()) return false;
//    }
        Map.Entry<Integer,Entry> e = m.entrySet().iterator().next(); // first item

        if (entry.getY() == e.getValue().getY()) {
//            Compare entry y with the max y
            m.remove(entry.getY_rank());

            return true;
        } else {
//            Smaller than the max sorted list
            m.remove(entry.getY_rank());


            return false;
        }


    }


}
