import java.io.PrintWriter;

public class HelloThread extends Thread
{
    public HelloThread()
    {
        super();
    }

    @Override public void run()
    {
        for(int i = 0; i < 10000; i++)
        {
            System.out.println("Hello ");

        }
    }
}