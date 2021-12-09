public class HKD extends Currency{

    HKD(){}

    HKD(String name, double amt){
        setName(name);
        setAmount(amt);
        setExchangeRate2CNY(0.8135);
        setExchangeRate2USD(0.1282);
        setExchangeRate2HKD(1.0);
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
