package hw2;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private WeightedQuickUnionUF grid;
    private final int n;
    public boolean[] openmap;
    public boolean[] fullmap;
    private int numberofopensite;
    public Percolation(int n){
        if (n<=0)
            throw new java.lang.IllegalArgumentException();
        grid = new WeightedQuickUnionUF(n*n);
        openmap = new boolean[n*n];
        fullmap = new boolean[n*n];
        this.n = n;
        numberofopensite = 0;
        for(int i=0;i<n*n;i++){
            fullmap[i] = false;
            openmap[i] = false;
        }
        for(int i=0;i<n;i++){
            fullmap[i] = true;
        }
    }

    private int index(int row,int col){
        if(row>=n || row<0 || col>=n ||col<0)
            return -1;
        int index = row*n+col;
        return index;
    }

    public void open(int row,int col){
        if(row>=n || row<0 || col>=n ||col<0)
            throw new java.lang.IndexOutOfBoundsException();
        int index = index(row, col);
        if (openmap[index])return;
        openmap[index] = true;
        numberofopensite++;
        int[][] dir = new int[][]{{1,0},{-1,0},{0,1},{0,-1}};

        for(int i=0;i<4;i++){
            int index1 = index(row+dir[i][0],col+dir[i][1]);
            if(index1 == -1)continue;
            if(!openmap[index1])continue;
            int p1 = grid.find(index1);
            int p = grid.find(index);
            grid.union(index1,index);
            if (fullmap[p] || fullmap[index1] || fullmap[index] || fullmap[p1]){
                fullmap[p] = true;
                fullmap[p1] = true;
                fullmap[index] = true;
                fullmap[index1] = true;
            }
        }

    }

    public boolean isOpen(int row,int col){
        if(row>=n || row<0 || col>=n ||col<0)
            throw new java.lang.IndexOutOfBoundsException();
        return openmap[index(row, col)];
    }

    public boolean isFull(int row,int col){
        if(row>n || row<0 || col>n ||col<0)
            throw new java.lang.IndexOutOfBoundsException();
        int index = index(row, col);
        return fullmap[grid.find(index)];
    }

    public int numberOfOpenSites(){
        return numberofopensite;
    }

    public boolean percolates(){
        for(int i=0;i<n;i++){
            if (isFull(n-1,i))return true;
        }
        return false;
    }

    public static void main(String[] args) {
        Percolation p = new Percolation(5);

    }
}
