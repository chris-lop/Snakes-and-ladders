import java.util.concurrent.ThreadLocalRandom;

public class LadderAndSnake {
    // -----------------------------------------------------
    // Assignment 0
    // Question: Part I
    // Written by: Christopher Lopez, ID: 40199547
    // -----------------------------------------------------

    // Class attributes
    Spot[][] board = new Spot[10][10];
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

    // Play Method (Main Game Engine)
    public void play() {
        // Creation of the minimum 2 players
        Player Player1 = new Player();
        Player Player2 = new Player();
        Player1.setName("Player 1");
        Player2.setName("Player 2");

        // Placing players on a player list
        Player[] playerList = new Player[this.players];
        playerList[0] = Player1;
        playerList[1] = Player2;

        // Creation of 1 or 2 more players depending on user input
        if (this.players == 3) {
            Player Player3 = new Player();
            Player3.setName("Player 3");
            playerList[2] = Player3;
        } else if (this.players == 4) {
            Player Player3 = new Player();
            Player Player4 = new Player();
            Player3.setName("Player 3");
            Player4.setName("Player 4");
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
            System.out.println(playerList[i].getName() + " got a dice value of " + playerList[i].initialRoll);
        }

        // Sorting Algorithm
        Player temporaryPlayer = new Player();
        for (int i = 0; i < playerList.length; i++) {
            for (int j = i + 1; j < playerList.length; j++) {
                if (playerList[i].initialRoll > playerList[j].initialRoll) {
                    temporaryPlayer = playerList[i];
                    playerList[i] = playerList[j];
                    playerList[j] = temporaryPlayer;
                }
            }
        }

        boolean equality = false;
        int equalityStart = -2, equalityEnd = 1;
        // First Equality check to set equality start and equality end values
        for (int j = 0; j < playerList.length; j++) {
            for (int k = j + 1; k < playerList.length; k++) {
                if (k != j && playerList[k].initialRoll == playerList[j].initialRoll) {
                    if (equalityStart == -2) {
                        equalityStart = j;
                    }
                    equality = true;
                    equalityEnd = k;
                    System.out.println("A tie was achieved between " + playerList[j].getName() + " and " + playerList[k].getName()+ ". Attempting to break the tie.");
                }
            }
        }

        while (equality == true) {

            // Equality Checker, if it finds one, replaces equal values by -1
            for (int j = equalityStart; j <= equalityEnd; j++) {
                for (int k = j + 1; k <= equalityEnd; k++) {
                    if (k != j && playerList[k].initialRoll == playerList[j].initialRoll) {
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

            // Flip dice for -1 values
            if (equality == true) {
                for (int i = 0; i < playerList.length; i++) {
                    if (playerList[i].initialRoll == -1) {
                        playerList[i].initialRoll = this.flipDice();
                        System.out.println(playerList[i].getName() + " got a dice value of " + playerList[i].initialRoll);
                    }
                }
            }

            // Sorting Algorithm from equality start to equality end
            Player temporaryPlayer2 = new Player();
            for (int i = equalityStart; i <= equalityEnd; i++) {
                for (int j = i + 1; j <= equalityEnd; j++) {
                    if (playerList[i].initialRoll > playerList[j].initialRoll) {
                        temporaryPlayer2 = playerList[i];
                        playerList[i] = playerList[j];
                        playerList[j] = temporaryPlayer2;
                    }
                }
            }
        }

        // Printing final decision
        System.out.print("Reached final decision on order of playing: ");
        for (int j = playerList.length-1; j>=0; j--) {
            System.out.print(playerList[j].getName());
            if (j>0)
                System.out.print(", ");
        }

        // Game Start
        System.out.println();
        System.out.println("Now Starting the Game...");

        /*
        // Board Setup (spot #)
        // Verify Board Bounds (10 or 9)
        int SpotAmount = 100;

        for (int i = 0; i <= 10; i++) {
            if (i%2 == 1) {
                for (int j = 10; j>=0; j--) {
                    board[i][j].setSpot(SpotAmount);
                    SpotAmount--;
                }
            } else if (i%2 == 0) {
                for (int j = 0; j<=10; j++) {
                    board[i][j].setSpot(SpotAmount);
                    SpotAmount--;
                }
            }
        }
        */

        // While loop that keeps the game going until someone reaches victory (tile 100 = victory)
        boolean victory = false;

        while (victory == false) {
            for (int j = playerList.length-1; j>=0; j--) {
                
                // Dice flips for the player
                playerList[j].setRoll(this.flipDice());
                System.out.println(playerList[j].getName() + " got a dice value of " + playerList[j].getRoll());

                // Increment spot - if it goes past 100, go backwards
                if (playerList[j].getSpot()+playerList[j].getRoll()<=100){
                    playerList[j].move(playerList[j].getRoll());
                    System.out.print(playerList[j].getName() + " moved to square " + playerList[j].getSpot());
                } else {
                    playerList[j].move(playerList[j].getRoll());
                    playerList[j].move((100-playerList[j].getSpot())*2);
                    System.out.print(playerList[j].getName() + " moved to square " + playerList[j].getSpot());
                }
                

                // Check player coordinate in array and effect (ladder or snake or victory)
                    // If Statement - Ladders
                    if (playerList[j].getSpot()==1) {
                        playerList[j].setSpot(38);
                        System.out.print(" then up to square "+playerList[j].getSpot());
                    }
                    if (playerList[j].getSpot()==4) {
                        playerList[j].setSpot(14);
                        System.out.print(" then up to square "+playerList[j].getSpot());
                    }
                    if (playerList[j].getSpot()==9) {
                        playerList[j].setSpot(31);
                        System.out.print(" then up to square "+playerList[j].getSpot());
                    }
                    if (playerList[j].getSpot()==36) {
                        playerList[j].setSpot(44);
                        System.out.print(" then up to square "+playerList[j].getSpot());
                    }
                    if (playerList[j].getSpot()==21) {
                        playerList[j].setSpot(42);
                        System.out.print(" then up to square "+playerList[j].getSpot());
                    }
                    if (playerList[j].getSpot()==51) {
                        playerList[j].setSpot(67);
                        System.out.print(" then up to square "+playerList[j].getSpot());
                    }
                    if (playerList[j].getSpot()==71) {
                        playerList[j].setSpot(91);
                        System.out.print(" then up to square "+playerList[j].getSpot());
                    }
                    if (playerList[j].getSpot()==80) {
                        playerList[j].setSpot(100);
                        System.out.print(" then up to square "+playerList[j].getSpot());
                    }
                    if (playerList[j].getSpot()==28) {
                        playerList[j].setSpot(84);
                        System.out.print(" then up to square "+playerList[j].getSpot());
                    }

                     // If Statement - Snakes
                     if (playerList[j].getSpot()==98) {
                         playerList[j].setSpot(78);
                         System.out.print(" then down to square "+playerList[j].getSpot());
                     }
                     if (playerList[j].getSpot()==97) {
                         playerList[j].setSpot(76);
                         System.out.print(" then down to square "+playerList[j].getSpot());
                     }
                     if (playerList[j].getSpot()==95) {
                         playerList[j].setSpot(24);
                         System.out.print(" then down to square "+playerList[j].getSpot());
                     }
                     if (playerList[j].getSpot()==93) {
                         playerList[j].setSpot(68);
                         System.out.print(" then down to square "+playerList[j].getSpot());
                     }
                     if (playerList[j].getSpot()==79) {
                         playerList[j].setSpot(19);
                         System.out.print(" then down to square "+playerList[j].getSpot());
                     }
                     if (playerList[j].getSpot()==64) {
                         playerList[j].setSpot(60);
                        System.out.print(" then down to square "+playerList[j].getSpot());
                     }
                     if (playerList[j].getSpot()==48) {
                         playerList[j].setSpot(30);
                         System.out.print(" then down to square "+playerList[j].getSpot());
                     }
                     if (playerList[j].getSpot()==16) {
                         playerList[j].setSpot(6);
                         System.out.print(" then down to square "+playerList[j].getSpot());
                     }

                    System.out.print("\n");

                    // if victory, set boolean to true
                    if (playerList[j].getSpot()==100) {
                        victory = true;
                        System.out.println("Game is over, "+playerList[j].getName()+" won the game!");
                        System.exit(0);
                    }
            }
            
            System.out.println();

            if (victory == false)
                System.out.println("Game not over, flipping again");
        }    
    }
}