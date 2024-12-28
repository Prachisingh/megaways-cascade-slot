package slotmachine.test;

import slotmachine.FreeSpins;
import slotmachine.SlotMachine;
import slotmachine.Spin;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class RTPTest {

    static int runs = 1000_000;
    static int finishedCount = 0;
    static long startingTime;
    static BigDecimal totalWins = BigDecimal.ZERO;
    static BigDecimal totalFreeSpinsWins = BigDecimal.ZERO;
    static BigDecimal totalBaseGameWins = BigDecimal.ZERO;
    static BigDecimal highestWinMultiplier = BigDecimal.ZERO;

    static BigDecimal highestWin = BigDecimal.ZERO;
    static int numOfTimesFsTriggered;


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
            BigDecimal currentsWins = BigDecimal.ZERO;
            Spin baseSpin = SlotMachine.playBaseGame(stake);
            baseGameWin = baseGameWin.add(baseSpin.getTotalWin());
            if (baseSpin.isFsTriggered()) {
                numOfTimesFsTriggered++;
                Spin freeSpin = FreeSpins.playFreeSpins(rng);
                freeSpinWins = freeSpin.getTotalWin();
            }
            totalWins = totalWins.add(baseGameWin).add(freeSpinWins);
            currentsWins = baseGameWin.add(freeSpinWins);
            totalFreeSpinsWins = totalFreeSpinsWins.add(freeSpinWins);
            totalBaseGameWins = totalBaseGameWins.add(baseGameWin);
            BigDecimal currentWinMultiplier = currentsWins.divide(BigDecimal.valueOf(stake), new MathContext(4, RoundingMode.HALF_EVEN));
            if (currentWinMultiplier.compareTo(highestWinMultiplier) > 0) {
                highestWinMultiplier = currentWinMultiplier;
            }
            if (currentsWins.compareTo(highestWin) > 0) {
                highestWin = currentsWins;
            }
        }
        int totalStake = stake * runs;
        long timeTaken = System.currentTimeMillis() - time;
        System.out.println("time taken : " + (timeTaken/1000)/60);
        System.out.println("Total Win: " + totalWins);
        BigDecimal rtp = totalWins.divide(BigDecimal.valueOf(totalStake), new MathContext(4, RoundingMode.HALF_EVEN));
        System.out.println("RTP is " + rtp + "%");
        System.out.println("Breakup:");
        System.out.println("Base Game RTP :" + totalBaseGameWins.divide(BigDecimal.valueOf(totalStake),new MathContext(4, RoundingMode.HALF_EVEN)));
        System.out.println("Free Spins RTP :" + totalFreeSpinsWins.divide(BigDecimal.valueOf(totalStake), new MathContext(4, RoundingMode.HALF_EVEN)));
        System.out.println("Highest win: " + highestWin);
        System.out.println("Highest win Multiplier: " + highestWinMultiplier);
        System.out.println("Number of times FreeSpins triggered "+ numOfTimesFsTriggered);
        System.out.println("Free Spin trigger frequency: " + runs/numOfTimesFsTriggered );
        System.out.println("Free Spins Average pay: " + totalFreeSpinsWins.divide(BigDecimal.valueOf(numOfTimesFsTriggered)) );

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
