import edu.princeton.cs.algs4.BST;
import edu.princeton.cs.algs4.BinarySearch;
import edu.princeton.cs.algs4.BinarySearchST;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.Stopwatch;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.*;

public class Query {
  //     O(log N + m log m)
//    N
//    Q
//complete binary tree T
  String noResult = "none";
  ArrayList<Entry> entries;
  ArrayList<Entry> queries;
  BST<Entry, ArrayList<Entry>> bst;
  TreeMap<Entry, ArrayList<Entry>> tm;
  int size;
  int query_size;

  public Query(int size) {
    this.size = size;
    this.entries = new ArrayList<Entry>(size);
    this.bst = new BST<Entry, ArrayList<Entry>>();
    this.tm = new TreeMap<Entry, ArrayList<Entry>>();

  }

  public void initQueries(int size) {
    this.query_size = size;
    this.queries = new ArrayList<Entry>(size);
  }

  public ArrayList<Entry> getQueries() {
    return queries;
  }

  public void setQueries(ArrayList<Entry> queries) {
    this.queries = queries;
  }

  public ArrayList<Entry> getEntries() {
    return entries;
  }

  public void setEntries(ArrayList<Entry> entries) {
    this.entries = entries;
  }

  public void custructTree() {
//    n logn
    for (Entry e : entries
      ) {
      tm.put(e, new ArrayList<Entry>());

    }
  }

  public void search(Entry e) {
    ArrayList<Entry> list = new ArrayList<Entry>(tm.tailMap(e, false).keySet());
    Collections.sort(list, Entry.Comparators.Y_reversed);
    ArrayList<Entry> result = new ArrayList<Entry>();
    for (Entry temp_e : list
      ) {
      if (temp_e.getY() > e.getY()) {
        result.add(temp_e);
      } else {
        break;
      }
    }
    Collections.sort(result); //sort result by x
    if (result.size() == 0) {
      System.out.println(noResult);
    } else {
      for (Entry r : result
        ) {
        System.out.println(r);
      }

    }


  }

  public static void main(String[] args) {
    Stopwatch s = new Stopwatch();
    //    Stdin Workaround
//    if (args.length > 1)
//      try {
//        System.setIn(new FileInputStream(args[1]));
//      } catch (FileNotFoundException e) {
//        e.printStackTrace();
//      }
    int size = StdIn.readInt(); //set size
    Query query = new Query(size);
    for (int i = 0; i < size; i++) {
      int x = StdIn.readInt();
      int y = StdIn.readInt();
      Entry entry = new Entry(x, y);
      query.getEntries().add(entry);

    }
    size = StdIn.readInt(); //query size
    query.initQueries(size);
    for (int i = 0; i < size; i++) {
      int x = StdIn.readInt();
      int y = StdIn.readInt();
      Entry entry = new Entry(x, y);
      query.getQueries().add(entry);

    }
    query.custructTree();
    for (Entry q : query.getQueries()
      ) {
      query.search(q);

    }

//    System.out.println(s.elapsedTime());

  }

}
