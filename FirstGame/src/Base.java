
public class Base {
    int x, y;
    int health = 1000;

    Base(int startingX, int startingY) {
        this.x = startingX;
        this.y = startingY;
    }

    public String getName() {
        if (this.x == 2500) {
            return ("1");
        } else {
            return ("2");
        }

    }
}
