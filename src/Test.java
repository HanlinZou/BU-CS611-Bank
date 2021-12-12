/**
 * 测试样例，提交的时候可以删掉
 */
public final class Test {
    private CustomerDao customerDao = CustomerDao.getInstance();
    private ManagerDao managerDao = ManagerDao.getInstance();
    private StockDao stockDao = StockDao.getInstance();
    BankTimer timer = BankTimer.getInstance();

    public Test() {
    }

    public void testUser() {
        System.out.println("---------- User -----------");

        // create
        Customer c = new Customer("customer2", "654321");
        System.out.println(c.getType() + " " + c.saveString());
        Manager m = new Manager("manager1", "654321");
        System.out.println(m.getType() + " " + m.saveString());

        // query
        c = customerDao.queryByName("customer1");
        System.out.println(c.getType() + " " + c.saveString());

        c = customerDao.queryByName("customer2");
        System.out.println(c.getType() + " " + c.saveString());

        m = managerDao.queryByName("manager1");
        System.out.println(m.getType() + " " + m.saveString());

        // delete
        customerDao.deleteFromDatabase(c);
        managerDao.deleteFromDatabase(m);
    }

    public void testStock() {
        System.out.println("---------- Stock -----------");

        // create
        Stock s = new Stock("google", 1.5);
        System.out.println(s.saveString());

        // query
        s = stockDao.queryByName("apple");
        System.out.println(s.saveString());

        s = stockDao.queryByName("google");
        System.out.println(s.saveString());

        // delete
        stockDao.deleteFromDatabase(s);
    }

    public void testCurrency() {
        System.out.println("---------- Currency -----------");

        CNY cny = new CNY(10);
        System.out.println(cny.getExchangeRate2CNY() + " " + cny.getExchangeRate2HKD() + " " + cny.getExchangeRate2USD());
        USD usd = new USD(10);
        System.out.println(usd.getExchangeRate2CNY() + " " + usd.getExchangeRate2HKD() + " " + usd.getExchangeRate2USD());
        HKD hkd = new HKD(10);
        System.out.println(hkd.getExchangeRate2CNY() + " " + hkd.getExchangeRate2HKD() + " " + hkd.getExchangeRate2USD());
    }

    public void testAccount() {
        System.out.println("---------- Account -----------");

        SavingAccount account = new SavingAccount("1");
        System.out.println(account.saveString());

        account.deposit(10, "cny");
        account.deposit(20, "usd");
        account.deposit(30, "hkd");

        SavingAccount queryAccount = account.getDao().queryById("1");
        System.out.println(queryAccount.saveString());

        account.withdraw(5, "cny");
        queryAccount = account.getDao().queryById("1");
        System.out.println(queryAccount.saveString());

        account.getDao().deleteFromDatabase(account);
    }

    public void testTimer() {
        new Thread(timer).start();  // print current date and time stamp ervery 1 sec
    }

    public void testAll() {
        testUser();
        testStock();
        testCurrency();
        testTimer();
        testAccount();
    }
}
