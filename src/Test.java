/**
 * 测试样例，提交的时候可以删掉
 */

public final class Test {
    private CustomerDao customerDao = CustomerDao.getInstance();
    private ManagerDao managerDao = ManagerDao.getInstance();
    private StockDao stockDao = StockDao.getInstance();
    private LogDao logDao = LogDao.getInstance();
    BankTimer timer = BankTimer.getInstance();

    public Test() {
    }

    public void testUser() {
        System.out.println("======== User ========");

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
        System.out.println("======== Stock ========");

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
        System.out.println("======== Currency ========");

        CNY cny = new CNY(10);
        System.out.println(cny.getExchangeRate2CNY() + " " + cny.getExchangeRate2HKD() + " " + cny.getExchangeRate2USD());
        USD usd = new USD(10);
        System.out.println(usd.getExchangeRate2CNY() + " " + usd.getExchangeRate2HKD() + " " + usd.getExchangeRate2USD());
        HKD hkd = new HKD(10);
        System.out.println(hkd.getExchangeRate2CNY() + " " + hkd.getExchangeRate2HKD() + " " + hkd.getExchangeRate2USD());
    }

    public void testAccount() {
        System.out.println("======== Account ========");

        System.out.println("--- Saving / Checking ---");

        SavingAccount account = new SavingAccount("1");
        System.out.println(account.saveString());

        account.deposit(10, "cny");
        account.deposit(20, "usd");
        account.deposit(30, "hkd");

        SavingAccount queryAccount = account.getDao().queryByUserId("1");
        System.out.println(account.saveString());

        account.withdraw(5, "cny");
        queryAccount = account.getDao().queryByUserId("1");
        System.out.println(queryAccount.saveString());

        account.getDao().deleteFromDatabase(account);

        System.out.println("-------- Stock --------");

        System.out.println("create account:");
        StockAccount stockAccount = new StockAccount("1", 5000.0);
        System.out.println(stockAccount.saveString());

        System.out.println("deposit:");
        stockAccount.deposit(1000, "usd");
        System.out.println(stockAccount.saveString());
        stockAccount.deposit(1000, "cny");
        System.out.println(stockAccount.saveString());

        System.out.println("query:");
        StockAccount queryStockAccount = stockAccount.getDao().queryByUserId("1");
        System.out.println(queryStockAccount.saveString());

        System.out.println("withdraw:");
        stockAccount.withdraw(1000);
        queryStockAccount = stockAccount.getDao().queryByUserId("1");
        System.out.println(queryStockAccount.saveString());

        System.out.println("buy stock:");
        stockAccount.buyStock("1", 100);
        queryStockAccount = stockAccount.getDao().queryByUserId("1");
        System.out.println(queryStockAccount.saveString());

        System.out.println("stock price increases:");
        Stock stock = stockDao.queryById("1");
        double oldPrice = stock.getPrice();
        stock.setPrice(80.2);

        System.out.println("sell stock:");
        stockAccount.sellStock("1", 50.5);
        queryStockAccount = stockAccount.getDao().queryByUserId("1");
        System.out.println(queryStockAccount.saveString());

        stock.setPrice(oldPrice);
        stockAccount.getDao().deleteFromDatabase(stockAccount);
    }

    public void testCustomer() {
        System.out.println("======== Customer ========");

        Customer customer = new Customer("test2", "827489");

        System.out.println("open checking account:");
        customer.openCheckingAccount();
        System.out.println(customer.accountInquiry());

        System.out.println("open saving account:");
        customer.openSavingAccount();
        System.out.println(customer.accountInquiry());

        System.out.println("deposit to accounts:");
        customer.getSavingAccount().deposit(20000, "usd");
        customer.getCheckingAccount().deposit(10000, "usd");
        System.out.println(customer.accountInquiry());

        System.out.println("transfer saving -> checking:");
        customer.transfer(1, 500, "usd");
        System.out.println(customer.accountInquiry());

        System.out.println("open stock account:");
        customer.openStockAccount(5000);
        System.out.println(customer.accountInquiry());

        System.out.println("transfer saving -> stock:");
        customer.depositStockAccount(500);
        System.out.println(customer.accountInquiry());

        System.out.println("close accounts:");
        customer.closeCheckingAccount();
        customer.closeSavingAccount();

        customerDao.deleteFromDatabase(customer);
    }

    public void testTimer() {
        System.out.println("======== Timers ========");
        new Thread(timer).start();  // print current date and time stamp ervery 1 sec
    }

    public void testAll() {
        testUser();
        testStock();
        testCurrency();
        testTimer();
        testAccount();
        testCustomer();
    }
}
