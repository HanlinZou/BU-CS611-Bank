public final class Stock extends BankObject {
    private double price;
    private StockDao stockDao = StockDao.getInstance();

    /**
     * Loads a stock from database (with ID).
     *
     * @param id Stock id.
     * @param name Stock name.
     * @param price Stock price.
     */
    public Stock(String id, String name, double price) {
        super(id, name);
        setPrice(price);
    }

    /**
     * Creates a new stock, generates a new ID and add it to database.
     *
     * @param name Stock name.
     * @param price Stock price.
     */
    public Stock(String name, double price) {
        super(name);
        setPrice(price);
        setID(stockDao.getNewId());  // generates a new id
        stockDao.addToDatabase(this);  // add to database
    }

    /**
     * Sets price for this stock.
     *
     * @param price Stock's price.
     */
    public void setPrice(double price){
        this.price = price;
    }

    /**
     * Returns price of the stock.
     *
     * @return Stock's price.
     */
    public double getPrice(){
        return price;
    }

    @Override
    public String toString() {
        return "Stock: id='" + getID() + "', name='" + getName() + "', price='" + price + "'";
    }

    @Override
    public String saveString() {
        return getID() + "," + getName() + "," + price;
    }
}
