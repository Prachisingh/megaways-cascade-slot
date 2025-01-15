package slotmachine.service;

import java.util.Map;

public class WinBreakup {
    Map <String, OfAKindWins> winningMap ;

    public Map<String, OfAKindWins> getWinningMap() {
        return winningMap;
    }

    public void setWinningMap(Map<String, OfAKindWins> winningMap) {
        this.winningMap = winningMap;
    }
}
