import java.util.Random;

public class Vehicle {
    Base owner;
    double heading;
    int speed;
    int gunRange = 500;
    int gunDamage = 50;
    int health = 100;
    int x, y;
    int ammo = 3;
    Game game;

    Vehicle(Game g) {
        this.game = FirstGame.game;
        Random rnd = new Random();
        this.heading = rnd.nextDouble() * 2 * Math.PI;
        this.speed = (int) (rnd.nextDouble() * 50) + 25;
    }

    void assignBase(Base b) {
        this.owner = b;
        this.x = this.owner.x;
        this.y = this.owner.y;
    }

    void calculate() {
        Base b = this.game.inRange(this);
        if (b != null) {
            this.game.shootAt(this, b);

        }
    }
}
