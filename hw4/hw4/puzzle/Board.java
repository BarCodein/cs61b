package hw4.puzzle;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Board implements WorldState{

    private int[][] tiles;
    public Board(int[][] tiles){
        this.tiles = new int[tiles.length][tiles.length];
        for (int i=0;i<size();i++){
            for (int j=0;j<size();j++){
                this.tiles[i][j] = tiles[i][j];
            }
        }
    }
    public int tileAt(int i,int j){
        if (i<0 || i>=size() || j<0 || j>=size()){
            throw new java.lang.IndexOutOfBoundsException();
        }
        return tiles[i][j];
    }
    public int size(){
        return tiles.length;
    }
    private boolean inlegal(int x,int y){
        if (x>=size() || x<0 || y>=size() || y<0)
            return true;
        return false;
    }
    private int[][] neibhbor(int[][] previous,int posx,int posy,int cx,int cy){
        int tx = posx+cx;
        int ty = posy+cy;
        if (inlegal(tx,ty))
            return null;
        int[][] re = new int[size()][size()];
        for (int i=0;i<size();i++){
            for (int j=0;j<size();j++){
                re[i][j] = previous[i][j];
            }
        }

        int tmp;
        tmp = re[tx][ty];
        re[tx][ty] = re[posx][posy];
        re[posx][posy] = tmp;
        return re;
    }
    @Override
    public Iterable<WorldState> neighbors(){
        int x=-1,y=-1;
        for (int i=0;i<size();i++){
            for (int j=0;j<size();j++){
                if (tileAt(i,j)==0)
                    x=i;y=j;
            }
        }
        List<WorldState> list = new ArrayList<>();
        int[][] dir = new int[][]{{0,1},{0,-1},{1,0},{-1,0}};
        for (int i=0;i<4;i++){
            Board newstate = new Board(neibhbor(tiles,x,y,dir[i][0],dir[i][1]));
            list.add(newstate);
        }
        class tileIterable implements Iterable<WorldState>{
            @Override
            public Iterator<WorldState> iterator() {
                return list.iterator();
            }
        }
        return new tileIterable();
    }
    public int hamming(){
        int sum=0;
        int index=1;
        for (int i=0;i<size();i++){
            for (int j=0;j<size();j++){
                if (tiles[i][j]!=index)
                    sum++;
                index++;
            }
        }
        return sum;
    }
    private int distance(int i,int j,int num){
        int posi = num/size();
        int posj = num%size();
        return Math.abs(posi-i)+Math.abs(posj-j);
    }
    public int manhattan(){
        int sum=0;
        for (int i=0;i<size();i++){
            for (int j=0;j<size();j++){
                sum+=distance(i,j,tiles[i][j]);
            }
        }
        return sum;
    }
    @Override
    public int estimatedDistanceToGoal() {
        return 0;
    }
    private boolean equal(Board o){
        for (int i=0;i<size();i++){
            for (int j=0;j<size();j++){
                if (this.tiles[i][j] != o.tiles[i][j])
                    return false;
            }
        }
        return true;
    }
    @Override
    public boolean equals(Object obj) {
        if (obj == null)
            return false;
        if (this.getClass()!=obj.getClass())
            return false;
        if (this == obj)
            return true;
        return equal((Board) obj);
    }
    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        int N = size();
        s.append(N + "\n");
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                s.append(String.format("%2d ", tileAt(i,j)));
            }
            s.append("\n");
        }
        s.append("\n");
        return s.toString();
    }

}
