
import java.util.Comparator;

public class Entry implements Comparable<Entry> {
  private int x;
  private int y;
  private int y_rank;


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

  public int getY_rank() {
    return y_rank;
  }

  public void setY_rank(int y_rank) {
    this.y_rank = y_rank;
  }

  @Override
  public int compareTo(Entry o) {
    return Comparators.X.compare(this, o);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    Entry entry = (Entry) o;

    if (x != entry.x) return false;
    return y == entry.y;
  }

  @Override
  public int hashCode() {
    int result = x;
    result = 31 * result + y;
    return result;
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
    public static Comparator<Entry> Y_reversed = new Comparator<Entry>() {
      @Override
      public int compare(Entry o1, Entry o2) {
        return Integer.compare(o2.getY(),o1.getY());
      }
    };
  }
}
