package hw2;
import org.junit.Test;
import static org.junit.Assert.*;
public class testPercolationStats {
    @Test
    public void testP(){
        PercolationStats p = new PercolationStats(5,500,new PercolationFactory());
        for(int i=0;i<100;i++){
            System.out.println(p.mean());
        }

    }
}
