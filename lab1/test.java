public class test{
    /**
     * 
     * why in the end of the world can't we find love
     * @param args
     */
    public static void draw(int n){
        int t = 1;
        while (t<=n)
        {
            int j = 1;
            while (j<t)
            {
                System.out.print("*");
                j = j+1;
            }
            System.out.println();
            t = t+1;
        }
    }
    public static void main(String[] args){
        int a;
        a = 1;
        double b=3.14;
        System.out.println(a);
        String c = "Asuka";
        if (a == 1){
            System.out.println("hello world");
        }
        System.out.print(b);
        System.out.print(c);
        int t = 1;
        while (t<=5)
        {
            int j = 1;
            while (j<t)
            {
                System.out.print("*");
                j = j+1;
            }
            System.out.println();
            t = t+1;
        }
        draw(10);
}
}