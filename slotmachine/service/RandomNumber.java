package slotmachine.service;

public class RandomNumber {

  private int bits;
  private int range;
  private int value;

  public int getBits() {
    return bits;
  }

  public void setBits(int bits) {
    this.bits = bits;
  }

  public int getRange() {
    return range;
  }

  public void setRange(int range) {
    this.range = range;
  }

  public int getValue() {
    return value;
  }

  public void setValue(int value) {
    this.value = value;
  }
}
