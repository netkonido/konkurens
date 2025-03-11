public class ThreadSafeMutableInteger{
    private int Value;
    ThreadSafeMutableInteger()
    {
    }
    ThreadSafeMutableInteger(int startingValue)
    {
        this.Value = startingValue;
    }
    public synchronized int get()
    {
        return Value;
    }
    public synchronized void set(int value)
    {
        this.Value = value;
    }

    public synchronized int getAndIncrement()
    {
        int tmp = Value;
        Value += 1;
        return tmp;
    }
    public synchronized int getAndDecrement()
    {
        int tmp = Value;
        Value -= 1;
        return tmp;
    }
    public synchronized int getAndAdd(int value)
    {
        int tmp = Value;
        Value += value;
        return tmp;
    }
    public synchronized int incrementAndGet()
    {
        Value += 1;
        return Value;
    }

    public synchronized int decrementAndGet()
    {
        Value -= 1;
        return Value;
    }
    public synchronized int addAndGet(int value)
    {
        Value += value;
        return Value;
    }
}
