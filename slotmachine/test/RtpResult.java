package slotmachine.test;

import java.math.BigDecimal;

public class RtpResult {
    private BigDecimal totalWins = BigDecimal.ZERO;
    private BigDecimal totalFreeSpinsWins = BigDecimal.ZERO;
    private BigDecimal totalBaseGameWins = BigDecimal.ZERO;
    private BigDecimal highestWinMultiplier = BigDecimal.ZERO;

    private BigDecimal highestWin = BigDecimal.ZERO;
    private int numOfTimesFsTriggered;
    private int totalRuns;

    public void merge(RtpResult newRtpResult) {
        this.totalWins = this.totalWins.add(newRtpResult.getTotalWins());
        this.totalFreeSpinsWins = this.totalFreeSpinsWins.add(newRtpResult.getTotalFreeSpinsWins());
        this.totalBaseGameWins = this.totalBaseGameWins.add(newRtpResult.getTotalBaseGameWins());
        this.highestWinMultiplier = this.highestWinMultiplier.add(newRtpResult.getHighestWinMultiplier());
        this.highestWin = this.highestWin.add(newRtpResult.getHighestWin());

        this.numOfTimesFsTriggered += newRtpResult.getNumOfTimesFsTriggered();
        this.totalRuns += newRtpResult.getTotalRuns();

    }
    public BigDecimal getTotalWins() {
        return totalWins;
    }

    public void setTotalWins(BigDecimal totalWins) {
        this.totalWins = totalWins;
    }

    public BigDecimal getTotalFreeSpinsWins() {
        return totalFreeSpinsWins;
    }

    public void setTotalFreeSpinsWins(BigDecimal totalFreeSpinsWins) {
        this.totalFreeSpinsWins = totalFreeSpinsWins;
    }

    public BigDecimal getTotalBaseGameWins() {
        return totalBaseGameWins;
    }

    public void setTotalBaseGameWins(BigDecimal totalBaseGameWins) {
        this.totalBaseGameWins = totalBaseGameWins;
    }

    public BigDecimal getHighestWinMultiplier() {
        return highestWinMultiplier;
    }

    public void setHighestWinMultiplier(BigDecimal highestWinMultiplier) {
        this.highestWinMultiplier = highestWinMultiplier;
    }

    public BigDecimal getHighestWin() {
        return highestWin;
    }

    public void setHighestWin(BigDecimal highestWin) {
        this.highestWin = highestWin;
    }

    public int getNumOfTimesFsTriggered() {
        return numOfTimesFsTriggered;
    }

    public void setNumOfTimesFsTriggered(int numOfTimesFsTriggered) {
        this.numOfTimesFsTriggered = numOfTimesFsTriggered;
    }

    public int getTotalRuns() {
        return totalRuns;
    }

    public void setTotalRuns(int totalRuns) {
        this.totalRuns = totalRuns;
    }
}
