public abstract class User extends BankObject {
    private String password;
    private String type;

    public User() {
    }

    public User(String type, String name, String password) {
        super(name);
        setType(type);
        setPassword(password);
    }

    public User(String type, String id, String name, String password) {
        super(id, name);
        setType(type);
        setPassword(password);
    }

    /**
     * Returns type of the user (customer / manager).
     *
     * @return User's type.
     */
    public String getType(){
        return type;
    }

    /**
     * Sets type for this user.
     *
     * @param name User's name.
     */
    public void setType(String type){
        this.type = type;
    }

    /**
     * Returns this user's password.
     *
     * @return User's password.
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets password for this user.
     *
     * @param password User's password.
     */
    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return type + " user: id='" + getID() + "', name='" + getName() + "', password='" + password + "'";
    }

    public String displayString() {
        return type + " user: id='" + getID() + "', name='" + getName() + "'";
    }

    @Override
    public String saveString() {
        return getID() + "," + getName() + "," + password;
    }
}
