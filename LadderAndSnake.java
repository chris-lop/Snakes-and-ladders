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
    public void play() {
        // Creation of the minimum 2 players
        Player Player1 = new Player();
        Player Player2 = new Player();
        
        // Placing players on a player list
        Player[ ] playerList = new Player[this.players];
        playerList[0] = Player1;
        playerList[1] = Player2;

        // Creation of 1 or 2 more players depending on user input
        if (this.players == 3) {
            Player Player3 = new Player();
            playerList[2] = Player3;
        } else if (this.players == 4) {
            Player Player3 = new Player();
            Player Player4 = new Player();
            playerList[2] = Player3;
            playerList[3] = Player4;
        }

        // Displaying amount of players in game
        System.out.println("Game is played by "+this.players+" players");

        // Algorithm deciding which player will go first
        // TODO: Use getters and setters instead of this operator
        System.out.println("Now deciding which player will start playing;");

        for (int i = 0; i<=this.players-1; i++) {
            playerList[i].order = this.flipDice();
        }

        // Placing roll values on temporary integer array
        // TODO: Use getters and setters instead of this operator
        int[] rollList = new int[this.players];
        for (int i = 0; i<=this.players-1; i++) {
            rollList[i] = playerList[i].order;
        }
        
        // Sorting Algorithm
        boolean sorted = false;
        int temp;

        while(!sorted) {
            sorted = true;
            for (int i = 0; i < rollList.length - 1; i++) {
                if (rollList[i] > rollList[i+1]) {
                    temp = rollList[i];
                    rollList[i] = rollList[i+1];
                    rollList[i+1] = temp;
                    sorted = false;
                }
            }
        }

        // Algorithm Checking for equalities
        
    }
}