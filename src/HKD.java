public class HKD extends Currency {

    HKD(){}

    HKD(double amt){
        setName("Hong Kong Dollar");
        setAmount(amt);
        setExchangeRate2CNY(configDao.getConfigDouble("HKD2CNY", 1.0));
        setExchangeRate2USD(configDao.getConfigDouble("HKD2USD", 1.0));
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
