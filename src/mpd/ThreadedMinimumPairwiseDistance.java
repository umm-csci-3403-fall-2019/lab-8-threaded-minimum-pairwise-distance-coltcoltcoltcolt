package mpd;

public class ThreadedMinimumPairwiseDistance implements MinimumPairwiseDistance, Runnable {

    private static long tempresult1 = Integer.MAX_VALUE;
    private static long tempresult2 = Integer.MAX_VALUE;
    private static long tempresult3 = Integer.MAX_VALUE;
    private static long tempresult4 = Integer.MAX_VALUE;


    public void run() {
    }

    public long start(int[] n) {
        int arrayLength = n.length;
        int half = (arrayLength / 2) + 1;



        Thread Thread1 = new Thread(()->{
                long result = Integer.MAX_VALUE;
                for (int i = 0; i < half; i++) {
                    for (int j = 0; j < i; j++) {
                        // 0 <= J < I < N/2 and N/2 <= J < I < N
                        long diff = Math.abs(n[i] - n[j]);
                        if (diff < result) {
                            tempresult1 = diff;
                        }
                        else
                            tempresult1 = result;
                    }
                }
        });
        Thread Thread2 = new Thread(()-> {
                long result = Integer.MAX_VALUE;
                for (int i = half; i < arrayLength; i++) {
                    for (int j = 0; (j + half) < i; j++) {
                        //  N/2 <= J+ N/2 < I < N
                        long diff = Math.abs(n[i] - n[j]);
                        if (diff < result) {
                            tempresult2 = diff;
                        }
                    }
                }
            });
        Thread Thread3 = new Thread(()-> {
                long result = Integer.MAX_VALUE;
                for (int i = 0; (i + half) < arrayLength; i++) {
                    for (int j = half; j < (i + half) - 2; j++) { //account for half-array size
                        // N/2 <= I < J + N/2 < N
                        long diff = Math.abs(n[j] - n[i]);
                        if (diff < result) {
                            tempresult3 = diff;
                        }
                    }
                }
        });
        Thread Thread4 = new Thread(()-> {
                long result = Integer.MAX_VALUE;
                for (int i = half; i < arrayLength; i++) {
                    for (int j = half; j < i; j++) {
                        // handles pairs in the ranges of 0 <= J < I < N/2 and N/2 <= J < I < N
                        long diff = Math.abs(n[i] - n[j]);
                        if (diff < result) {
                            tempresult4 = diff;
                        }
                    }
                }
            });

        Thread1.start();
        Thread2.start();
        Thread3.start();
        Thread4.start();
        try{
            Thread1.join();
            Thread2.join();
            Thread3.join();
            Thread4.join();
        }
        catch(InterruptedException IE){
            System.out.println("There was an interruption in the threads.");
        }


        long min12 = Math.min(tempresult1, tempresult2);
        long min34 = Math.min(tempresult3, tempresult4);

        return Math.min(min12,min34);

    }


    @Override
    public long minimumPairwiseDistance(int[] n) {
        ThreadedMinimumPairwiseDistance threadedPair = new ThreadedMinimumPairwiseDistance();
        return threadedPair.start(n);
    }
}


