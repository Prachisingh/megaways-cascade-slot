package slotmachine.test;

import slotmachine.FreeSpins;
import slotmachine.SlotMachine;
import slotmachine.Spin;
import slotmachine.service.WeightedPrizeService;

import java.math.BigDecimal;
import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class RTPTest {

    static int runs = 1000_000;
    static int finishedCount = 0;
    static long startingTime;
    static BigDecimal totalWins = BigDecimal.ZERO;


    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService;

        int numOfAvailableThreads = Runtime.getRuntime().availableProcessors();
        System.out.println("available number of threads =  " + numOfAvailableThreads);
        startingTime = System.currentTimeMillis();
        executorService = Executors.newFixedThreadPool(numOfAvailableThreads);
        int stake1 = 1;
        int stake2 = 2;
        int stake3 = 3;

        CountDownLatch countDownLatch = new CountDownLatch(3);
        executorService.submit(() -> playGame(stake1, countDownLatch));
//        executorService.submit(() -> playGame(stake2, countDownLatch));
//        executorService.submit(() -> playGame(stake3, countDownLatch));
        executorService.shutdown();
        countDownLatch.await();
        long timeTakes = System.currentTimeMillis() - startingTime;
        System.out.println("Over all Time taken by thread " + timeTakes);

    }

    private static void playGame(int stake, CountDownLatch latch) {
        long time = System.currentTimeMillis();
        Random rng = new Random();


        for (int i = 0; i < runs; i++) {
            BigDecimal baseGameWin = BigDecimal.ZERO;
            BigDecimal freeSpinWins = BigDecimal.ZERO;
            Spin baseSpin = SlotMachine.playBaseGame(stake);
            baseGameWin = baseGameWin.add(baseSpin.getTotalWin());
//            if (baseSpin.isFsTriggered()) {
//                Spin freeSpin = FreeSpins.playFreeSpins(rng);
//                freeSpinWins = freeSpin.getTotalWin();
//            }
            totalWins = totalWins.add(baseGameWin).add(freeSpinWins);
        }
        int totalStake = stake * runs;
        long timeTakes = System.currentTimeMillis() - time;
        System.out.println("time taken : " + timeTakes);
        System.out.println("Total Win: " + totalWins);
        BigDecimal rtp = totalWins.divide(BigDecimal.valueOf(totalStake));
        System.out.println("RTP is " + rtp + "%");
//        finished();
        latch.countDown();
    }

    private static synchronized void finished() {
        finishedCount++;
        if (finishedCount == 3) {
            long timeTakes = System.currentTimeMillis() - startingTime;
            System.out.println("Over all Time taken by thread " + timeTakes / 1000);
        }
    }
}
