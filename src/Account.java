public abstract class Account extends BankObject {
    private String type;
    private String userId;

    /**
     * Loads an account from database (with ID).
     *
     * @param type Account type.
     * @param id Stock id.
     * @param userId ID of the user this account belongs to.
     */
    public Account(String type, String id, String userId) {
        super(id, userId + "-" + id);
        setType(type);
        setUserId(userId);
    }

    /**
     * Creates a new account (no ID).
     *
     * @param type Account type.
     * @param userId ID of the user this account belongs to.
     */
    public Account(String type, String userId) {
        super();
        setType(type);
        setUserId(userId);
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserId() {
        return userId;
    }

    public abstract AccountDao<?> getDao();

    /**
     * Withdraws money from an account.
     *
     * @param money Money to be saved.
     * @param currencyType Currency type.
     */
    public abstract boolean withdraw(double money, String currencyType);

    /**
     * Deposits money into an account.
     *
     * @param money Money to be saved.
     * @param currencyType Currency type.
     */
    public abstract boolean deposit(double money, String currencyType);
}
