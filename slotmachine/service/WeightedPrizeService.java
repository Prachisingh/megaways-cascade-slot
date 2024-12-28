package slotmachine.service;


import java.util.Random;

public class WeightedPrizeService {

    public static int getPrizes(Random rng, WeightedPrizeData weightedPrizeData){

        int randomNumber = rng.nextInt(weightedPrizeData.getWeightsSum());
        for(var config : weightedPrizeData.getConfigs()){
            if(config.getWeight() > 0 && randomNumber >= config.getStartingRange() && randomNumber < config.getEndRange()) {
                return config.getPrize();
            }
        }

        throw new RuntimeException("shouldn't reach here, please fix your get prize config code");
    }

    public int getPrize(RandomNumbersTrackingService randomNumbersTrackerService, WeightedPrizeData weightedPrizeData){

        int randomNumber = randomNumbersTrackerService.getRandomNumber(weightedPrizeData.getWeightsSum());
        for(var config : weightedPrizeData.getConfigs()){
            if(config.getWeight() > 0 && randomNumber >= config.getStartingRange() && randomNumber < config.getEndRange()) {
                return config.getPrize();
            }
        }

        throw new RuntimeException("shouldn't reach here, please fix your get prize config code");
    }

    public int getPrizeAndDecreaseWeight(RandomNumbersTrackingService randomNumbersTrackerService, WeightedPrizeData weightedPrizeData){
        return getPrizeAndDecreaseWeight(randomNumbersTrackerService, weightedPrizeData, false);
    }

    public int getPrizeAndDecreaseWeight(RandomNumbersTrackingService randomNumbersTrackerService, WeightedPrizeData weightedPrizeData, boolean shouldRemoveAllWeights){

        int randomNumber = randomNumbersTrackerService.getRandomNumber(weightedPrizeData.getWeightsSum());
        for(var config : weightedPrizeData.getConfigs()){
            if(config.getWeight() > 0 && randomNumber >= config.getStartingRange() && randomNumber < config.getEndRange()) {
                if(shouldRemoveAllWeights){
                    config.setWeight(0);
                } else{
                    config.setWeight(config.getWeight() -1);
                }

                weightedPrizeData.reInitialiseConfigWeights();
                return config.getPrize();
            }
        }

        throw new RuntimeException("shouldn't reach here, please fix your get prize config code");
    }
}