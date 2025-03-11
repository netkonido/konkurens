public class SimpleAdderThread extends Thread {
    private ThreadSafeMutableInteger Number;
    private int AddValue;
    SimpleAdderThread(ThreadSafeMutableInteger number, int addValue)
    {
        this.Number = number;
        this.AddValue = addValue;
    }

    @Override
    public void run() {
        for (int i = 0; i < 10_000; i++) {
            Number.getAndAdd(AddValue);
        }
    }
}
