public class Account {
    protected String type;
    protected CNY CNYBalance;
    protected USD USDBalance;
    protected HKD HKDBalance;

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
}
