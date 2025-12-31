package hw2;

import org.junit.Test;
import static org.junit.Assert.*;
public class testPercolation {
    public void print(Percolation p){
        int N=5;
        for(int i=0;i<N;i++){
            for(int j=0;j<N;j++){
                System.out.print(p.fullmap[i*N+j]);
                System.out.print(' ');
            }
            System.out.println();
        }
        System.out.println("************");
        for(int i=0;i<N;i++){
            for(int j=0;j<N;j++){
                System.out.print(p.openmap[i*N+j]);
                System.out.print(' ');
            }
            System.out.println();
        }
        System.out.println("************");
    }
    @Test
    public void testP(){
        int N=5;
        Percolation p = new Percolation(N);
        assertTrue(p.isFull(0, 0));
        assertFalse(p.isFull(1, 0));
        assertFalse(p.isOpen(0,0));
        assertFalse(p.isOpen(0,1));
        p.open(0,0);
        assertTrue(p.isOpen(0,0));

        print(p);

        p.open(1,0);
        p.open(2,0);
        print(p);
        //assertTrue(p.isOpen(1,0));
        assertTrue(p.isOpen(2,0));
        assertTrue(p.isFull(1,0));
        assertTrue(p.isFull(2,0));
        assertFalse(p.percolates());
        p.open(3,0);
        p.open(3,1);
        p.open(4,1);
        print(p);
        assertTrue(p.percolates());
        print(p);
    }
}
