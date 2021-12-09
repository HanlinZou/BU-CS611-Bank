public class Loan {
    private String name;
    private double interest;
    private String collateral;
    private double USD;

    Loan(){}

    Loan(String name, double interest, String collateral, double USD){
        setName(name);
        setInterest(interest);
        setCollateral(collateral);
        setUSD(USD);
    }

    public void setName(String name){
        this.name = name;
    }

    public String getName(){
        return name;
    }

    public void setInterest(double interest){
        this.interest = interest;
    }

    public double getInterest(){
        return interest;
    }

    public void setCollateral(String collateral){
        this.collateral = collateral;
    }

    public String getCollateral(){
        return collateral;
    }

    public void setUSD(double amt){
        USD = amt;
    }

    public double getUSD(){
        return USD;
    }
}
