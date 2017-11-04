public class Num implements Comparable<Num>{
    int index;
    int value;

    public Num(int index, int value) {
        this.index = index;
        this.value = value;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "value:"+value+" index:"+index;
    }

    @Override
    public int compareTo(Num o) {
        return Integer.compare(this.value, o.getValue());
    }
}
