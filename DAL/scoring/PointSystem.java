package DAL.scoring;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;

public class PointSystem implements scoringConstants{
    private long startTime;
    private long finishTime;

    public PointSystem(){
        startTime = System.currentTimeMillis();
    }

    public int calculatePoints(int totalFuelConsumption){
        int fuelPoints = totalFuelConsumption * pointDecreaseFuelConsumption;
        int timePoints = (int)TimeUnit.MILLISECONDS.toMinutes(calculateTimeElapsed()) * pointDecreasePerMinute;
        int points = startScore - fuelPoints - timePoints;
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





    public long calculateTimeElapsed(){
        return finishTime - startTime;
    }

    public void setFinishTime() {
        this.finishTime = System.currentTimeMillis();
    }

    public double getStartTime() {
        return startTime;
    }

    public double getFinishTime() {
        return finishTime;
    }

}
