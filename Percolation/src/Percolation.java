

/**
 * Created by jay on 2017/8/26.
 */

public class Percolation {
    /* States Int
    Blocked - 0
    Open - 1
     */
    int[][] grid; //2D array
    int size;
    boolean isFlowed;
    WeightedQuickUnionUF qf; //Weighted Quick Find for grading
    int count;
    int top;
    int bottom;

    //    Create a new n by n grid where all cells are initially blocked
    public Percolation(int n) {
        size = n;
        grid = new int[n][n];
        isFlowed = false;
        qf = new WeightedQuickUnionUF(size * size + 2);
        top = size * size;
        bottom = size * size + 1;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                grid[i][j] = 0; //Set all blocked
            }
        }
    }

    //Open the site at coordinate (x,y), where x represents the horizontal axis and y the vertical one.
    //For consistency purposes, (0,0) will be the bottom-left cell of the grid and (n-1,n-1) will be on the top-right.
    //The graphical capabilities discussed later assume a similar convention.
    public void open(int x, int y) {
        if (isOpen(x, y)) {
            return;
        }
        validateIndex(x, y);
        grid[x][y] = 1;
        count++;
//        Flow the water while open the entry
        if (y == size - 1) { // quick top connect
            qf.union(convertIndex(x, y), top);
        }
        if (y == 0) { // quick bottom connect
            qf.union(convertIndex(x, y), bottom);
        }
//        Left
        if (x - 1 >= 0 && isOpen(x - 1, y)) {
            qf.union(convertIndex(x, y), convertIndex(x - 1, y));
        }
//        Right
        if (x + 1 < size && isOpen(x + 1, y)) {
            qf.union(convertIndex(x, y), convertIndex(x + 1, y));
        }
//        Down
        if (y - 1 >= 0 && isOpen(x, y - 1)) {
            qf.union(convertIndex(x, y), convertIndex(x, y - 1));
        }
//        Up
        if (y + 1 < size && isOpen(x, y + 1)) {
            qf.union(convertIndex(x, y), convertIndex(x, y + 1));
        }

    }

    //    Returns true if cell (x,y) is open due to a previous call to open(int x, int y)
    public boolean isOpen(int x, int y) {
        validateIndex(x, y);
        return (1 == grid[x][y]);
    }

    //    Returns true if there is a path from cell (x,y) to the surface (i.e. there is percolation up to this cell)
    public boolean isFull(int x, int y) {
        validateIndex(x, y);
        if (isOpen(x, y)) {
//            connect to top row opened considered as full

            if (qf.connected(top, convertIndex(x, y))) {
                return true;
            }
        }


        return false;
    }

    //    Analyzes the entire grid and returns true if the whole system percolates
    public boolean percolates() {
//if any entry of the bottom is full,
// considered as perlocate

        boolean result = qf.connected(top, bottom);


        return result;
    }

    //    Create a main method that reads a description of a grid from standard input (using StdIn.java)
    // and validates if the system described percolates or not, printing to standard output
    // a simple "Yes" or "No" answer (using StdOut.java).
    public static void main(String[] args) {
        try {
            String file = args[0];
            In inFile = new In(file); //read file
            int size = inFile.readInt(); //set size
            Percolation p = new Percolation(size);
            while (!inFile.isEmpty()) {
                int x_row = inFile.readInt();
                int y_col = inFile.readInt();
                p.open(x_row, y_col);
            }
            if (p.percolates()) {
                System.out.println("Yes");
            } else {
                System.out.println("No");
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            e.printStackTrace();
        }

    }

    //  x and y should be in the grid
    public void validateIndex(int x, int y) throws IndexOutOfBoundsException {
        if (x < 0 || x > size - 1 || y < 0 || y > size - 1) {
            throw new IndexOutOfBoundsException("The Index is out of bound");
        }
    }

    // craeta a unique index for each grid entry
    //for example, if 4*4
    //Then, 0~15
    public int convertIndex(int x, int y) {
        return y * size + x;

    }

//    //  Get the environment started for the full water places
//    public void flowWater() {
//        isFlowed = true;
////        start from top
//        for (int i = 0; i < size; i++) {
//            if (grid[0][i] == 1){
//
//            }
//        }
//    }

    public int getSize() {
        return size;
    }

    public int getGridSize() {
        return size * size;
    }

    //count the open cells amount
    public int countOpenCell() {
        return count;
    }

    public void randomOpen() {
        int x = StdRandom.uniform(size);
        int y = StdRandom.uniform(size);
        open(x, y);

    }
}






