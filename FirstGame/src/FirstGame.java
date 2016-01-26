
public class FirstGame {

    static final int MAP_WIDTH = 10000, MAP_HEIGHT = 10000;
    static final int MAX_VEHICLES = 20;
    static Base base1 = new Base(2500, 2500);
    static Base base2 = new Base(7500, 7500);

    public static void main(String[] args) throws InterruptedException {

        Vehicle[] vehicle = new Vehicle[MAX_VEHICLES];

        for (int i = 0; i < MAX_VEHICLES; i++) {
            vehicle[i] = new Vehicle((i % 2 == 0) ? base1 : base2);
        }

        boolean gameOver = false;
        Base winner = null;
        while (gameOver == false) {
            /*
             * System.out.println("Vehicle 1 is at " + vehicle1.x + ", " +
             * vehicle1.y + ". Vehicle 2 is at " + vehicle2.x + ", " +
             * vehicle2.y + ".");
             */

            for (int i = 0; i < MAX_VEHICLES && gameOver == false; i++) {
                move(vehicle[i]);
                Base target = inRange(vehicle[i]);
                if (target != null && vehicle[i].ammo > 0 && target.health > 0
                        && gameOver == false) {
                    target.health -= vehicle[i].gunDamage;
                    System.out.println("Vehicle " + i + " hit base "
                            + vehicle[i].owner.getName() + ". Base "
                            + vehicle[i].owner.getName() + " now has "
                            + target.health + " health.");
                    vehicle[i].ammo--;
                    gameOver = (base1.health <= 0) || (base2.health <= 0);
                    if (target != null && target.health <= 0) {
                        winner = (vehicle[i].owner.getName() == "1") ? base2
                                : base1;
                    }
                }
            }

            Thread.sleep(20);
        }
        System.out.print("Base " + winner.getName() + " wins!");
    }

    static void move(Vehicle vehicle) {
        vehicle.x = ((int) (vehicle.x
                + vehicle.speed * Math.cos(vehicle.heading)) + MAP_WIDTH)
                % MAP_WIDTH;
        vehicle.y = ((int) (vehicle.y
                + vehicle.speed * Math.cos(vehicle.heading)) + MAP_HEIGHT)
                % MAP_HEIGHT;
    }

    static Base inRange(Vehicle v) {
        Base b = (base1 == v.owner) ? base2 : base1;
        double distance = Math
                .sqrt(Math.pow(v.x - b.x, 2) + Math.pow(v.y - b.y, 2));
        return ((distance < v.gunRange) ? b : null);
    }

}
