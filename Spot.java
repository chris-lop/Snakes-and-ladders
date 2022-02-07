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
 * The Spot class is the blueprint for a typical spot object in this program. This object is used to create the board of the game, which is a 2D-array of Spot objects. 
 * It holds important attributes for spots, such as the integer value of the spot on the board, and boolean variables that determine if a given player is or is not on a specific spot.
 * While this feature is not fully implemented since it is not necessary for the program, it is still included as it is needed for the creation of the 2D board.
*/
public class Spot {
    // Attributes
    int spot;
    boolean p1, p2, p3, p4;


    /** 
     * This mutator method changes the spot value for a given spot on a board.
     * @param InsertSpot This variable represents the new spot integer value that represents a specific location on the board.
    */
    public void setSpot(int InsertSpot) {
        spot = InsertSpot;
    }
}
