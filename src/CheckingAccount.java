public class CheckingAccount extends BasicAccount {
    private CheckingAccountDao accountDao = null;
    private BankTimer timer = BankTimer.getInstance();

    /**
     * Loads a checkings account from database (with ID).
     *
     * @param id Account id.
     * @param userId ID of the user this account belongs to.
     */
    public CheckingAccount(String id, String userId, double cny, double usd, double hkd) {
        super("checking", id, userId, cny, usd, hkd);
    }

    /**
     * Creates a new checking account, generates a new ID and add it to database.
     *
     * @param userId ID of the user this account belongs to.
     */
    public CheckingAccount(String userId) {
        super("checking", userId, 0.0, 0.0, 0.0);
        setID(getDao().getNewId());  // generates a new id
        setName(userId + "-" + getID());
        getDao().addToDatabase(this);  // add to database
    }

    public AccountDao<CheckingAccount> getDao() {
        if (accountDao == null) {
            accountDao = CheckingAccountDao.getInstance();
        }
        return accountDao;
    }

    /**
     * Deposit money into an account. Checking account takes 3% fee.
     *
     * @param money Money to be saved.
     * @param currencyType Currency type.
     */
    @Override
    public boolean deposit(double money, String currencyType) {
        Currency currency = getCurrency(currencyType);
        double gain = 0.97 * money;

        currency.setAmount(currency.getAmount() + gain);

        getDao().saveToDatabase();  // update database
        new Log("customer", getUserId(), timer.getTimeStr(), "Checking: deposit " + money + " " + currencyType.toUpperCase() + " (get " + gain + " after fee).");  // log

        return true;
    }

    /**
     * Transfers money from this account to another. Checking account takes 3% fee.
     *
     * @param account Another account.
     * @param money Money to be transfered.
     * @param currencyType Currency type.
     */
    @Override
    public boolean transfer(Account account, double money, String currencyType) {
        Currency currency = getCurrency(currencyType);
        double cost = 1.03 * money;
        if (currency.setAmount(currency.getAmount() - cost)) {
            account.deposit(money, currencyType);

            getDao().saveToDatabase();  // update database for this acount
            account.getDao().saveToDatabase();  // update database for another acount
            new Log("customer", getUserId(), timer.getTimeStr(), "Checking: transfer " + money + " " + currencyType.toUpperCase() + " from " + getType() + " to " + account.getType() + " (cost " + cost + " with fee).");  // log

            return true;
        }
        return false;
    }

    @Override
    public String toString(){
        return "Checking Account Balance: \nUSD: " + getUSDBalance() + "\nCNY: " +
            getCNYBalance() + "\nHKD: " + getHKDBalance();
    }
}
