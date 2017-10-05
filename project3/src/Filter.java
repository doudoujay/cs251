import edu.princeton.cs.algs4.*;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.Stopwatch;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;

public class Filter {
  ArrayList<Entry> entries;
  int size;
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
    if(args.length > 1)
      try {
        System.setIn(new FileInputStream(args[1]));
      } catch (FileNotFoundException e) {
        e.printStackTrace();
      }

    int size = StdIn.readInt(); //set size
    Filter filter = new Filter(size);
    for (int i = 0; i < size; i++) {
      int x = StdIn.readInt();
      int y = StdIn.readInt();
      Entry entry = new Entry(x,y);
      filter.getEntries().add(entry);
    }
    filter.filt();
//    Print result
    for (Entry e:filter.getEntries()
         ) {
      System.out.println(e);
    }
    System.out.println(s.elapsedTime());





  }

  public void filt(){
    ArrayList<Entry> entries_y_sorted= new ArrayList<Entry>(this.entries);
    Collections.sort(this.entries); //sort along x for entries
    Collections.sort(entries_y_sorted,Entry.Comparators.Y); //Sort along y


    int index= 0;

    for(Iterator<Entry> iter = this.entries.iterator();iter.hasNext();){
      Entry entry = iter.next();
      if(!isValid(entry,index)){
        iter.remove();
      }else{
        index++;
      }

    }
//    Get rid off the inferior
  }

  public boolean isValid(Entry entry, int index) {
//        from S every record (x[i],y[i]) for which there
// exists another record (x[j],y[j]) having both x[j] > x[i] and y[j] > y[i].
//    applied to sorted entries
    for (int i = index+1; i < this.entries.size(); i++) {
      if(entry.getY() < this.entries.get(i).getY()) return false;
    }

    return true;

  }






}
