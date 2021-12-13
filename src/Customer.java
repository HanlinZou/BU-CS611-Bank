import java.util.ArrayList;
import java.util.Scanner;

public final class Customer extends User {
    private ArrayList<Loan> loanArrayList;
    private SavingAccount savingAccount;
    private CheckingAccount checkingAccount;
    private StockAccount stockAccount;

    private CustomerDao customerDao = CustomerDao.getInstance();
    private StockAccountDao stockAccountDao = StockAccountDao.getInstance();
    private SavingAccountDao savingAccountDao = SavingAccountDao.getInstance();
    private CheckingAccountDao checkingAccountDao = CheckingAccountDao.getInstance();
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
        loanArrayList = new ArrayList<>();  // creates a new loan list
        setID(customerDao.getNewId());  // generates a new id
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
    public Customer(String id, String name, String password) {
        super("customer", id, name, password);

        // TODO we have to load loan list from database.
        loanArrayList = new ArrayList<>();

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
            new Log(getID(), timer.getDateStr(), "Open a saving account, cost 10.0 USD as fee.");  // log
            return true;
        }
        else System.out.println("You already have a saving account.");return false;
    }

    /**
     * 10% of total USD fee when close a saving account.
     */
    public void closeSavingAccount() {
        if (savingAccount.getUSDBalance() >= 0 && stockAccount == null) {
            System.out.println(
                "You have " + savingAccount.getUSDBalance() + " USD. " +
                "10% close fee: " + String.format("%.2f", savingAccount.getUSDBalance() * 0.1) +
                "You can have " + String.format("%.2f", savingAccount.getUSDBalance() * 0.9) + " USD back."
            );
            savingAccount.getDao().deleteFromDatabase(savingAccount);  // update savingAccount database
            savingAccount = null;
            new Log(getID(), timer.getDateStr(), "Close a saving account.");  // log
        }
        else if (savingAccount.getUSDBalance() < 0)
            System.out.println("You idiot owe me money. I can't let you close your account.");
        else
            System.out.println("You need to close your stock account first");
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
            new Log(getID(), timer.getDateStr(), "Open a checking account, cost 15.0 USD as fee.");  // log

            return true;
        }
        else System.out.println("You already have a checking account.");return false;
    }

    /**
     * 15% of total USD fee when close a saving account.
     */
    public void closeCheckingAccount(){
        if (checkingAccount.getUSDBalance() >= 0) {
            System.out.println(
                "You have " + checkingAccount.getUSDBalance() + " USD. " +
                "10% close fee: " + String.format("%.2f", checkingAccount.getUSDBalance() * 0.15) +
                "You can have " + String.format("%.2f", checkingAccount.getUSDBalance() * 0.85) + " USD back."
            );

            checkingAccount.getDao().deleteFromDatabase(checkingAccount);  // delete checkingAccount from database
            checkingAccount = null;
            new Log(getID(), timer.getDateStr(), "Close a checking account.");  // log
        }
        else System.out.println("You idiot owe me money. I can't let you close your account.");
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
    	if (this.stockAccount != null)
    	{
    		return -3;
    	}
    	else if (savingAccount != null) {
            if (savingAccount.getUSDBalance() >= 5000.0) {
                if (stockMoney < 1000 || stockMoney > savingAccount.getUSDBalance() - 2500.0) return 0;
                stockAccount = new StockAccount(getID(), stockMoney);
                savingAccount.setUSDBalance(-1.0 * stockMoney);

                savingAccount.getDao().saveToDatabase();  // update savingAccount database
                new Log(getID(), timer.getDateStr(), "Open a stock account and deposit " + stockMoney + " USD.");  // log

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
    public void depositStockAccount(){
        if (savingAccount.getUSDBalance() >= 2500) {
            Scanner sc = new Scanner(System.in);
            System.out.println("Min: 0.0 Max: " + (savingAccount.getUSDBalance() - 2500.0) +
                " How much you want to deposit into your stocking account: ");
            String choice = sc.next();
            while (!choice.matches("^[-//+]?//d+(//.//d*)?|//.//d+$") || Double.parseDouble(choice) < 0.0 ||
                Double.parseDouble(choice) > savingAccount.getUSDBalance() - 2500.0) {
                System.out.print("Your selection is invalid, try again: ");
                choice = sc.next();
            }
            stockAccount.deposit(Double.parseDouble(choice), "usd");
            savingAccount.setUSDBalance(-1.0 * Double.parseDouble(choice));
        }
        else
            System.out.println("You need to have at least $2500 in your saving account.");
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
        new Log(getID(), timer.getDateStr(), "Close a stock account.");  // log

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
}
