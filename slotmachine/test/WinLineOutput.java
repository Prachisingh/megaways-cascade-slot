package slotmachine.test;



import java.math.BigDecimal;


public class WinLineOutput {
    private BigDecimal rtp;

    public BigDecimal getRtp() {
        return rtp;
    }

    public void setRtp(BigDecimal rtp) {
        this.rtp = rtp;
    }

    public String getOutputString() {
        return outputString;
    }

    public void setOutputString(String outputString) {
        this.outputString = outputString;
    }

    private String outputString;

    public WinLineOutput(BigDecimal rtp, String outputString) {
        this.rtp = rtp;
        this.outputString = outputString;
    }
}
