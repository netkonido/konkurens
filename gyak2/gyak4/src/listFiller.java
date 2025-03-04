import java.util.ArrayList;
import java.util.List;

public class listFiller extends Thread {
    public static ArrayList<Integer> Mylist = new ArrayList<Integer>();
    private final int From;

    public listFiller(int from) {
        this.From = from;
    }

    @Override
    public void run() {
            addNumbers(From);
    }

    public void addNumbers(int from) {
        for (int i = from; i < 1_000_000; i += 2) {
            synchronized (listFiller.Mylist){listFiller.Mylist.add(i);};
        }

    }
}
