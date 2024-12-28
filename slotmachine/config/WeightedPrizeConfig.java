package slotmachine.config;

public class WeightedPrizeConfig {
    int weight;
    int prize; //generic name here, could be reel index, symbolId, etc.
    int startingRange;
    int endRange;

    public WeightedPrizeConfig(int weight, int prize) {
        this.weight = weight;
        this.prize = prize;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public int getPrize() {
        return prize;
    }

    public void setPrize(int prize) {
        this.prize = prize;
    }

    public int getStartingRange() {
        return startingRange;
    }

    public void setStartingRange(int startingRange) {
        this.startingRange = startingRange;
    }

    public int getEndRange() {
        return endRange;
    }

    public void setEndRange(int endRange) {
        this.endRange = endRange;
    }
}
