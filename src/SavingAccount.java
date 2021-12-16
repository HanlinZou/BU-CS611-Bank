public final class SavingAccount extends BasicAccount implements TimerObserver {
    private SavingAccountDao accountDao = null;
    private static ConfigDao configDao = ConfigDao.getInstance();
    private BankTimer timer = BankTimer.getInstance();

    /**
     * Loads a saving account from database (with ID).
     *
     * @param id Account id.
     * @param userId ID of the user this account belongs to.
     */
    public SavingAccount(String id, String userId, double cny, double usd, double hkd) {
        super("saving", id, userId, cny, usd, hkd);
        timer.addObserver(this);  // observe time changing
    }

    /**
     * Creates a new saving account, generates a new ID and add it to database.
     *
     * @param userId ID of the user this account belongs to.
     */
    public SavingAccount(String userId) {
        super("saving", userId, 0.0, 0.0, 0.0);
        setID(getDao().getNewId());  // generates a new id
        setName(userId + "-" + getID());
        getDao().addToDatabase(this);  // add to database
        timer.addObserver(this);  // observe time changing
    }

    public AccountDao<SavingAccount> getDao() {
        if (accountDao == null) {
            accountDao = SavingAccountDao.getInstance();
        }
        return accountDao;
    }

    /**
     * Deposit money into an account. Saving account doesn't take fee.
     *
     * @param money Money to be saved.
     * @param currencyType Currency type.
     */
    @Override
    public boolean deposit(double money, String currencyType) {
        Currency currency = getCurrency(currencyType);
        currency.setAmount(currency.getAmount() + money);

        getDao().saveToDatabase();  // update database
        new Log("customer", getUserId(), timer.getTimeStr(), "Saving: deposit " + money + " " + currencyType.toUpperCase() + ".");  // log

        return true;
    }

    /**
     * Transfers money from this account to another. Saving account doesn't take fee.
     *
     * @param account Another account.
     * @param money Money to be transfered.
     * @param currencyType Currency type.
     */
    @Override
    public boolean transfer(Account account, double money, String currencyType) {
        Currency currency = getCurrency(currencyType);
        if (currency.setAmount(currency.getAmount() - money)) {
            account.deposit(money, currencyType);

            getDao().saveToDatabase();  // update database for this acount
            account.getDao().saveToDatabase();  // update database for another acount
            new Log("customer", getUserId(), timer.getTimeStr(), "Saving: transfer " + money + " " + currencyType.toUpperCase() + " from " + getType() + " to " + account.getType() + ".");  // log

            return true;
        }
        return false;
    }

    @Override
    public void onTimeChange() {
        // pass
    }

    @Override
    public void onDayChange() {
        // pass
    }

    /**
     * Adds interest to this account every config.INTERVAL seconds.
     */
    @Override
    public void onMonthChange() {
        double rate = configDao.getConfigDouble("RATE", 0.003);

        double cny = rate * getCNYBalance();
        double usd = rate * getUSDBalance();
        double hkd = rate * getHKDBalance();

        setCNYBalance(cny);
        setUSDBalance(usd);
        setHKDBalance(hkd);

        getDao().saveToDatabase();  // update database for this acount
        new Log("customer", getUserId(), timer.getTimeStr(), "Saving: get interest: " + cny + " CNY; " + usd + " USD; " + hkd + " HKD.");  // log
    }

    @Override
    public void onYearChange() {
        // pass
    }

    @Override
    public String toString() {
        return "Saving Account Balance: \nUSD: " + getUSDBalance() + "\nCNY: " +
            getCNYBalance() + "\nHKD: " + getHKDBalance();
    }
}
