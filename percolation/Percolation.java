/* *****************************************************************************
 *  Name:              Alan Turing
 *  Coursera User ID:  123456
 *  Last modified:     1/1/2019
 **************************************************************************** */

public class Percolation {

    boolean[] grid;
    int n;

    public Percolation(int n) {
        grid = new boolean[n * n];
        if (n <= 0) throw new IllegalArgumentException("enter an int >= 0");
    }

    public void open(int row, int col) {
    }

    public boolean isOpen(int row, int col) {
        return false;
    }

    public boolean ifFull(int row, int col) {
        return false;
    }

    public int numberOfOpenSites() {
        return 0;
    }

    public boolean percolate() {
        return false;
    }

}
