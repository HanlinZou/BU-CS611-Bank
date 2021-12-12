import java.util.HashMap;
import java.util.Map;

public class StockAccount {
    private String id;  // ID of this account
    private String userId;  // ID of the user this account belongs to

    public double balance;
    // Number of stocks this account owns
    private Map<Stock, Integer> stock2share;
    // Money that the account spends on each stock
    private Map<Stock, Double> stock2money;

    public double accumulatedProfit;

    private StockDao stockDao = StockDao.getInstance();
    // private StockAccountDao stockAccountDao = StockAccountDao.getInstance();

    public StockAccount() {
    }

    public StockAccount(String userId, double balance) {
        // this.id = stockAccountDao.getNewId();  // generate a new id
        this.userId = userId;
        this.balance = balance;
        this.accumulatedProfit = 0;
        stock2share = new HashMap<>();
        stock2money = new HashMap<>();
    }

    public StockAccount(String id, String userId, double balance, double accumulatedProfit) {
        this.id = id;
        this.userId = userId;
        this.balance = balance;
        this.accumulatedProfit = accumulatedProfit;
        stock2share = new HashMap<>();
        stock2money = new HashMap<>();
    }

    public void setStock2Share(Map<Stock, Integer> stock2share){
        this.stock2share = stock2share;
    }

    public void setStock2Money(Map<Stock, Double> stock2money){
        this.stock2money = stock2money;
    }

    /**
     * Returns the accumulated profit of this accounts.
     *
     * @return Accumulated profit.
     */
    public double getAccumulatedProfit() {
        return accumulatedProfit;
    }

    /**
     * Deposit money to this account.
     *
     * @param money Money value to deposit.
     */
    public void deposit(double money) {
        balance += money;

        // TODO: log

        // TODO: update account database
        // stockAccountDao.update(this);
    }

    /**
     * Withdraw money from this account.
     *
     * @param money Money value to withdraw.
     *
     * @return true: successful / false: fail
     */
    public boolean withdraw(double money) {
        if (balance < money) return false;

        balance -= money;

        // TODO: log

        // TODO: update account database
        // stockAccountDao.update(this);

        return true;
    }

    /**
     * Buys a stock.
     *
     * @param stockId ID of the stock to be bought.
     * @param share How many share the account wants to buy.
     *
     * @return true: successful / false: fail
     */
    public boolean buyStock(String stockId, int share) {
        Stock stock = stockDao.queryById(stockId);

        if (stock == null) return false;  // wrong stockId

        double spentMoney = share * stock.getPrice();
        if(balance < spentMoney) return false;  // no enough balance

        // update current share
        int newShare = stock2share.getOrDefault(stock, 0) + share;
        stock2share.put(stock, newShare);
        // update spent money
        double newMoney = stock2money.getOrDefault(stock, 0.0) + spentMoney;
        stock2money.put(stock, newMoney);
        // update current balance
        this.balance -= spentMoney;

        // TODO: log

        // TODO: update account database
        // stockAccountDao.update(this);

        return true;
    }

    /**
     * Sells a stock.
     *
     * @param stockId ID of the stock to be bought.
     * @param share How many share the account wants to sell.
     *
     * @return true: successful / false: fail
     */
    public boolean sellStock(String stockId, int share) {
        Stock stock = stockDao.queryById(stockId);

        if (stock == null) return false;  // wrong stockId

        int oldShare = stock2share.getOrDefault(stock, 0);
        double oldMoney = stock2money.getOrDefault(stock, 0.0);

        if(oldShare < share) return false;  // no enough share

        double gainMoney = share * stock.getPrice();

        stock2share.put(stock, oldShare - share);  // update current share
        stock2money.put(stock, oldMoney - gainMoney);  // update spent money
        this.balance += gainMoney;  // update current balance

        double pricePerShare = oldShare == 0 ? 0 : oldMoney / oldShare;
        this.accumulatedProfit += share * (stock.getPrice() - pricePerShare); // update accumulated profit

        // TODO: log

        // TODO: update account database
        // stockAccountDao.update(this);

        return true;
    }

    /**
     * Returns a list of stocks this account owns to be displayed on UI.
     */
    public String displayStocks() {
        String str = "";

        for (Stock stock : stock2share.keySet()) {
            int share = stock2share.get(stock);
            double money = stock2money.get(stock);

            str += ("Stock name: " + stock.getName() + System.lineSeparator());
            str += ("Current price: " + stock.getPrice() + System.lineSeparator());
            str += ("Current share: " + share + System.lineSeparator());
            str += ("Spent money: " + money + System.lineSeparator());
        }

        return str;
    }

    /**
     * Computes the estimated profit (how much total profit would this account
     * gain if it sells all stocks now.
     *
     * @return Estimated profit
     */
    public double calculateEstimatedProfit() {
        double estimatedProfit = 0;

        for (Stock stock : stock2share.keySet()) {
            int share = stock2share.get(stock);
            double money = stock2money.get(stock);
            estimatedProfit += stock.getPrice() * share - money;
        }

        return estimatedProfit;
    }

    /**
     * Returns the number of stocks this account owns.
     *
     * @return Stock number.
     */
    public int getStockNum() {
        int num = 0;
        for (Stock stock : stock2share.keySet()) {
            if (stock2share.get(stock) > 0) num++;
        }
        return num;
    }

    public String toString(){
        return "Balance: $" + balance + "\nNum of stocks: " + getStockNum() +
            "\nEstimated Profit: $" + calculateEstimatedProfit() + "\nAccumulated Profit: $" + accumulatedProfit;
    }

    public String saveString() {
        String str = "";

        str += balance;
        str += "|";

        for (Stock stock : stock2share.keySet()) {
            int share = stock2share.get(stock);
            double money = stock2money.get(stock);

            str += stock.getID() + ":";
            str += share + "$";
            str += money + ",";
        }

        str = str.substring(0, str.length() - 1);
        return str;
    }
}
