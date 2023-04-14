package model;

/**
 * Friends class
 */
public class Friends {
    private int friend_ID;
    private String name;
    private String address;
    private String phone;
    private int food_ID;
    private int time_ID;

    /**
     * Constructor for Friends
     * @param friend_ID
     * @param name
     * @param address
     * @param phone
     * @param food_ID
     * @param time_ID
     */
    public Friends(int friend_ID, String name, String address, String phone, int food_ID, int time_ID) {
        this.friend_ID = friend_ID;
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.food_ID = food_ID;
        this.time_ID = time_ID;
    }

    /**
     * Getter for friend_ID
     * @return friend_ID
     */
    public int getFriend_ID() {
        return friend_ID;
    }

    /**
     * Setter for friend_ID
     * @param friend_ID
     */
    public void setFriend_ID(int friend_ID) {
        this.friend_ID = friend_ID;
    }

    /**
     * Getter for name
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * Setter for name
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Getter for address
     * @return address
     */
    public String getAddress() {
        return address;
    }

    /**
     * Setter for address
     * @param address
     */
    public void setAddress(String address) {
        this.address = address;
    }


    /**
     * Getter for phone
     * @return phone
     */
    public String getPhone() {
        return phone;
    }

    /**
     * Setter for phone
     * @param phone
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * Getter for food_ID
     * @return food_ID
     */
    public int getFood_ID() {
        return food_ID;
    }

    /**
     * Setter for food_ID
     * @param food_ID
     */
    public void setFood_ID(int food_ID) {
        this.food_ID = food_ID;
    }

    /**
     * Getter for time_ID
     * @return time_ID
     */
    public int getTime_ID() {
        return time_ID;
    }

    /**
     * Setter for time_ID
     * @param time_ID
     */
    public void setTime_ID(int time_ID) {
        this.time_ID = time_ID;
    }


    @Override
    public String toString() {
        return friend_ID
                + " - "
                + name;
    }
}

