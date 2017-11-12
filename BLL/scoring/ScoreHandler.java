package BLL.scoring;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class ScoreHandler implements ScoringConstants {
    private long startTime;
    private long finishTime;

    public ScoreHandler(){
        startTime = System.currentTimeMillis();
    }

    public int calculatePoints(int totalFuelConsumption) {
        int fuelPoints = totalFuelConsumption * pointDecreaseFuelConsumption;
        int timePoints = (int)TimeUnit.MILLISECONDS.toMinutes(calculateTimeElapsed()) * pointDecreasePerMinute;

        return startScore - fuelPoints - timePoints;
    }

    public int getStars(int points) {
        int stars;

        if(points >= 8000){
            stars = 5;
        } else if (points >= 6000){
            stars = 4;
        } else if (points >= 4000){
            stars = 3;
        } else if (points >= 2000){
            stars = 2;
        } else if (points >= 0){
            stars = 1;
        } else{
            stars = 0;
        }

        return stars;
    }

    public long calculateTimeElapsed() {
        return System.currentTimeMillis() - startTime;
    }

    public double getStartTime() {
        return startTime;
    }

    public double getFinishTime() {
        return finishTime;
    }
}
