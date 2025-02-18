
import java.util.Random;
import java.util.concurrent.CountDownLatch;

public class Dog extends Thread {

    public int x;
    public int y;
    public Farm farm;
    private final CountDownLatch latch;

    public Dog(int y, int x, Farm f, CountDownLatch l) {
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
        System.out.println("Dog is running");
        while(!farm.isOver){
           logic();
        }

    }

    @Override
    public String toString() {
        return "D";
    }

    public boolean isValidMove(int x, int y){
      
      if(x<farm.width-1 && x>0 && y<farm.height-1 && y>0){
         if ((x>farm.safezoneX && x<=2*farm.safezoneX)&&(y>farm.safezoneY && y<=2*farm.safezoneY)) {
            return false;   
         }
         else{
            if(!(farm.field[y][x] instanceof Empty)){
               return false;
            }
            else{return true;}
         }
      }
      else{
         return false;
      }
      
    }

    public void logic(){
      Random rand=new Random();
      int nextX=x;
      int nextY=y;
      boolean isValid=false;
      do {
          int randomX=rand.nextInt(3);
          //balra
          if(randomX==0){
            nextX=x-1;
          }
          //jobbra
          if(randomX==2){
            nextX=x+1;
          }
          
          //ha 1 akkor x tengelyen nem mozog
          int randomY=rand.nextInt(3);
          //felfele
          if(randomY==0){
            nextY=y-1;
          }
          //lefele
          if(randomY==2){
            nextY=y+1;
          }

          if(isValidMove(nextX, nextY)){
            isValid=true;
          }
      } while (!isValid);
      synchronized (farm.field[x][y]) {
         synchronized (farm.field[nextX][nextY]) {
           farm.field[y][x] = new Empty();
           farm.field[nextY][nextX] = this;

           this.x=nextX;
           this.y=nextY;
         }
       }
       try{
         Thread.sleep(200);
       }
       catch(InterruptedException e){
         System.err.println(e.getStackTrace());
       }
    }
}
