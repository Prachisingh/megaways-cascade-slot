package slotmachine.config;

import slotmachine.dto.WeightedPrizeData;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class GameConfiguration {
    public  int boardHeight = 4;
    public  int boardWidth = 6;
    public  String AA = "AA";
    public  String BB = "BB";
    public  String CC = "CC";
    public  String DD = "DD";
    public  String EE = "EE";
    public  String FF = "FF";
    public  String GG = "GG";
    public  String HH = "HH";
    public  String JJ = "JJ";
    public  String KK = "KK";
    public  String SC = "SC";
    public  String WC = "WC";
    public  String SCATTER = "SC";

    public int getBoardHeight() {
        return boardHeight;
    }

    public int getBoardWidth() {
        return boardWidth;
    }

    public  Map<String, SlotSymbolWaysPayConfig> payout = createPayout();

    public  List<List<String[]>> reelSets = createReelSets();
    public  WeightedPrizeData reel1And2Sym = createReel1And2Sym();
    public  WeightedPrizeData reel3And4Sym = createRee3And4Sym();

    public  WeightedPrizeData reel5And6Sym = createRee5And6Sym();

    public  WeightedPrizeData reel1Fg = createReel1Fg();
    public  WeightedPrizeData reel2Fg = createReelSym2Fg();
    public  WeightedPrizeData reel3Fg = createReelSym3Fg();
    public  WeightedPrizeData reel4Fg = createReelSym4Fg();
    public  WeightedPrizeData reel5Fg = createReelSym5Fg();

    public  WeightedPrizeData reel6Fg = createReelSym6Fg();



    public  Map<String, SlotSymbolWaysPayConfig> createPayout() {

        return Map.of(
                AA, new SlotSymbolWaysPayConfig(2, List.of(BigDecimal.valueOf(1), BigDecimal.valueOf(2), BigDecimal.valueOf(10), BigDecimal.valueOf(20), BigDecimal.valueOf(60))).addWild(WC),
                BB, new SlotSymbolWaysPayConfig(3, List.of(BigDecimal.valueOf(1), BigDecimal.valueOf(2), BigDecimal.valueOf(2.5), BigDecimal.valueOf(5))).addWild(WC),
                CC, new SlotSymbolWaysPayConfig(3, List.of(BigDecimal.valueOf(0.3), BigDecimal.valueOf(0.5), BigDecimal.valueOf(1), BigDecimal.valueOf(2.5))).addWild(WC),
                DD, new SlotSymbolWaysPayConfig(3, List.of(BigDecimal.valueOf(0.3), BigDecimal.valueOf(0.5), BigDecimal.valueOf(0.8), BigDecimal.valueOf(2))).addWild(WC),
                EE, new SlotSymbolWaysPayConfig(3, List.of(BigDecimal.valueOf(0.2), BigDecimal.valueOf(0.4), BigDecimal.valueOf(0.6), BigDecimal.valueOf(1.5))).addWild(WC),
                FF, new SlotSymbolWaysPayConfig(3, List.of(BigDecimal.valueOf(0.2), BigDecimal.valueOf(0.4), BigDecimal.valueOf(0.6), BigDecimal.valueOf(1.5))).addWild(WC),
                GG, new SlotSymbolWaysPayConfig(3, List.of(BigDecimal.valueOf(0.2), BigDecimal.valueOf(0.4), BigDecimal.valueOf(0.6), BigDecimal.valueOf(1.5))).addWild(WC),
                HH, new SlotSymbolWaysPayConfig(3, List.of(BigDecimal.valueOf(0.1), BigDecimal.valueOf(0.2), BigDecimal.valueOf(0.4), BigDecimal.valueOf(1))).addWild(WC),
                JJ, new SlotSymbolWaysPayConfig(3, List.of(BigDecimal.valueOf(0.1), BigDecimal.valueOf(0.2), BigDecimal.valueOf(0.4), BigDecimal.valueOf(1))).addWild(WC),
                KK, new SlotSymbolWaysPayConfig(3, List.of(BigDecimal.valueOf(0.1), BigDecimal.valueOf(0.2), BigDecimal.valueOf(0.4), BigDecimal.valueOf(1))).addWild(WC)
        );
    }

    public  List<List<String[]>> createReelSets() {
        List<List<String[]>> gameReels = new ArrayList<>();
        List<String[]> bgReels = new ArrayList<>(5);

        bgReels.add(new String[]{
                GG, BB, FF, CC, JJ, EE, BB, DD, KK, HH, KK, FF, AA, KK, KK, JJ, JJ, CC, GG, SC, DD, EE, CC, AA, EE, GG, KK, HH, FF, CC, CC, BB, EE, CC, CC, CC, AA, EE, FF, DD, CC, HH, GG, EE, BB, FF, KK, SC, JJ, KK, HH, GG, FF, EE, CC, KK, JJ, EE, DD, HH, FF, KK, JJ, FF, GG, CC, DD, CC, HH, KK, GG, DD, DD, JJ, SC, CC, JJ, EE, HH, KK, JJ, FF, EE, CC, GG, EE, AA, DD, JJ, BB, KK, HH, HH, EE, FF, JJ, DD, FF, DD, SC, GG, KK, EE, FF, HH, KK, JJ, GG, HH, KK, JJ, JJ, FF, FF, EE, JJ, DD, GG, AA, KK, BB, GG, KK, DD, SC, EE, DD, JJ, KK, HH, HH, KK, KK, GG, HH, FF, BB, KK, AA, EE, DD, FF, SC, JJ, DD, CC, CC, JJ, KK, BB, BB, HH, CC, JJ, FF, HH, CC, KK, HH, FF, KK, AA, JJ, KK, BB, BB, JJ, JJ, FF, CC, EE, EE, KK, GG, GG, EE, FF, GG, HH, SC, JJ, BB, HH, GG, DD, DD, BB, GG, KK, HH, FF, EE, HH, JJ, KK, GG, GG, DD, HH, GG, AA, JJ, HH, KK, JJ, JJ, HH, KK, FF, HH, DD, JJ, EE, BB, KK, EE, JJ, EE, SC, CC, JJ, KK, KK, HH, EE, AA, FF, FF, DD, EE, GG, HH, BB, DD, EE, FF, SC, JJ, JJ, EE, EE, GG, JJ, FF, HH, DD, JJ, JJ, GG, GG, GG, KK, CC, DD, HH, HH, DD, KK, CC, GG, JJ, HH, HH, FF, KK, SC, AA, DD, GG, JJ, FF, GG, GG, KK, BB, SC, EE, DD, JJ, KK, FF, HH, DD, DD, KK, KK, AA, FF, GG, CC, CC, DD, KK, CC, FF, EE, KK, DD, HH, GG, JJ, CC, CC, CC, KK, DD, EE, EE, FF, GG, KK, JJ, KK, AA, GG, GG, CC, FF, DD, KK, HH, FF, EE, GG, CC, CC, JJ, EE, DD, JJ, KK, FF, FF, CC, FF, SC, FF, HH, CC, KK, FF, HH, JJ, BB, GG, EE, EE, JJ, KK, FF, GG, JJ, DD, KK, BB, FF, FF, JJ, EE, HH, KK, JJ, BB, GG, DD, DD, KK, HH, JJ, JJ, KK, KK, DD, FF, HH, JJ, HH, HH, SC, KK, JJ, JJ, AA, HH, HH, FF, GG, CC, CC, JJ, EE, HH, GG, KK, EE, AA, FF, HH, HH, GG, GG, CC, HH, SC, BB, JJ, GG, HH, EE, JJ, HH, HH, FF, FF, GG, JJ, KK, EE, HH, GG, AA, FF, BB, GG, JJ, JJ, JJ, JJ, KK, SC, EE, GG, GG, JJ, JJ, FF, FF, AA, KK, EE, HH, HH, GG, GG, FF, KK, KK, EE, EE, BB, BB, HH, DD, FF, EE, JJ, JJ, GG, HH, EE, JJ, AA, DD, DD, BB, GG, KK, KK, JJ, EE, SC, HH, JJ, EE, FF, EE, EE, JJ, JJ, KK, GG, SC, FF, FF, DD, EE, KK, GG, GG, GG, EE, FF, JJ, HH, KK, BB, EE, HH, SC, JJ, JJ, DD, KK, JJ, KK, FF, FF, JJ, JJ, EE, BB, BB, JJ, EE, DD, GG, HH, BB, DD, DD, SC, FF, EE, KK, KK, GG, GG, JJ, DD, EE,
        });
        bgReels.add(new String[]{
                JJ, BB, BB, KK, KK, HH, HH, CC, HH, JJ, HH, GG, GG, FF, EE, HH, FF, DD, GG, CC, CC, HH, KK, KK, FF, DD, CC, EE, KK, HH, KK, EE, JJ, FF, FF, JJ, KK, FF, GG, JJ, DD, GG, GG, JJ, KK, JJ, FF, GG, AA, JJ, KK, HH, HH, FF, FF, HH, KK, FF, FF, BB, SC, JJ, FF, EE, EE, HH, DD, DD, GG, BB, HH, KK, CC, GG, GG, DD, DD, JJ, CC, AA, KK, KK, EE, SC, HH, FF, KK, CC, WC, EE, JJ, HH, GG, FF, EE, KK, JJ, CC, CC, KK, HH, BB, FF, SC, FF, GG, FF, HH, AA, JJ, GG, HH, KK, EE, EE, JJ, DD, CC, KK, JJ, KK, HH, JJ, KK, AA, FF, EE, KK, JJ, DD, EE, HH, SC, BB, KK, EE, KK, AA, DD, GG, JJ, EE, BB, GG, CC, DD, FF, KK, AA, EE, GG, FF, HH, AA, EE, GG, GG, HH, HH, EE, BB, BB, JJ, AA, SC, KK, KK, EE, GG, DD, HH, AA, KK, CC, JJ, HH, JJ, HH, GG, BB, JJ, AA, GG, HH, CC, CC, SC, HH, JJ, GG, DD, FF, FF, EE, JJ, HH, KK, CC, GG, CC, BB, KK, KK, SC, GG, KK, HH, AA, EE, GG, CC, FF, FF, EE, JJ, HH, GG, GG, FF, FF, JJ, AA, HH, JJ, KK, HH, EE, HH, SC, CC, HH, GG, FF, JJ, GG, GG, FF, AA, KK, BB, FF, HH, EE, EE, GG, CC, KK, JJ, FF, EE, SC, HH, HH, GG, GG, EE, JJ, AA, DD, DD, FF, SC, GG, FF, KK, HH, BB, KK, GG, DD, GG, KK, AA, JJ, BB, JJ, HH, CC, HH, HH, EE, JJ, BB, HH, HH, FF, JJ, EE, GG, FF, FF, KK, BB, JJ, EE, GG, JJ, CC, GG, GG, KK, DD, DD, EE, FF, KK, JJ, GG, KK, HH, FF, JJ, KK, SC, BB, KK, KK, EE, CC, CC, GG, EE, FF, HH, DD, DD, EE, DD, DD, KK, AA, EE, GG, DD, KK, GG, JJ, KK, EE, JJ, EE, KK, KK, EE, HH, JJ, FF, FF, SC, GG, HH, KK, CC, GG, AA, EE, FF, EE, FF, GG, DD, KK, FF, BB, HH, FF, CC, CC, KK, GG, JJ, SC, CC, EE, EE, GG, GG, FF, KK, KK, CC, JJ, KK, KK, EE, EE, JJ, DD, DD, FF, JJ, GG, HH, AA, JJ, BB, EE, CC, HH, AA, CC, DD, GG, BB, BB, HH, HH, DD, JJ, EE, FF, KK, EE, HH, HH, KK, DD, DD, GG, EE, FF, DD, DD, HH, GG, HH, KK, GG, JJ, AA, EE, HH, HH, FF, GG, JJ, EE, DD, JJ, FF, GG, JJ, CC, CC, EE, BB, FF, JJ, FF, SC, HH, GG, FF, EE, KK, KK, KK, HH, FF, FF, KK, DD, SC, CC, KK, HH, JJ, EE, KK, FF, HH, BB, KK, FF, EE, CC, FF, EE, HH, JJ, HH, FF, GG, EE, CC, CC, JJ, HH, BB, GG, FF, KK, KK, GG, JJ, AA, FF, FF, SC, EE, EE, DD, HH, KK, JJ, GG, EE, HH, BB, KK, GG, BB, KK, GG, CC, HH, BB, KK, JJ, SC, KK, FF, FF, EE, JJ, AA, GG, KK, CC, FF,
        });
        bgReels.add(new String[]{
                AA, AA, HH, HH, CC, DD, JJ, JJ, EE, FF, JJ, FF, AA, DD, HH, FF, CC, EE, GG, HH, EE, SC, GG, CC, CC, JJ, HH, KK, EE, JJ, KK, KK, DD, EE, EE, BB, GG, CC, FF, DD, EE, KK, JJ, KK, CC, EE, CC, KK, HH, FF, AA, GG, DD, EE, EE, HH, GG, CC, CC, BB, KK, HH, DD, DD, JJ, AA, JJ, EE, HH, HH, CC, SC, JJ, JJ, KK, EE, EE, HH, BB, GG, GG, EE, CC, AA, JJ, CC, GG, EE, HH, EE, HH, BB, GG, CC, DD, GG, HH, HH, FF, AA, SC, HH, KK, WC, KK, EE, CC, AA, KK, FF, JJ, EE, FF, FF, BB, GG, FF, JJ, AA, HH, BB, JJ, HH, GG, CC, CC, SC, EE, HH, KK, DD, JJ, CC, AA, GG, HH, KK, GG, GG, JJ, KK, EE, EE, GG, FF, CC, JJ, KK, HH, EE, KK, KK, AA, EE, EE, EE, FF, SC, BB, DD, DD, AA, GG, DD, EE, HH, GG, EE, KK, EE, JJ, CC, CC, KK, KK, SC, BB, BB, KK, HH, GG, BB, KK, JJ, FF, FF, GG, JJ, HH, HH, DD, KK, BB, GG, HH, JJ, FF, FF, KK, JJ, AA, GG, KK, KK, BB, JJ, JJ, AA, DD, SC, FF, GG, JJ, JJ, HH, BB, BB, JJ, CC, CC, GG, DD, DD, AA, CC, FF, SC, JJ, EE, CC, JJ, KK, AA, FF, GG, GG, FF, KK, GG, CC, CC, JJ, GG, DD, BB, FF, GG, AA, CC, HH, KK, BB, BB, FF, AA, FF, HH, HH, EE, BB, CC, KK, KK, SC, GG, JJ, AA, GG, AA, FF, FF, BB, HH, HH, EE, EE, JJ, HH, GG, HH, DD, KK, KK, BB, AA, SC, KK, DD, JJ, DD, DD, GG, FF, AA, BB, KK, HH, BB, BB, FF, GG, EE, EE, HH, HH, DD, BB, BB, HH, JJ, SC, DD, DD, HH, HH, AA, BB, EE, FF, FF, KK, DD, CC, CC, DD, DD, GG, EE, EE, GG, HH, DD, KK, EE, GG, AA, KK, BB, KK, DD, DD, EE, EE, KK, DD, GG, CC, CC, HH, DD, JJ, GG, GG, HH, HH, DD, DD, CC, CC, KK, HH, EE, BB, CC, AA, KK, FF, FF, GG, BB, KK, FF, FF, DD, BB, AA, GG, SC, HH, JJ, GG, FF, GG, GG, KK, DD, JJ, FF, EE, EE, HH, KK, JJ, KK, AA, JJ, DD, KK, JJ, EE, HH, BB, BB, GG, GG, HH, FF, KK, CC, AA, DD, EE, EE, DD, JJ, GG, HH, HH, KK, JJ, EE, DD, GG, JJ, FF, FF, KK, KK, HH, AA, JJ, DD, EE, CC, AA, AA, DD, HH, CC, CC, KK, HH, EE, JJ, DD, SC, HH, HH, BB, EE, FF, DD, FF, DD, GG, GG, GG, BB, JJ, CC, GG, SC, AA, AA, BB, JJ, HH, KK, AA, CC, BB, KK, GG, BB, JJ, CC, DD, DD, SC, HH, AA, CC, KK, DD, DD, FF, HH, JJ, JJ, KK, CC, DD, EE, GG, HH, BB, CC, BB, FF, FF, GG, GG, JJ, KK, KK, DD, DD, JJ, GG, HH, BB, BB, GG, SC, FF, JJ, CC, GG, EE, JJ, EE, EE, HH, CC, FF, HH, FF, JJ, FF, CC,
        });
        bgReels.add(new String[]{
                AA, HH, HH, KK, GG, GG, AA, KK, HH, HH, JJ, KK, KK, CC, FF, GG, DD, HH, GG, EE, JJ, JJ, AA, GG, DD, GG, EE, HH, HH, KK, JJ, KK, KK, HH, FF, KK, KK, JJ, CC, EE, HH, HH, GG, GG, BB, KK, DD, KK, EE, EE, GG, DD, HH, DD, HH, EE, EE, DD, DD, KK, JJ, JJ, DD, KK, GG, KK, EE, HH, KK, KK, GG, JJ, KK, GG, HH, HH, SC, EE, FF, BB, JJ, CC, DD, EE, GG, DD, HH, JJ, CC, BB, SC, GG, FF, DD, CC, JJ, KK, KK, KK, FF, GG, EE, SC, HH, FF, FF, DD, KK, HH, EE, JJ, JJ, CC, CC, AA, JJ, KK, GG, JJ, HH, BB, SC, GG, JJ, DD, DD, CC, JJ, EE, FF, CC, WC, DD, BB, JJ, KK, HH, BB, BB, KK, EE, CC, FF, JJ, FF, HH, GG, SC, EE, KK, KK, FF, GG, EE, CC, EE, JJ, FF, FF, CC, KK, AA, EE, EE, BB, JJ, JJ, CC, FF, HH, GG, DD, DD, AA, HH, HH, KK, GG, FF, EE, SC, BB, FF, KK, KK, KK, JJ, BB, BB, FF, CC, DD, DD, HH, EE, GG, CC, FF, FF, KK, GG, JJ, BB, DD, FF, JJ, GG, SC, CC, FF, KK, JJ, DD, BB, EE, FF, JJ, FF, DD, AA, JJ, EE, CC, FF, DD, JJ, CC, CC, JJ, DD, GG, FF, KK, KK, JJ, JJ, JJ, HH, HH, EE, DD, GG, DD, CC, FF, KK, JJ, EE, DD, JJ, JJ, KK, KK, WC, FF, JJ, EE, EE, HH, FF, DD, FF, FF, CC, JJ, SC, AA, KK, KK, FF, CC, BB, HH, HH, EE, EE, HH, KK, JJ, EE, CC, FF, JJ, KK, GG, EE, JJ, CC, GG, DD, DD, JJ, EE, EE, CC, JJ, KK, KK, FF, GG, CC, HH, HH, BB, EE, CC, FF, HH, HH, GG, DD, HH, HH, KK, KK, GG, JJ, CC, KK, JJ, FF, CC, EE, EE, HH, HH, HH, CC, DD, HH, CC, CC, FF, HH, JJ, KK, KK, FF, HH, CC, SC, FF, JJ, BB, GG, DD, DD, DD, EE, JJ, KK, EE, KK, FF, EE, CC, CC, JJ, DD, FF, JJ, HH, BB, GG, EE, SC, KK, KK, JJ, FF, KK, EE, KK, CC, EE, FF, FF, BB, DD, EE, DD, JJ, SC, HH, EE, CC, GG, HH, HH, DD, EE, FF, KK, AA, KK, FF, CC, FF, GG, GG, FF, SC, HH, JJ, JJ, EE, GG, FF, DD, GG, CC, CC, HH, AA, HH, JJ, EE, CC, FF, BB, BB, AA, GG, GG, BB, BB, JJ, JJ, EE, GG, AA, CC, GG, DD, FF, SC, CC, EE, DD, DD, FF, EE, BB, GG, CC, JJ, JJ, HH, HH, JJ, EE, EE, KK, CC, JJ, SC, FF, GG, GG, DD, DD, EE, KK, HH, HH, GG, EE, GG, JJ, DD, GG, FF, JJ, JJ, KK, GG, FF, CC, CC, FF, GG, DD, FF, SC, JJ, KK, EE, KK, FF, GG, DD, CC, AA, FF, HH, BB, DD, KK, KK, KK, JJ, JJ, GG, GG, HH, CC, CC, SC, JJ, EE, BB, BB, EE, EE, JJ, GG, FF, HH, HH, KK, BB, JJ, GG, EE, EE, KK, FF, JJ, KK, DD,});
        bgReels.add(new String[]{
                GG, CC, CC, BB, FF, EE, BB, CC, JJ, DD, DD, BB, BB, KK, AA, CC, FF, DD, BB, GG, FF, FF, EE, HH, FF, BB, GG, JJ, EE, KK, JJ, AA, AA, DD, DD, KK, BB, FF, EE, GG, DD, FF, KK, AA, AA, BB, FF, JJ, BB, SC, KK, HH, EE, BB, DD, GG, HH, HH, AA, BB, HH, GG, FF, HH, JJ, DD, CC, DD, EE, DD, AA, HH, JJ, SC, JJ, DD, GG, GG, AA, DD, AA, BB, FF, DD, BB, FF, HH, HH, BB, BB, AA, EE, JJ, KK, EE, GG, CC, CC, FF, EE, KK, GG, HH, EE, BB, BB, FF, KK, JJ, CC, HH, DD, DD, FF, JJ, HH, CC, SC, DD, GG, EE, AA, GG, HH, FF, FF, EE, HH, GG, DD, EE, AA, FF, BB, HH, KK, DD, KK, AA, JJ, GG, EE, BB, SC, CC, DD, EE, KK, GG, DD, JJ, JJ, KK, GG, EE, AA, DD, GG, BB, EE, BB, BB, HH, EE, EE, SC, DD, JJ, FF, DD, EE, FF, EE, GG, GG, JJ, BB, BB, JJ, GG, HH, AA, CC, KK, CC, CC, HH, DD, GG, JJ, BB, FF, JJ, KK, GG, BB, DD, CC, JJ, EE, BB, HH, SC, CC, CC, HH, KK, KK, GG, JJ, CC, HH, FF, HH, CC, DD, BB, BB, FF, FF, HH, EE, EE, JJ, KK, SC, AA, CC, HH, FF, BB, JJ, CC, CC, EE, DD, EE, HH, DD, GG, FF, KK, KK, EE, JJ, CC, SC, KK, CC, JJ, FF, HH, DD, CC, JJ, FF, AA, AA, DD, KK, KK, JJ, JJ, EE, BB, SC, BB, HH, KK, JJ, EE, DD, CC, CC, GG, GG, KK, CC, FF, GG, AA, FF, KK, KK, SC, HH, HH, EE, KK, FF, BB, CC, GG, GG, JJ, JJ, GG, CC, BB, KK, CC, DD, DD, JJ, GG, DD, BB, BB, GG, HH, EE, GG, GG, KK, KK, SC, DD, DD, CC, GG, JJ, JJ, FF, BB, DD, DD, CC, AA, FF, CC, SC, HH, DD, DD, HH, GG, JJ, AA, BB, GG, EE, JJ, HH, BB, SC, FF, FF, AA, EE, CC, KK, HH, CC, BB, GG, EE, FF, HH, FF, JJ, GG, JJ, BB, BB, CC, CC, CC, WC, DD, BB, KK, GG, CC, EE, SC, BB, BB, FF, KK, FF, HH, HH, KK, CC, JJ, EE, KK, SC, DD, DD, GG, JJ, DD, DD, BB, BB, EE, BB, KK, EE, FF, DD, GG, CC, CC, GG, BB, FF, FF, HH, AA, EE, GG, CC, KK, HH, GG, EE, BB, KK, HH, EE, DD, CC, DD, AA, HH, JJ, GG, BB, KK, KK, SC, JJ, KK, CC, EE, GG, FF, FF, JJ, GG, CC, BB, EE, AA, GG, CC, EE, CC, KK, JJ, CC, EE, DD, HH, HH, CC, JJ, FF, FF, CC, CC, CC, BB, KK, AA, GG, HH, FF, KK, JJ, HH, DD, DD, EE, BB, EE, BB, GG, DD, JJ, JJ, EE, CC, BB, GG, JJ, DD, JJ, FF, HH, HH, DD, GG, AA, FF, FF, EE, CC, HH, HH, SC, HH, CC, EE, EE, BB, HH, BB, KK, AA, HH, GG, CC, DD, AA, SC, EE, CC, DD, FF, FF, AA, GG, GG, JJ, HH, CC, FF,});
        bgReels.add(new String[]{
                FF, BB, FF, AA, KK, BB, HH, EE, FF, FF, BB, JJ, SC, CC, AA, AA, EE, DD, KK, AA, HH, GG, HH, GG, EE, JJ, AA, KK, CC, JJ, HH, BB, BB, DD, DD, FF, SC, EE, CC, JJ, DD, KK, AA, EE, BB, CC, GG, KK, KK, HH, JJ, HH, CC, GG, DD, AA, HH, BB, FF, AA, HH, DD, KK, CC, FF, FF, DD, DD, KK, EE, DD, BB, EE, JJ, BB, DD, SC, JJ, AA, BB, HH, CC, AA, EE, EE, GG, HH, GG, CC, AA, FF, SC, JJ, GG, CC, HH, GG, CC, KK, KK, JJ, CC, HH, CC, BB, BB, EE, JJ, EE, AA, HH, JJ, CC, CC, FF, SC, BB, JJ, KK, KK, BB, EE, AA, BB, KK, GG, CC, GG, FF, KK, KK, AA, BB, EE, HH, CC, BB, KK, BB, GG, SC, CC, KK, FF, EE, DD, JJ, HH, BB, EE, KK, CC, JJ, GG, CC, SC, DD, FF, KK, CC, GG, AA, HH, FF, BB, BB, AA, GG, CC, DD, JJ, AA, HH, GG, GG, FF, FF, BB, EE, HH, DD, FF, FF, JJ, EE, EE, GG, DD, BB, KK, CC, GG, SC, KK, FF, AA, CC, EE, AA, EE, EE, HH, DD, BB, FF, FF, AA, DD, CC, EE, EE, DD, GG, BB, FF, DD, SC, GG, GG, FF, EE, DD, CC, KK, GG, HH, KK, CC, BB, GG, JJ, BB, KK, EE, AA, BB, EE, HH, EE, FF, GG, CC, HH, CC, FF, EE, HH, FF, GG, FF, EE, FF, BB, EE, FF, HH, AA, FF, DD, JJ, GG, BB, BB, CC, GG, GG, AA, AA, JJ, KK, CC, BB, FF, GG, BB, DD, JJ, EE, DD, GG, GG, DD, CC, BB, EE, FF, HH, CC, WC, GG, CC, HH, AA, FF, CC, BB, JJ, EE, SC, JJ, HH, DD, DD, JJ, AA, CC, KK, HH, FF, JJ, GG, CC, DD, BB, FF, JJ, HH, EE, EE, AA, FF, CC, GG, JJ, DD, DD, KK, DD, SC, FF, BB, CC, AA, DD, HH, JJ, KK, KK, FF, FF, CC, KK, KK, BB, GG, BB, GG, GG, FF, CC, AA, DD, FF, KK, EE, CC, CC, BB, SC, KK, FF, DD, BB, JJ, DD, GG, EE, EE, HH, KK, GG, DD, EE, CC, HH, KK, BB, GG, GG, SC, BB, KK, BB, HH, AA, CC, JJ, DD, DD, GG, BB, FF, DD, EE, KK, AA, CC, EE, JJ, JJ, SC, DD, KK, DD, HH, EE, JJ, KK, DD, HH, CC, CC, DD, FF, DD, HH, AA, CC, KK, JJ, HH, SC, DD, JJ, BB, BB, HH, BB, DD, DD, EE, CC, CC, GG, KK, DD, CC, AA, GG, GG, KK, DD, EE, JJ, CC, EE, FF, DD, AA, HH, JJ, SC, EE, CC, BB, DD, GG, AA, KK, EE, FF, FF, EE, EE, WC, JJ, DD, EE, HH, GG, JJ, KK, SC, BB, AA, EE, JJ, CC, CC, FF, AA, GG, KK, CC, JJ, EE, AA, JJ, JJ, KK, GG, CC, SC, BB, AA, BB, GG, KK, BB, DD, FF, BB, JJ, HH, FF, CC, CC, JJ, SC, AA, BB, BB, DD, FF, HH, JJ, KK, HH, GG, EE, BB, SC, GG, FF, KK, GG, GG, AA, EE, BB,});

        List<String[]> topReel = new ArrayList<>(5);
        topReel.add(new String[]{
                KK, KK, FF, BB, CC, AA, DD, JJ, FF, BB, HH, EE, HH, KK, CC, JJ, DD, HH, KK, JJ, KK, CC, CC, KK, DD, DD, JJ, JJ, KK, JJ, DD, CC, JJ, JJ, CC, BB, KK, EE, JJ, BB, KK, KK, FF, KK, HH, EE, DD, GG, BB, CC, KK, FF, CC, DD, KK, JJ, EE, FF, DD, JJ, KK, DD, FF, GG, CC, AA, CC, JJ, KK, BB, JJ, DD, BB, HH, DD, CC, AA, KK, HH, HH, CC, JJ, KK, GG, EE, JJ, JJ, DD, DD, KK, GG, HH, EE, BB, JJ, DD, DD, KK, HH, KK, CC, WC, KK, EE, EE, DD, JJ, KK, DD, DD, GG, BB, KK, AA, JJ, KK, CC, JJ, FF, EE, DD, KK, JJ, FF, KK, HH, HH, DD, HH, JJ, GG, EE, CC, JJ, BB, FF, JJ, GG, DD, GG, HH, JJ, GG, JJ, JJ, FF, EE, KK, BB, EE, JJ, JJ, KK, FF, EE, EE, JJ, CC, JJ, DD, JJ, BB, KK, KK, HH, JJ, FF, CC, AA, GG, EE, JJ, KK, JJ, JJ, DD, GG, DD, CC, JJ, EE, KK, GG, FF, FF, HH, GG, FF, AA, FF, EE, KK, BB, KK, GG, FF, BB, BB, EE, AA, HH, EE, FF, HH, KK, EE, EE, FF, BB, DD, AA, CC, JJ, KK, GG, FF, CC, GG, EE, HH, KK, FF, DD, DD, JJ, EE, CC, KK, EE, GG, HH, FF, DD, KK, EE, HH, FF, HH, KK, EE, GG, KK, FF, BB, HH, BB, CC, HH, HH, EE, GG, HH, JJ, HH, CC, JJ, KK, CC, AA, EE, JJ, JJ, EE, KK, FF, DD, AA, GG, HH, FF, JJ, AA, KK, EE, KK, GG, BB, BB, GG, CC, HH, BB, AA, GG, KK, JJ, FF, AA, EE, BB, KK, HH, CC, FF, CC, KK, DD, KK, FF, CC, KK, DD, GG, KK, BB, AA, AA, HH, EE, EE, HH, JJ, KK, FF, BB, AA, CC, BB, KK, HH, FF, DD, GG, AA, FF, CC, FF, HH, FF, KK, FF, DD, CC, HH, HH, EE, GG, CC, JJ, EE, FF, JJ, BB, GG, FF, GG, EE, KK, EE, GG, DD, DD, HH, KK, CC, FF, JJ, EE, BB, KK, HH, BB, GG, JJ, AA, KK, CC, JJ, HH, GG, KK, HH, FF, HH, JJ, HH, EE, GG, KK, AA, JJ, EE, BB, BB, EE, GG, CC, CC, EE, KK, HH, GG, CC, AA, GG, FF, HH, GG, HH, JJ, CC, KK, HH, KK, GG, GG, HH, EE, JJ, EE, CC, GG, AA, GG, JJ, KK, BB, JJ, GG, BB, FF, CC, GG, KK, JJ, JJ, EE, KK, GG, DD, CC, HH, EE, HH, JJ, HH, KK, DD, EE, HH, EE, DD, HH, FF, JJ, GG, HH, KK, JJ, GG, HH, DD, EE, JJ, FF, HH, BB, KK, EE, JJ, CC, KK, DD, BB, GG, GG, HH, GG, EE, HH, CC, JJ, BB, FF, GG, FF, JJ, HH, KK, GG, EE, DD, FF, JJ, DD, KK, EE, GG, HH, EE, FF, JJ, HH, FF, BB, KK, HH, JJ, KK, KK, JJ, HH, FF, EE, GG, FF, KK, CC, KK, FF, DD, JJ, KK, FF, HH, HH, JJ, AA, GG, BB, GG, EE, JJ, CC, KK, HH, JJ, DD, AA, EE,});


        List<String[]> fgReels = new ArrayList<>(5);

        fgReels.add(new String[]{
                GG, BB, FF, CC, JJ, EE, BB, DD, KK, HH, KK, FF, AA, KK, KK, JJ, JJ, CC, GG, HH, DD, EE, CC, AA, EE, GG, KK, HH, FF, CC, CC, BB, EE, CC, CC, CC, AA, EE, FF, DD, CC, HH, GG, EE, BB, FF, KK, GG, JJ, KK, HH, GG, FF, EE, CC, KK, JJ, EE, DD, HH, FF, KK, JJ, FF, GG, CC, DD, CC, HH, KK, GG, DD, DD, JJ, KK, CC, JJ, EE, HH, KK, JJ, FF, EE, CC, GG, EE, AA, DD, JJ, BB, KK, HH, HH, EE, FF, JJ, DD, FF, DD, KK, GG, KK, EE, FF, HH, KK, JJ, GG, HH, KK, JJ, JJ, FF, FF, EE, JJ, DD, GG, AA, KK, BB, GG, KK, DD, SC, EE, DD, JJ, KK, HH, HH, KK, KK, GG, HH, FF, BB, KK, AA, EE, DD, FF, SC, JJ, DD, CC, CC, JJ, KK, BB, BB, HH, CC, JJ, FF, HH, CC, KK, HH, FF, KK, AA, JJ, KK, BB, BB, JJ, JJ, FF, CC, EE, EE, KK, GG, GG, EE, FF, GG, HH, KK, JJ, BB, HH, GG, DD, DD, BB, GG, KK, HH, FF, EE, HH, JJ, KK, GG, GG, DD, HH, GG, AA, JJ, HH, KK, JJ, JJ, HH, KK, FF, HH, DD, JJ, EE, BB, KK, EE, JJ, EE, JJ, CC, JJ, KK, KK, HH, EE, AA, FF, FF, DD, EE, GG, HH, BB, DD, EE, FF, SC, JJ, JJ, EE, EE, GG, JJ, FF, HH, DD, JJ, JJ, GG, GG, GG, KK, CC, DD, HH, HH, DD, KK, CC, GG, JJ, HH, HH, FF, KK, KK, AA, DD, GG, JJ, FF, GG, GG, KK, BB, SC, EE, DD, JJ, KK, FF, HH, DD, DD, KK, KK, AA, FF, GG, CC, CC, DD, KK, CC, FF, EE, KK, DD, HH, GG, JJ, CC, CC, CC, KK, DD, EE, EE, FF, GG, KK, JJ, KK, AA, GG, GG, CC, FF, DD, KK, HH, FF, EE, GG, CC, CC, JJ, EE, DD, JJ, KK, FF, FF, CC, FF, KK, FF, HH, CC, KK, FF, HH, JJ, BB, GG, EE, EE, JJ, KK, FF, GG, JJ, DD, KK, BB, FF, FF, JJ, EE, HH, KK, JJ, BB, GG, DD, DD, KK, HH, JJ, JJ, KK, KK, DD, FF, HH, JJ, HH, HH, KK, KK, JJ, JJ, AA, HH, HH, FF, GG, CC, CC, JJ, EE, HH, GG, KK, EE, AA, FF, HH, HH, GG, GG, CC, HH, GG, BB, JJ, GG, HH, EE, JJ, HH, HH, FF, FF, GG, JJ, KK, EE, HH, GG, AA, FF, BB, GG, JJ, JJ, JJ, JJ, KK, SC, EE, GG, GG, JJ, JJ, FF, FF, AA, KK, EE, HH, HH, GG, GG, FF, KK, KK, EE, EE, BB, BB, HH, DD, FF, EE, JJ, JJ, GG, HH, EE, JJ, AA, DD, DD, BB, GG, KK, KK, JJ, EE, SC, HH, JJ, EE, FF, EE, EE, JJ, JJ, KK, GG, KK, FF, FF, DD, EE, KK, GG, GG, GG, EE, FF, JJ, HH, KK, BB, EE, HH, SC, JJ, JJ, DD, KK, JJ, KK, FF, FF, JJ, JJ, EE, BB, BB, JJ, EE, DD, GG, HH, BB, DD, DD, SC, FF, EE, KK, KK, GG, GG, JJ, DD, EE,
        });

        fgReels.add(new String[]{
                JJ, BB, BB, KK, KK, HH, HH, CC, HH, JJ, HH, GG, GG, FF, EE, HH, FF, DD, GG, CC, CC, HH, KK, KK, FF, DD, CC, EE, KK, HH, KK, EE, JJ, FF, FF, JJ, KK, FF, GG, JJ, DD, GG, GG, JJ, KK, JJ, FF, GG, AA, JJ, KK, HH, HH, FF, FF, HH, KK, FF, FF, BB, SC, JJ, FF, EE, EE, HH, DD, DD, GG, BB, HH, KK, CC, GG, GG, DD, DD, JJ, CC, AA, KK, KK, EE, SC, HH, FF, KK, CC, WC, EE, JJ, HH, GG, FF, EE, KK, JJ, CC, CC, KK, HH, BB, FF, JJ, FF, GG, FF, HH, AA, JJ, GG, HH, KK, EE, EE, JJ, DD, CC, KK, JJ, KK, HH, JJ, KK, AA, FF, EE, KK, JJ, DD, EE, HH, EE, BB, KK, EE, KK, AA, DD, GG, JJ, EE, BB, GG, CC, DD, FF, KK, AA, EE, GG, FF, HH, AA, EE, GG, GG, HH, HH, EE, BB, BB, JJ, AA, SC, KK, KK, EE, GG, DD, HH, AA, KK, CC, JJ, HH, JJ, HH, GG, BB, JJ, AA, GG, HH, CC, CC, SC, HH, JJ, GG, DD, FF, FF, EE, JJ, HH, KK, CC, GG, CC, BB, KK, KK, JJ, GG, KK, HH, AA, EE, GG, CC, FF, FF, EE, JJ, HH, GG, GG, FF, FF, JJ, AA, HH, JJ, KK, HH, EE, HH, KK, CC, HH, GG, FF, JJ, GG, GG, FF, AA, KK, BB, FF, HH, EE, EE, GG, CC, KK, JJ, FF, EE, KK, HH, HH, GG, GG, EE, JJ, AA, DD, DD, FF, KK, GG, FF, KK, HH, BB, KK, GG, DD, GG, KK, AA, JJ, BB, JJ, HH, CC, HH, HH, EE, JJ, BB, HH, HH, FF, JJ, EE, GG, FF, FF, KK, BB, JJ, EE, GG, JJ, CC, GG, GG, KK, DD, DD, EE, FF, KK, JJ, GG, KK, HH, FF, JJ, KK, SC, BB, KK, KK, EE, CC, CC, GG, EE, FF, HH, DD, DD, EE, DD, DD, KK, AA, EE, GG, DD, KK, GG, JJ, KK, EE, JJ, EE, KK, KK, EE, HH, JJ, FF, FF, GG, GG, HH, KK, CC, GG, AA, EE, FF, EE, FF, GG, DD, KK, FF, BB, HH, FF, CC, CC, KK, GG, JJ, SC, CC, EE, EE, GG, GG, FF, KK, KK, CC, JJ, KK, KK, EE, EE, JJ, DD, DD, FF, JJ, GG, HH, AA, JJ, BB, EE, CC, HH, AA, CC, DD, GG, BB, BB, HH, HH, DD, JJ, EE, FF, KK, EE, HH, HH, KK, DD, DD, GG, EE, FF, DD, DD, HH, GG, HH, KK, GG, JJ, AA, EE, HH, HH, FF, GG, JJ, EE, DD, JJ, FF, GG, JJ, CC, CC, EE, BB, FF, JJ, FF, GG, HH, GG, FF, EE, KK, KK, KK, HH, FF, FF, KK, DD, KK, CC, KK, HH, JJ, EE, KK, FF, HH, BB, KK, FF, EE, CC, FF, EE, HH, JJ, HH, FF, GG, EE, CC, CC, JJ, HH, BB, GG, FF, KK, KK, GG, JJ, AA, FF, FF, JJ, EE, EE, DD, HH, KK, JJ, GG, EE, HH, BB, KK, GG, BB, KK, GG, CC, HH, BB, KK, JJ, GG, KK, FF, FF, EE, JJ, AA, GG, KK, CC, FF,
        });

        fgReels.add(new String[]{
                AA, AA, HH, HH, CC, DD, JJ, JJ, EE, FF, JJ, FF, AA, DD, HH, FF, CC, EE, GG, HH, EE, KK, GG, CC, CC, JJ, HH, KK, EE, JJ, KK, KK, DD, EE, EE, BB, GG, CC, FF, DD, EE, KK, JJ, KK, CC, EE, CC, KK, HH, FF, AA, GG, DD, EE, EE, HH, GG, CC, CC, BB, KK, HH, DD, DD, JJ, AA, JJ, EE, HH, HH, CC, KK, JJ, JJ, KK, EE, EE, HH, BB, GG, GG, EE, CC, AA, JJ, CC, GG, EE, HH, EE, HH, BB, GG, CC, DD, GG, HH, HH, FF, AA, JJ, HH, KK, WC, KK, EE, CC, AA, KK, FF, JJ, EE, FF, FF, BB, GG, FF, JJ, AA, HH, BB, JJ, HH, GG, CC, CC, SC, EE, HH, KK, DD, JJ, CC, AA, GG, HH, KK, GG, GG, JJ, KK, EE, EE, GG, FF, CC, JJ, KK, HH, EE, KK, KK, AA, EE, EE, EE, FF, SC, BB, DD, DD, AA, GG, DD, EE, HH, GG, EE, KK, EE, JJ, CC, CC, KK, KK, JJ, BB, BB, KK, HH, GG, BB, KK, JJ, FF, FF, GG, JJ, HH, HH, DD, KK, BB, GG, HH, JJ, FF, FF, KK, JJ, AA, GG, KK, KK, BB, JJ, JJ, AA, DD, SC, FF, GG, JJ, JJ, HH, BB, BB, JJ, CC, CC, GG, DD, DD, AA, CC, FF, KK, JJ, EE, CC, JJ, KK, AA, FF, GG, GG, FF, KK, GG, CC, CC, JJ, GG, DD, BB, FF, GG, AA, CC, HH, KK, BB, BB, FF, AA, FF, HH, HH, EE, BB, CC, KK, KK, JJ, GG, JJ, AA, GG, AA, FF, FF, BB, HH, HH, EE, EE, JJ, HH, GG, HH, DD, KK, KK, BB, AA, SC, KK, DD, JJ, DD, DD, GG, FF, AA, BB, KK, HH, BB, BB, FF, GG, EE, EE, HH, HH, DD, BB, BB, HH, JJ, FF, DD, DD, HH, HH, AA, BB, EE, FF, FF, KK, DD, CC, CC, DD, DD, GG, EE, EE, GG, HH, DD, KK, EE, GG, AA, KK, BB, KK, DD, DD, EE, EE, KK, DD, GG, CC, CC, HH, DD, JJ, GG, GG, HH, HH, DD, DD, CC, CC, KK, HH, EE, BB, CC, AA, KK, FF, FF, GG, BB, KK, FF, FF, DD, BB, AA, GG, KK, HH, JJ, GG, FF, GG, GG, KK, DD, JJ, FF, EE, EE, HH, KK, JJ, KK, AA, JJ, DD, KK, JJ, EE, HH, BB, BB, GG, GG, HH, FF, KK, CC, AA, DD, EE, EE, DD, JJ, GG, HH, HH, KK, JJ, EE, DD, GG, JJ, FF, FF, KK, KK, HH, AA, JJ, DD, EE, CC, AA, AA, DD, HH, CC, CC, KK, HH, EE, JJ, DD, SC, HH, HH, BB, EE, FF, DD, FF, DD, GG, GG, GG, BB, JJ, CC, GG, SC, AA, AA, BB, JJ, HH, KK, AA, CC, BB, KK, GG, BB, JJ, CC, DD, DD, JJ, HH, AA, CC, KK, DD, DD, FF, HH, JJ, JJ, KK, CC, DD, EE, GG, HH, BB, CC, BB, FF, FF, GG, GG, JJ, KK, KK, DD, DD, JJ, GG, HH, BB, BB, GG, HH, FF, JJ, CC, GG, EE, JJ, EE, EE, HH, CC, FF, HH, FF, JJ, FF, CC,});

        fgReels.add(new String[]{
                AA, HH, HH, KK, GG, GG, AA, KK, HH, HH, JJ, KK, KK, CC, KK, GG, DD, HH, GG, EE, JJ, JJ, AA, GG, DD, GG, EE, HH, HH, KK, JJ, KK, KK, HH, FF, KK, KK, JJ, CC, EE, HH, HH, GG, GG, BB, KK, DD, KK, EE, EE, GG, DD, HH, DD, HH, EE, EE, DD, DD, KK, JJ, JJ, DD, KK, GG, KK, EE, HH, KK, KK, GG, JJ, KK, GG, HH, HH, KK, EE, FF, BB, JJ, CC, DD, EE, GG, DD, HH, JJ, CC, BB, KK, GG, FF, DD, CC, JJ, KK, KK, KK, FF, GG, EE, JJ, HH, FF, FF, DD, KK, HH, EE, JJ, JJ, CC, CC, AA, JJ, KK, GG, JJ, HH, BB, FF, GG, JJ, DD, DD, CC, JJ, EE, FF, CC, WC, DD, BB, JJ, KK, HH, BB, BB, KK, EE, CC, FF, JJ, FF, HH, GG, JJ, EE, KK, KK, FF, GG, EE, CC, EE, JJ, FF, FF, CC, KK, AA, EE, EE, BB, JJ, JJ, CC, FF, HH, GG, DD, DD, AA, HH, HH, KK, GG, FF, EE, JJ, BB, FF, KK, KK, KK, JJ, BB, BB, FF, CC, DD, DD, HH, EE, GG, CC, FF, FF, KK, GG, JJ, BB, DD, FF, JJ, GG, KK, CC, FF, KK, JJ, DD, BB, EE, FF, JJ, FF, DD, AA, JJ, EE, CC, FF, DD, JJ, CC, CC, JJ, DD, GG, FF, KK, KK, JJ, JJ, JJ, HH, HH, EE, DD, GG, DD, CC, FF, KK, JJ, EE, DD, JJ, JJ, KK, KK, WC, FF, JJ, EE, EE, HH, FF, DD, FF, FF, CC, JJ, GG, AA, KK, KK, FF, CC, BB, HH, HH, EE, EE, HH, KK, JJ, EE, CC, FF, JJ, KK, GG, EE, JJ, CC, GG, DD, DD, JJ, EE, EE, CC, JJ, KK, KK, FF, GG, CC, HH, HH, BB, EE, CC, FF, HH, HH, GG, DD, HH, HH, KK, KK, GG, JJ, CC, KK, JJ, FF, CC, EE, EE, HH, HH, HH, CC, DD, HH, CC, CC, FF, HH, JJ, KK, KK, FF, HH, CC, SC, FF, JJ, BB, GG, DD, DD, DD, EE, JJ, KK, EE, KK, FF, EE, CC, CC, JJ, DD, FF, JJ, HH, BB, GG, EE, JJ, KK, KK, JJ, FF, KK, EE, KK, CC, EE, FF, FF, BB, DD, EE, DD, JJ, JJ, HH, EE, CC, GG, HH, HH, DD, EE, FF, KK, AA, KK, FF, CC, FF, GG, GG, FF, SC, HH, JJ, JJ, EE, GG, FF, DD, GG, CC, CC, HH, AA, HH, JJ, EE, CC, FF, BB, BB, AA, GG, GG, BB, BB, JJ, JJ, EE, GG, AA, CC, GG, DD, FF, JJ, CC, EE, DD, DD, FF, EE, BB, GG, CC, JJ, JJ, HH, HH, JJ, EE, EE, KK, CC, JJ, KK, FF, GG, GG, DD, DD, EE, KK, HH, HH, GG, EE, GG, JJ, DD, GG, FF, JJ, JJ, KK, GG, FF, CC, CC, FF, GG, DD, FF, KK, JJ, KK, EE, KK, FF, GG, DD, CC, AA, FF, HH, BB, DD, KK, KK, KK, JJ, JJ, GG, GG, HH, CC, CC, KK, JJ, EE, BB, BB, EE, EE, JJ, GG, FF, HH, HH, KK, BB, JJ, GG, EE, EE, KK, FF, JJ, KK, DD,
        });

        fgReels.add(new String[]{
                GG, CC, CC, BB, FF, EE, BB, CC, JJ, DD, DD, BB, BB, KK, AA, KK, FF, DD, BB, GG, FF, FF, EE, HH, FF, BB, GG, JJ, EE, KK, JJ, AA, AA, DD, DD, KK, BB, FF, EE, GG, DD, FF, KK, AA, AA, BB, FF, JJ, BB, SC, KK, HH, EE, BB, DD, GG, HH, HH, AA, BB, HH, GG, FF, HH, JJ, DD, CC, DD, EE, DD, AA, HH, JJ, KK, JJ, DD, GG, GG, AA, DD, AA, BB, FF, DD, BB, FF, HH, HH, BB, BB, AA, EE, JJ, KK, EE, GG, CC, CC, FF, EE, KK, GG, HH, EE, BB, BB, FF, KK, JJ, CC, HH, DD, DD, FF, JJ, HH, CC, SC, DD, GG, EE, AA, GG, HH, FF, FF, EE, HH, GG, DD, EE, AA, FF, BB, HH, KK, DD, KK, AA, JJ, GG, EE, BB, GG, CC, DD, EE, KK, GG, DD, JJ, JJ, KK, GG, EE, AA, DD, GG, BB, EE, BB, BB, HH, EE, EE, SC, DD, JJ, FF, DD, EE, FF, EE, GG, GG, JJ, BB, BB, JJ, GG, HH, AA, CC, KK, CC, CC, HH, DD, GG, JJ, BB, FF, JJ, KK, GG, BB, DD, CC, JJ, EE, BB, HH, SC, CC, CC, HH, KK, KK, GG, JJ, CC, HH, FF, HH, CC, DD, BB, BB, FF, FF, HH, EE, EE, JJ, KK, JJ, AA, CC, HH, FF, BB, JJ, CC, CC, EE, DD, EE, HH, DD, GG, FF, KK, KK, EE, JJ, CC, SC, KK, CC, JJ, FF, HH, DD, CC, JJ, FF, AA, AA, DD, KK, KK, JJ, JJ, EE, BB, KK, BB, HH, KK, JJ, EE, DD, CC, CC, GG, GG, KK, CC, FF, GG, AA, FF, KK, KK, FF, HH, HH, EE, KK, FF, BB, CC, GG, GG, JJ, JJ, GG, CC, BB, KK, CC, DD, DD, JJ, GG, DD, BB, BB, GG, HH, EE, GG, GG, KK, KK, SC, DD, DD, CC, GG, JJ, JJ, FF, BB, DD, DD, CC, AA, FF, CC, JJ, HH, DD, DD, HH, GG, JJ, AA, BB, GG, EE, JJ, HH, BB, KK, FF, FF, AA, EE, CC, KK, HH, CC, BB, GG, EE, FF, HH, FF, JJ, GG, JJ, BB, BB, CC, CC, CC, WC, DD, BB, KK, GG, CC, EE, EE, BB, BB, FF, KK, FF, HH, HH, KK, CC, JJ, EE, KK, SC, DD, DD, GG, JJ, DD, DD, BB, BB, EE, BB, KK, EE, FF, DD, GG, CC, CC, GG, BB, FF, FF, HH, AA, EE, GG, CC, KK, HH, GG, EE, BB, KK, HH, EE, DD, CC, DD, AA, HH, JJ, GG, BB, KK, KK, JJ, JJ, KK, CC, EE, GG, FF, FF, JJ, GG, CC, BB, EE, AA, GG, CC, EE, CC, KK, JJ, CC, EE, DD, HH, HH, CC, JJ, FF, FF, CC, CC, CC, BB, KK, AA, GG, HH, FF, KK, JJ, HH, DD, DD, EE, BB, EE, BB, GG, DD, JJ, JJ, EE, CC, BB, GG, JJ, DD, JJ, FF, HH, HH, DD, GG, AA, FF, FF, EE, CC, HH, HH, GG, HH, CC, EE, EE, BB, HH, BB, KK, AA, HH, GG, CC, DD, AA, DD, EE, CC, DD, FF, FF, AA, GG, GG, JJ, HH, CC, FF,});

        fgReels.add(new String[]{
                FF, BB, FF, AA, KK, BB, HH, EE, FF, FF, BB, JJ, JJ, CC, AA, AA, EE, DD, KK, AA, HH, GG, HH, GG, EE, JJ, AA, KK, CC, JJ, HH, BB, BB, DD, DD, FF, HH, EE, CC, JJ, DD, KK, AA, EE, BB, CC, GG, KK, KK, HH, JJ, HH, CC, GG, DD, AA, HH, BB, FF, AA, HH, DD, KK, CC, FF, FF, DD, DD, KK, EE, DD, BB, EE, JJ, BB, DD, KK, JJ, AA, BB, HH, CC, AA, EE, EE, GG, HH, GG, CC, AA, FF, SC, JJ, GG, CC, HH, GG, CC, KK, KK, JJ, CC, HH, CC, BB, BB, EE, JJ, EE, AA, HH, JJ, CC, CC, FF, HH, BB, JJ, KK, KK, BB, EE, AA, BB, KK, GG, CC, GG, FF, KK, KK, AA, BB, EE, HH, CC, BB, KK, BB, GG, SC, CC, KK, FF, EE, DD, JJ, HH, BB, EE, KK, CC, JJ, GG, CC, SC, DD, FF, KK, CC, GG, AA, HH, FF, BB, BB, AA, GG, CC, DD, JJ, AA, HH, GG, GG, FF, FF, BB, EE, HH, DD, FF, FF, JJ, EE, EE, GG, DD, BB, KK, CC, GG, SC, KK, FF, AA, CC, EE, AA, EE, EE, HH, DD, BB, FF, FF, AA, DD, CC, EE, EE, DD, GG, BB, FF, DD, KK, GG, GG, FF, EE, DD, CC, KK, GG, HH, KK, CC, BB, GG, JJ, BB, KK, EE, AA, BB, EE, HH, EE, FF, GG, CC, HH, CC, FF, EE, HH, FF, GG, FF, EE, FF, BB, EE, FF, HH, AA, FF, DD, JJ, GG, BB, BB, CC, GG, GG, AA, AA, JJ, KK, CC, BB, FF, GG, BB, DD, JJ, EE, DD, GG, GG, DD, CC, BB, EE, FF, HH, CC, WC, GG, CC, HH, AA, FF, CC, BB, JJ, EE, SC, JJ, HH, DD, DD, JJ, AA, CC, KK, HH, FF, JJ, GG, CC, DD, BB, FF, JJ, HH, EE, EE, AA, FF, CC, GG, JJ, DD, DD, KK, DD, KK, FF, BB, CC, AA, DD, HH, JJ, KK, KK, FF, FF, CC, KK, KK, BB, GG, BB, GG, GG, FF, CC, AA, DD, FF, KK, EE, CC, CC, BB, SC, KK, FF, DD, BB, JJ, DD, GG, EE, EE, HH, KK, GG, DD, EE, CC, HH, KK, BB, GG, GG, KK, BB, KK, BB, HH, AA, CC, JJ, DD, DD, GG, BB, FF, DD, EE, KK, AA, CC, EE, JJ, JJ, EE, DD, KK, DD, HH, EE, JJ, KK, DD, HH, CC, CC, DD, FF, DD, HH, AA, CC, KK, JJ, HH, SC, DD, JJ, BB, BB, HH, BB, DD, DD, EE, CC, CC, GG, KK, DD, CC, AA, GG, GG, KK, DD, EE, JJ, CC, EE, FF, DD, AA, HH, JJ, DD, EE, CC, BB, DD, GG, AA, KK, EE, FF, FF, EE, EE, WC, JJ, DD, EE, HH, GG, JJ, KK, SC, BB, AA, EE, JJ, CC, CC, FF, AA, GG, KK, CC, JJ, EE, AA, JJ, JJ, KK, GG, CC, FF, BB, AA, BB, GG, KK, BB, DD, FF, BB, JJ, HH, FF, CC, CC, JJ, EE, AA, BB, BB, DD, FF, HH, JJ, KK, HH, GG, EE, BB, SC, GG, FF, KK, GG, GG, AA, EE, BB,});

        List<String[]> fgTopReel = new ArrayList<>(5);
        fgTopReel.add(new String[]{
                KK, KK, FF, BB, CC, AA, DD, JJ, FF, BB, HH, EE, HH, KK, CC, JJ, DD, HH, KK, JJ, KK, CC, CC, KK, DD, DD, JJ, JJ, KK, JJ, DD, CC, JJ, JJ, CC, BB, KK, EE, JJ, BB, KK, KK, FF, KK, HH, EE, DD, GG, BB, CC, KK, FF, CC, DD, KK, JJ, EE, FF, DD, JJ, KK, DD, FF, GG, CC, AA, CC, JJ, KK, BB, JJ, DD, BB, HH, DD, CC, AA, KK, HH, HH, CC, JJ, KK, GG, EE, JJ, JJ, DD, DD, KK, GG, HH, EE, BB, JJ, DD, DD, KK, HH, KK, CC, WC, KK, EE, EE, DD, JJ, KK, DD, DD, GG, BB, KK, AA, JJ, KK, CC, JJ, FF, EE, DD, KK, JJ, FF, KK, HH, HH, DD, HH, JJ, GG, EE, CC, JJ, BB, FF, JJ, GG, DD, GG, HH, JJ, GG, JJ, JJ, FF, EE, KK, BB, EE, JJ, JJ, KK, FF, EE, EE, JJ, CC, JJ, DD, JJ, BB, KK, KK, HH, JJ, FF, CC, AA, GG, EE, JJ, KK, JJ, JJ, DD, GG, DD, CC, JJ, EE, KK, GG, FF, FF, HH, GG, FF, AA, FF, EE, KK, BB, KK, GG, FF, BB, BB, EE, AA, HH, EE, FF, HH, KK, EE, EE, FF, BB, DD, AA, CC, JJ, KK, GG, FF, CC, GG, EE, HH, KK, FF, DD, DD, JJ, EE, CC, KK, EE, GG, HH, FF, DD, KK, EE, HH, FF, HH, KK, EE, GG, KK, FF, BB, HH, BB, CC, HH, HH, EE, GG, HH, JJ, HH, CC, JJ, KK, CC, AA, EE, JJ, JJ, EE, KK, FF, DD, AA, GG, HH, FF, JJ, AA, KK, EE, KK, GG, BB, BB, GG, CC, HH, BB, AA, GG, KK, JJ, FF, AA, EE, BB, KK, HH, CC, FF, CC, KK, DD, KK, FF, CC, KK, DD, GG, KK, BB, AA, AA, HH, EE, EE, HH, JJ, KK, FF, BB, AA, CC, BB, KK, HH, FF, DD, GG, AA, FF, CC, FF, HH, FF, KK, FF, DD, CC, HH, HH, EE, GG, CC, JJ, EE, FF, JJ, BB, GG, FF, GG, EE, KK, EE, GG, DD, DD, HH, KK, CC, FF, JJ, EE, BB, KK, HH, BB, GG, JJ, AA, KK, CC, JJ, HH, GG, KK, HH, FF, HH, JJ, HH, EE, GG, KK, AA, JJ, EE, BB, BB, EE, GG, CC, CC, EE, KK, HH, GG, CC, AA, GG, FF, HH, GG, HH, JJ, CC, KK, HH, KK, GG, GG, HH, EE, JJ, EE, CC, GG, AA, GG, JJ, KK, BB, JJ, GG, BB, FF, CC, GG, KK, JJ, JJ, EE, KK, GG, DD, CC, HH, EE, HH, JJ, HH, KK, DD, EE, HH, EE, DD, HH, FF, JJ, GG, HH, KK, JJ, GG, HH, DD, EE, JJ, FF, HH, BB, KK, EE, JJ, CC, KK, DD, BB, GG, GG, HH, GG, EE, HH, CC, JJ, BB, FF, GG, FF, JJ, HH, KK, GG, EE, DD, FF, JJ, DD, KK, EE, GG, HH, EE, FF, JJ, HH, FF, BB, KK, HH, JJ, KK, KK, JJ, HH, FF, EE, GG, FF, KK, CC, KK, FF, DD, JJ, KK, FF, HH, HH, JJ, AA, GG, BB, GG, EE, JJ, CC, KK, HH, JJ, DD, AA, EE,
        });
        gameReels.add(bgReels);
        gameReels.add(topReel);
        gameReels.add(fgReels);
        gameReels.add(fgTopReel);

        return gameReels;
    }

    public  WeightedPrizeData createReel1And2Sym() {
        return new WeightedPrizeData()
                .addWeightedConfig(new WeightedPrizeConfig(27, 2))
                .addWeightedConfig(new WeightedPrizeConfig(27, 3))
                .addWeightedConfig(new WeightedPrizeConfig(28, 4))
                .addWeightedConfig(new WeightedPrizeConfig(11, 5))
                .addWeightedConfig(new WeightedPrizeConfig(4, 6))
                .addWeightedConfig(new WeightedPrizeConfig(3, 7));
    }

    public  WeightedPrizeData createRee3And4Sym() {
        return new WeightedPrizeData()
                .addWeightedConfig(new WeightedPrizeConfig(26, 2))
                .addWeightedConfig(new WeightedPrizeConfig(24, 3))
                .addWeightedConfig(new WeightedPrizeConfig(19, 4))
                .addWeightedConfig(new WeightedPrizeConfig(15, 5))
                .addWeightedConfig(new WeightedPrizeConfig(10, 6))
                .addWeightedConfig(new WeightedPrizeConfig(6, 7));
    }

    public  WeightedPrizeData createRee5And6Sym() {
        return new WeightedPrizeData()
                .addWeightedConfig(new WeightedPrizeConfig(28, 2))
                .addWeightedConfig(new WeightedPrizeConfig(24, 3))
                .addWeightedConfig(new WeightedPrizeConfig(19, 4))
                .addWeightedConfig(new WeightedPrizeConfig(13, 5))
                .addWeightedConfig(new WeightedPrizeConfig(10, 6))
                .addWeightedConfig(new WeightedPrizeConfig(6, 7));
    }

    public  WeightedPrizeData createReel1Fg() {
        return new WeightedPrizeData()
                .addWeightedConfig(new WeightedPrizeConfig(35, 2))
                .addWeightedConfig(new WeightedPrizeConfig(27, 3))
                .addWeightedConfig(new WeightedPrizeConfig(15, 4))
                .addWeightedConfig(new WeightedPrizeConfig(13, 5))
                .addWeightedConfig(new WeightedPrizeConfig(7, 6))
                .addWeightedConfig(new WeightedPrizeConfig(3, 7));
    }
    public  WeightedPrizeData createReelSym2Fg() {
        return new WeightedPrizeData()
                .addWeightedConfig(new WeightedPrizeConfig(35, 2))
                .addWeightedConfig(new WeightedPrizeConfig(28, 3))
                .addWeightedConfig(new WeightedPrizeConfig(14, 4))
                .addWeightedConfig(new WeightedPrizeConfig(12, 5))
                .addWeightedConfig(new WeightedPrizeConfig(7, 6))
                .addWeightedConfig(new WeightedPrizeConfig(4, 7));
    }

    public  WeightedPrizeData createReelSym3Fg() {
        return new WeightedPrizeData()
                .addWeightedConfig(new WeightedPrizeConfig(35, 2))
                .addWeightedConfig(new WeightedPrizeConfig(29, 3))
                .addWeightedConfig(new WeightedPrizeConfig(15, 4))
                .addWeightedConfig(new WeightedPrizeConfig(10, 5))
                .addWeightedConfig(new WeightedPrizeConfig(8, 6))
                .addWeightedConfig(new WeightedPrizeConfig(3, 7));
    }

    public  WeightedPrizeData createReelSym4Fg() {
        return new WeightedPrizeData()
                .addWeightedConfig(new WeightedPrizeConfig(35, 2))
                .addWeightedConfig(new WeightedPrizeConfig(27, 3))
                .addWeightedConfig(new WeightedPrizeConfig(17, 4))
                .addWeightedConfig(new WeightedPrizeConfig(11, 5))
                .addWeightedConfig(new WeightedPrizeConfig(6, 6))
                .addWeightedConfig(new WeightedPrizeConfig(4, 7));
    }
    public  WeightedPrizeData createReelSym5Fg() {
        return new WeightedPrizeData()
                .addWeightedConfig(new WeightedPrizeConfig(31, 2))
                .addWeightedConfig(new WeightedPrizeConfig(25, 3))
                .addWeightedConfig(new WeightedPrizeConfig(20, 4))
                .addWeightedConfig(new WeightedPrizeConfig(13, 5))
                .addWeightedConfig(new WeightedPrizeConfig(7, 6))
                .addWeightedConfig(new WeightedPrizeConfig(4, 7));
    }
    public  WeightedPrizeData createReelSym6Fg() {
        return new WeightedPrizeData()
                .addWeightedConfig(new WeightedPrizeConfig(27, 2))
                .addWeightedConfig(new WeightedPrizeConfig(25, 3))
                .addWeightedConfig(new WeightedPrizeConfig(19, 4))
                .addWeightedConfig(new WeightedPrizeConfig(14, 5))
                .addWeightedConfig(new WeightedPrizeConfig(9, 6))
                .addWeightedConfig(new WeightedPrizeConfig(6, 7));
    }
}
