/**
 * 测试样例，提交的时候可以删掉
 */
public final class Test {
    private CustomerDao customerDao = CustomerDao.getInstance();
    private ManagerDao managerDao = ManagerDao.getInstance();
    BankTimer timer = BankTimer.getInstance();

    public Test() {
    }

    public void testCreateCustomer() {
        Customer c = new Customer("customer2", "123456");
        System.out.println(c.getID() + " " + c.getName() + " " + c.getType() + " " + c.getPassword());
    }

    public void testQueryCustomer() {
        Customer c = customerDao.query("customer2");
        System.out.println(c.getID() + " " + c.getName() + " " + c.getType() + " " + c.getPassword());
    }

    public void testCreateManager() {
        Manager m = new Manager("manager1", "654321");
        System.out.println(m.getID() + " " + m.getName() + " " + m.getType() + " " + m.getPassword());
    }

    public void testQueryManager() {
        Manager m = managerDao.query("manager1");
        System.out.println(m.getID() + " " + m.getName() + " " + m.getType() + " " + m.getPassword());
    }

    public void testTimer() {
        new Thread(timer).start();  // timer 会每隔一秒输出当前时间和时间戳
    }

    public void testAll() {
        testCreateCustomer();
        testQueryCustomer();
        testCreateManager();
        testQueryManager();
        testTimer();
    }
}
