import org.junit.Test;
import static org.junit.Assert.*;

public class TestOffByOne {

    // You must use this CharacterComparator and not instantiate
    // new ones, or the autograder might be upset.
    static CharacterComparator offByOne = new OffByN(5);

    @Test
    public void testOfByOne(){
        char t11 = 'a',t12 = 'b';
        char t21 = 'A',t22 = 'a';
        char t31 = 'd',t32 = 'd';
        char t41 = '&',t42 = '%';
        char t51 = 'f',t52 = 'a';
        assertFalse(offByOne.equalChars(t11,t12));
        assertFalse(offByOne.equalChars(t21,t22));
        assertFalse(offByOne.equalChars(t31,t32));
        assertFalse(offByOne.equalChars(t41,t42));
        assertTrue(offByOne.equalChars(t51,t52));
    }
    // Your tests go here.
    //Uncomment this class once you've created your CharacterComparator interface and OffByOne class. **/
}
