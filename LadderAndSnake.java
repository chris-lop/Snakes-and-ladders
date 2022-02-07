// -----------------------------------------------------
// Assignment 0
// Question: Part I
// Written by: Christopher Lopez, ID: 40199547
// -----------------------------------------------------

import java.util.concurrent.ThreadLocalRandom;

/** 
 * Name(s) and ID(s) Christopher Lopez, 40199547
 * COMP 249
 * Assignment # 0
 * Due Date February 7th, 2022
 * 
 * The LadderAndSnake class is the blueprint for the core game engine. It holds important game functions such as the dice roll method, and the play method, which initiates and runs the entire game.
*/
public class LadderAndSnake {

    // Class attributes
    Spot[][] board = new Spot[10][10];
    int players;

    // Default Constructor

    /** 
     * Default constructor for class LadderAndSnake
    */
    public LadderAndSnake() {

    }
    
    // Parameterized constructor

    /** 
     * Parameterized constructor for class LadderAndSnake
     * @param InsertPlayers Variable holding the number of players playing the given game.
    */
    public LadderAndSnake(int InsertPlayers) {
        players = InsertPlayers;
    }

    // Basic Methods (Getters, Setters)

    /** 
     * This method returns the amount of players in the given game.
     * @return Returns the amount of players in the given game.
    */
    public int getPlayers() {
        return this.players;
    }

    /** 
     * This mutator method changes the number of players in a game.
     * @param NewPlayers Variable holding the new amount of players.
    */
    public void setPlayers(int NewPlayers) {
        players = NewPlayers;
    }

    // Method that flips dice and returns values from 1 to 6 inclusively

    /** 
     * This method generates a random integer from 1 to 6 inclusively.
     * @return A random integer from 1 to 6 inclusively.
    */
    public int flipDice() {
        return ThreadLocalRandom.current().nextInt(1, 7);
    }

    // Play Method (Main Game Engine)

    /** 
     * This method is the main game engine that will run the entire game.
     * It first creates the amount of player objects required based on the user input.
     * Then, it runs an algorithm that decides the order of play amongst the players based on random dice rolls, and sorts them in an array.
     * This algorithm also accounts for equalities, and re-rolls players who have equalities until the equality is broken.
     * Finally, it starts the game, and makes each player roll a dice until one of the players win.
    */
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
                        System.out.println("Terminating the program.");
                        System.exit(0);
                    }
            }
            
            System.out.println();

            if (victory == false)
                System.out.println("Game not over, flipping again");
        }    
    }
}