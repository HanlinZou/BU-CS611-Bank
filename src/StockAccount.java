import java.util.ArrayList;

public class StockAccount {
    public double balance;
    public ArrayList<BoughtStock> stocks;
    public double estimatedEarn;
    public double accumulatedEarn;

    StockAccount(){}

    StockAccount(double balance){
        this.balance = balance;
        stocks = new ArrayList<>();
        estimatedEarn = 0;
        accumulatedEarn = 0;
    }


}
