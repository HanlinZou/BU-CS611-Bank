import java.util.ArrayList;
import java.util.Scanner;

public final class Customer extends User {
    private ArrayList<Loan> loanArrayList;
    private Saving savingAcc;
    private Checking checkingAcc;
    private StockAccount stockAccount = null;
    private CustomerDao customerDao = CustomerDao.getInstance();

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
    }

    /**
     * 10 USD fee when open a saving account.
     */
    public void openSavingAccount(){
        savingAcc = new Saving();
        savingAcc.setUSDBalance(-10.0);
    }

    /**
     * 10% of total USD fee when close a saving account.
     */
    public void closeSavingAccount(){
        if(savingAcc.getUSDBalance() > 0) {
            System.out.println("You have " + savingAcc.getUSDBalance() + " USD. " +
                    "10% close fee: " + String.format("%.2f", savingAcc.getUSDBalance() * 0.1) +
                    "You can have " + String.format("%.2f", savingAcc.getUSDBalance() * 0.9) + " USD back.");
            savingAcc = null;
        }
        else
            System.out.println("You don't have any USD. I can't let you close your account.");
    }

    /**
     * 15 USD fee when open a checking account.
     */
    public void openCheckingAccount(){
        checkingAcc = new Checking();
        checkingAcc.setUSDBalance(-15.0);
    }

    /**
     * 15% of total USD fee when close a saving account.
     */
    public void closeCheckingAccount(){
        if(checkingAcc.getUSDBalance() > 0) {
            System.out.println("You have " + checkingAcc.getUSDBalance() + " USD. " +
                    "10% close fee: " + String.format("%.2f", checkingAcc.getUSDBalance() * 0.15) +
                    "You can have " + String.format("%.2f", checkingAcc.getUSDBalance() * 0.85) + " USD back.");
            checkingAcc = null;
        }
        else
            System.out.println("You don't have any USD. I can't let you close your account.");
    }

    public void accountInquiry(){
        if(savingAcc != null)
            System.out.println("Saving Account Balance: \nUSD: " + savingAcc.getUSDBalance() +
                "\nCNY: " + savingAcc.getCNYBalance() + "\nHKD: " + savingAcc.getHKDBalance());
        if(checkingAcc != null)
            System.out.println("Saving Account Balance: \nUSD: " + checkingAcc.getUSDBalance() +
                "\nCNY: " + checkingAcc.getCNYBalance() + "\nHKD: " + checkingAcc.getHKDBalance());
    }

    //TODO: read data to provide transaction history

    public void openStockAccount() {
        if (savingAcc.getUSDBalance() >= 5000.0){
            Scanner sc = new Scanner(System.in);
            System.out.println("Min: 1000.0 Max: " + (savingAcc.getUSDBalance() - 2500.0) +
                " How much you want to deposit into your stocking account: ");
            String choice = sc.next();
            while(!choice.matches("^[-//+]?//d+(//.//d*)?|//.//d+$") || Double.parseDouble(choice) < 1000.0 ||
                Double.parseDouble(choice) > savingAcc.getUSDBalance() - 2500.0){
                System.out.print("Your selection is invalid, try again: ");
                choice = sc.next();
            }
            stockAccount = new StockAccount(Double.parseDouble(choice));
        }
        else
            System.out.println("You need to have at least $5000.0 in Saving to open a stock account.");
    }

    public void depositStockAccount(){}
    public void withdrawStockAccount(){}
}
