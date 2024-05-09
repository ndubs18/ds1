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
        for (int i = nSquared; i > (nSquared - n); --i) {
            wqfGrid.union(i, nSquared + 1);
        }

    }

    public void open(int row, int col) {

        if (!validate(row, col)) {
            throw new ArrayIndexOutOfBoundsException("Grid coordinates are not valid");
        }

        int index = flatCoords(row, col);

        if (!grid[index]) {
            grid[index] = true;
            openSites++;
        }
        else return;

        // index to union with original
        int tempIndex;

        // top
        if (validate(row - 1, col) && isOpen(row - 1, col)) {
            tempIndex = flatCoords(row - 1, col);
            wqfFull.union(index + 1, tempIndex + 1);
            wqfGrid.union(index + 1, tempIndex + 1);

        }
        // bottom
        if (validate(row + 1, col) && isOpen(row + 1, col)) {
            tempIndex = flatCoords(row + 1, col);
            wqfFull.union(index + 1, tempIndex + 1);
            wqfGrid.union(index + 1, tempIndex + 1);
        }
        // right
        if (validate(row, col + 1) && isOpen(row, col + 1)) {
            tempIndex = flatCoords(row, col + 1);
            wqfFull.union(index + 1, tempIndex + 1);
            wqfGrid.union(index + 1, tempIndex + 1);
        }
        // left
        if (validate(row, col - 1) && isOpen(row, col - 1)) {
            tempIndex = flatCoords(row, col - 1);
            wqfFull.union(index + 1, tempIndex + 1);
            wqfGrid.union(index + 1, tempIndex + 1);
        }

    }

    public boolean isOpen(int row, int col) {
        if (!validate(row, col)) {
            throw new ArrayIndexOutOfBoundsException();
        }
        int coord = flatCoords(row, col);
        return grid[coord];
    }

    public boolean isFull(int row, int col) {
        if (!validate(row, col)) {
            throw new ArrayIndexOutOfBoundsException();
        }

        if (isOpen(row, col)) {
            // get index and adjust for data structure with virtual top row (everything shifted to the right one)
            int coord = flatCoords(row, col) + 1;
            if (coord <= N) return true;

            return wqfFull.find(coord) == wqfFull.find(0);
        }

        return false;
    }

    public int numberOfOpenSites() {
        return openSites;
    }

    public boolean percolates() {
        // we don't use the isFull definition
        int virtualTop = 0;
        int virtualBottom = N * N + 1;

        // corner case: 1 site
        if (virtualBottom == 2) {
            return isOpen(1, 1);
        }

        // corner case: no sites
        if (virtualBottom == 0) {
            return false;
        }

        return wqfGrid.find(virtualTop) == wqfGrid.find(virtualBottom);
    }

    private boolean validate(int x, int y) {
        if ((x < 1 || x > N) || (y < 1 || y > N)) return false;
        return true;

    }

    private int flatCoords(int x, int y) {
        return ((x - 1) * N) + (y - 1);
    }

}
