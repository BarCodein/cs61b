package synthesizer;
import edu.princeton.cs.algs4.In;
import org.junit.Test;
import static org.junit.Assert.*;

/** Tests the ArrayRingBuffer class.
 *  @author Josh Hug
 */

public class TestArrayRingBuffer {
    @Test
    public void someTest() {
        ArrayRingBuffer<Integer> arb = new ArrayRingBuffer<Integer>(10);
        assertEquals(arb.peek(),null);
        assertEquals(arb.capacity(),10);
        assertEquals(arb.fillCount(),0);
        assertEquals(arb.dequeue(),null);
        arb.enqueue(1);
        arb.enqueue(2);
        assertEquals(arb.peek(),(Integer) 1);
        assertEquals(arb.dequeue(),(Integer) 1);
        assertEquals(arb.capacity(),10);
        assertEquals(1,arb.fillCount());
        arb.enqueue(3);
        arb.enqueue(4);
        arb.enqueue(5);
        arb.enqueue(6);
        arb.dequeue();
        arb.dequeue();
        arb.dequeue();
        arb.dequeue();
        assertEquals((Integer) 6,arb.peek());
        assertEquals((Integer) 6,arb.dequeue());
    }

    /** Calls tests for ArrayRingBuffer. */
    public static void main(String[] args) {
        jh61b.junit.textui.runClasses(TestArrayRingBuffer.class);
    }
} 
