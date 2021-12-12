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
        if(savingAcc == null) {
            savingAcc = new Saving();
            savingAcc.setUSDBalance(-10.0);
            System.out.println("Successfully opened a saving account.");
        }
        else
            System.out.println("You already have a saving account.");
    }

    /**
     * 10% of total USD fee when close a saving account.
     */
    public void closeSavingAccount(){
        if(savingAcc.getUSDBalance() >= 0 && stockAccount == null) {
            System.out.println("You have " + savingAcc.getUSDBalance() + " USD. " +
                    "10% close fee: " + String.format("%.2f", savingAcc.getUSDBalance() * 0.1) +
                    "You can have " + String.format("%.2f", savingAcc.getUSDBalance() * 0.9) + " USD back.");
            savingAcc = null;
        }
        else if(savingAcc.getUSDBalance() < 0)
            System.out.println("You idiot owe me money. I can't let you close your account.");
        else
            System.out.println("You need to close your stock account first");
    }

    /**
     * 15 USD fee when open a checking account.
     */
    public void openCheckingAccount(){
        if(checkingAcc == null) {
            checkingAcc = new Checking();
            checkingAcc.setUSDBalance(-15.0);
            System.out.println("Successfully opened a checking account.");
        }
        else
            System.out.println("You already have a checking account.");
    }

    /**
     * 15% of total USD fee when close a saving account.
     */
    public void closeCheckingAccount(){
        if(checkingAcc.getUSDBalance() >= 0) {
            System.out.println("You have " + checkingAcc.getUSDBalance() + " USD. " +
                    "10% close fee: " + String.format("%.2f", checkingAcc.getUSDBalance() * 0.15) +
                    "You can have " + String.format("%.2f", checkingAcc.getUSDBalance() * 0.85) + " USD back.");
            checkingAcc = null;
        }
        else
            System.out.println("You idiot owe me money. I can't let you close your account.");
    }

    public void accountInquiry(){
        if(savingAcc != null)
            System.out.println(savingAcc);
        if(checkingAcc != null)
            System.out.println(checkingAcc);
        if(stockAccount != null)
            System.out.println(stockAccount);
    }

    //TODO: read data to provide transaction history

    public void openStockAccount() {
        if(savingAcc != null) {
            if (savingAcc.getUSDBalance() >= 5000.0) {
                Scanner sc = new Scanner(System.in);
                System.out.println("Min: 1000.0 Max: " + (savingAcc.getUSDBalance() - 2500.0) +
                    " How much you want to deposit into your stocking account: ");
                String choice = sc.next();
                while (!choice.matches("^[-//+]?//d+(//.//d*)?|//.//d+$") || Double.parseDouble(choice) < 1000.0 ||
                    Double.parseDouble(choice) > savingAcc.getUSDBalance() - 2500.0) {
                    System.out.print("Your selection is invalid, try again: ");
                    choice = sc.next();
                }
                stockAccount = new StockAccount(getID(), Double.parseDouble(choice));
                savingAcc.setUSDBalance(-1.0 * Double.parseDouble(choice));
            } else
                System.out.println("You need to have at least $5000.0 in Saving to open a stock account.");
        }
        else
            System.out.println("You idiot open your saving account first");
    }

    /**
     * USD from SAVING to STOCK
     */
    public void depositStockAccount(){
        if(savingAcc.getUSDBalance() >= 2500) {
            Scanner sc = new Scanner(System.in);
            System.out.println("Min: 0.0 Max: " + (savingAcc.getUSDBalance() - 2500.0) +
                " How much you want to deposit into your stocking account: ");
            String choice = sc.next();
            while (!choice.matches("^[-//+]?//d+(//.//d*)?|//.//d+$") || Double.parseDouble(choice) < 0.0 ||
                Double.parseDouble(choice) > savingAcc.getUSDBalance() - 2500.0) {
                System.out.print("Your selection is invalid, try again: ");
                choice = sc.next();
            }
            stockAccount.deposit(Double.parseDouble(choice));
            savingAcc.setUSDBalance(-1.0 * Double.parseDouble(choice));
        }
        else
            System.out.println("You need to have at least $2500 in your saving account.");
    }

    /**
     * USD from STOCK to SAVING
     */
    public void withdrawStockAccount(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Min: 0.0 Max: " + stockAccount.balance +
            " How much you want to withdraw from your stocking account: ");
        String choice = sc.next();
        while(!choice.matches("^[-//+]?//d+(//.//d*)?|//.//d+$") || Double.parseDouble(choice) < 0.0 ||
            Double.parseDouble(choice) > stockAccount.balance){
            System.out.print("Your selection is invalid, try again: ");
            choice = sc.next();
        }
        stockAccount.withdraw(Double.parseDouble(choice));
        savingAcc.setUSDBalance(Double.parseDouble(choice));
    }

    public void closeStockAccount(){
        System.out.println("I don't like you leaving us. So you can't have one cent back.");
        stockAccount = null;
        System.out.println("Stock account closed successfully.");
    }

    /**
     * Money transfer between SAVING & CHECKING
     */
    public void transfer(){
        Scanner sc = new Scanner(System.in);
        System.out.print("1. Saving -> Checking\n2. Checking -> Saving\n3. Leave\nPick one: ");
        String choice = sc.next();
        while(!choice.matches("^[1-3]$")){
            System.out.print("Your selection is invalid, try again: ");
            choice = sc.next();
        }
        switch (choice) {
            case "1":
                savingAcc.transfer(checkingAcc);
                break;
            case "2":
                checkingAcc.transfer(savingAcc);
        }
    }

}
