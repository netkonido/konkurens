import java.util.Collection;
import java.util.Enumeration;
import java.util.Iterator;

public class Iterators {
    static void nonSyncIterate(Collection<Integer> collection, int n)
    {
        var it = collection.iterator();
        while(it.hasNext())
        {
            System.out.print(n + ": ");
            System.out.println(it.next());
        }
    }

    static void syncIterate(Collection<Integer> collection, int n)
    {
        synchronized (collection)
        {
            nonSyncIterate(collection, n);
        }
    }
}
