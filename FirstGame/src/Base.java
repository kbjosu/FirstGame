
public class Base {
    int x, y;
    int health = 1000;
    String name;

    Base(String n, int startingX, int startingY) {
    	this.name = n;
        this.x = startingX;
        this.y = startingY;
    }

    public String getName() {
       return this.name;
    }
}
