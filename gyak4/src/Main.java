import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        ArrayList<Thread> adderThreads = new ArrayList<Thread>();
        ThreadSafeMutableInteger n = new ThreadSafeMutableInteger(0);
        for (int i = 0; i < 5; i++) {
            adderThreads.add(new SimpleAdderThread(n, true));
            adderThreads.add(new SimpleAdderThread(n, false));
        }
        for(Thread var: adderThreads)
        {
            var.start();
        }

        try
        {
            for(Thread var: adderThreads)
            {
                var.join();
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        System.out.println(n.get());

    }
}