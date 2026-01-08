package lab14;

import lab14lib.Generator;

public class SawToothGenerator implements Generator {
    private final int period;
    private int state;
    public SawToothGenerator(int period){
        this.period = period;
        state = 0;
    }
    public double next(){
        state++;
        int phrase;
        phrase = state%period;
        return (-1+2*(double)phrase/period);
    }
}
