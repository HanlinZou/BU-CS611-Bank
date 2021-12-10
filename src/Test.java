/**
 * 测试样例，提交的时候可以删掉
 */
public final class Test {
    private CustomerDao customerDao = CustomerDao.getInstance();
    private ManagerDao managerDao = ManagerDao.getInstance();
    BankTimer timer = BankTimer.getInstance();

    public Test() {
    }

    public void testCreateUser() {
        Customer c = new Customer("customer2", "123456");
        System.out.println(c.getID() + " " + c.getName() + " " + c.getType() + " " + c.getPassword());
        Manager m = new Manager("manager1", "654321");
        System.out.println(m.getID() + " " + m.getName() + " " + m.getType() + " " + m.getPassword());
    }

    public void testQueryUser() {
        Customer c = customerDao.query("customer2");
        System.out.println(c.getID() + " " + c.getName() + " " + c.getType() + " " + c.getPassword());
        Manager m = managerDao.query("manager1");
        System.out.println(m.getID() + " " + m.getName() + " " + m.getType() + " " + m.getPassword());
    }

    public void testCurrency() {
        CNY cny = new CNY(10);
        System.out.println(cny.getExchangeRate2CNY() + " " + cny.getExchangeRate2HKD() + " " + cny.getExchangeRate2USD());
        USD usd = new USD(10);
        System.out.println(usd.getExchangeRate2CNY() + " " + usd.getExchangeRate2HKD() + " " + usd.getExchangeRate2USD());
        HKD hkd = new HKD(10);
        System.out.println(hkd.getExchangeRate2CNY() + " " + hkd.getExchangeRate2HKD() + " " + hkd.getExchangeRate2USD());
    }

    public void testTimer() {
        new Thread(timer).start();  // timer 会每隔一秒输出当前时间和时间戳
    }

    public void testAll() {
        testCreateUser();
        testQueryUser();
        testCurrency();
        testTimer();
    }
}
