package slotmachine.service;

import java.math.BigDecimal;

public class Spin {

   public BigDecimal totalWin;
   public boolean isFsTriggered;
   int fsAwarded;

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

    public int getFsAwarded() {
        return fsAwarded;
    }

    public void setFsAwarded(int fsAwarded) {
        this.fsAwarded = fsAwarded;
    }
}

