import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {

    boolean[] grid;
    int N;
    int nSquared;
    WeightedQuickUnionUF wqfGrid;
    WeightedQuickUnionUF wqfFull;

    int virtualTop;
    int virtualBottom;
    int openSites;

    public Percolation(int n) {
        if (n <= 0) throw new IllegalArgumentException("enter an int >= 0");
        this.N = n;
        this.nSquared = n * n;
        this.grid = new boolean[nSquared];
        this.openSites = 0;

        wqfGrid = new WeightedQuickUnionUF(nSquared + 2); // includes virtual top and bottom
        wqfFull = new WeightedQuickUnionUF(nSquared + 1); // includes virtual top

        this.virtualTop = nSquared;
        this.virtualBottom = nSquared + 1;

        // merge top row to virtual top of both UF data structures
        for (int i = 1; i <= N; i++) {
            wqfGrid.union(i, 0);
            wqfFull.union(i, 0);
        }
        // merge bottom row to virtual bottom
        for (int i = nSquared; i > (nSquared - n); ++i) {
            wqfGrid.union(i, nSquared + 1);
        }

    }

    public void open(int row, int col) {

        if (!validate(row, col)) {
            throw new ArrayIndexOutOfBoundsException("Grid coordinates are not valid");
        }
        else if (!grid[flatCoords(row, col)]) {
            int coords = flatCoords(row, col);
            grid[coords] = true;
            openSites++;
        }

        if (isOpen(row - 1, col)) {

        }

        if (isOpen(row, col + 1)) {

        }

        if (isOpen(row + 1, col)) {

        }

        if (isOpen(row, col - 1)) {

        }
    }

    public boolean isOpen(int row, int col) {
        int coords = flatCoords(row, col);
        return grid[coords];
    }

    public boolean ifFull(int row, int col) {
        return false;
    }

    public int numberOfOpenSites() {
        return openSites;
    }

    public boolean percolates() {
        return false;
    }

    private boolean validate(int x, int y) {
        if (x < 0 && x > N) return false;
        else if (y < 0 && y > N) return false;
        return true;

    }

    private int flatCoords(int x, int y) {
        return ((x - 1) * N) + (y - 1);
    }

}
