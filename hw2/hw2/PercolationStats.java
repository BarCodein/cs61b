package hw2;

public class PercolationStats {
    private PercolationFactory pf;
    private int T;
    private int N;
    private double[] data;
    public PercolationStats(int N,int T,PercolationFactory pf){
        this.pf = pf;
        this.T = T;
        this.N = N;
        data = new double[T];
    }
    private void print(Percolation p){
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
    }
    public int[] randomOpen(Percolation p){
        int index =
                edu.princeton.cs.introcs.StdRandom.uniform(
                        N*N-p.numberOfOpenSites());
        int count = 0;
        int row=0,col=0;
        for(row=0;row<N;row++){
            for(col=0;col<N;col++){
                if(!p.isOpen(row,col)){
                    if(index == count){
                        return new int[]{row,col,index,count};
                    }
                    count++;
                }
            }
        }
        return new int[]{row,col,index,count};
    }
    public int openCountOfPercolation(){
        Percolation p = pf.make(N);
        while (!p.percolates()){
            int row,col;
            int[] re= randomOpen(p);
            row = re[0];
            col = re[1];
            if(p.isOpen(row,col))System.out.println('*');
            p.open(row,col);

        }

        return p.numberOfOpenSites();
    }
    private void sample(){
        for(int i=0;i<T;i++){
            data[i] = (double) openCountOfPercolation()/N/N;
        }
    }
    public double mean(){
        double sum = 0;
        for(int i=0;i<T;i++){
            sum+=data[i];
        }
        double mean = sum/T;
        return mean;
    }

    public double stddev(){
        double sum=0;
        double mean = mean();
        for(int i=0;i<T;i++){
            double item = data[i];
            sum+=(item-mean)*(item-mean);
        }
        return Math.sqrt(sum/(T-1));
    }
    public double confidenceLow(){
        double mean = mean();
        double sigma = stddev();
        return mean-1.96*sigma/Math.sqrt(T);

    }

    public double condifenceHigh(){
        double mean = mean();
        double sigma = stddev();
        return mean+1.96*sigma/Math.sqrt(T);
    }
}
