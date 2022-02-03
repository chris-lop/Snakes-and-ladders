public class Player {
    int x, y, spot = 0, initialRoll;
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

    public void setRoll(int InsertRoll) {
        initialRoll = InsertRoll;
    }

    public void setSpot(int InsertSpot) {
        spot = InsertSpot;
    }

    public String getName() {
        return this.name;
    }

    public int getSpot() {
        return this.spot;
    }

    public int getRoll() {
        return this.initialRoll;
    }

    public void move(int InsertSpot) {
            spot += InsertSpot;
    }
}
