import java.util.Scanner;

public class Account {
    protected String type;
    protected CNY CNYBalance;
    protected USD USDBalance;
    protected HKD HKDBalance;

    Account(){
        CNYBalance = new CNY(0.0);
        USDBalance = new USD(0.0);
        HKDBalance = new HKD(0.0);
    }

    public void setType(String type){
        this.type = type;
    }

    public String getType(){
        return type;
    }

    public void setCNYBalance(double amt){
        CNYBalance.setAmount(CNYBalance.getAmount() + amt);
    }

    public double getCNYBalance(){
        return CNYBalance.getAmount();
    }

    /** Using CNY to purchase USD
     *
     * @param USDAmt Amount of USD customer wants to purchase
     */
    public void CNY2USD(double USDAmt){
        setUSDBalance(USDAmt);
        setCNYBalance(-1.0 * USDAmt * USDBalance.getExchangeRate2CNY());
    }

    /**
     * Using CNY to purchase HKD
     * @param HKDAmt Amount of HKD customer wants to purchase
     */
    public void CNY2HKD(double HKDAmt){
        setHKDBalance(HKDAmt);
        setCNYBalance(-1.0 * HKDAmt * HKDBalance.getExchangeRate2CNY());
    }

    public void setUSDBalance(double amt){
        USDBalance.setAmount(USDBalance.getAmount() + amt);
    }

    public double getUSDBalance(){
        return USDBalance.getAmount();
    }

    /**
     * Using USD to purchase CNY
     * @param CNYAmt Amount of CNY customer wants to purchase
     */
    public void USD2CNY(double CNYAmt){
        setCNYBalance(CNYAmt);
        setUSDBalance(-1.0 * CNYAmt * CNYBalance.getExchangeRate2USD());
    }

    /**
     * Using USD to purchase HKD
     * @param HKDAmt Amount of HKD customer wants to purchase
     */
    public void USD2HKD(double HKDAmt){
        setHKDBalance(HKDAmt);
        setUSDBalance(-1.0 * HKDAmt * HKDBalance.getExchangeRate2USD());
    }

    public void setHKDBalance(double amt){
        HKDBalance.setAmount(HKDBalance.getAmount() + amt);
    }

    public double getHKDBalance(){
        return HKDBalance.getAmount();
    }

    /**
     * Using HKD to purchase CNY
     * @param CNYAmt Amount of CNY customer wants to purchase
     */
    public void HKD2CNY(double CNYAmt){
        setCNYBalance(CNYAmt);
        setHKDBalance(-1.0 * CNYAmt * CNYBalance.getExchangeRate2HKD());
    }

    /**
     * Using HKD to purchase USD
     * @param USDAmt Amount of USD customer wants to purchase
     */
    public void HKD2USD(double USDAmt){
        setHKDBalance(USDAmt);
        setUSDBalance(-1.0 * USDAmt * USDBalance.getExchangeRate2HKD());
    }

    public void withdraw(){
        Scanner sc = new Scanner(System.in);
        System.out.print("Which currency you want to withdraw: 1. CNY 2. USD 3. HKD");
        String choice = sc.next();
        while(!choice.matches("^[1-3]$")){
            System.out.print("Your selection is invalid, try again: ");
            choice = sc.next();
        }

        switch (choice){
            case "1":
                System.out.println("You current have " + getCNYBalance() + " CNY.");
                System.out.print("How much you want to withdraw: ");
                choice = sc.next();
                if(Integer.parseInt(choice) <= getCNYBalance()) {
                    setCNYBalance(-1.0 * Double.parseDouble(choice));
                    System.out.println("Withdraw successful. You current have " + getCNYBalance() + " CNY.");
                }
                else
                    System.out.print("You don't have enough.");
                break;
            case "2":
                System.out.println("You current have " + getUSDBalance() + " USD.");
                System.out.print("How much you want to withdraw: ");
                choice = sc.next();
                if(Integer.parseInt(choice) <= getUSDBalance()) {
                    setUSDBalance(-1.0 * Double.parseDouble(choice));
                    System.out.println("Withdraw successful. You current have " + getUSDBalance() + " USD.");
                }
                else
                    System.out.print("You don't have enough.");
                break;
            case "3":
                System.out.println("You current have " + getHKDBalance() + " HKD.");
                System.out.print("How much you want to withdraw: ");
                choice = sc.next();
                if(Integer.parseInt(choice) <= getHKDBalance()) {
                    setHKDBalance(-1.0 * Double.parseDouble(choice));
                    System.out.println("Withdraw successful. You current have " + getHKDBalance() + " HKD.");
                }
                else
                    System.out.print("You don't have enough.");
        }
    }

    public void deposit(){
        Scanner sc = new Scanner(System.in);
        System.out.print("Which currency you want to withdraw: 1. CNY 2. USD 3. HKD");
        String choice = sc.next();
        while(!choice.matches("^[1-3]$")){
            System.out.print("Your selection is invalid, try again: ");
            choice = sc.next();
        }

        switch (choice){
            case "1":
                System.out.println("You current have " + getCNYBalance() + " CNY.");
                System.out.print("How much you want to deposit: ");
                choice = sc.next();
                setCNYBalance(Double.parseDouble(choice));
                System.out.println("Deposit successful. You current have " + getCNYBalance() + " CNY.");
                break;
            case "2":
                System.out.println("You current have " + getUSDBalance() + " USD.");
                System.out.print("How much you want to withdraw: ");
                choice = sc.next();
                setUSDBalance(Double.parseDouble(choice));
                System.out.println("Deposit successful. You current have " + getUSDBalance() + " USD.");
                break;
            case "3":
                System.out.println("You current have " + getHKDBalance() + " HKD.");
                System.out.print("How much you want to withdraw: ");
                choice = sc.next();
                setHKDBalance(Double.parseDouble(choice));
                System.out.println("Deposit successful. You current have " + getHKDBalance() + " HKD.");
        }
    }
}
