import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Vector;
import java.util.concurrent.Executors;

public class Main {
    public static void main(String[] args) {
        var al = new ArrayList<Integer>();
        var ll = new LinkedList<Integer>();
        var v = new Vector<Integer>();
        var sc = Collections.synchronizedCollection(new Vector<Integer>());
        var sl = Collections.synchronizedList(new ArrayList<Integer>());
        for (int i = 0; i < 10000; i++) {
            al.add(i);
            ll.add(i);
            v.add(i);
            sc.add(i);
            sl.add(i);
        }
        Iterators.syncIterate(al, 1);

        var ExSvc = Executors.newFixedThreadPool(2);
        for (int i = 0; i < 2; i++) {
            int id = i;
            ExSvc.submit(() -> {
                Iterators.syncIterate(al, id);
            });
        }
        ExSvc.shutdown();

    }
}