public class Customer extends Thread
{
    public Customer(String name) {
            setName(name);
        }

    @Override
    public void run() {
        synchronized (Main.waitingRoom) {
            if (Main.waitingRoom.size() < Main.WAITING_ROOM_SIZE) {
                System.err.println("                                                  "
                        + getName() + ": Sitting in the waiting room.");
                Main.waitingRoom.add(this);
                if (Main.waitingRoom.size() == 1) {
                    if (!Main.barber.isAwake()) {
                        synchronized (Main.LockObject) {
                            Main.barber.notify();
                        }
                    }
                }
            } else {
                System.err.println("                                                  "
                        + getName() + ": Leaving.");
            }
        }
    }
}
