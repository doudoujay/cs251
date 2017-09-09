/**
 * Created by jay on 2017/8/26.
 */

public class PercolationVisualizer {
    private static int delay = 200; //Delay for while loop animation


    //Update the UI corresponding to p
    public static void drawUI(Percolation p) {
        double unit = 1.0 / p.getSize();
        double unit_half = 0.5 / p.getSize();
        //        600*600
        StdDraw.setCanvasSize(600, 600);
        StdDraw.clear();
        StdDraw.setPenColor(StdDraw.BLACK); // set default black for block
        StdDraw.filledSquare(0.5, 0.5, 0.5); //init canvas with all blocked

//        Draw Grid
        for (int i = 0; i < p.getSize(); i++) { //row
            for (int j = 0; j < p.getSize(); j++) { //col
                if (p.isFull(i, j)) {
                    StdDraw.setPenColor(StdDraw.BOOK_BLUE);
                } else if (p.isOpen(i, j)) {
                    StdDraw.setPenColor(StdDraw.WHITE);
                } else {
                    StdDraw.setPenColor(StdDraw.BLACK);
                }
                StdDraw.filledSquare(i * unit + unit_half, j * unit + unit_half, unit_half); //draw square
            }
        }


    }

    //Wrapper func for drawUI
    public static void drawWrapper(Percolation p) {
        drawUI(p);
        StdDraw.show();
        StdDraw.pause(delay);
    }

    //simulation func
    public static void simulate(String file) {
        In inFile = new In(file); //read file
        int size = inFile.readInt(); //set size
        Percolation p = new Percolation(size);
        StdDraw.enableDoubleBuffering(); //buffer for animation
//        init draw
        drawWrapper(p);

        while (!inFile.isEmpty()) {
            int x_row = inFile.readInt();
            int y_col = inFile.readInt();
            p.open(x_row, y_col);
            drawWrapper(p);
        }


    }


    public static void main(String[] args) {
        String file = args[0];
        simulate(file);
    }
}

