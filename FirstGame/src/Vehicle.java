import java.util.Random;

public class Vehicle {
    Base owner;
    double heading;
    int speed = 5;
    int gunRange = 500;
    int gunDamage = 50;
    int health = 100;

    Vehicle(Base startingOwner) {
        this.owner = startingOwner;
        Random rnd = new Random();
        this.heading = rnd.nextDouble();

    }

}
