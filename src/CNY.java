public class CNY extends Currency{

    CNY(){}

    CNY(String name, double amt){
        setName(name);
        setAmount(amt);
        setExchangeRate2CNY(1.00);
        setExchangeRate2USD(0.1576);
        setExchangeRate2HKD(1.2293);
    }

    public void setExchangeRate2CNY(double rate) {
        this.exchangeRate2CNY = rate;
    }

    public void setExchangeRate2USD(double rate) {
        this.exchangeRate2USD = rate;
    }

    public void setExchangeRate2HKD(double rate) {
        this.exchangeRate2HKD = rate;
    }
}
