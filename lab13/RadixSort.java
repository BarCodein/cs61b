import edu.princeton.cs.algs4.Queue;
import org.junit.Test;

/**
 * Class for doing Radix sort
 *
 * @author Akhil Batra, Alexander Hwang
 *
 */
public class RadixSort {
    /**
     * Does LSD radix sort on the passed in array with the following restrictions:
     * The array can only have ASCII Strings (sequence of 1 byte characters)
     * The sorting is stable and non-destructive
     * The Strings can be variable length (all Strings are not constrained to 1 length)
     *
     * @param asciis String[] that needs to be sorted
     *
     * @return String[] the sorted array
     */
    public static String[] sort(String[] asciis) {
        // TODO: Implement LSD Sort
        int max=0;
        for (String s:asciis){
            if (s.length()>max)
                max=s.length();
        }
        for (int i=max-1;i>=0;i--){
            sortHelperLSD(asciis,i);
        }
        return asciis;
    }

    /**
     * LSD helper method that performs a destructive counting sort the array of
     * Strings based off characters at a specific index.
     * @param asciis Input array of Strings
     * @param index The position to sort the Strings on.
     */
    private static void sortHelperLSD(String[] asciis, int index) {
        // Optional LSD helper method for required LSD radix sort
        int size= asciis.length;
        Queue<String>[] queues = new Queue[257];
        for (int i=0;i<257;i++){
            queues[i] = new Queue<>();
        }
        for (int i=0;i<size;i++){
            if (asciis[i].length()<=index) {
                queues[0].enqueue(asciis[i]);
                continue;
            }
            int num = (int) asciis[i].charAt(index);
            num = num+1;
            queues[num].enqueue(asciis[i]);
        }

        int count=0;
        for (int i=0;i<257;i++){
            while (!queues[i].isEmpty()){
                asciis[count] = queues[i].dequeue();
                count++;
            }
        }
        return ;
    }

    /**
     * MSD radix sort helper function that recursively calls itself to achieve the sorted array.
     * Destructive method that changes the passed in array, asciis.
     *
     * @param asciis String[] to be sorted
     * @param start int for where to start sorting in this method (includes String at start)
     * @param end int for where to end sorting in this method (does not include String at end)
     * @param index the index of the character the method is currently sorting on
     *
     **/
    private static void sortHelperMSD(String[] asciis, int start, int end, int index) {
        // Optional MSD helper method for optional MSD radix sort
        return;
    }
    private void print(String[] s){
        for (int i=0;i<s.length;i++){
            System.out.print(s[i]+' ');
        }
        System.out.println();
    }
    @Test
    public void test(){
        String[] ascii = new String[]{"a","asuka","langley","tokyo","beijing","newyork",
        "waterloo","China","soyo","kazega"};
        print(ascii);
        sort(ascii);
        print(ascii);
    }
}
