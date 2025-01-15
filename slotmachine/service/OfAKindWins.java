package slotmachine.service;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class OfAKindWins {
    Map<Integer, BigDecimal> winningMap = new HashMap<>();
    Map<Integer, Integer> occuranceMap = new HashMap<>();

    public Map<Integer, BigDecimal> getWinningMap() {
        return winningMap;
    }

    public void setWinningMap(Map<Integer, BigDecimal> winningMap) {
        this.winningMap = winningMap;
    }

    public Map<Integer, Integer> getOccuranceMap() {
        return occuranceMap;
    }

    public void setOccuranceMap(Map<Integer, Integer> occuranceMap) {
        this.occuranceMap = occuranceMap;
    }
}
