public final class Loan extends BankObject {
    private double interest;
    private double value;
    private LoanDao loanDao = LoanDao.getInstance();

    /**
     * Loads a loan from database (with ID).
     *
     * @param id Loan id.
     * @param name Loan name.
     * @param interest Loan rate.
     * @param value Loan value.
     */
    public Loan(String id, String name, double interest, double value) {
        super(id, name);
        setInterest(interest);
        setValue(value);
    }

    /**
     * Creates a new loan, generates a new ID and add it to database.
     *
     * @param name Loan name.
     * @param interest Loan rate.
     * @param value Loan value.
     */
    Loan(String name, double interest, double value) {
        super(name);
        setInterest(interest);
        // setCollateral(collateral);
        setValue(value);
        setID(loanDao.getNewId());  // generates a new id
        loanDao.addToDatabase(this);  // add to database
    }

    public void setInterest(double interest){
        this.interest = interest;
    }

    public double getInterest(){
        return interest;
    }

    public void setValue(double amt){
        value = amt;
    }

    public double getValue(){
        return value;
    }

    @Override
    public String toString() {
        return "Loan: id='" + getID() + "', name='" + getName() + "', interest rate='" + interest + "', value='" + value + "'";
    }

    public String displayString() {
        return getName() + " (interest rate: " + interest + ", value: " + value + ")";
    }

    @Override
    public String saveString() {
        return getID() + "," + getName() + "," + interest + "," + value;
    }
}
