import java.util.concurrent.atomic.AtomicInteger;

public class Bank {
    private int LoanAmount;
    public Bank() {
        LoanAmount = 0;
    }

    public int getLoanAmount() {
        return LoanAmount;
    }
    public synchronized void TakeOutLoan(int amount)
    {
        LoanAmount += amount;
    }
}
