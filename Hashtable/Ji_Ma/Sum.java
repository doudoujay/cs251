public class Sum implements Comparable<Sum>{
    int index_1;
    int index_2;
    int sum;

    public Sum(int index_1, int index_2, int sum) {
        this.index_1 = index_1;
        this.index_2 = index_2;
        this.sum = sum;
    }

    public int getIndex_1() {
        return index_1;
    }

    public void setIndex_1(int index_1) {
        this.index_1 = index_1;
    }

    public int getIndex_2() {
        return index_2;
    }

    public void setIndex_2(int index_2) {
        this.index_2 = index_2;
    }

    public int getSum() {
        return sum;
    }

    public void setSum(int sum) {
        this.sum = sum;
    }

    @Override
    public String toString() {
        return "idx1: "+index_1+" idx2: "+index_2+" sum: "+sum;
    }

    @Override
    public int compareTo(Sum o) {
        return Integer.compare(this.sum,o.getSum());
    }
}
