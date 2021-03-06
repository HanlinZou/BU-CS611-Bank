import java.util.List;

public final class Manager extends User {
    private ManagerDao managerDao = ManagerDao.getInstance();
    private CustomerDao customerDao = CustomerDao.getInstance();
    private LogDao logDao = LogDao.getInstance();
    private StockDao stockDao = StockDao.getInstance();
    private LoanDao loanDao = LoanDao.getInstance();

    private BankTimer timer = BankTimer.getInstance();

    public Manager() {
    }

    /**
     * Creates a new manager and add it to database.
     *
     * @param name Manager name.
     * @param password Manager password.
     */
    public Manager(String name, String password) {
        super("manager", name, password);
        setID(managerDao.getNewId());  // generates a new id
        managerDao.addToDatabase(this);  // add to database
    }

    /**
     * Loads a manager from database.
     *
     * @param id Manager ID.
     * @param name Manager name.
     * @param password Manager password.
     */
    public Manager(String id, String name, String password) {
        super("manager", id, name, password);
    }

    /**
     * Returns info of a specific customer by the given ID.
     *
     * @param uid Customer ID.
     */
    public String getCustomerInfoById(String uid) {
        Customer customer = customerDao.queryById(uid);
        return customer.displayString();
    }

    /**
     * Returns info of a specific customer by the given name.
     *
     * @param name Customer name.
     */
    public String getCustomerInfoByName(String name) {
        Customer customer = customerDao.queryByName(name);
        return customer.displayString();
    }

    /**
     * Returns daily report (all logs generated in today).
     *
     * @return A list of Log objects.
     */
    public List<Log> getDailyReport() {
        return logDao.queryToday();
    }

    /**
     * Adds a new stock.
     *
     * @param name Stock name.
     * @param price Stock price.
     *
     * @return true: success / false: failed
     */
    public boolean addStock(String name, double price) {
        if (stockDao.queryByName(name) != null) return false;  // dublicate name
        if (price < 0 || price > 10000) return false;  // invalid price
        Stock stock = new Stock(name, price);
        new Log("manager", getID(), timer.getTimeStr(), "Add a new stock " + name + " (id: " + stock.getID() + "; price: " + price + ").");  // log
        return true;
    }

    /**
     * Adjusts a stock's price.
     *
     * @param stock A stock object.
     * @param price Stock price.
     *
     * @return true: success / false: failed
     */
    public boolean adjustStockPrice(Stock stock, double price) {
        if (stock == null) return false;  // stock isn't exist
        if (price < 0 || price > 10000) return false;  // invalid price
        new Log("manager", getID(), timer.getTimeStr(), "Stock " + stock.getName() + " (id: " + stock.getID() + ")'s price: " + stock.getPrice() + " -> " + price + ".");  // log
        stock.setPrice(price);
        stockDao.saveToDatabase();  // update database
        return true;
    }

    /**
     * Adjusts a stock's price by name.
     *
     * @param name Stock name.
     * @param price Stock price.
     *
     * @return true: success / false: failed
     */
    public boolean adjustStockPriceByName(String name, double price) {
        return adjustStockPrice(stockDao.queryByName(name), price);
    }

    /**
     * Adjusts a stock's price by id.
     *
     * @param id Stock id.
     * @param price Stock price.
     *
     * @return true: success / false: failed
     */
    public boolean adjustStockPriceById(String id, double price) {
        return adjustStockPrice(stockDao.queryById(id), price);
    }

    /**
     * Adds a new loan.
     *
     * @param name Loan name.
     * @param interest Loan interest rate.
     * @param value Loan value.
     *
     * @return true: success / false: failed
     */
    public boolean addLoan(String name, double interest, double value) {
        if (loanDao.queryByName(name) != null) return false;  // duplicate name
        if (interest < 0 || interest > 1) return false;  // invalid interest
        if (value < 0 || value > 100000000) return false;  // invalid value
        Loan loan = new Loan(name, interest, value);
        new Log("manager", getID(), timer.getTimeStr(), "Add a new loan " + name + " (id: " + loan.getID() + "; interest rate: " + loan.getInterest() + "; value: " + loan.getValue() + ").");  // log
        return true;
    }

    /**
     * Adjusts a loan's interest rate.
     *
     * @param name Loan name.
     * @param interest Loan's interest rate.
     *
     * @return true: success / false: failed
     */
    public boolean adjustLoanInterest(String name, double interest) {
        Loan loan = loanDao.queryByName(name);  // loan isn't exist
        if (loan == null) return false;
        if (interest < 0 || interest > 1) return false;  // invalid interest
        new Log("manager", getID(), timer.getTimeStr(), "Loan " + name + " (id: " + loan.getID() + ")'s interest: " + loan.getInterest() + " -> " + interest + ".");  // log
        loan.setInterest(interest);
        loanDao.saveToDatabase();  // update database
        return true;
    }

    /**
     * Adjusts a loan's value.
     *
     * @param name Loan name.
     * @param value Loan's value.
     *
     * @return true: success / false: failed
     */
    public boolean adjustLoanValue(String name, double value) {
        Loan loan = loanDao.queryByName(name);  // loan isn't exist
        if (loan == null) return false;
        if (value < 0 || value > 100000000) return false;  // invalid value
        new Log("manager", getID(), timer.getTimeStr(), "Loan " + name + " (id: " + loan.getID() + ")'s value: " + loan.getValue() + " -> " + value + ".");  // log
        loan.setValue(value);
        loanDao.saveToDatabase();  // update database
        return true;
    }

    /**
     * Adjusts a loan's value and interest.
     *
     * @param name Loan name.
     * @param value Loan's value.
     *
     * @return true: success / false: failed
     */
    public boolean adjustLoan(String name, double value, double interest) {
        Loan loan = loanDao.queryByName(name);

        if (loan == null) return false;  // loan isn't exist
        if (value < 0 || value > 100000000) return false;  // invalid value
        if (interest < 0 || interest > 1) return false;  // invalid interest

        new Log("manager", getID(), timer.getTimeStr(), "Loan " + name + " (id: " + loan.getID() + ")'s value: " + loan.getValue() + " -> " + value + ".");  // log
        new Log("manager", getID(), timer.getTimeStr(), "Loan " + name + " (id: " + loan.getID() + ")'s interest: " + loan.getInterest() + " -> " + interest + ".");  // log

        loan.setValue(value);
        loan.setInterest(interest);
        loanDao.saveToDatabase();  // update database

        return true;
    }
}
