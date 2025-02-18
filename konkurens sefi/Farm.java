
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.CountDownLatch;

public class Farm extends Thread {

    public int dogCount;
    public int sheepCount;
    public int width;
    public int height;
    public int safezoneX;
    public int safezoneY;
    public Object[][] field;
    public ArrayList<Thread> animals = new ArrayList<>();
    public CountDownLatch latch;
    public boolean isOver;
    //public final ReentrantLock[][] lock;

    public Farm(int w, int h, int sc, int dc) {
        width = w;
        height = h;
        sheepCount = sc;
        dogCount = dc;
        field = new Object[height][width];
        this.latch = new CountDownLatch(dc + sc);
        isOver = false;
        //lock=new ReentrantLock[height][width];

        safezoneX = (width - 2) / 3;
        safezoneY = (height - 2) / 3;

        //mezők feltöltése
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if (i == 0 || i == (height - 1)) {
                    field[i][j] = new Wall();
                } else if (j == 0 || j == (width - 1)) {
                    field[i][j] = new Wall();
                } else {
                    field[i][j] = new Empty();
                }
                //lock[i][j] = new ReentrantLock();
            }
        }

        Random rand = new Random();

        //random kapuk
        int randomXwall1 = rand.nextInt((width - 2) - 1 + 1) + 1;
        int randomXwall2 = rand.nextInt((width - 2) - 1 + 1) + 1;
        field[0][randomXwall1] = new Gate();
        field[height - 1][randomXwall2] = new Gate();

        int randomYwall1 = rand.nextInt((height - 2) - 1 + 1) + 1;
        int randomYwall2 = rand.nextInt((height - 2) - 1 + 1) + 1;
        field[randomYwall2][0] = new Gate();
        field[randomYwall1][width - 1] = new Gate();

        Set<Coordinate> coordinates = new HashSet<>();
        Set<Coordinate> sheepCoordinates = new HashSet<>();
        //különböző random pozi kutyák

        while (coordinates.size() < dc) {
            int randomX = rand.nextInt(((width - 2) - 1) + 1) + 1;
            int randomY = rand.nextInt(((height - 2) - 1) + 1) + 1;
            if (randomX <= safezoneX || randomX > 2 * safezoneX) {
                if (randomY <= safezoneY || randomY > (2 * safezoneY)) {
                    coordinates.add(new Coordinate(randomX, randomY));
                }
            }
            /* try {
                Thread.sleep(500);
            } catch (Exception e) {
                System.out.println();
            } */
        }
        for (Coordinate c : coordinates) {
            Dog d = new Dog(c.y, c.x, this, latch);
            field[c.y][c.x] = d;
            animals.add(d);
        }
        System.out.println("kutyak");
        System.out.println(coordinates);

        //különböző random pozi juhok
        while (sheepCoordinates.size() < sc) {
            int randomX = rand.nextInt(((width - 2) - 1) + 1) + 1;
            int randomY = rand.nextInt((height - 1) + 1) + 1;
            if (randomX > safezoneX && randomX <= 2 * safezoneX) {
                if (randomY > safezoneY && randomY <= 2 * safezoneY) {
                    sheepCoordinates.add(new Coordinate(randomX, randomY));
                }
            }
        }

        for (Coordinate c : sheepCoordinates) {
            Sheep s = new Sheep(c.y, c.x, this, latch);
            field[c.y][c.x] = s;
            animals.add(s);
        }
        System.out.println("juhok");
        System.out.println(sheepCoordinates);
    }

    @Override
    public void run() {
        synchronized (this) {
            for (Thread thread : animals) {
                thread.start();
            }
            System.out.println("\033[H\033[2J");
            
        }
        while (!isOver) {
            synchronized (this) {
                for (int i = 0; i < height; i++) {
                    for (int j = 0; j < width; j++) {
                        System.out.print(" ");
                        System.out.print(field[i][j]);
                    }
                    System.out.println();
                }
                System.out.println(animals);
            }
            try {
                Thread.sleep(200);
                //System.out.println("\u001B[0;0H");
            } catch (InterruptedException e) {
                System.err.println(e.getStackTrace());
            }
            //System.out.println("\033[H\033[2J");
        }
        //System.out.println("Sheep escaped");
    }
}
