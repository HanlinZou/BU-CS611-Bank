public abstract class Currency {
    protected String name;
    protected double amount;
    protected double exchangeRate2CNY;
    protected double exchangeRate2USD;
    protected double exchangeRate2HKD;

    Currency(){}

    public void setName(String name){
        this.name = name;
    }

    public String getName(){
        return name;
    }

    public void setAmount(double amt){
        amount = Double.parseDouble(String.format("%.2f", amt));
    }

    public double getAmount(){
        return amount;
    }

    public abstract void setExchangeRate2CNY(double rate);
    public abstract void setExchangeRate2USD(double rate);
    public abstract void setExchangeRate2HKD(double rate);

    public double getExchangeRate2CNY(){
        return exchangeRate2CNY;
    }

    public double getExchangeRate2USD(){
        return exchangeRate2USD;
    }

    public double getExchangeRate2HKD(){
        return exchangeRate2HKD;
    }
}
