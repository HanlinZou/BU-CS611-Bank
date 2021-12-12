public class CNY extends Currency {
    CNY() {
    }

    CNY(double amt) {
        setName("Chinese Yuan");
        setAmount(amt);
        setExchangeRate2CNY(1.00);
        setExchangeRate2USD(configDao.getConfigDouble("CNY2USD", 1.0));
        setExchangeRate2HKD(configDao.getConfigDouble("CNY2HKD", 1.0));
    }
}
