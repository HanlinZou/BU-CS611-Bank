public class USD extends Currency {

    USD() {
    }

    USD(double amt) {
        setName("United States Dollar");
        setAmount(amt);
        setExchangeRate2CNY(configDao.getConfigDouble("USD2CNY", 1.0));
        setExchangeRate2USD(1.0);
        setExchangeRate2HKD(configDao.getConfigDouble("USD2HKD", 1.0));
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
