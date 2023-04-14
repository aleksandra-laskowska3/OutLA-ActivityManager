package model;

/**
 * Food class
 */
public class Food {
    private int food_ID;
    private String type;

    /**
     * Constructor for Food
     * @param food_ID
     * @param type
     */
    public Food(int food_ID, String type){
        this.food_ID = food_ID;
        this.type = type;
    }

    /**
     * Getter for food_ID
     * @return food_ID
     */
    public int getFood_ID(){
        return food_ID;
    }

    /**
     * Setter for food_ID
     * @param food_ID
     */
    public void setFood_ID(int food_ID){
        this.food_ID = food_ID;
    }

    /**
     * Getter for type
     * @return type
     */
    public String getType(){
        return type;
    }

    /**
     * Setter for type
     * @param type
     */
    public void setType(String type){
        this.type = type;
    }


    @Override
    public String toString() {
        return  food_ID +
                " - " + type;
    }
}
