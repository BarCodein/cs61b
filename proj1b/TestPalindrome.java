import org.junit.Test;
import static org.junit.Assert.*;

public class TestPalindrome {
    // You must use this palindrome, and not instantiate
    // new Palindromes, or the autograder might be upset.
    static Palindrome palindrome = new Palindrome();

    @Test
    public void testWordToDeque() {
        Deque d = palindrome.wordToDeque("persiflage");
        String actual = "";
        for (int i = 0; i < "persiflage".length(); i++) {
            actual += d.removeFirst();
        }
        assertEquals("persiflage", actual);
    } //Uncomment this class once you've created your Palindrome class.

    @Test
    public void testIsPalindrome(){
        String t1 = "cat";
        String t2 = "carac";
        String t3 = "a";
        String t4 = "";
        String t5 = "Langley";
        String t6 = "sodudos";
        String t7 = "Asukakusa";
        assertTrue(!palindrome.isPalindrome(t1));
        assertTrue(palindrome.isPalindrome(t2));
        assertTrue(palindrome.isPalindrome(t3));
        assertTrue(palindrome.isPalindrome(t4));
        assertTrue(!palindrome.isPalindrome(t5));
        assertTrue(palindrome.isPalindrome(t6));
        assertTrue(!palindrome.isPalindrome(t7));
    }
    @Test
    public void testIsPalindromeOBY(){
        String t1 = "cat";
        String t2 = "carbb";
        String t3 = "a";
        String t4 = "";
        String t5 = "Langley";
        String t6 = "soduepr";
        String t7 = "Asukakusa";
        assertTrue(!palindrome.isPalindrome(t1,new OffByOne()));
        assertTrue(palindrome.isPalindrome(t2,new OffByOne()));
        assertTrue(palindrome.isPalindrome(t3,new OffByOne()));
        assertTrue(palindrome.isPalindrome(t4,new OffByOne()));
        assertTrue(!palindrome.isPalindrome(t5,new OffByOne()));
        assertTrue(palindrome.isPalindrome(t6,new OffByOne()));
        assertTrue(!palindrome.isPalindrome(t7,new OffByOne()));
    }
}
