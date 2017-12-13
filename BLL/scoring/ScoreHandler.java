package BLL.scoring;

import java.util.concurrent.TimeUnit;

/**
 * ScoreHandler controls the point system inside the game.
 * It calculates the total amount of points.
 * An optional option is to convert points to stars.
 */
public class ScoreHandler implements ScoringConstants {
    private long startTime;

    public ScoreHandler() {
        startTime = System.currentTimeMillis();
    }

    /**
     * Gets the start
     * @return
     */
    public long getStartTime() {
        return startTime;
    }

    public void setStartTimeOffset(long offset) {
        this.startTime = startTime - offset;
    }

    /**
     * Calculates the total amount of points the player received throughout the game.
     * @param totalFuelConsumption the amount of fuel the player has used
     * @return score the player obtained
     */
    public int calculatePoints(int totalFuelConsumption) {
        int fuelPoints = totalFuelConsumption * pointDecreaseFuelConsumption;
        int timePoints = (int)TimeUnit.MILLISECONDS.toMinutes(calculateTimeElapsed()) * pointDecreasePerMinute;

        return startScore - fuelPoints - timePoints;
    }

    /**
     * By giving the amount of point, it will return a number of stars.
     * @param points total points of the player
     * @return how many stars based on points
     */
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

    /**
     * Calculates how much time has elapsed since the beginning of the game.
     * The time is in milliseconds.
     * @return a difference between start and real-time.
     */
    public long calculateTimeElapsed() {
        return System.currentTimeMillis() - startTime;
    }
}
