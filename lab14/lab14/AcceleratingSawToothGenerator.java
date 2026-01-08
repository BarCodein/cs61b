package lab14;

import lab14lib.Generator;

public class AcceleratingSawToothGenerator implements Generator {
    private int period;
    private int state;
    private double arg;
    public AcceleratingSawToothGenerator(int period,double arg){
        this.period = period;
        this.arg = arg;
        state = 0;
    }
    private void update(){
        state++;
        if (state == period){
            state = 0;
            period = (int)Math.round(period*arg);
        }
    }
    private double normal(){
        return -1+2*((double)state/period);
    }
    @Override
    public double next(){
        update();
        double normal = normal();
        return normal;
    }
}
