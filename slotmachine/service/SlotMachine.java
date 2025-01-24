package slotmachine.service;

import slotmachine.config.GameConfiguration;
import slotmachine.config.SlotSymbolWaysPayConfig;
import slotmachine.dto.WinData;
import slotmachine.util.GameUtility;

import java.math.BigDecimal;
import java.util.*;

/**
 * Main class of the game that starts the base game, so the spinning and performs cascading. It also triggers the free games.
 */
public class SlotMachine {

    public static void main(String[] args) {
        Random rng = new Random();
        int stake = 1;
        GameConfiguration configuration = new GameConfiguration();
        play(stake, rng, configuration);

    }

    // pass game configuration
    public static void play(int stake, Random rng, GameConfiguration gameConfiguration) {
        List<String[]> bgReelSet = gameConfiguration.createReelSets().getFirst();
        Spin baseGameResponse = playBaseGame(stake, rng, false, bgReelSet, gameConfiguration);
        if (baseGameResponse.isFsTriggered) {
            FreeSpins.playFreeSpins(rng, baseGameResponse.getFsAwarded(), gameConfiguration);
        }
    }

    public static Spin playBaseGame(int stake, Random rng, boolean isFreeGame, List<String[]> bgReelsA, GameConfiguration gameConfiguration) {
        Spin spin = new Spin();
        List<Integer> stopPosition = new ArrayList<>();

        BigDecimal totalWin = BigDecimal.ZERO;


        List<String[]> slotFace = new ArrayList<>();

        createGrid(rng, isFreeGame, bgReelsA, stopPosition, slotFace, gameConfiguration);

        List<List<WinData>> cascadeList = new ArrayList<>();
        int scatterCount;
        totalWin = cascade(stake, slotFace, totalWin, stopPosition, cascadeList, spin, isFreeGame, gameConfiguration);
        scatterCount = checkForScatterSym(slotFace, gameConfiguration);
        if (scatterCount >= 4) {

            int fsAwarded = 12 + ((scatterCount - 4) * 5);
            spin.setFsAwarded(fsAwarded);
            spin.setFsTriggered(true);
        }
        spin.setTotalWin(totalWin);

        return spin;
    }

    public static void createGrid(Random rng, boolean isFreeGame, List<String[]> bgReelsA, List<Integer> stopPosition, List<String[]> slotFace, GameConfiguration gameConfiguration) {
        int stopPos;
        int reelIdx = 1;
        for (String[] reel : bgReelsA) {
            stopPos = rng.nextInt(reel.length); //
            if (isFreeGame) {
                selectFsBoardHeight(rng, reelIdx, gameConfiguration);
            } else {
                selectBoardHeight(rng, reelIdx, gameConfiguration);
            }

            String[] slotFaceReel = selectReels(gameConfiguration.getBoardHeight(), reel, stopPos);
            stopPosition.add(stopPos);
            slotFace.add(slotFaceReel);
            reelIdx++;
        }
        String[] topReel = getTopReel(rng, stopPosition, isFreeGame, gameConfiguration);
        slotFace.add(topReel);
        fillTopReel(slotFace, topReel);
    }

    public static BigDecimal cascade(int stake, List<String[]> slotFace, BigDecimal totalWin, List<Integer> stopPosition, List<List<WinData>> cascadeList, Spin spin, boolean isFreeGame, GameConfiguration gameConfiguration) {
        List<WinData> winDataList;
        do {
            winDataList = calculateWin(slotFace, stake, gameConfiguration);
            totalWin = getTotalWin(winDataList, totalWin);
            if (!winDataList.isEmpty()) {
                removeSymFromWinPos(winDataList, slotFace, gameConfiguration);
                shiftSymbolsDownwards(slotFace);
                int numOfEmptySym = shiftTopReelLeftAndGetNumOfEmptySym(slotFace);
                if (numOfEmptySym > 0) {
                    fillTopReelEmptyPos(numOfEmptySym, slotFace, stopPosition, gameConfiguration);
                }

                fillEmptyPosition(slotFace, stopPosition, isFreeGame, gameConfiguration);
            }
            cascadeList.add(winDataList);
            spin.setCascadeList(cascadeList);
        } while (!winDataList.isEmpty());
        return totalWin;
    }

    private static void selectBoardHeight(Random rng, int reelIdx, GameConfiguration gameConfiguration) {
        int boardHeight = 0;
        if (reelIdx == 1 || reelIdx == 2) {
            boardHeight = WeightedPrizeService.getPrizes(rng, gameConfiguration.reel1And2Sym);
        } else if (reelIdx == 3 || reelIdx == 4) {
            boardHeight = WeightedPrizeService.getPrizes(rng, gameConfiguration.reel3And4Sym);
        } else if (reelIdx == 5 || reelIdx == 6) {
            boardHeight = WeightedPrizeService.getPrizes(rng, gameConfiguration.reel5And6Sym);
        }
        gameConfiguration.setBoardHeight(boardHeight);
    }

    private static void selectFsBoardHeight(Random rng, int reelIdx, GameConfiguration gameConfiguration) {
        int boardHeight = 0;
        if (reelIdx == 1) {
            boardHeight = WeightedPrizeService.getPrizes(rng, gameConfiguration.reel1Fg);
        } else if (reelIdx == 2) {
            boardHeight = WeightedPrizeService.getPrizes(rng, gameConfiguration.reel2Fg);
        } else if (reelIdx == 3) {
            boardHeight = WeightedPrizeService.getPrizes(rng, gameConfiguration.reel3Fg);
        } else if (reelIdx == 4) {
            boardHeight = WeightedPrizeService.getPrizes(rng, gameConfiguration.reel4Fg);
        } else if (reelIdx == 5) {
            boardHeight = WeightedPrizeService.getPrizes(rng, gameConfiguration.reel5Fg);
        } else if (reelIdx == 6) {
            boardHeight = WeightedPrizeService.getPrizes(rng, gameConfiguration.reel6Fg);
        }
        gameConfiguration.setBoardHeight(boardHeight);
    }

    private static BigDecimal getTotalWin(List<WinData> winDataList, BigDecimal totalWin) {
        for (WinData win : winDataList) {
            if (win.getWinAmount() != null) {
                totalWin = totalWin.add(win.getWinAmount());
            }
        }
        return totalWin;
    }

    private static void fillTopReelEmptyPos(int numOfEmptySym, List<String[]> slotFace, List<Integer> stopPositions, GameConfiguration gameConfiguration) {
        String[] topReel = gameConfiguration.reelSets.get(1).getFirst();

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

    private static String[] getTopReel(Random rng, List<Integer> stopPosition, boolean isFreeGame, GameConfiguration gameConfiguration) {
        int topReelStopPos;
        String[] topReel;
        if (isFreeGame) {
            topReel = gameConfiguration.reelSets.get(3).getFirst();
        } else {
            topReel = gameConfiguration.reelSets.get(1).getFirst();
        }

        topReelStopPos = rng.nextInt(topReel.length);

        String[] topFaceReel = selectTopReel(6, topReel, topReelStopPos);
        topReelStopPos += 3;
        topReelStopPos = topReelStopPos % topReel.length;
        stopPosition.add(topReelStopPos);
        return topFaceReel;
    }

    private static void fillEmptyPosition(List<String[]> slotFace, List<Integer> stopPositions, boolean isFreeGame, GameConfiguration gameConfiguration) {
        List<String[]> reels;
        if (isFreeGame) {
            reels = gameConfiguration.reelSets.get(2);
        } else {
            reels = gameConfiguration.reelSets.getFirst();
        }

        List<Integer> reelLengths = GameUtility.getReelLength(reels);
        int reelIdx = 0;
        for (int col = 0; col < gameConfiguration.boardWidth; col++) {
            String[] reel = slotFace.get(col);
            for (int i = reel.length - 1; i > 0; i--) {
                if (reel[i].contains("-1") && i < 5) {
                    stopPositions.set(reelIdx, stopPositions.get(reelIdx) + reelLengths.get(reelIdx) - 1);
                    stopPositions.set(reelIdx, stopPositions.get(reelIdx) % reelLengths.get(reelIdx));

                    reel[i] = reels.get(reelIdx)[stopPositions.get(reelIdx)];
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

    private static void removeSymFromWinPos(List<WinData> winDataList, List<String[]> slotFace, GameConfiguration gameConfiguration) {
        for (WinData win : winDataList) {
            for (Integer pos : win.getPosList()) {
                int row = pos / gameConfiguration.boardWidth;
                int reel = pos % gameConfiguration.boardWidth;
                slotFace.get(reel)[row] = "  -1";
            }

        }
    }

    private static String[] selectReels(int boardHeight, String[] reel, int position) {
        String[] boardReel = new String[boardHeight + 1];
        for (int i = 1; i <= boardHeight; i++) {
            boardReel[i] = reel[(position + i - 1) % reel.length];
        }
        return boardReel;
    }

    private static String[] addElementsToTopReel(int numSym, String[] reel, List<Integer> stopPositions) {
        String[] boardReel = new String[numSym];
        for (int i = 0; i < numSym; i++) {
            stopPositions.set(6, stopPositions.get(6) + reel.length + 1);
            stopPositions.set(6, stopPositions.get(6) % reel.length);
            boardReel[i] = reel[stopPositions.get(6)];
        }

        return boardReel;
    }

    private static String[] selectTopReel(int boardHeight, String[] reel, int position) {
        String[] boardReel = new String[boardHeight];
        for (int i = 1; i < boardHeight - 1; i++) {
            boardReel[i] = reel[(position + i - 1) % reel.length];
        }
        // -100 is used as empty symbols as edge symbols on first row, so that it contains just 4 symbols
        boardReel[0] = "-100";
        boardReel[5] = "-100";
        return boardReel;
    }

    private static List<WinData> calculateWin(List<String[]> slotFace, int stake, GameConfiguration gameConfiguration) {
        BigDecimal totalWin = BigDecimal.ZERO;
        List<WinData> winDataList = new ArrayList<>();

        for (int row = 0; row < slotFace.getFirst().length; row++) {

            String symToCompare = slotFace.getFirst()[row]; // only first column elements need to be compared.
            boolean exists = winDataList.stream().anyMatch(sym -> sym.getSymbolName().equals(symToCompare)); // if the symbol is already compared
            if (!winDataList.isEmpty() && exists) {
                continue;
            }

            WinData winData = checkForWinCombination(symToCompare, slotFace, gameConfiguration);
            populateWin(winData, winDataList, stake, gameConfiguration);
            if (winData.getWinAmount() != null) {
                totalWin = totalWin.add(winData.getWinAmount());
            }
        }

        return winDataList;
    }

    private static int checkForScatterSym(List<String[]> slotFace, GameConfiguration gameConfiguration) {
        int counter = 0;

        for (int col = 0; col < gameConfiguration.boardWidth; col++) {
            for (int row = 0; row < slotFace.get(col).length; row++) {
                String sym = slotFace.get(col)[row];
                if (sym.contains(gameConfiguration.SCATTER)) {
                    counter++;
                }
            }
        }
        return counter;
    }

    private static void populateWin(WinData winData, List<WinData> winDataList, int stake, GameConfiguration gameConfiguration) {
        SlotSymbolWaysPayConfig payOut = gameConfiguration.payout.get(winData.getSymbolName());
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

    private static WinData checkForWinCombination(String symToCompare, List<String[]> slotFace, GameConfiguration gameConfiguration) {
        SlotSymbolWaysPayConfig payOut = gameConfiguration.payout.get(symToCompare);
        WinData winData = new WinData();
        List<Integer> posList = new ArrayList<>();
        Map<Integer, Integer> symCountPerColMap = new HashMap<>();
        int currentCol = 0;

        for (int col = 0; col < gameConfiguration.boardWidth; col++) {
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
