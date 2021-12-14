public abstract class BasicAccount extends Account {
    private CNY CNYBalance;
    private USD USDBalance;
    private HKD HKDBalance;
    private BankTimer timer = BankTimer.getInstance();

    /**
     * Loads an account from database (with ID).
     */
    public BasicAccount(String type, String id, String userId, double cny, double usd, double hkd) {
        super(type, id, userId);
        CNYBalance = new CNY(cny);
        USDBalance = new USD(usd);
        HKDBalance = new HKD(hkd);
    }

    /**
     * Creates a new account (no ID).
     */
    public BasicAccount(String type, String userId, double cny, double usd, double hkd) {
        super(type, userId);
        CNYBalance = new CNY(cny);
        USDBalance = new USD(usd);
        HKDBalance = new HKD(hkd);
    }

    protected Currency getCurrency(String currencyType) {
        if (currencyType.equalsIgnoreCase("cny")) return CNYBalance;
        else if (currencyType.equalsIgnoreCase("usd")) return USDBalance;
        else return HKDBalance;
    }

    public boolean setCNYBalance(double amt) {
        return CNYBalance.setAmount(CNYBalance.getAmount() + amt);
    }

    public double getCNYBalance() {
        return CNYBalance.getAmount();
    }

    /**
     * Using CNY to purchase USD, 5% fee
     *
     * @param USDAmt Amount of USD customer wants to purchase
     */
    public boolean CNY2USD(double USDAmt) {
        double cost = USDAmt * USDBalance.getExchangeRate2CNY();
        double costWithFee = 1.05 * cost;

        setUSDBalance(USDAmt);
        if (setCNYBalance(-costWithFee)) {
            getDao().saveToDatabase();  // update database
            new Log("customer", getUserId(), timer.getTimeStr(), getType() + ": tranfer " + cost + " CNY (" + costWithFee + " with fee) to " + USDAmt + " USD.");  // log
            return true;
        }

        return false;
    }

    /**
     * Using CNY to purchase HKD, 5% fee
     *
     * @param HKDAmt Amount of HKD customer wants to purchase
     */
    public boolean CNY2HKD(double HKDAmt) {
        double cost = HKDAmt * HKDBalance.getExchangeRate2CNY();
        double costWithFee = 1.05 * cost;

        setHKDBalance(HKDAmt);
        if (setCNYBalance(-costWithFee)) {
            getDao().saveToDatabase();
            new Log("customer", getUserId(), timer.getTimeStr(), getType() + ": tranfer " + cost + " CNY (" + costWithFee + " with fee) to " + HKDAmt + " HKD.");  // log
            return true;
        }

        return false;
    }

    public boolean setUSDBalance(double amt) {
        return USDBalance.setAmount(USDBalance.getAmount() + amt);
    }

    public boolean setUSDBalanceAnyway(double amt) {
        return USDBalance.setAmountAnyway(USDBalance.getAmount() + amt);
    }

    public double getUSDBalance() {
        return USDBalance.getAmount();
    }

    /**
     * Using USD to purchase CNY, 5% fee
     *
     * @param CNYAmt Amount of CNY customer wants to purchase
     */
    public boolean USD2CNY(double CNYAmt) {
        double cost = CNYAmt * CNYBalance.getExchangeRate2USD();
        double costWithFee = 1.05 * cost;

        setCNYBalance(CNYAmt);
        if (setUSDBalance(-costWithFee)) {
            getDao().saveToDatabase();
            new Log("customer", getUserId(), timer.getTimeStr(), getType() + ": tranfer " + cost + " USD (" + costWithFee + " with fee) to " + CNYAmt + " CNY.");  // log
            return true;
        }

        return false;
    }

    /**
     * Using USD to purchase HKD, 5% fee
     *
     * @param HKDAmt Amount of HKD customer wants to purchase
     */
    public boolean USD2HKD(double HKDAmt) {
        double cost = HKDAmt * HKDBalance.getExchangeRate2USD();
        double costWithFee = 1.05 * cost;

        setHKDBalance(HKDAmt);
        if (setUSDBalance(-costWithFee)) {
            getDao().saveToDatabase();
            new Log("customer", getUserId(), timer.getTimeStr(), getType() + ": tranfer " + cost + " USD (" + costWithFee + " with fee) to " + HKDAmt + " HKD.");  // log
            return true;
        }

        return false;
    }

    public boolean setHKDBalance(double amt) {
        return HKDBalance.setAmount(HKDBalance.getAmount() + amt);
    }

    public double getHKDBalance() {
        return HKDBalance.getAmount();
    }

    /**
     * Using HKD to purchase CNY, 5% fee
     *
     * @param CNYAmt Amount of CNY customer wants to purchase
     */
    public boolean HKD2CNY(double CNYAmt) {
        double cost = CNYAmt * CNYBalance.getExchangeRate2HKD();
        double costWithFee = 1.05 * cost;

        setCNYBalance(CNYAmt);
        if (setHKDBalance(-costWithFee)) {
            getDao().saveToDatabase();
            new Log("customer", getUserId(), timer.getTimeStr(), getType() + ": tranfer " + cost + " HKD (" + costWithFee + " with fee) to " + CNYAmt + " CNY.");  // log
            return true;
        }

        return false;
    }

    /**
     * Using HKD to purchase USD, 5% fee
     *
     * @param USDAmt Amount of USD customer wants to purchase
     */
    public boolean HKD2USD(double USDAmt) {
        double cost = USDAmt * USDBalance.getExchangeRate2HKD();
        double costWithFee = 1.05 * cost;

        setUSDBalance(USDAmt);
        if (setHKDBalance(-cost)) {
            getDao().saveToDatabase();
            new Log("customer", getUserId(), timer.getTimeStr(), getType() + ": tranfer " + cost + " HKD (" + costWithFee + " with fee) to " + USDAmt + " USD.");  // log
            return true;
        }

        return false;
    }

    /**
     * Withdraw from an account, 3% fee
     *
     * @param money Money to be withdrawn.
     * @param currencyType Currency type.
     */
    @Override
    public boolean withdraw(double money, String currencyType) {
        Currency currency = getCurrency(currencyType);
        double cost = 1.03 * money;
        if (currency.setAmount(currency.getAmount() - cost)) {
            getDao().saveToDatabase();
            new Log("customer", getUserId(), timer.getTimeStr(), getType() + ": withdraw " + money + " " + currencyType.toUpperCase() + " (cost " + cost + " with fee).");  // log
            return true;
        }
        return false;
    }

    @Override
    public String saveString() {
        return getID() + "," + getUserId() + "," + getCNYBalance() + "," + getUSDBalance() + "," + getHKDBalance();
    }

    /**
     * Transfers money from this account to another.
     *
     * @param account Another account.
     * @param money Money to be transfered.
     * @param currencyType Currency type.
     */
    public abstract boolean transfer(Account account, double money, String currencyType);
}
