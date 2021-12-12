public abstract class Currency {
    protected String name;
    protected double amount;
    protected double exchangeRate2CNY;
    protected double exchangeRate2USD;
    protected double exchangeRate2HKD;
    protected ConfigDao configDao = ConfigDao.getInstance();

    public Currency() {
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public boolean setAmountAnyway(double amt) {
        amount = Double.parseDouble(String.format("%.2f", amt));
        return true;
    }

    public boolean setAmount(double amt) {
        if (amt < 0) return false;
        return setAmountAnyway(amt);
    }

    public double getAmount() {
        return amount;
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

    public double getExchangeRate2CNY() {
        return exchangeRate2CNY;
    }

    public double getExchangeRate2USD() {
        return exchangeRate2USD;
    }

    public double getExchangeRate2HKD() {
        return exchangeRate2HKD;
    }
}
