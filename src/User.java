public abstract class User {
    private String name;
    private String password;
    private String type;
    private String id;

    public User() {
    }

    public User(String type, String name, String password) {
        setType(type);
        setName(name);
        setPassword(password);
    }

    public User(String type, String id, String name, String password) {
        setType(type);
        setName(name);
        setPassword(password);
        setID(id);
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
     * Sets ID for this user.
     *
     * @param id User's ID.
     */
    public void setID(String id){
        this.id = id;
    }

    /**
     * Returns ID of the user.
     *
     * @return User's ID.
     */
    public String getID(){
        return id;
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
     * Returns this user's name.
     *
     * @return User's name.
     */
    public String getName(){
        return name;
    }

    /**
     * Sets name for this user.
     *
     * @param name User's name.
     */
    public void setName(String name){
        this.name = name;
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
        return type + " user: name='" + name + "', password='" + password;
    }

    public String saveString() {
        return id + " " + name + " " + password;
    }
}
