public class WorldThread extends Thread
{
    public WorldThread()
    {
        super();
    }

    @Override public void run()
    {
        for(int i = 0; i < 10000; i++)
        {
            System.out.println("world!");

        }
    }
}