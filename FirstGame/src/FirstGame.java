
public class FirstGame {

    static final int MAP_WIDTH = 10000, MAP_HEIGHT = 10000;

    public static void main(String[] args) throws InterruptedException {

        Base base1 = new Base(2500, 2500);
        Base base2 = new Base(7500, 7500);

        Vehicle vehicle1 = new Vehicle(base1);
        Vehicle vehicle2 = new Vehicle(base2);

        boolean gameOver = false;

        while (gameOver == false) {
            /**
             * System.out.println("Vehicle 1 is at " + vehicle1.x + ", " +
             * vehicle1.y + ". Vehicle 2 is at " + vehicle2.x + ", " +
             * vehicle2.y + ".");
             */

            move(vehicle1);
            move(vehicle2);

            if (inRange(vehicle1, base2)) {
                System.out.println("Shoot v1, b2");
                base2.health -= vehicle1.gunDamage;
                System.out.println("Base 2 health = " + base2.health);
            }
            if (inRange(vehicle2, base1)) {
                System.out.println("Shoot v2, b1");
                base1.health -= vehicle2.gunDamage;
                System.out.println("Base 1 health = " + base1.health);
            }
            gameOver = (base1.health <= 0) || (base2.health <= 0);

            //Thread.sleep(500);
        }
    }

    static void move(Vehicle vehicle) {
        vehicle.x = ((int) (vehicle.x
                + vehicle.speed * Math.cos(vehicle.heading)) + MAP_WIDTH)
                % MAP_WIDTH;
        vehicle.y = ((int) (vehicle.y
                + vehicle.speed * Math.cos(vehicle.heading)) + MAP_HEIGHT)
                % MAP_HEIGHT;
    }

    static boolean inRange(Vehicle v, Base b) {
        double distance = Math
                .sqrt(Math.pow(v.x - b.x, 2) + Math.pow(v.y - b.y, 2));
        return (distance < v.gunRange);
    }

}
