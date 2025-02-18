
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.CountDownLatch;

public class Sheep extends Thread {

    public int x;
    public int y;
    public Farm farm;
    private final CountDownLatch latch;

    public Sheep(int y, int x, Farm f, CountDownLatch l) {
        this.x = x;
        this.y = y;
        farm = f;
        latch = l;

    }

    @Override
    public void run() {

        try {
            latch.countDown();
            latch.await();
        } catch (InterruptedException e) {
            System.err.println(e.getStackTrace());

        }
        System.out.println("Sheep is running");
        while (!farm.isOver) {
            logic();
        }
    }

    @Override
    public String toString() {
        return "S";
    }

    /* public void logic() {
        int nextX = x;
        int nextY = y;
        boolean isValid = false;

        int toRight = 0;
        int toLeft = 0;
        int toUp = 0;
        int toDown = 0;
        do {
            if (x > 1 && y > 1 && x < farm.width - 1 && y < farm.height - 1) {
                for (int i = (y - 1); i <= (y + 1); i++) {
                    for (int j = (x - 1); j <= (j + 1); j++) {
                        if (farm.field[i][j] instanceof Dog) {
                            if ((y + 1) - i == 2) {
                                toDown++;
                            }
                            if ((y + 1) - i == 0) {
                                toUp++;
                            }
                            if ((x + 1) - j == 2) {
                                toRight++;
                            }
                            if ((x + 1) - j == 0) {
                                toLeft++;
                            }
                        }
                    }
                }
                if (toLeft > toRight) {
                    nextX = x - 1;
                } else if (toRight > toLeft) {
                    nextX = x + 1;
                }
                if (toUp > toDown) {
                    nextY = y - 1;
                } else if (toDown > toUp) {
                    nextY = y + 1;
                }

                if (nextX == x && nextY == y) {
                    for (int i = (y - 1); i <= (y + 1) && !isValid; i++) {
                        for (int j = (x - 1); j <= (j + 1) && !isValid; j++) {
                            if (farm.field[i][j] instanceof Empty) {
                                nextX = j;
                                nextY = i;
                                isValid = true;
                            }
                        }
                    }
                } else {
                    if (farm.field[nextY][nextX] instanceof Empty) {
                        isValid = true;
                    } else {
                        for (int i = (y - 1); i <= (y + 1) && !isValid; i++) {
                            for (int j = (x - 1); j <= (j + 1) && !isValid; j++) {
                                if (farm.field[i][j] instanceof Empty) {
                                    nextX = j;
                                    nextY = i;
                                    isValid = true;
                                }
                            }
                        }
                    }
                }
                synchronized (farm.field[y][x]) {
                    synchronized (farm.field[nextY][nextX]) {
                        farm.field[y][x] = new Empty();
                        farm.field[nextY][nextX] = this;

                        this.x = nextX;
                        this.y = nextY;
                    }
                }

            }

        } while (isValid);
    } */
    public void logic() {
        int nextX = this.x;
        int nextY = this.y;
        

        ArrayList<int[]> possibleMoves = new ArrayList<>();
        ArrayList<int[]> dogsNearby = new ArrayList<>();
        ArrayList<int[]> toDelete = new ArrayList<>();

        for (int i = (y - 1); i <= (y + 1); i++) {
            for (int j = (x - 1); j <= (x + 1); j++) {
                if (farm.field[i][j] instanceof Empty || farm.field[i][j] instanceof Gate) {
                    possibleMoves.add(new int[]{i, j});
                }
                if (farm.field[i][j] instanceof Dog) {
                    dogsNearby.add(new int[]{i, j});
                }
            }
        }

        for (int[] d : dogsNearby) {
            for (int[] pm : possibleMoves) {
                if (pm[0] == d[0] && pm[1] == d[1] + 1) {
                    toDelete.add(pm);
                }
                if (pm[0] == d[0] && pm[1] == d[1] - 1) {
                    toDelete.add(pm);
                }
                if (pm[0] == d[0] + 1 && pm[1] == d[1]) {
                    toDelete.add(pm);
                }
                if (pm[0] == d[0] - 1 && pm[1] == d[1]) {
                    toDelete.add(pm);
                }
            }
        }
        possibleMoves.removeAll(toDelete);

        Random rand = new Random();
        if (!possibleMoves.isEmpty()) {
            int randomMove = rand.nextInt(possibleMoves.size());
            int[] move = possibleMoves.get(randomMove);
            nextY = move[0];
            nextX = move[1];
        }

        synchronized (farm.field[y][x]) {
            synchronized (farm.field[nextY][nextX]) {
                farm.field[y][x] = new Empty();
                farm.field[nextY][nextX] = this;

                this.x = nextX;
                this.y = nextY;
                
            }
        }
        if(farm.field[y][x] instanceof Gate){
            farm.isOver=true;
        }
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            System.err.println(e.getStackTrace());
        }
    }

}
