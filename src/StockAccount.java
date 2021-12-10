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

    public void calculateEstimatedProfit(){
        estimatedProfit = 0;
        double cost, latestPrice;
        for (BoughtStock stock : stocks) {
            cost = stock.purchasePrice;
            // TODO: read the current price of this stock!
            latestPrice = 1;
            estimatedProfit += (latestPrice - cost) * stock.share;
        }
        estimatedProfit = Double.parseDouble(String.format("%.2f", estimatedProfit));
    }

    /**
     * Sell a customer's stock
     * @param latestPrice the latest price the stock
     * @param share how much share the customer wants to sale
     */
    public void sellStock(double latestPrice, double share){
        /*TODO: 1. find the stock customer wants to sell in his stock list
                2. calculate the total sell price, add it to balance
                3. adjust remaining share, if 0, remove this stock from list
        */

    }
    public void adjustAccumulatedProfit(double amt){
        accumulatedProfit = Double.parseDouble(String.format("%.2f", accumulatedProfit + amt));
        calculateEstimatedProfit();
    }
}
