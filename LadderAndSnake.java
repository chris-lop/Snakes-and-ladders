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
            throw new IllegalArgumentException("InsertPlayers must be in range 2-4 but found " + InsertPlayers);
        }
        players = InsertPlayers;
    }

    // TODO: Basic Methods (Getters, Setters, etc.)

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
        Player[] playerList = new Player[this.players];
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
        System.out.println("Game is played by " + this.players + " players");

        // Algorithm deciding which player will go first
        // TODO: Use getters and setters instead of this operator
        System.out.println("Now deciding which player will start playing;");

        for (int i = 0; i <= this.players - 1; i++) {
            playerList[i].initialRoll = this.flipDice();
        }

        // Sorting Algorithm
        boolean sorted = false;
        int temp;

        while (!sorted) {
            sorted = true;
            for (int i = 0; i < playerList.length - 1; i++) {
                if (playerList[i].initialRoll < playerList[i + 1].initialRoll) {
                    temp = playerList[i].initialRoll;
                    playerList[i].initialRoll = playerList[i + 1].initialRoll;
                    playerList[i + 1].initialRoll = temp;
                    sorted = false;
                }
            }
        }

        // Sorting test REMOVE LATER
        for (int i = 0; i <= playerList.length - 1; i++) {
            System.out.println(playerList[i].initialRoll);
        }

        // Equality checker
        boolean equality;
        boolean sorted2 = false;
        int equalityStart = 1, equalityEnd = playerList.length - 1;

        do {
            for (int i = equalityStart; i <= equalityEnd; i++) {
                if (playerList[i].initialRoll == playerList[i - 1].initialRoll) {
                    playerList[i].initialRoll = -1;
                    playerList[i - 1].initialRoll = -1;
                    equalityEnd = i;
                    equality = true;

                    if (playerList[i].initialRoll != playerList[i - 1].initialRoll && playerList[i].initialRoll == -1) {
                        equalityStart = i;
                    }
                }
            }

            // REMOVE LATER
            System.out.println("for loop 1");

            if (playerList[equalityStart].initialRoll != playerList[equalityEnd].initialRoll) {
                equality = false;
            }

            if (equality = true) {
                for (int i = equalityStart; i <= equalityEnd; i++) {
                    playerList[i].initialRoll = this.flipDice();
                }

                // REMOVE LATER
                System.out.println("for loop 2");
            }

            // TODO FIX SORTING ALGO
            while (!sorted2) {
                sorted = true;
                for (int i = equalityStart; i <= equalityEnd; i++) {
                    if (playerList[i].initialRoll < playerList[i + 1].initialRoll) {
                        temp = playerList[i].initialRoll;
                        playerList[i].initialRoll = playerList[i + 1].initialRoll;
                        playerList[i + 1].initialRoll = temp;
                        sorted2 = false;
                    }
                }
            }

            // REMOVE LATER
            System.out.println("Sorting1");

        } while (equality = true);

        // Sorting test - REMOVE LATER
        for (int i = 0; i <= playerList.length - 1; i++) {
            System.out.println(playerList[i].initialRoll);
        }

    }
}