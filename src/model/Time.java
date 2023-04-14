package model;

/**
 * Time class
 */
public class Time {
    private int time_ID;
    private String timeOfDay;

    /**
     * Constructor for Time
     * @param time_ID
     * @param timeOfDay
     */
    public Time(int time_ID, String timeOfDay){
        this.time_ID = time_ID;
        this.timeOfDay = timeOfDay;

    }

    /**
     * Getter for time_ID
     * @return time_ID
     */
    public int getTime_ID(){
        return time_ID;
    }

    /**
     * Setter for time_ID
     * @param time_ID
     */
    public void setTime_ID(int time_ID){
        this.time_ID = time_ID;
    }

    /**
     * Getter for timeOfDay
     * @return timeOfDay
     */
    public String getTimeOfDay(){
        return timeOfDay;
    }

    /**
     * Setter for timeOfDay
     * @param timeOfDay
     */
    public void setTimeOfDay(String timeOfDay){
        this.timeOfDay = timeOfDay;
    }



    @Override
    public String toString() {
        return time_ID
        + " - "
        + timeOfDay;
    }
}
