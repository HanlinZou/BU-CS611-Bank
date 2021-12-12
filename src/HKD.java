public class HKD extends Currency {

    HKD(){}

    HKD(double amt){
        setName("Hong Kong Dollar");
        setAmount(amt);
        setExchangeRate2CNY(configDao.getConfigDouble("HKD2CNY", 1.0));
        setExchangeRate2USD(configDao.getConfigDouble("HKD2USD", 1.0));
        setExchangeRate2HKD(1.0);
    }
}
