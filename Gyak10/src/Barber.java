import java.util.HashSet;
import java.util.Set;

public class Barber extends Thread
{
    private boolean awake = true;

    public boolean isAwake() {
            return awake;
        }

    @Override
    public void run() {
        while (Main.open) {
            Customer nextCustomer = null;

            synchronized (Main.waitingRoom) {
                if (!Main.waitingRoom.isEmpty()) {
                    nextCustomer = Main.waitingRoom.iterator().next();
                    Main.waitingRoom.remove(nextCustomer);
                }
            }

            if (nextCustomer != null) {
                System.out.println("Barber: Cutting hair of customer " + nextCustomer.getName());
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    System.err.println("Barber: Oh! I am fired!");
                    return;
                }
                System.out.println("Barber: Hair of customer " + nextCustomer.getName() + " is cut.");
            } else {
                synchronized (Main.LockObject) {
                    awake = false;
                    try {
                        this.wait();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}