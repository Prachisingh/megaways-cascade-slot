package slotmachine.service;

import java.util.ArrayList;
import java.util.List;

public class WeightedPrizeData {
    int weightsSum = 0;

    // config with weight of 0 will be omitted automatically
    List<WeightedPrizeConfig> configs = new ArrayList<>();

    public WeightedPrizeData addWeightedConfig(WeightedPrizeConfig config) {
        this.configs.add(config);
        config.setStartingRange(weightsSum);
        this.weightsSum += config.getWeight();
        config.setEndRange(weightsSum);
        return this;
    }

    public  WeightedPrizeData reInitialiseConfigWeights(List<Integer> newWeights){
        if(newWeights.size() != this.configs.size()) throw new RuntimeException("please pass in the same elements of newWeights");

        weightsSum = 0;
        for(int i = 0; i < this.configs.size(); i++){
            var config = this.configs.get(i);
            int newWeight = newWeights.get(i);
            config.setWeight(newWeight);
            config.setStartingRange(weightsSum);
            this.weightsSum += config.getWeight();
            config.setEndRange(weightsSum);
        }

        return this;
    }

    public  WeightedPrizeData reInitialiseConfigWeights(){
        weightsSum = 0;
        for (WeightedPrizeConfig config : this.configs) {
            config.setStartingRange(weightsSum);
            this.weightsSum += config.getWeight();
            config.setEndRange(weightsSum);
        }

        return this;
    }

    public int getWeightsSum() {
        return weightsSum;
    }

    public void setWeightsSum(int weightsSum) {
        this.weightsSum = weightsSum;
    }

    public List<WeightedPrizeConfig> getConfigs() {
        return configs;
    }

    public void setConfigs(List<WeightedPrizeConfig> configs) {
        this.configs = configs;
    }
}
