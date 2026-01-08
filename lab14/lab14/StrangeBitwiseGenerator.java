package lab14;

import lab14lib.Generator;

public class StrangeBitwiseGenerator implements Generator {
    private final int period;
    private int state;
    public StrangeBitwiseGenerator(int period){
        this.period = period;
        state = 0;
    }
    public double next(){
        state++;
        int phrase;
        phrase = state & (state >> 7) % period;
        return (-1+2*(double)phrase/period);
    }
}
