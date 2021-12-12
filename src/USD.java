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
}
