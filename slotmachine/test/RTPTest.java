package slotmachine.test;

import slotmachine.dto.WinData;
import slotmachine.service.*;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class RTPTest {

    static int eachRun = 125000_00;
    static int finishedCount = 0;
    static long startingTime;
    static RtpResult rtpResult = new RtpResult();
    private static int finishedThreadCount = 0;
    static int availableThreads = Runtime.getRuntime().availableProcessors();
    static int stake = 1;


    public static void main(String[] args) throws InterruptedException {
        int numOfAvailableThreads = Runtime.getRuntime().availableProcessors();
        System.out.println("available number of threads =  " + numOfAvailableThreads);
        startingTime = System.currentTimeMillis();

        ExecutorService executorService = Executors.newFixedThreadPool(numOfAvailableThreads);
        for (int i = 0; i < numOfAvailableThreads; i++) {
            executorService.submit(() -> runTask());
        }

    }

    private static RtpResult playGame() {
        BigDecimal totalWins = BigDecimal.ZERO;
        BigDecimal totalFreeSpinsWins = BigDecimal.ZERO;
        BigDecimal totalBaseGameWins = BigDecimal.ZERO;
        BigDecimal highestWinMultiplier = BigDecimal.ZERO;

        BigDecimal highestWin = BigDecimal.ZERO;
        int numOfTimesFsTriggered = 0;
        RtpResult rtpRe = new RtpResult();
//        List<WinBand> winSummaryBands = getWinBands();
//        long time = System.currentTimeMillis();
        Random rng = new Random();
//        Map<String, OfAKindWins> winningMap = new HashMap<>();
        for (int i = 0; i < eachRun; i++) {

            BigDecimal baseGameWin = BigDecimal.ZERO;
            BigDecimal freeSpinWins = BigDecimal.ZERO;
            BigDecimal currentWins = BigDecimal.ZERO;
            Spin baseSpin = SlotMachine.playBaseGame(stake, rng);
            //calculateOfAKindWins(baseSpin, winningMap);
            baseGameWin = baseGameWin.add(baseSpin.getTotalWin());
            if (baseSpin.isFsTriggered()) {
                numOfTimesFsTriggered++;
                Spin freeSpin = FreeSpins.playFreeSpins(rng, baseSpin.getFsAwarded());
                freeSpinWins = freeSpin.getTotalWin();
                // calculateOfAKindWins(freeSpin, winningMap);

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
//            for (WinBand winBand : winSummaryBands) {
//                boolean updated = winBand.update(currentWins.divide(BigDecimal.valueOf(stake), new MathContext(4, RoundingMode.HALF_EVEN)));
//                if (updated) break;
//            }
        }
        RtpResult rtpResult = new RtpResult();
        rtpResult.setTotalWins(totalWins);
        rtpResult.setTotalFreeSpinsWins(totalFreeSpinsWins);
        rtpResult.setTotalBaseGameWins(totalBaseGameWins);
        rtpResult.setHighestWinMultiplier(highestWinMultiplier);
        rtpResult.setHighestWin(highestWin);
        rtpResult.setNumOfTimesFsTriggered(numOfTimesFsTriggered);
        rtpResult.setTotalRuns(eachRun);

        return rtpResult;

    }

    private static List<WinBand> getWinBands() {
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
        return winSummaryBands;
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

    private static synchronized void addToRtpResult(RtpResult newRtpResult) {

        ++finishedThreadCount;
        if (rtpResult == null) {
            rtpResult = newRtpResult;
        } else {
            rtpResult.merge(newRtpResult);
        }

        if (finishedThreadCount == availableThreads) {
            printData();


//            RtpOutput.writeRtpResultToExcelFile(command + RtpUtils.convertNumToStrings(this.rtpResult.getTotalRuns()) + "_Summary" + rtp.unscaledValue(), this.rtpResult);
        }
    }

    private static void printData() {
        int totalStake = rtpResult.getTotalRuns() * stake;
        System.out.println("thread running  " + finishedThreadCount + " out of " + availableThreads);
        System.out.println("Total Win: " + rtpResult.getTotalWins());

        BigDecimal rtp = rtpResult.getTotalWins().divide(BigDecimal.valueOf(totalStake), new MathContext(4, RoundingMode.HALF_EVEN));
        System.out.println("Total Runs : " + rtpResult.getTotalRuns());
        System.out.println("RTP is " + rtp + "%");
        System.out.println("Breakup:");
        System.out.println("Base Game RTP :" + rtpResult.getTotalBaseGameWins().divide(BigDecimal.valueOf(totalStake), new MathContext(4, RoundingMode.HALF_EVEN)));
        System.out.println("Free Spins RTP :" + rtpResult.getTotalFreeSpinsWins().divide(BigDecimal.valueOf(totalStake), new MathContext(4, RoundingMode.HALF_EVEN)));
        System.out.println("Highest win: " + rtpResult.getHighestWin());
        System.out.println("Highest win Multiplier: " + rtpResult.getHighestWinMultiplier());
        System.out.println("Number of times FreeSpins triggered " + rtpResult.getNumOfTimesFsTriggered());
        System.out.println("Free Spin trigger frequency: " + (double) rtpResult.getNumOfTimesFsTriggered() / eachRun);
        System.out.println("Avg Spins to trigger free Spins : " + eachRun / rtpResult.getNumOfTimesFsTriggered());
        System.out.println("Free Spins Average pay: " + rtpResult.getTotalFreeSpinsWins().divide(BigDecimal.valueOf(rtpResult.getNumOfTimesFsTriggered()), new MathContext(4, RoundingMode.HALF_EVEN)));
        System.out.println();
    }

    private static void runTask() {

        try {
            addToRtpResult(playGame());
        } catch (Exception e) {
            System.out.println("running the RTP_Single_Test_only.run() into errors with error message: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }
}
