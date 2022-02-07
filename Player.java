// -----------------------------------------------------
// Assignment 0
// Question: Part I
// Written by: Christopher Lopez, ID: 40199547
// -----------------------------------------------------

/** 
 * Name(s) and ID(s) Christopher Lopez, 40199547
 * COMP 249
 * Assignment # 0
 * Due Date February 7th, 2022
 * 
 * The Player class is the blueprint for a typical game player. It holds important player attributes such as the player's spot value on the board, a player's roll value, and a player's name.
*/
public class Player {
    int spot = 0, initialRoll;
    String name;
    boolean first = false, second = false, third = false, fourth = false;

    /** 
     * Default constructor for class Player
    */
    public Player() {
    }

    /** 
     * This mutator method changes the name value for a given player.
     * @param InsertName This variable represents the new name that will be given to a player.
    */
    public void setName(String InsertName) {
        name = InsertName;
    }

    /** 
     * This mutator method changes the roll value for a given player.
     * @param InsertRoll This variable represents the new roll value that will be associated to a given player.
    */
    public void setRoll(int InsertRoll) {
        initialRoll = InsertRoll;
    }

    /** 
     * This mutator method changes the spot value of a given player. This spot value represents a player's location on the board.
     * @param InsertSpot This variable represents the new spot value that will be associated to a given player.
    */
    public void setSpot(int InsertSpot) {
        spot = InsertSpot;
    }

    /** 
     * This method returns the name associated to a given player.
     * @return Returns the name variable that holds a String representing the name of the player.
    */
    public String getName() {
        return this.name;
    }

    /** 
     * This method returns the spot associated to a given player.
     * @return Returns the spot variable that holds an Integer representing the location of the player on the board.
    */
    public int getSpot() {
        return this.spot;
    }

    /** 
     * This method returns the roll value associated to a given player.
     * @return Returns the InitialRoll variable that holds an Integer representing the roll amount of the given player.
    */
    public int getRoll() {
        return this.initialRoll;
    }

    /** 
     * This method moves the player according to a given integer value. It adds this integer value to the player's current location, so that the result becomes the player's new location.
     * @param InsertSpot This variable represents the integer amount that will be added to the player's current location. It will be associated to the player's roll value.
    */
    public void move(int InsertSpot) {
            spot += InsertSpot;
    }
}
