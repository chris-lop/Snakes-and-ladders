import java.util.Scanner;

public class PlayLadderAndSnake {
    public static void main(String args[]) {
        Scanner keyboard = new Scanner(System.in);

        System.out.println("How Many Players?");
        int PlayerCount = keyboard.nextInt();

        LadderAndSnake game1 = new LadderAndSnake(PlayerCount);

        game1.play();
        keyboard.close();

    }
}
