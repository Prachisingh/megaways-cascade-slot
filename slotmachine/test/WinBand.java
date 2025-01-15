package slotmachine.test;

import java.math.BigDecimal;

public class WinBand {
    private final BigDecimal min;
    private final BigDecimal max;
    private BigDecimal count;
    private BigDecimal total;

    public WinBand(double min, double max) {
        this.min = BigDecimal.valueOf(min);
        this.max = BigDecimal.valueOf(max);
        this.count = BigDecimal.ZERO;
        this.total = BigDecimal.ZERO;
    }

    public boolean update(BigDecimal value) {
        if (this.isValid(value)) {
            this.count = this.count.add(BigDecimal.ONE);
            this.total = this.total.add(value);
            return true;
        } else {
            return false;
        }
    }
    private boolean isValid(BigDecimal value) {
        if(isMinAndMaxSame()) return value.compareTo(this.min) == 0;

        return value.compareTo(this.min) >= 0 && this.max.compareTo(value) > 0;
    }

    public BigDecimal getCount() {
        return this.count;
    }

    public BigDecimal getTotal() {
        return this.total;
    }

    public void increaseTotal(BigDecimal value){
        this.total = this.total.add(value);
    }

    public void increaseCount(BigDecimal count){
        this.count = this.count.add(count);
    }

    public BigDecimal getMin() {
        return this.min;
    }

    public BigDecimal getMax() {
        return this.max;
    }

    public boolean isMinAndMaxSame(){
        return this.min.compareTo(this.max) == 0;
    }
}
