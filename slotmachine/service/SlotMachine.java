package slotmachine.service;

import slotmachine.config.GameConfiguration;
import slotmachine.config.SlotSymbolWaysPayConfig;
import slotmachine.dto.WinData;
import slotmachine.util.GameUtility;

import java.math.BigDecimal;
import java.util.*;

import static slotmachine.config.GameConfiguration.*;

/**
 * Main class of the game that starts the base game, so the spinning and performs cascading. It also triggers the free games.
 */
public class SlotMachine {

    public static void main(String[] args) {
        Random rng = new Random();
        int stake = 1;
        play(stake, rng);

    }

    public static void play(int stake, Random rng) {

        Spin baseGameResponse = playBaseGame(stake, rng);
        if (baseGameResponse.isFsTriggered) {
            FreeSpins.playFreeSpins(rng, baseGameResponse.getFsAwarded());
        }
    }

    public static Spin playBaseGame(int stake, Random rng) {
        Spin spin = new Spin();
        List<Integer> stopPosition = new ArrayList<>();

        BigDecimal totalWin = BigDecimal.ZERO;


        List<String[]> slotFace = new ArrayList<>();

        int stopPos;
        int reelIdx = 1;
        List<String[]> bgReelsA = getReelSets().getFirst();
        for (String[] reel : bgReelsA) {
            stopPos = rng.nextInt(reel.length); //
            if (reelIdx == 1 || reelIdx == 2) {
                boardHeight = WeightedPrizeService.getPrizes(rng, GameConfiguration.reel1And2Sym());
            }
            else if (reelIdx == 3 || reelIdx == 4) {
                boardHeight = WeightedPrizeService.getPrizes(rng, GameConfiguration.reel3And4Sym());
            }
            else if  (reelIdx == 5 || reelIdx == 6) {
                boardHeight = WeightedPrizeService.getPrizes(rng, GameConfiguration.reel5And6Sym());
            }
            String[] slotFaceReel = selectReels(boardHeight, reel, stopPos);
            stopPosition.add(stopPos);
            slotFace.add(slotFaceReel);
            reelIdx++;
        }
        String[] topReel = getTopReel(rng, stopPosition);
        slotFace.add(topReel);
        fillTopReel(slotFace, topReel);

        List<WinData> winDataList;
        List<List<WinData>> cascadeList = new ArrayList<>();
        int scatterCount  = 0;
        do {
            winDataList = calculateWin(slotFace, stake);
            scatterCount = scatterCount+ checkForScatterSym(slotFace);
            totalWin = getTotalWin(winDataList, totalWin);
            if (!winDataList.isEmpty()) {
                removeSymFromWinPos(winDataList, slotFace);
                shiftSymbolsDownwards(slotFace);
                int numOfEmptySym = shiftTopReelLeftAndGetNumOfEmptySym(slotFace);
                if (numOfEmptySym > 0) {
                    fillTopReelEmptyPos(rng, numOfEmptySym, slotFace, stopPosition);
                }

                fillEmptyPosition(slotFace, stopPosition);
            }
            cascadeList.add(winDataList);
            spin.setCascadeList(cascadeList);
        } while (!winDataList.isEmpty());
        scatterCount = checkForScatterSym(slotFace);
        if (scatterCount >= 4) {

            int fsAwarded = 12 + ((scatterCount - 4) * 5);
            spin.setFsAwarded(fsAwarded);
            spin.setFsTriggered(true);
        }
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

    private static void fillTopReelEmptyPos(Random rng, int numOfEmptySym, List<String[]> slotFace, List<Integer> stopPositions ) {
        String[] topReel = getReelSets().get(1).getFirst();

        String[] topFaceReel = addElementsToTopReel(numOfEmptySym, topReel, stopPositions);
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

    private static String[] getTopReel(Random rng, List<Integer> stopPosition) {
        int topReelStopPos;
        String[] topReel = getReelSets().get(1).getFirst();
        topReelStopPos = rng.nextInt(topReel.length);

        String[] topFaceReel = selectTopReel(6, topReel, topReelStopPos);
        topReelStopPos += 3;
        topReelStopPos = topReelStopPos % topReel.length;
        stopPosition.add(topReelStopPos);
        return topFaceReel;
    }

    private static void fillEmptyPosition(List<String[]> slotFace, List<Integer> stopPositions) {
        List<String[]> bgReels = getReelSets().get(0);
        List<Integer> reelLengths = GameUtility.getReelLength(bgReels);
        int reelIdx = 0;
        for (int col = 0; col < boardWidth; col++) {
            String[] reel = slotFace.get(col);
            for (int i = reel.length - 1; i > 0; i--) {
                if (reel[i].contains("-1") && i < 5) {
                    stopPositions.set(reelIdx, stopPositions.get(reelIdx) + reelLengths.get(reelIdx) - 1);
                    stopPositions.set(reelIdx, stopPositions.get(reelIdx) % reelLengths.get(reelIdx));

                    reel[i] = bgReels.get(reelIdx)[stopPositions.get(reelIdx)];
                }
            }
            reelIdx++;
        }
    }


    private static void shiftSymbolsDownwards(List<String[]> slotFaceContainingRemovedSymbols) {
        boolean some;
        for (String[] reel : slotFaceContainingRemovedSymbols) {

            for (int i = reel.length - 1; i > 1; i--) {

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
        String[] boardReel = new String[boardHeight + 1];
        for (int i = 1; i <= boardHeight; i++) {
            boardReel[i] = reel[(position + i-1) % reel.length];
        }
        return boardReel;
    }

    private static String[] addElementsToTopReel(int numSym, String[] reel, List<Integer> stopPositions) {
        String[] boardReel = new String[numSym];
        for (int i = 0; i < numSym; i++) {
            stopPositions.set(6, stopPositions.get(6) + reel.length+1);
            stopPositions.set(6, stopPositions.get(6) % reel.length);
            boardReel[i] = reel[stopPositions.get(6)];
        }

        return boardReel;
    }

    private static String[] selectTopReel(int boardHeight, String[] reel, int position) {
        String[] boardReel = new String[boardHeight];
        for (int i = 1; i < boardHeight - 1; i++) {
            boardReel[i] = reel[(position + i-1) % reel.length];
        }
        // -100 is used as empty symbols as edge symbols on first row, so that it contains just 4 symbols
        boardReel[0] = "-100";
        boardReel[5] = "-100";
        return boardReel;
    }

    private static List<WinData> calculateWin(List<String[]> slotFace, int stake) {
        BigDecimal totalWin = BigDecimal.ZERO;
        List<WinData> winDataList = new ArrayList<>();

        for (int row = 0; row < slotFace.getFirst().length; row++) {

            String symToCompare = slotFace.getFirst()[row]; // only first column elements need to be compared.
            boolean exists = winDataList.stream().anyMatch(sym -> sym.getSymbolName().equals(symToCompare)); // if the symbol is already compared
            if (!winDataList.isEmpty() && exists) {
                continue;
            }

            WinData winData = checkForWinCombination(symToCompare, slotFace);
            populateWin(winData, winDataList, stake);
            if (winData.getWinAmount() != null) {
                totalWin = totalWin.add(winData.getWinAmount());
            }
        }
//        System.out.println("Cascade win:" + totalWin);

        for (WinData win : winDataList) {

//            System.out.println("- Ways win " + win.getPosList().stream().map(Object::toString).collect(Collectors.joining("-")) + ", " + win.getSymbolName() + " X" + win.getSymCountOnEachCol().size() + ", " + win.getWinAmount() + ", Ways: " + win.getWays());
        }

        return winDataList;
    }

    private static int checkForScatterSym(List<String[]> slotFace) {
        int counter = 0;

        for (int col = 0; col < boardWidth; col++) {
            for (int row = 0; row < slotFace.get(col).length; row++) {
                String sym = slotFace.get(col)[row];
                if (sym.contains(SCATTER)) {
                    counter++;
                }
            }
        }
        return counter;
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

    private static WinData checkForWinCombination(String symToCompare, List<String[]> slotFace) {
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
            for (int row = 0; row < slotFace.get(col).length; row++) {

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
