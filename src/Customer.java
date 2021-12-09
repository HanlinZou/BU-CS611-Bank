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

    public void openSavingAccount(){
        savingAcc = new Saving();
    }

    public void closeSavingAccount(){
        savingAcc = null;
    }

    public void openCheckingAccount(){
        checkingAcc = new Checking();
    }

    public void closeCheckingAccount(){
        checkingAcc = null;
    }
}
