package mpd;

import java.util.Random;

public class ThreadedMain {

    public static void main(String[] args) {
        int numValues = Integer.parseInt(args[0]);
        ThreadedMain threadedMain = new ThreadedMain();
       threadedMain.start(numValues);


        //System.out.println("The minimum distance was " + result);
    }

    public void run(){}

//    Lower left: 0 ≤ j < i < N/2
//    Bottom right: N/2 ≤ j + N/2 < i < N
//    Top right: N/2 ≤ j < i < N
//    Center: N/2 ≤ i ≤ j + N/2 < N

    private void start(int numValues){
        MinimumPairwiseDistance mpd = new ThreadedMinimumPairwiseDistance();
        Random random = new Random();
        int[] values = new int[numValues];
        for (int i = 0; i < numValues; ++i) {
            values[i] = random.nextInt();
        }

        Thread Thread1 = new Thread(new ThreadedMain()) {
            @Override
            public void run() {
                long result = mpd.minimumPairwiseDistance(values);
            }
        };
    }

}
