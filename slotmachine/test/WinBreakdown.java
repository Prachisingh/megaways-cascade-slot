package slotmachine.test;


import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class WinBreakdown {
    protected final Integer id;
    protected final Map<Integer, BigDecimal> occurrences;
    protected final Map<Integer, BigDecimal> total;

    public WinBreakdown(Integer id) {
        this.id = id;
        this.occurrences = new HashMap<>();
        this.total = new HashMap<>();
    }

//    public void merge(WinBreakdown other){
//        for (var entry : other.getOccurrences().entrySet()) {
//            if (this.occurrences.containsKey(entry.getKey())) {
//                this.occurrences.put(entry.getKey(), this.occurrences.get(entry.getKey()).add(entry.getValue()));
//            } else {
//                this.occurrences.put(entry.getKey(), entry.getValue());
//            }
//        }
//
//        for (var entry : other.getTotal().entrySet()) {
//            if (this.total.containsKey(entry.getKey())) {
//                this.total.put(entry.getKey(), this.total.get(entry.getKey()).add(entry.getValue()));
//            } else {
//                this.total.put(entry.getKey(), entry.getValue());
//            }
//        }
//    }

    public void update(int numberOfSymbols, BigDecimal amount) {
        BigDecimal previousOccurrences = this.occurrences.getOrDefault(numberOfSymbols, BigDecimal.ZERO);
        BigDecimal previousTotal = this.total.getOrDefault(numberOfSymbols, BigDecimal.ZERO);
        this.occurrences.put(numberOfSymbols, previousOccurrences.add(BigDecimal.ONE));
        this.total.put(numberOfSymbols, previousTotal.add(amount));
    }

    public WinLineOutput output(BigDecimal totalSpins, BigDecimal cashBet) {
        StringBuilder message = new StringBuilder();
        Collection<Integer> keys = this.occurrences.keySet();
        Iterator var4 = keys.iterator();

        BigDecimal paylineRtp = BigDecimal.ZERO;
        while(var4.hasNext()) {
            Integer key = (Integer)var4.next();
            BigDecimal occurrencesCount = this.occurrences.get(key);
            BigDecimal modifierTotal = this.total.get(key);
            BigDecimal rtp = modifierTotal.divide(totalSpins, new MathContext(4, RoundingMode.HALF_EVEN))
                    .divide(cashBet, new MathContext(4, RoundingMode.HALF_EVEN));
            paylineRtp = paylineRtp.add(rtp);
            message.append("\n").append(key).append(" OAK occurrences count ").append(occurrencesCount).append(" : total winning of ").append(modifierTotal).append(" : appearing ").append(percentage(occurrencesCount, totalSpins)).append("% : EV (average win): ").append(divide(modifierTotal, occurrencesCount)).append(" :RTP ").append(rtp.toPlainString());
        }
        message.append("\n").append("total rtp ").append(paylineRtp.toPlainString());

        return new WinLineOutput(paylineRtp, message.toString());
    }


    private BigDecimal divide(BigDecimal a, BigDecimal b, int scale) {
        BigDecimal value = BigDecimal.ZERO;
        if (b.compareTo(BigDecimal.ZERO) > 0) {
            value = a.divide(b, scale, RoundingMode.HALF_EVEN);
        }

        return value;
    }

    private BigDecimal divide(BigDecimal a, BigDecimal b) {
        return divide(a, b, 10);
    }

    private BigDecimal percentage(BigDecimal a, BigDecimal b) {
        return divide(a, b).movePointRight(2);
    }

}
