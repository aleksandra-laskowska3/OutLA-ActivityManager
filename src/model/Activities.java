package model;
import java.time.LocalDateTime;

/**
 * Activities class
 */
public class Activities {
    private int activity_ID;
    private String title;
    private String description;
    private String location;
    private String price;
    private String pet;
    private LocalDateTime start;
    private LocalDateTime end;
    private int friend_ID;


    /**
     * Constructor for Activities
     * @param activity_ID
     * @param title
     * @param description
     * @param location
     * @param price
     * @param pet
     * @param start
     * @param end
     * @param friend_ID
     */
    public Activities(int activity_ID, String title, String description, String location, String price, String pet, LocalDateTime start,
                      LocalDateTime end, int friend_ID){
        this.activity_ID = activity_ID;
        this.title = title;
        this.description = description;
        this.location = location;
        this.price = price;
        this.pet = pet;
        this.start = start;
        this.end = end;
        this.friend_ID = friend_ID;


    }

    /**
     * Getter for activity_ID
     * @return activity_ID
     */
    public int getActivity_ID() {
        return activity_ID;
    }

    /**
     * Setter for activity ID
     * @param activity_ID
     */

    public void setActivity_ID(int activity_ID) {
        this.activity_ID = activity_ID;
    }

    /**
     * Getter for title
     * @return title
     */
    public String getTitle(){
        return title;
    }

    /**
     * Setter for title
     * @param title
     */
    public void setTitle(String title){
        this.title = title;
    }

    /**
     * Getter for description
     * @return description
     */
    public String getDescription(){
        return description;
    }

    /**
     * Setter for description
     * @param description
     */
    public void setDescription(String description){
        this.description = description;
    }

    /**
     * Getter for location
     * @return location
     */
    public String getLocation(){
        return location;
    }
    /**
     * Setter for location
     * @param location
     */
    public void setLocation(String location){
        this.location = location;
    }
    /**
     * Getter for price
     * @return price
     */
    public String getPrice() {return price;}
    /**
     * Setter for price
     * @param price
     */
    public void setPrice(String price) {this.price = price;}

    /**
     * Getter for pet
     * @return pet
     */
    public String getPet() {return pet;}
    /**
     * Setter for pet
     * @param pet
     */
    public void setPet(String pet) {this.pet = pet;}

    /**
     * Getter for start
     * @return start
     */
    public LocalDateTime getStart(){
        return start;
    }

    /**
     * Setter for start
     * @param start
     */
    public void setStart(LocalDateTime start){
        this.start = start;
    }

    /**
     * Getter for end
     * @return end
     */
    public LocalDateTime getEnd(){
        return end;
    }

    /**
     * Setter for end
     * @param end
     */
    public void setEnd(LocalDateTime end){
        this.end = end;
    }

    /**
     * Getter for friend_ID
     * @return friend_ID
     */
    public int getFriend_ID(){
        return friend_ID;
    }

    /**
     * Setter for friend_ID
     * @param friend_ID
     */
    public void setFriend_ID(int friend_ID){
        this.friend_ID = friend_ID;
    }




}
