import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.ArrayList;

public final class Customer extends User implements TimerObserver {
    private Map<Loan, String> loan2collateral;

    private SavingAccount savingAccount;
    private CheckingAccount checkingAccount;
    private StockAccount stockAccount;

    private CustomerDao customerDao = CustomerDao.getInstance();
    private StockAccountDao stockAccountDao = StockAccountDao.getInstance();
    private SavingAccountDao savingAccountDao = SavingAccountDao.getInstance();
    private CheckingAccountDao checkingAccountDao = CheckingAccountDao.getInstance();
    private LoanDao loanDao = LoanDao.getInstance();

    private BankTimer timer = BankTimer.getInstance();

    public Customer() {
    }

    /**
     * Creates a new customer and add it to database.
     *
     * @param name Customer name.
     * @param password Customer password.
     */
    public Customer(String name, String password) {
        super("customer", name, password);
        setID(customerDao.getNewId());  // generates a new id
        loan2collateral = new HashMap<>();  // creates a new loan map
        customerDao.addToDatabase(this);   // add to database

        savingAccount = null;
        checkingAccount = null;
        stockAccount = null;
    }

    /**
     * Loads a customer from database.
     *
     * @param id Customer ID.
     * @param name Customer name.
     * @param password Customer password.
     */
    public Customer(
        String id,
        String name,
        String password,
        Map<Loan, String> loan2collateral
    ) {
        super("customer", id, name, password);

        this.loan2collateral = loan2collateral;

        // read accounts from database
        savingAccount = savingAccountDao.queryByUserId(getID());
        checkingAccount = checkingAccountDao.queryByUserId(getID());
        stockAccount = stockAccountDao.queryByUserId(getID());
    }

    public SavingAccount getSavingAccount() {
        return savingAccount;
    }

    public StockAccount getStockAccount() {
        return stockAccount;
    }

    public CheckingAccount getCheckingAccount() {
        return checkingAccount;
    }

    /**
     * 10 USD fee when open a saving account.
     */
    //xiansong: changed the return type to bool
    public boolean openSavingAccount() {
        if (savingAccount == null) {
            savingAccount = new SavingAccount(getID());
            savingAccount.setUSDBalanceAnyway(-10.0);
            System.out.println("Successfully opened a saving account.");

            savingAccount.getDao().saveToDatabase();  // update savingAccount database
            new Log("customer", getID(), timer.getTimeStr(), "Open a saving account; cost 10.0 USD as fee.");  // log
            return true;
        }
        else System.out.println("You already have a saving account.");
        return false;
    }

    /**
     * 10% of total USD fee when close a saving account.
     */
    public int closeSavingAccount() {
        if (savingAccount.checkBalance() && getLoanNum() == 0 && stockAccount == null) {
            // balance >=0, no loan, no stock account
            System.out.println(
                "You have " + savingAccount.getUSDBalance() + " USD. " +
                "10% close fee: " + String.format("%.2f", savingAccount.getUSDBalance() * 0.1) +
                "You can have " + String.format("%.2f", savingAccount.getUSDBalance() * 0.9) + " USD back."
            );
            savingAccount.getDao().deleteFromDatabase(savingAccount);  // update savingAccount database
            savingAccount = null;
            new Log("customer", getID(), timer.getTimeStr(), "Close a saving account.");  // log
            return 1;
        } else if (!savingAccount.checkBalance() || getLoanNum() > 0) {
            // balance < 0 or has loan
        	System.out.println("You idiot owe me money. I can't let you close your account.");
         	return 0;
        } else {
        	System.out.println("You need to close your stock account first");
         	return -1;
        }
    }

    /**
     * 15 USD fee when open a checking account.
     */
    //xiansong: changed the return type to bool
    public boolean openCheckingAccount() {
        if (checkingAccount == null) {
            checkingAccount = new CheckingAccount(getID());
            checkingAccount.setUSDBalanceAnyway(-15.0);
            System.out.println("Successfully opened a checking account.");

            checkingAccount.getDao().saveToDatabase();  // update checkingAccount database
            new Log("customer", getID(), timer.getTimeStr(), "Open a checking account; cost 15.0 USD as fee.");  // log

            return true;
        }
        else System.out.println("You already have a checking account.");return false;
    }

    /**
     * 15% of total USD fee when close a saving account.
     */
    public int closeCheckingAccount(){
        if (checkingAccount.checkBalance()) {
            System.out.println(
                "You have " + checkingAccount.getUSDBalance() + " USD. " +
                "10% close fee: " + String.format("%.2f", checkingAccount.getUSDBalance() * 0.15) +
                "You can have " + String.format("%.2f", checkingAccount.getUSDBalance() * 0.85) + " USD back."
            );

            checkingAccount.getDao().deleteFromDatabase(checkingAccount);  // delete checkingAccount from database
            checkingAccount = null;
            new Log("customer", getID(), timer.getTimeStr(), "Close a checking account.");  // log
            return 1;
        }
        else {
        	System.out.println("You idiot owe me money. I can't let you close your account.");
        	return 0;
        }
    }

    public String accountInquiry() {
        String str = "";

        if (savingAccount != null) str += savingAccount.toString() + "\n";
        if (checkingAccount != null) str += checkingAccount.toString() + "\n";
        if (stockAccount != null) str += stockAccount.toString() + "\n";

        if (str.length() > 0) str = str.substring(0, str.length() - 1);
        return str;
    }

    // TODO: read data to provide transaction history
    //xiansong: changed the return type to int, add a stockacount == null condition
    public int openStockAccount(double stockMoney) {
    	if (this.stockAccount != null) return -3;
    	else if (savingAccount != null) {
            if (savingAccount.getUSDBalance() >= 5000.0) {
                if (stockMoney < 1000 || stockMoney > savingAccount.getUSDBalance() - 2500.0) return 0;
                stockAccount = new StockAccount(getID(), stockMoney);
                savingAccount.setUSDBalance(-1.0 * stockMoney);

                savingAccount.getDao().saveToDatabase();  // update savingAccount database
                new Log("customer", getID(), timer.getTimeStr(), "Open a stock account and deposit " + stockMoney + " USD.");  // log

                return 1;
            } else {
                System.out.println("You need to have at least $5000.0 in Saving to open a stock account.");
                return -2;
            }
        }
        else {
            System.out.println("You idiot open your saving account first");
            return -1;
        }
    }

    /**
     * USD from SAVING to STOCK
     */
    public boolean depositStockAccount(double money) {
        if (savingAccount.getUSDBalance() - money >= 2500) {
            savingAccount.transfer(stockAccount, money, "usd");
            return true;
        }
        else return false;
    }

    /**
     * USD from STOCK to SAVING
     */
    public boolean withdrawStockAccount(double money) {
        if (money < 0.0) return false;
        if (stockAccount.withdraw(money)) {
            savingAccount.deposit(money, "usd");
            return true;
        }
        return false;
    }

    public boolean closeStockAccount() {
        System.out.println("I don't like you leaving us. So you can't have one cent back.");

        stockAccountDao.deleteFromDatabase(stockAccount);
        stockAccount = null;
        new Log("customer", getID(), timer.getTimeStr(), "Close a stock account.");  // log

        System.out.println("Stock account closed successfully.");
        return true;
    }

    /**
     * Money transfer between SAVING & CHECKING
     *
     * @param transferType 1: saving -> checking 2: checking -> saving
     * @param money Money to be transfered.
     * @param currencyType Currency type.
     */
    public boolean transfer(int transferType, double money, String currencyType) {
        if (transferType == 1) return savingAccount.transfer(checkingAccount, money, currencyType);
        else return checkingAccount.transfer(savingAccount, money, currencyType);
    }

    /**
     * Buys a loan.
     *
     * @param loanId ID of the loan to be bought.
     * @param collateral Name of collateral use to buy the loan.
     *
     * @return true: successful / false: fail
     */
    public boolean buyLoan(String loanName, String collateral) {
        Loan loan = loanDao.queryByName(loanName);

        if (loan == null || savingAccount == null) return false;  // wrong loanId or no saving account

        loan2collateral.put(loan, collateral);
        savingAccount.setUSDBalance(loan.getValue());

        customerDao.saveToDatabase();  // update customer database
        new Log("customer", getID(), timer.getTimeStr(), "Buy loan " + loanName + " (id: " + loan.getID() + "; interest rate: " + loan.getInterest() + ") using " + collateral + " get " + loan.getValue() + " USD.");  // log

        return true;
    }

    /**
     * Sells a loan.
     *
     * @param loanId ID of the loan to sell.
     *
     * @return true: successful / false: fail
     */
    public boolean sellLoan(String loanName) {
        Loan loan = loanDao.queryById(loanName);

        if (loan == null || savingAccount == null) return false;  // wrong loanId or no saving account

        double cost = (1 + loan.getInterest()) * loan.getValue();

        if (savingAccount.setUSDBalance(-cost)) {
            String collateral = loan2collateral.get(loan);

            loan2collateral.remove(loan);
            customerDao.saveToDatabase();  // update customer database
            new Log("customer", getID(), timer.getTimeStr(), "Sell loan " + loanName + " (id: " + loan.getID() + "; interest rate: " + loan.getInterest() + ") cost " + cost + " USD get " + collateral + "back.");  // log

            return true;
        }

        return false;
    }

    /**
     * Returns a list of loans this account owns to be displayed on UI.
     *
     * @return A string containing loan information
     */
    public String displayLoans() {
        String str = "";

        for (Loan loan : loan2collateral.keySet()) {
            String collateral = loan2collateral.get(loan);

            str += ("Loan name: " + loan.getName() + System.lineSeparator());
            str += ("Loan interest rate: " + loan.getInterest() + System.lineSeparator());
            str += ("Loan value: " + loan.getValue() + System.lineSeparator());
            str += ("Collateral: " + collateral + System.lineSeparator());
        }

        return str;
    }

    /**
     * Returns a list of loan objects.
     *
     * @return A list of loans.
     */
    public List<Loan> getLoanList() {
        List<Loan> list = new ArrayList<>();
        for (Loan loan : loan2collateral.keySet()) list.add(loan);
        return list;
    }

    /**
     * Returns the number of loans this account owns.
     *
     * @return Loan number.
     */
    public int getLoanNum() {
        return loan2collateral.keySet().size();
    }

    @Override
    public void onTimeChange() {
        // pass
    }

    @Override
    public void onDayChange() {
        // pass
    }

    @Override
    public void onMonthChange() {
        // pass
    }

    /**
     * Sells loans automatically.
     */
    @Override
    public void onYearChange() {
        for (Loan loan : loan2collateral.keySet()) sellLoan(loan.getName());
    }

    @Override
    public String displayString() {
        return displayString() + "\n\n" + "Accounts:" + "\n" + accountInquiry() + "\n\n" + "Loans:" + "\n" + displayLoans();
    }

    @Override
    public String saveString() {
        String str = ",";

        for (Loan loan : loan2collateral.keySet()) {
            String collateral = loan2collateral.get(loan);
            str += loan.getID() + ":" + collateral + ";";
        }

        str = str.substring(0, str.length() - 1);

        return getID() + "," + getName() + "," + getPassword() + str;
    }
}
