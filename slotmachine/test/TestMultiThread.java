package slotmachine.test;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TestMultiThread {
    private static int finishedThreadCount = 0;
    private static int mainResult = 0;
    static int numOfAvailableThreads = Runtime.getRuntime().availableProcessors();
    public static void main(String[] args) {

        ExecutorService executorService = Executors.newFixedThreadPool(numOfAvailableThreads);
        for (int i = 0; i < numOfAvailableThreads; i++) {
            executorService.submit(() -> doSum(1, 0));
        }

    }

    private static void doSum(int i, int j) {
        int rtpResult = i + j;
        System.out.println(rtpResult);
        addToRTPResult(rtpResult);

    }

    private static void addToRTPResult(int result) {
        ++finishedThreadCount;
        mainResult += result;

        if(finishedThreadCount == numOfAvailableThreads){
            System.out.println("thread running  " + finishedThreadCount + " out of " + numOfAvailableThreads);
            System.out.println("Add up result is " +  mainResult);

        }
    }
}
