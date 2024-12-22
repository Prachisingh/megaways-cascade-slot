package slotmachine;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

import static slotmachine.GameConfiguration.*;

public class SlotMachine {

    public static void main(String[] args) {
        int stake = 1;
        play(stake);
    }

    public static List<WinData> play(int stake) {
        List<Integer> stopPosition = new ArrayList<>();

        Random rng = new Random();
        List<String[]> slotFace = new ArrayList<>();
        int stopPos;
        List<String[]> bgReelsA = GameConfiguration.getReelSets().get(0);
        for (String[] reel : bgReelsA) {
            stopPos = rng.nextInt(reel.length); //
            String[] slotFaceReel = selectReels(boardHeight, reel, stopPos);
            stopPosition.add(stopPos);
            slotFace.add(slotFaceReel);
        }
        // 0-6-27-10-3


        System.out.println("Stop Positions:" + stopPosition.stream().map(Object::toString).collect(Collectors.joining("-")));
        System.out.println("Screen:");


        printSlotFace(slotFace);

        List<WinData> winDataList = calculateWin(slotFace, stake, boardHeight, boardWidth);
        List<String[]> slotFaceContainingRemovedSymbols = removeSymFromWinPos(winDataList, slotFace);
        System.out.println("Screen after removing Winning Symbols");
        printSlotFace(slotFaceContainingRemovedSymbols);
        // Shift symbols downwards
        shiftSymbols(slotFaceContainingRemovedSymbols);
        System.out.println("Shifted Symbols ");
        printSlotFace(slotFaceContainingRemovedSymbols);

        return winDataList;
    }

    private static void shiftSymbols(List<String[]> slotFaceContainingRemovedSymbols) {
        boolean some;
        for(String[] reel: slotFaceContainingRemovedSymbols){
        for(int i = boardHeight - 1; i > 0; i--) {
            if (reel[i].contains("-1")) {
                some = false;
                for (int j = i; j > 0; j--) {
                    if (!reel[j - 1].contains("-1")) {
                        some = true;
                    }
                    reel[j] = reel[j - 1];

                }
                reel[0] = "  -1";
                if (some) {
                    i++;
                }
            }
        }
        }
    }

    private static void printSlotFace(List<String[]> slotFaceContainingRemovedSymbols) {
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 5; col++) {

                System.out.print(" " + slotFaceContainingRemovedSymbols.get(col)[row]);
            }
            System.out.println();
        }
    }

    private static List<String[]> removeSymFromWinPos(List<WinData> winDataList, List<String[]> slotFace) {
        for (WinData win : winDataList) {
            for (Integer pos : win.getPosList()) {
                int row = pos / boardWidth;
                int reel = pos % boardWidth;
                slotFace.get(reel)[row] = "  -1";
            }

        }
        return slotFace;
    }

    private static String[] selectReels(int boardHeight, String[] reel, int position) {
        String[] boardReel = new String[boardHeight];
        for (int i = 0; i < boardHeight; i++) {
            boardReel[i] = reel[(position + i) % reel.length];
        }
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
        System.out.println("Total wins:" + totalWin);

        for (WinData win : winDataList) {

            System.out.println("- Ways win " + win.getPosList().stream().map(Object::toString).collect(Collectors.joining("-")) + ", " + win.getSymbolName() + " X" + win.getSymCountOnEachCol().size() + ", " + win.getWinAmount() + ", Ways: " + win.getWays());
        }

        return winDataList;
    }

    private static void populateWin(WinData winData, List<WinData> winDataList, int stake) {
        SlotSymbolWaysPayConfig payOut = GameConfiguration.getPayout().get(winData.getSymbolName());
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
        SlotSymbolWaysPayConfig payOut = GameConfiguration.getPayout().get(symToCompare);
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

                if (symToCompare.equals(currentSym) || payOut.getWilds().contains(currentSym)) {

                    symCountPerColumn++;
                    symCountPerColMap.put(col, symCountPerColumn);

                    posList.add(pos);

                    currentCol = col;
                }
                pos += 5;
            }
        }
        winData.setPosList(posList);
        winData.setSymCountOnEachCol(symCountPerColMap);
        winData.setSymbolName(symToCompare);
        return winData;
    }


}