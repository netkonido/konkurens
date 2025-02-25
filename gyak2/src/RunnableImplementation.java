public class RunnableImplementation implements Runnable{
    @Override
    public void run() {
        for (int i = 0; i < 10000; i++)
        {
            System.out.println("Now this, is where the money at!");
        }
    }
}
