public class LinearAdderThread implements Runnable
{
    static int sum = 0;
    private  int From;
    private  int To;
    public LinearAdderThread(int from, int to)
    {
        this.From = from;
        this.To = to;
    }
    @Override
    public void run() {
        for (int i = From; i < To; i++) {
            sum += i;
        }
    }
}
