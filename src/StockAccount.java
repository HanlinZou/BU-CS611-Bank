import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.ArrayList;

public final class StockAccount extends Account {
    public double balance;
    // Number of stocks this account owns
    private Map<Stock, Double> stock2share;
    // Money that the account spends on each stock
    private Map<Stock, Double> stock2money;

    public double accumulatedProfit;

    private StockDao stockDao = StockDao.getInstance();
    private StockAccountDao accountDao = null;
    private static ConfigDao configDao = ConfigDao.getInstance();

    private BankTimer timer = BankTimer.getInstance();

    /**
     * Loads a stock account from database (with ID).
     *
     * @param id Account id.
     * @param userId ID of the user this account belongs to.
     * @param balance Balance of this account.
     * @param accumulatedProfit Accumulated profit of this account.
     * @param stock2share Number of stocks this account owns
     * @param stock2money Money that the account spends on each stock
     */
    public StockAccount(
        String id,
        String userId,
        double balance,
        double accumulatedProfit,
        Map<Stock, Double> stock2share,
        Map<Stock, Double> stock2money
    ) {
        super("stock", id, userId);

        setBalance(balance);
        this.accumulatedProfit = accumulatedProfit;
        this.stock2share = stock2share;
        this.stock2money = stock2money;
    }

    /**
     * Creates a new stock account, generates a new ID and add it to database.
     *
     * @param userId ID of the user this account belongs to.
     * @param balance Balance of this account.
     */
    public StockAccount(String userId, double balance) {
        super("stock", userId);

        setBalance(balance);
        accumulatedProfit = 0;
        stock2share = new HashMap<>();
        stock2money = new HashMap<>();

        setID(getDao().getNewId());  // generates a new id
        setName(userId + "-" + getID());
        getDao().addToDatabase(this);  // add to database
    }

    public AccountDao<StockAccount> getDao() {
        if (accountDao == null) {
            accountDao = StockAccountDao.getInstance();
        }
        return accountDao;
    }

    public void setStock2Share(Map<Stock, Double> stock2share) {
        this.stock2share = stock2share;
    }

    public Map<Stock, Double> getStock2Share() {
    	return this.stock2share;
    }

    public void setStock2Money(Map<Stock, Double> stock2money){
        this.stock2money = stock2money;
    }

    public void setBalance(double balance) {
        this.balance = Double.parseDouble(String.format("%.2f", balance));
    }

    public double getBalance() {
        return balance;
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
    public boolean deposit(double money, String currencyType) {
        if (currencyType.equalsIgnoreCase("usd")) balance += money;
        else if (currencyType.equalsIgnoreCase("cny")) balance += money * configDao.getConfigDouble("CNY2USD", 1.0);
        else balance += money * configDao.getConfigDouble("HKD2USD", 1.0);

        balance = Double.parseDouble(String.format("%.2f", balance));

        getDao().saveToDatabase();  // update stock account database
        new Log("customer", getUserId(), timer.getTimeStr(), "Stock: deposit " + money + " " + currencyType.toUpperCase() + ".");  // log

        return true;
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
        balance = Double.parseDouble(String.format("%.2f", balance));

        getDao().saveToDatabase();  // update stock account database
        new Log("customer", getUserId(), timer.getTimeStr(), "Stock: withdraw " + money + ".");  // log

        return true;
    }

    @Override
    public boolean withdraw(double money, String currencyType) {
        return withdraw(money);
    }

    /**
     * Buys a stock.
     *
     * @param name Name of the stock to be bought.
     * @param share How many share the account wants to buy.
     *
     * @return true: successful / false: fail
     */
    public boolean buyStock(String name, double share) {
        Stock stock = stockDao.queryByName(name);

        if (stock == null) return false;  // wrong stockId

        double spentMoney = share * stock.getPrice();
        if (balance < spentMoney) return false;  // no enough balance

        // update current share
        double newShare = stock2share.getOrDefault(stock, 0.0) + share;
        stock2share.put(stock, newShare);
        // update spent money
        double newMoney = stock2money.getOrDefault(stock, 0.0) + spentMoney;
        newMoney = Double.parseDouble(String.format("%.2f", newMoney));
        stock2money.put(stock, newMoney);
        // update current balance
        this.balance -= spentMoney;
        this.balance = Double.parseDouble(String.format("%.2f", this.balance));

        getDao().saveToDatabase();  // update stock account database
        new Log("customer", getUserId(), timer.getTimeStr(), "Buy " + share + " share stock " + name + " (id: " + stock.getID() + "; price: " + stock.getPrice() + ").");  // log

        return true;
    }

    /**
     * Sells a stock.
     *
     * @param name Name of the stock to sell.
     * @param share How many share the account wants to sell.
     *
     * @return true: successful / false: fail
     */
    public boolean sellStock(String name, double share) {
        Stock stock = stockDao.queryByName(name);

        if (stock == null) return false;  // wrong stockId

        double oldShare = stock2share.getOrDefault(stock, 0.0);
        double oldMoney = stock2money.getOrDefault(stock, 0.0);

        if (oldShare < share) return false;  // no enough share

        double newShare = oldShare - share;
        double gainMoney = share * stock.getPrice();

        stock2share.put(stock, newShare);  // update current share
        stock2money.put(stock, Double.parseDouble(String.format("%.2f", oldMoney - gainMoney)));  // update spent money
        this.balance += gainMoney;  // update current balance
        this.balance = Double.parseDouble(String.format("%.2f", this.balance));

        double pricePerShare = oldShare == 0 ? 0 : oldMoney / oldShare;
        this.accumulatedProfit += share * (stock.getPrice() - pricePerShare); // update accumulated profit
        this.accumulatedProfit = Double.parseDouble(String.format("%.2f", this.accumulatedProfit));

        if (newShare == 0) { // remove stock if sell out
            stock2share.remove(stock);
            stock2money.remove(stock);
        }

        getDao().saveToDatabase();  // update stock account database
        new Log("customer", getUserId(), timer.getTimeStr(), "Sell " + share + " share stock " + name + " (id: " + stock.getID() + "; price: " + stock.getPrice() + ").");  // log

        return true;
    }

    /**
     * Returns a list of stocks this account owns to be displayed on UI.
     */
    public String displayStocks() {
        String str = "";

        for (Stock stock : stock2share.keySet())
        {
            double share = stock2share.get(stock);
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
            double share = stock2share.get(stock);
            double money = stock2money.get(stock);
            estimatedProfit += stock.getPrice() * share - money;
            estimatedProfit = Double.parseDouble(String.format("%.2f", estimatedProfit));
        }

        return estimatedProfit;
    }

    /**
     * Returns a list of stock objects.
     *
     * @return A list of loans.
     */
    public List<Stock> getLoanList() {
        List<Stock> list = new ArrayList<>();
        for (Stock stock : stock2share.keySet()) list.add(stock);
        return list;
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

    public String toString() {
        return "Stock Account: \nBalance: $" + balance + "\nNum of stocks: " + getStockNum() +
            "\nEstimated Profit: $" + calculateEstimatedProfit() + "\nAccumulated Profit: $" + accumulatedProfit;
    }

    @Override
    public String saveString() {
        String str = getID() + "," + getUserId() + "," + balance + "," + accumulatedProfit + ",";

        for (Stock stock : stock2share.keySet()) {
            double share = stock2share.get(stock);
            double money = stock2money.get(stock);

            str += stock.getID() + ":";
            str += share + "$";
            str += money + ";";
        }

        str = str.substring(0, str.length() - 1);
        return str;
    }
}
