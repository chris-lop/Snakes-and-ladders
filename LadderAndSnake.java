import java.util.concurrent.ThreadLocalRandom;

public class LadderAndSnake {
    // -----------------------------------------------------
    // Assignment 0
    // Question: Part I
    // Written by: Christopher Lopez, ID: 40199547
    // -----------------------------------------------------


    // Class attributes
    int[][] board = new int[10][10];
    int players;

    // Default Constructor
    public LadderAndSnake() {
        
    }

    // Parameterized constructor
    public LadderAndSnake(int InsertPlayers) {
        
        // Limits player input to 2-4 players
        if (InsertPlayers > 4 || InsertPlayers < 2) {
            throw new IllegalArgumentException("InsertPlayers must be in range 2-4 but found "+ InsertPlayers);
       }
        players = InsertPlayers;
    }

    // TODO:  Basic Methods (Getters, Setters, etc.)

    // Method that flips dice and returns values from 1 to 6 inclusively
    public int flipDice() {
       return ThreadLocalRandom.current().nextInt(1, 7);
    }

    // TODO: Play Method (Main Game Engine)

}