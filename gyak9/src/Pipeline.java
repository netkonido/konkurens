import java.util.List;
import java.util.concurrent.*;

public class Pipeline {
    public static void main(String[] args) throws Exception {
        var NO_FURTHER_INPUT = "";
        var NO_FURTHER_INPUT_l = -1;

        var bq = new ArrayBlockingQueue<String>(100);
        var lq = new ArrayBlockingQueue<Integer>(100);
        var pool = Executors.newCachedThreadPool();

        pool.submit(() -> {
            bq.addAll(List.of("a", "bb", "ccccccc", "ddd", "eeee", NO_FURTHER_INPUT));
        });

        pool.submit(() -> {
            try {
                String next = NO_FURTHER_INPUT;
                while (true) {
                    if(!bq.isEmpty())
                    {
                        next = bq.remove();
                    }

                    if(!next.equals(NO_FURTHER_INPUT))
                    {
                        lq.add(next.length());
                    }
                    else
                    {
                        lq.add(NO_FURTHER_INPUT_l);
                        break;
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        pool.submit(() -> {
            try {
                Integer next = NO_FURTHER_INPUT_l;
                while (true) {
                    if(!lq.isEmpty())
                    {
                        next = lq.remove();
                    }

                    if(!next.equals(NO_FURTHER_INPUT_l))
                    {
                        System.out.println(next);
                    }
                    else
                    {
                        System.out.println(NO_FURTHER_INPUT_l);
                        break;
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        pool.shutdown();
        pool.awaitTermination(10, TimeUnit.SECONDS);
    }
}
