import static org.junit.Assert.*;
import org.junit.Test;
public class TestArrayDequeGold {
    @Test
    public void testArray(){
        StudentArrayDeque<Integer> sad1 = new StudentArrayDeque<>();
        ArrayDequeSolution<Integer> rad1 = new ArrayDequeSolution<>();
        String message = new String();
        for (int i = 0; i < 10; i += 1) {
            double numberBetweenZeroAndOne = StdRandom.uniform();

            if (numberBetweenZeroAndOne < 0.5) {
                sad1.addLast(i);
                rad1.addLast(i);
                message = message + "addLast(" + i + ")\n";

            } else {
                sad1.addFirst(i);
                rad1.addFirst(i);
                message = message + "addFirst(" + i + ")\n";
            }
        }
        for (int i = 0; i < 10; i += 1) {
            double numberBetweenZeroAndOne = StdRandom.uniform();
            Integer s,r;
            if (numberBetweenZeroAndOne < 0.5) {
                s = sad1.removeLast();
                r = rad1.removeLast();
                message = message + "removeLast()\n";
            } else {
                s = sad1.removeFirst();
                r = rad1.removeFirst();
                message=message + "removeLast()\n";
            }
            assertEquals(message,s,r);
        }
    }
}
