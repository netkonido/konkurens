public class Main {
    static int Sum;
    public static void main(String[] args) {
        Thread helloThread = new HelloThread();
        Thread worldThread = new WorldThread();

        //helloThread.start();
        //worldThread.start();

        new Thread(()->{
           for (int i = 0; i < 10000; i++)
           {System.out.println("From unnamed function!");}
        }).start();

        (new Thread(){
            @Override
            public void run() {
                for (int i = 0; i < 10000; i++) {
                    System.out.println("From unnamed class");
                }
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("WTH is going on here???");
            }
        }).start();

        new Thread(new RunnableImplementation()).start();

    }
}