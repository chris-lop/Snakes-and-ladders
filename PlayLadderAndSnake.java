// -----------------------------------------------------
// Assignment 0
// Question: Part II
// Written by: Christopher Lopez, ID: 40199547
// -----------------------------------------------------

import java.util.Scanner;

/** 
 * Name(s) and ID(s) Christopher Lopez, 40199547
 * COMP 249
 * Assignment # 0
 * Due Date February 7th, 2022
 * 
 * The PlayLadderAndSnake class holds the main method of the program. This is where the entire program is run.
 * It contains the input validation feature that gives the user 3 attempts to input a correct amount of players before shutting down the program.
 * It also runs the core game engine if the player successfully enters a correct amount of players.
*/
public class PlayLadderAndSnake {
    public static void main(String args[]) {
        Scanner keyboard = new Scanner(System.in);


        System.out.println("Welcome to Chris' Snakes & Ladders Game!");
        System.out.println("This game was made by Christopher Lopez");
        System.out.println();
        System.out.println("Enter the # of players for your game - Number must be between 2 and 4 inclusively:");
        int PlayerCount = keyboard.nextInt();
        int attemptNb = 0;

        while (PlayerCount < 2 || PlayerCount > 4) {
            attemptNb++;

            if (attemptNb == 4) {
                System.out.println("Bad Attempt 4! You have exhausted all your chances. Program will terminate!");
                System.exit(0);
            } else {
                System.out.println("Bad Attempt "+attemptNb+" - Invalid # of players. Please enter a # between 2 and 4 inclusively: ");
                PlayerCount = keyboard.nextInt();
            }
          }

        LadderAndSnake game1 = new LadderAndSnake(PlayerCount);

        game1.play();
        keyboard.close();

    }
}
