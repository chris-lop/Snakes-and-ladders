import java.util.Scanner;

public class PlayLadderAndSnake {
    public static void main(String args[]) {
        Scanner keyboard = new Scanner(System.in);



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
