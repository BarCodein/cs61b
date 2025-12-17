public class max {
    /*output the max number in the array */
    public static int ma(int[] a){
        int t =0;
        int max = 0;
        while (t<a.length)
        {
            if (a[t]>max)
            {
                max = a[t];
            }
            t++;
        }
        return max;
    }
    public static int formax(int[] a)
    {
        int sum = 0;
        for (int i = 0;i<a.length;i++)
        {
            sum = sum+a[i];
        }
        return sum;
    }
    public static void main(String[] args){
        int[] number = new int[]{9,2,15,2,23,44};
        System.out.println(ma(number));
        System.out.print(formax(number));
    }
}
