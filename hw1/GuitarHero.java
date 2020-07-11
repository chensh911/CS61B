import edu.princeton.cs.algs4.StdAudio;
import synthesizer.GuitarString;


public class GuitarHero {
    private static String keyboard = "q2we4r5ty7u8i9op-[=zxdcfvgbnjmk,.;/' ";

    public static void main(String[] args) {
        synthesizer.GuitarString[] sgs = new synthesizer.GuitarString[37];
        for (int i = 0; i < keyboard.length(); i += 1) {
            double frequency = 440 * Math.pow(2, (i - 24) * 0.5);
            sgs[i] = new GuitarString(frequency);
        }
        while (true) {
            if (StdDraw.hasNextKeyTyped()) {
                char key = StdDraw.nextKeyTyped();
                if (keyboard.indexOf(key) != -1) {
                    sgs[keyboard.indexOf(key)].pluck();
                }
            }
            //compute superposition
            double sample = 0;
            for (GuitarString s: sgs) {
                sample += s.sample();
            }
            //play
            StdAudio.play(sample);
            //tic
            for (GuitarString s: sgs) {
                s.tic();
            }
        }
    }
}
