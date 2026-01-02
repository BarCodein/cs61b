package hw3.hash;

import java.util.List;

public class OomageTestUtility {
    public static boolean haveNiceHashCodeSpread(List<Oomage> oomages, int M) {
        /* TODO:
         * Write a utility function that returns true if the given oomages
         * have hashCodes that would distribute them fairly evenly across
         * M buckets. To do this, convert each oomage's hashcode in the
         * same way as in the visualizer, i.e. (& 0x7FFFFFFF) % M.
         * and ensure that no bucket has fewer than N / 50
         * Oomages and no bucket has more than N / 2.5 Oomages.
         */
        int N = oomages.size();
        int[] distribution = new int[M];
        for(int i=0;i<M;i++){
            distribution[i] =0;
        }
        for(int i=0;i<N;i++){
            int code = oomages.get(i).hashCode()%M;
            code = Math.abs(code);

            //System.out.print('*');
            //System.out.println(code);
            distribution[code]++;
            //System.out.println(distribution[code]);
        }
        for(int i=0;i<M;i++){
            //System.out.println(distribution[i]);
            if (distribution[i]>N/2.5 || distribution[i]<N/50)
                return false;
        }
        return true;

    }
}
