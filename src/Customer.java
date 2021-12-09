import java.util.ArrayList;

public class Customer {
    private String name;
    private ArrayList<Loan> loanArrayList;
    private Saving savingAcc;
    private Checking checkingAcc;

    Customer(){}

    Customer(String name){
        setName(name);
        loanArrayList = new ArrayList<>();
    }

    public void setName(String name){
        this.name = name;
    }

    public String getName(){
        return name;
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
