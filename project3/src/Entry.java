
import java.util.Comparator;

public class Entry implements Comparable<Entry> {
  private int x;
  private int y;


  public Entry(int x, int y) {
    this.x = x;
    this.y = y;
  }

  public int getX() {
    return x;
  }

  public void setX(int x) {
    this.x = x;
  }

  public int getY() {
    return y;
  }

  public void setY(int y) {
    this.y = y;
  }

  @Override
  public int compareTo(Entry o) {
    return Comparators.X.compare(this, o);
  }

  @Override
  public String toString() {
    return x + " " + y;
  }

  public static class Comparators {
    public static Comparator<Entry> X = new Comparator<Entry>() {
      @Override
      public int compare(Entry o1, Entry o2) {
        return Integer.compare(o1.getX(), o2.getX());
      }
    };

    public static Comparator<Entry> Y = new Comparator<Entry>() {
      @Override
      public int compare(Entry o1, Entry o2) {
        return Integer.compare(o1.getY(), o2.getY());
      }
    };
  }
}
