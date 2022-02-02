public class Player {
    int x, y, initialRoll;
    String name;
    boolean first = false, second = false, third = false, fourth = false;

    public Player() {
    }

    public Player(int InsertX, int InsertY) {
        x = InsertX;
        y = InsertY;
    }

    public void setName(String InsertName) {
        name = InsertName;
    }

    public String getName() {
        return this.name;
    }
}
