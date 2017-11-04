public class Quadruple implements Comparable<Quadruple> {
    int A;
    int B;
    int C;
    int D;

    public int getA() {
        return A;
    }

    public void setA(int a) {
        A = a;
    }

    public int getB() {
        return B;
    }

    public void setB(int b) {
        B = b;
    }

    public int getC() {
        return C;
    }

    public void setC(int c) {
        C = c;
    }

    public int getD() {
        return D;
    }

    public void setD(int d) {
        D = d;
    }

    public Quadruple(int a, int b, int c, int d) {
        A = a;
        B = b;
        C = c;
        D = d;
    }

    @Override
    public String toString() {
        return A+" "+B+" "+C+" "+D;
    }

    @Override
    public int compareTo(Quadruple o) {
        int comp = Integer.compare(this.A, o.getA());
        if (comp != 0) {
            return comp;
        } else {
            comp = Integer.compare(this.B, o.getB());
            if (comp != 0) {
                return comp;
            } else {
                comp = Integer.compare(this.C, o.getC());
                if (comp != 0) {
                    return comp;
                } else {
                    return Integer.compare(this.D, o.getD());

                }
            }
        }
    }
}

