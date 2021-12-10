import java.util.ArrayList;

public class StockAccount {
    public double balance;
    public ArrayList<BoughtStock> stocks;
    public double estimatedProfit;
    public double accumulatedProfit;

    StockAccount(){}

    StockAccount(double balance){
        this.balance = balance;
        stocks = new ArrayList<>();
        estimatedProfit = 0;
        accumulatedProfit = 0;
    }

    public void changeBalance(double amt){
        balance = Double.parseDouble(String.format("%.2f", balance + amt));
    }

    public void calculateEstimatedProfit(){}
    public void sellStock(double latestPrice){}
    public void adjustAccumulatedProfit(){}
}
