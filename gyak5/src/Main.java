import java.util.Arrays;
import java.util.HashSet;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args) {
        Bank MyBank = new Bank();
        var futures = new HashSet<Future<?>>();
        var loans = new int[10];
        var threadPool = Executors.newFixedThreadPool(10);
        for (int i = 0; i <10; i++) {
            int threadNumber = i;
            Future<?> future = threadPool.submit(new Runnable() {
                @Override
                public void run() {
                    int loansTaken = 0;
                    for (int k = 0; k < 10000; k++) {
                        int thisLoan = ThreadLocalRandom.current().nextInt(100, 1000);
                        MyBank.TakeOutLoan(thisLoan);
                        loansTaken += thisLoan;
                    }
                    loans[threadNumber] = loansTaken;
                }
            });
            futures.add(future);
        }


        try {
            for (Future<?> future : futures)
            {
                future.get();
            }
            threadPool.shutdown();
            if(!threadPool.awaitTermination(5, TimeUnit.SECONDS))
            {
                System.out.println("Threads did not shut down in 5 seconds");
            }
        }
        catch (Exception e)
        {

        }
        System.out.print("Total loan amount by bank: ");
        System.out.println(MyBank.getLoanAmount());
        System.out.print("Total loan amount by agents: ");
        System.out.println(Arrays.stream(loans).sum());

    }
}