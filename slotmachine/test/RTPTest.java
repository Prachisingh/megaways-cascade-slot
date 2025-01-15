package slotmachine.test;

import slotmachine.dto.WinData;
import slotmachine.service.*;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.*;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class RTPTest {

    static int runs = 5000_000_0;
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
        List<WinBand> winSummaryBands = new ArrayList<>();
        winSummaryBands.add(new WinBand(0, 0));
        winSummaryBands.add(new WinBand(0, 0.5));
        winSummaryBands.add(new WinBand(0, 5.1));
        winSummaryBands.add(new WinBand(1, 2));
        winSummaryBands.add(new WinBand(2, 4));
        winSummaryBands.add(new WinBand(4, 7));
        winSummaryBands.add(new WinBand(7, 10));
        winSummaryBands.add(new WinBand(10, 14));
        winSummaryBands.add(new WinBand(14, 20));
        winSummaryBands.add(new WinBand(20, 30));
        winSummaryBands.add(new WinBand(30, 40));
        winSummaryBands.add(new WinBand(40, 50));
        winSummaryBands.add(new WinBand(50, 60));
        winSummaryBands.add(new WinBand(60, 70));
        winSummaryBands.add(new WinBand(70, 90));
        winSummaryBands.add(new WinBand(90, 110));
        long time = System.currentTimeMillis();
        Random rng = new Random();
        Map<String, OfAKindWins> winningMap = new HashMap<>();
        int totalStake = stake * runs;
        for (int i = 0; i < runs; i++) {
            BigDecimal baseGameWin = BigDecimal.ZERO;
            BigDecimal freeSpinWins = BigDecimal.ZERO;
            BigDecimal currentWins = BigDecimal.ZERO;
            Spin baseSpin = SlotMachine.playBaseGame(stake, rng);
            baseGameWin = baseGameWin.add(baseSpin.getTotalWin());
            if (baseSpin.isFsTriggered()) {
                numOfTimesFsTriggered++;
                Spin freeSpin = FreeSpins.playFreeSpins(rng, baseSpin.getFsAwarded());
                freeSpinWins = freeSpin.getTotalWin();
                calculateOfAKindWins(baseSpin, winningMap);
            }
            totalWins = totalWins.add(baseGameWin).add(freeSpinWins);
            currentWins = baseGameWin.add(freeSpinWins);
            totalFreeSpinsWins = totalFreeSpinsWins.add(freeSpinWins);
            totalBaseGameWins = totalBaseGameWins.add(baseGameWin);
            BigDecimal currentWinMultiplier = currentWins.divide(BigDecimal.valueOf(stake), new MathContext(4, RoundingMode.HALF_EVEN));
            if (currentWinMultiplier.compareTo(highestWinMultiplier) > 0) {
                highestWinMultiplier = currentWinMultiplier;
            }
            if (currentWins.compareTo(highestWin) > 0) {
                highestWin = currentWins;
            }
//            updateWinBreakdown(winBreakdown, roundResult.getWinLineWins());
            for (WinBand winBand : winSummaryBands) {
                boolean updated = winBand.update(currentWins.divide(BigDecimal.valueOf(stake), new MathContext(4, RoundingMode.HALF_EVEN)));
                if(updated) break;
            }
        }


        long timeTaken = System.currentTimeMillis() - time;
        System.out.println("time taken : " + (timeTaken / 1000) / 60);
        System.out.println("Total Win: " + totalWins);
        BigDecimal rtp = totalWins.divide(BigDecimal.valueOf(totalStake), new MathContext(4, RoundingMode.HALF_EVEN));
        System.out.println("RTP is " + rtp + "%");
        System.out.println("Breakup:");
        System.out.println("Base Game RTP :" + totalBaseGameWins.divide(BigDecimal.valueOf(totalStake), new MathContext(4, RoundingMode.HALF_EVEN)));
        System.out.println("Free Spins RTP :" + totalFreeSpinsWins.divide(BigDecimal.valueOf(totalStake), new MathContext(4, RoundingMode.HALF_EVEN)));
        System.out.println("Highest win: " + highestWin);
        System.out.println("Highest win Multiplier: " + highestWinMultiplier);
        System.out.println("Number of times FreeSpins triggered " + numOfTimesFsTriggered);
        System.out.println("Free Spin trigger frequency: " + (double) numOfTimesFsTriggered / runs);
        System.out.println("Avg Spins to trigger free Spins : " + runs / numOfTimesFsTriggered);
        System.out.println("Free Spins Average pay: " + totalFreeSpinsWins.divide(BigDecimal.valueOf(numOfTimesFsTriggered), new MathContext(4, RoundingMode.HALF_EVEN)));
        System.out.println();
        getPayDistributionForEachSymbol(winningMap, totalStake);

        MathContext mathContext = new MathContext(6, RoundingMode.HALF_EVEN);
        for(WinBand winSummaryBand :winSummaryBands){
            BigDecimal frequency = winSummaryBand.getCount().divide(BigDecimal.valueOf(runs), mathContext);
            System.out.println("win between " + winSummaryBand.getMin() + " and " + winSummaryBand.getMax() + " count: " +  winSummaryBand.getCount());
            System.out.println("win between " + winSummaryBand.getMin() + " and " + winSummaryBand.getMax() + " frequency: " + frequency);
            System.out.println("--------------------------------------------------------");
            System.out.println();
        }


//        finished();
        latch.countDown();
    }

    private static void getPayDistributionForEachSymbol(Map<String, OfAKindWins> winningMap, int totalStake) {

        Iterator<String> iterator = winningMap.keySet().iterator();
        BigDecimal symWinRtp;
        while (iterator.hasNext()) {
            String symName = iterator.next();
            System.out.println("======" + symName + "==========");
            OfAKindWins ofAKindWins = winningMap.get(symName);
            Map<Integer, BigDecimal> winMap = ofAKindWins.getWinningMap();
            Map<Integer, Integer> occuranceMap = ofAKindWins.getOccuranceMap();
            Iterator<Integer> winIterator = winMap.keySet().iterator();
            Iterator<Integer> occuranceIterator = occuranceMap.keySet().iterator(); // divide it by hit rate, occurance/totalruns , frequency is reverse
            while (winIterator.hasNext()) {
                Integer numOfSym = winIterator.next();
                System.out.println(numOfSym + " of a kind");
                BigDecimal totalWin = winMap.get(numOfSym);
                symWinRtp = totalWin.divide(BigDecimal.valueOf(totalStake), new MathContext(4, RoundingMode.HALF_EVEN));
                System.out.println("RTP : " + symWinRtp);
            }
            System.out.println();
        }
    }

    private static void calculateOfAKindWins(Spin baseSpin, Map<String, OfAKindWins> winningMap) {
        // iterate cascadeList in baseGameWin
        // check sym count on each col size - gives of a kind
        //get Win amount, add it to previous
        // get ways, add it to occurances
        List<List<WinData>> cascadeList = baseSpin.getCascadeList();
        for (List<WinData> winDataList : cascadeList) {
            for (WinData cascade : winDataList) {
                int numOfSym = cascade.getSymCountOnEachCol().size();
                BigDecimal win = cascade.getWinAmount();
                int occurance = cascade.getWays();
                String symName = cascade.getSymbolName();
                if (winningMap.get(symName) != null) {
                    OfAKindWins ofAKindWins = winningMap.get(symName);
                    Map<Integer, BigDecimal> winMap = ofAKindWins.getWinningMap();
                    Map<Integer, Integer> occuranceMap = ofAKindWins.getOccuranceMap();
                    if (winMap.get(numOfSym) != null) {
                        winMap.put(numOfSym, winMap.get(numOfSym).add(win));
                        occuranceMap.put(numOfSym, occuranceMap.get(numOfSym) + occurance);
                    } else {
                        winMap.put(numOfSym, win);
                        occuranceMap.put(numOfSym, occurance);

                    }
                    ofAKindWins.setWinningMap(winMap);
                    ofAKindWins.setOccuranceMap(occuranceMap);

                    winningMap.put(symName, ofAKindWins);
                } else {
                    OfAKindWins ofAKindWins = new OfAKindWins();
                    Map<Integer, BigDecimal> winMap = ofAKindWins.getWinningMap();
                    winMap.put(numOfSym, win);
                    Map<Integer, Integer> occuranceMap = ofAKindWins.getOccuranceMap();
                    occuranceMap.put(numOfSym, occurance);

                    winningMap.put(symName, ofAKindWins);
                }
            }
        }
    }

    private static synchronized void finished() {
        finishedCount++;
        if (finishedCount == 3) {
            long timeTakes = System.currentTimeMillis() - startingTime;
            System.out.println("Over all Time taken by thread " + timeTakes / 1000);
        }
    }
}
