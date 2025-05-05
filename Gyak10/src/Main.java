import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class Main {
    public static final Object LockObject = new Object();
    public static final int WAITING_ROOM_SIZE = 5;
    public static final int MAX_CUSTOMERS = 30;

    public static Set<Customer> waitingRoom = new HashSet<>();
    public static boolean open = true;
    public static final Barber barber = new Barber();

    public static void main(String args[]) {
        Random rand = new Random();


        barber.start();
        for (int i = 0; i < MAX_CUSTOMERS; ++i) {
            Customer customer = new Customer("Customer" + i);
            customer.start();
            try {
                Thread.sleep(rand.nextInt(2500));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        open = false;
        try {
            Thread.sleep(rand.nextInt(1000));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        barber.interrupt();
    }
}
