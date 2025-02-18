public class Main {
    public static void main(String[] args) {
        Thread helloThread = new HelloThread();
        Thread worldThread = new WorldThread();

        helloThread.start();
        worldThread.start();

    }
}