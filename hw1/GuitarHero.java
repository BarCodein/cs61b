import synthesizer.GuitarString;

public class GuitarHero {
    public static void main(String[] args){
        GuitarString[] strings;
        String keyboard = "q2we4r5ty7u8i9op-[=zxdcfvgbnjmk,.;/' ";
        strings = new GuitarString[37];
        for (int i = 0; i < strings.length; i++) {
            strings[i] = new GuitarString(440.0 * Math.pow(2.0, (i - 24) / 12.0));
        }
        while (true) {

            /* check if the user has typed a key; if so, process it */
            if (StdDraw.hasNextKeyTyped()) {
                char key = StdDraw.nextKeyTyped();
                int index = keyboard.indexOf(key);
                if (index==-1)continue;
                strings[index].pluck();

            }
            double sample=0;
            for (GuitarString s:strings){
                sample+=s.sample();
            }
            StdAudio.play(sample);
            /* compute the superposition of samples */


            /* play the sample on standard audio */


            /* advance the simulation of each guitar string by one step */
            for (GuitarString s:strings){
                s.tic();
            }
        }
    }
}
