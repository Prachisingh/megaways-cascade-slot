package slotmachine.service;

import java.math.BigDecimal;

public class Spin {

   public BigDecimal totalWin;
   public boolean isFsTriggered;

    public BigDecimal getTotalWin() {
        return totalWin;
    }

    public void setTotalWin(BigDecimal totalWin) {
        this.totalWin = totalWin;
    }

    public boolean isFsTriggered() {
        return isFsTriggered;
    }

    public void setFsTriggered(boolean fsTriggered) {
        isFsTriggered = fsTriggered;
    }
}

