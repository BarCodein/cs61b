public class reak {
    public static void windows(int[] a, int n)
    {
        for (int i=0;i<a.length;i++)
        {
            if (a[i]<0){
                continue;
            }
            int j = i;
            int sum=0;
            while (j<=i+n)
            {
                if (j==a.length)
                {
                    break;
                }
                sum = sum+a[j];
                j++;
            }
            a[i]= sum;
        }
    }
    public static void main(String[] args)
    {
        int[] a = {1, 2, -3, 4, 5, 4};
        int n=3;
        windows(a, n);
        System.out.print(java.util.Arrays.toString(a));
    }
    
}
