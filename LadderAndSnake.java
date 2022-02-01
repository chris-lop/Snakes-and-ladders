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
        Player temporaryPlayer = new Player();
        for(int i=0; i<playerList.length; i++) {
            for(int j = i+1; j<playerList.length; j++) {
                if(playerList[i].initialRoll>playerList[j].initialRoll) {
                    temporaryPlayer = playerList[i];
                    playerList[i]= playerList[j];
                    playerList[j]= temporaryPlayer;
                }
            }
        }

        // Sorting test REMOVE LATER
        for (int i = 0; i <= playerList.length - 1; i++) {
            System.out.println(playerList[i].initialRoll);
        }

        boolean equality = false;
        int equalityStart = -2, equalityEnd = 1;
        // First Equality check to set equality start and equality end values
            for (int j=0;j<playerList.length;j++) {
                for (int k=j+1;k<playerList.length;k++) {
                    if (k!=j && playerList[k].initialRoll == playerList[j].initialRoll) {
                        if (equalityStart == -2) {
                            equalityStart = j;
                        }
                        equality=true;
                        equalityEnd = k;
                    }
                }
            }

        while (equality == true) {

            // Equality Checker, if it finds one, replaces equal values by -1
            for (int j=equalityStart;j<=equalityEnd;j++) {
                for (int k=j+1;k<=equalityEnd;k++) {
                    if (k!=j && playerList[k].initialRoll == playerList[j].initialRoll) {
                        playerList[j].initialRoll = -1;
                        playerList[k].initialRoll = -1;
                    }
                }
            }

            // Checks for -1 values, and sets equality to false if there isnt any -1
            equality = false;
            for (int f = 0; f <= playerList.length - 1; f++) {
                if (playerList[f].initialRoll == -1) {
                    equality = true;
                }
            }
            // REMOVE LATER
            System.out.println("equality is "+equality);

            // REMOVE LATER
            System.out.println("for loop 1");
            
            // Flip dice for -1 values
            if (equality == true) {
                for (int i = 0; i < playerList.length; i++) {
                    if (playerList[i].initialRoll == -1)
                    playerList[i].initialRoll = this.flipDice();
                }

                // REMOVE LATER
                System.out.println("for loop 2");
            }

            //Sorting Algorithm from equality start to equality end
            Player temporaryPlayer2 = new Player();
            for(int i=equalityStart; i<=equalityEnd; i++) {
                for(int j = i+1; j<=equalityEnd; j++) {
                    if(playerList[i].initialRoll>playerList[j].initialRoll) {
                        temporaryPlayer2 = playerList[i];
                        playerList[i]= playerList[j];
                        playerList[j]= temporaryPlayer2;
                    }
                }
            }
            
            // REMOVE LATER
            System.out.println("Sorting1");

            // Sorting test - REMOVE LATER
            for (int i = 0; i <= playerList.length - 1; i++) {
            System.out.println(playerList[i].initialRoll);
            }
        }
    }
}