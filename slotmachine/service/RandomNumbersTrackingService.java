package slotmachine.service;


import java.util.List;

public interface RandomNumbersTrackingService {

  int getRandomNumber(int range);

  List<RandomNumber> getUserRandomNumbers();
}
