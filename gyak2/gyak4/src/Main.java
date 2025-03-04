public class Main {
    public static void main(String[] args) {
        //create two subroutines
        var evenAdder = new listFiller(0);
        var oddAdder = new listFiller(1);

        evenAdder.start();
        oddAdder.start();

        try
        {
            evenAdder.join();
            oddAdder.join();
        }
        catch (Exception e)
        {
          System.out.println("Exception occurred:");
          System.out.println(e.toString());
        }

        System.out.println(listFiller.Mylist.size());
        for (int i = 11500; i < 12000; i++) {
            System.out.println(listFiller.Mylist.get(i));
        }
    }
}