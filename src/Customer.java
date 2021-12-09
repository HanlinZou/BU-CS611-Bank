import java.util.ArrayList;

public final class Customer extends User {
    private ArrayList<Loan> loanArrayList;
    private Saving savingAcc;
    private Checking checkingAcc;
    private CustomerDAO customerDao = CustomerDAO.getInstance();

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
        loanArrayList = new ArrayList<>();
        setID(customerDao.getNewID());  // generates a new id
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
        // TODO we have to load loan array from database.
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
}
