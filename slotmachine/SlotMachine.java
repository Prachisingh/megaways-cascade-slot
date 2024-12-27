package slotmachine;

import slotmachine.service.WeightedPrizeService;
import slotmachine.util.GameUtility;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

import static slotmachine.GameConfiguration.*;

public class SlotMachine {

    public static void main(String[] args) {
        int stake = 1;
        WeightedPrizeService weightedPrizeService = new WeightedPrizeService();
        play(stake);

    }

    public static Spin play(int stake) {
        Random rng = new Random();
        Spin baseGameResponse = playBaseGame(stake);
        if (baseGameResponse.isFsTriggered) {
            //System.out.println("========================= Fs triggered from base game ===============================");
            Spin freeSpinResponse = FreeSpins.playFreeSpins(rng);
            //System.out.println("Total Free Spin Wins " + freeSpinResponse.getTotalWin());
        }

        return baseGameResponse;
    }

    public static Spin playBaseGame(int stake) {
        Spin spin = new Spin();
//        weightedPrizeService.getPrize(new Ra)
        Random rng = new Random();
        List<Integer> stopPosition = new ArrayList<>();
        String[] topReel = getTopReel(rng);
        BigDecimal totalWin = BigDecimal.ZERO;


        List<String[]> slotFace = new ArrayList<>();

        int stopPos;
        List<String[]> bgReelsA = getReelSets().get(0);
        for (String[] reel : bgReelsA) {
            stopPos = rng.nextInt(reel.length); //
            String[] slotFaceReel = selectReels(boardHeight, reel, stopPos);
            stopPosition.add(stopPos);
            slotFace.add(slotFaceReel);
        }
        slotFace.add(topReel);
        fillTopReel(slotFace, topReel);

        // 0-6-27-10-3


        //System.out.println("Stop Positions:" + stopPosition.stream().map(Object::toString).collect(Collectors.joining("-")));
        //System.out.println("Screen:");


        printSlotFace(slotFace);

        List<WinData> winDataList = new ArrayList<>();
        int cascadeCounter = 0;
        boolean fsTriggered = false;
        do {
            cascadeCounter++;
            winDataList = calculateWin(slotFace, stake, boardHeight, boardWidth);
            fsTriggered = checkForScatterSym(slotFace);
            totalWin = getTotalWin(winDataList, totalWin);
            if (!winDataList.isEmpty()) {
                //System.out.println("============================================");
                //System.out.println("Cascade: " + cascadeCounter);
                removeSymFromWinPos(winDataList, slotFace);
                //System.out.println("Screen after removing Winning Symbols");
                printSlotFace(slotFace);
                shiftSymbolsDownwards(slotFace);
                int numOfEmptySym = shiftTopReelLeftAndGetNumOfEmptySym(slotFace);
                //System.out.println();
                //System.out.println("Shifted Symbols ");
                printSlotFace(slotFace);
                if (numOfEmptySym > 0) {
                    fillTopReelEmptyPos(rng, numOfEmptySym, slotFace);
                }

                fillEmptyPosition(slotFace, stopPosition, rng, numOfEmptySym);
            }
        } while (!winDataList.isEmpty());

        //System.out.println("total Win of Base Game is: " + totalWin);

        spin.setFsTriggered(fsTriggered);
        spin.setTotalWin(totalWin);

        return spin;
    }

    private static BigDecimal getTotalWin(List<WinData> winDataList, BigDecimal totalWin) {
        for (WinData win : winDataList) {
            if (win.getWinAmount() != null) {
                totalWin = totalWin.add(win.getWinAmount());
            }
        }
        return totalWin;
    }

    private static void fillTopReelEmptyPos(Random rng, int numOfEmptySym, List<String[]> slotFace) {
        String[] topReel = getReelSets().get(1).getFirst();
        int topReelStopPos = rng.nextInt(topReel.length);
        String[] topFaceReel = addElementsToTopReel(numOfEmptySym, topReel, topReelStopPos);
        int j = 0;
        for (int i = 1; i < 5; i++) {

            if (slotFace.get(i)[0].contains("-1")) {
                slotFace.get(i)[0] = topFaceReel[j];
                j++;
            }
        }
    }

    private static int shiftTopReelLeftAndGetNumOfEmptySym(List<String[]> slotFace) {
        List<String> topList = new ArrayList<>();
        for (int i = 1; i < 5; i++) {

            if (!slotFace.get(i)[0].contains("-1"))
                topList.add(slotFace.get(i)[0]);
        }

        for (int i = 0; i < topList.size(); i++) {
            slotFace.get(i + 1)[0] = topList.get(i);
        }
        int numOfEmptySym = 4 - topList.size();
        // fill with -1

        for (int i = numOfEmptySym; i > 0; i--) {
            slotFace.get(5 - i)[0] = "-1";
        }
        return numOfEmptySym;
    }


    private static void fillTopReel(List<String[]> slotFace, String[] topReel) {
        for (int i = 0; i < topReel.length; i++) {
            slotFace.get(i)[0] = topReel[i];
        }
    }

    private static String[] getTopReel(Random rng) {
        int topReelStopPos;
        String[] topReel = getReelSets().get(1).getFirst();
        topReelStopPos = rng.nextInt(topReel.length);
        String[] topFaceReel = selectTopReel(6, topReel, topReelStopPos);
        return topFaceReel;
    }

    private static List<String[]> fillEmptyPosition(List<String[]> slotFace, List<Integer> stopPositions, Random rng, int numOfEmptySym) {
        List<String[]> bgReels = getReelSets().get(0);

        List<Integer> reelLengths = GameUtility.getReelLength(bgReels);
        int reelIdx = 0;
        for (String[] reel : slotFace) {
            for (int i = boardHeight - 1; i > 0; i--) {
                if (reel[i].contains("-1") && (i > 0 && i < 5)) {
                    stopPositions.set(reelIdx, stopPositions.get(reelIdx) + reelLengths.get(reelIdx) - 1);
                    stopPositions.set(reelIdx, stopPositions.get(reelIdx) % reelLengths.get(reelIdx));

                    reel[i] = bgReels.get(reelIdx)[stopPositions.get(reelIdx)];
                }
            }
            reelIdx++;
        }
        //System.out.println("New stop positions: " + stopPositions.stream().map(Object::toString).collect(Collectors.joining("-")));
        //System.out.println("New screen ");
        printSlotFace(slotFace);
        return slotFace;
    }

    static boolean isTopReal(int row, int reelIdx) {

        return row > 0;
    }

    private static void shiftSymbolsDownwards(List<String[]> slotFaceContainingRemovedSymbols) {
        boolean some;
        for (String[] reel : slotFaceContainingRemovedSymbols) {

            for (int i = boardHeight - 1; i > 1; i--) {

                if (reel[i].contains("-1")) {
                    some = false;
                    for (int j = i; j > 1; j--) {
                        if (!reel[j - 1].contains("-1")) {
                            some = true;
                        }
                        reel[j] = reel[j - 1];

                    }
                    reel[1] = "  -1";
                    if (some) {
                        i++;
                    }
                }
            }
        }
    }

    private static void printSlotFace(List<String[]> slotFace) {
        for (int row = 0; row < boardHeight; row++) {
            for (int col = 0; col < boardWidth; col++) {

                //System.out.print(" " + slotFace.get(col)[row]);
            }
            //System.out.println();
        }
    }

    private static void removeSymFromWinPos(List<WinData> winDataList, List<String[]> slotFace) {
        for (WinData win : winDataList) {
            for (Integer pos : win.getPosList()) {
                int row = pos / boardWidth;
                int reel = pos % boardWidth;
                slotFace.get(reel)[row] = "  -1";
            }

        }
    }

    private static String[] selectReels(int boardHeight, String[] reel, int position) {
        String[] boardReel = new String[boardHeight];
        for (int i = 1; i < boardHeight; i++) {
            boardReel[i] = reel[(position + i) % reel.length];
        }
        return boardReel;
    }

    private static String[] addElementsToTopReel(int numSym, String[] reel, int position) {
        String[] boardReel = new String[numSym];
        for (int i = 0; i < numSym; i++) {
            boardReel[i] = reel[(position + i) % reel.length];
        }

        return boardReel;
    }

    private static String[] selectTopReel(int boardHeight, String[] reel, int position) {
        String[] boardReel = new String[boardHeight];
        for (int i = 1; i < boardHeight - 1; i++) {
            boardReel[i] = reel[(position + i) % reel.length];
        }
        boardReel[0] = "-100";
        boardReel[5] = "-100";
        return boardReel;
    }

    private static List<WinData> calculateWin(List<String[]> slotFace, int stake, int boardHeight, int boardWidth) {
        BigDecimal totalWin = BigDecimal.ZERO;
        List<WinData> winDataList = new ArrayList<>();

        for (int row = 0; row < boardHeight; row++) {

            String symToCompare = slotFace.getFirst()[row]; // only first column elements need to be compared.
            boolean exists = winDataList.stream().anyMatch(sym -> sym.getSymbolName().equals(symToCompare)); // if the symbol is already compared
            if (!winDataList.isEmpty() && exists) {
                continue;
            }

            WinData winData = checkForWinCombination(symToCompare, boardHeight, boardWidth, slotFace);
            populateWin(winData, winDataList, stake);
            if (winData.getWinAmount() != null) {
                totalWin = totalWin.add(winData.getWinAmount());
            }
        }
        //System.out.println("Cascade win:" + totalWin);

        for (WinData win : winDataList) {

            //System.out.println("- Ways win " + win.getPosList().stream().map(Object::toString).collect(Collectors.joining("-")) + ", " + win.getSymbolName() + " X" + win.getSymCountOnEachCol().size() + ", " + win.getWinAmount() + ", Ways: " + win.getWays());
        }

        return winDataList;
    }

    private static boolean checkForScatterSym(List<String[]> slotFace) {
        int counter = 0;

        for (int col = 0; col < boardWidth; col++) {
            for (int row = 0; row < boardHeight; row++) {
                String sym = slotFace.get(col)[row];
                if (sym.contains(SCATTER)) {
                    counter++;
                }
            }
        }
        if (counter >= 3) {
            //System.out.println("Free Spins triggered");
            return true;
        }
        return false;
    }

    private static void populateWin(WinData winData, List<WinData> winDataList, int stake) {
        SlotSymbolWaysPayConfig payOut = getPayout().get(winData.getSymbolName());
        BigDecimal symbolWin;
        int ways;
        if (payOut != null && winData.getSymCountOnEachCol().size() >= payOut.getMinimumMatch()) {
            symbolWin = payOut.getWinAmount(winData.getSymCountOnEachCol().size());

            ways = 1;
            for (Map.Entry<Integer, Integer> entry : winData.getSymCountOnEachCol().entrySet()) {
                ways *= entry.getValue();
            }
            BigDecimal finalWin = symbolWin.multiply(BigDecimal.valueOf(ways));
            winData.setWinAmount(finalWin.multiply(BigDecimal.valueOf(stake))); // multiply with stake
            winData.setWays(ways);
            winData.setBasePayout(symbolWin);
            winDataList.add(winData);
        }
    }

    private static WinData checkForWinCombination(String symToCompare, int boardHeight, int boardWidth, List<String[]> slotFace) {
        SlotSymbolWaysPayConfig payOut = getPayout().get(symToCompare);
        WinData winData = new WinData();
        List<Integer> posList = new ArrayList<>();
        Map<Integer, Integer> symCountPerColMap = new HashMap<>();
        int currentCol = 0;

        for (int col = 0; col < boardWidth; col++) {
            int symCountPerColumn = 0;
            int pos = col;
            if (col - currentCol > 1)
                break;
            for (int row = 0; row < boardHeight; row++) {

                String currentSym = slotFace.get(col)[row];

                if (payOut != null && (symToCompare.equals(currentSym) || payOut.getWilds().contains(currentSym))) {

                    symCountPerColumn++;
                    symCountPerColMap.put(col, symCountPerColumn);

                    posList.add(pos);

                    currentCol = col;
                }
                pos += 6;
            }
        }
        winData.setPosList(posList);
        winData.setSymCountOnEachCol(symCountPerColMap);
        winData.setSymbolName(symToCompare);
        return winData;
    }
}
