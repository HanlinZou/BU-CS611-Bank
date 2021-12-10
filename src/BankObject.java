public abstract class BankObject {
    private String id;
    private String name;

    public BankObject() {
    }

    /**
     * Loads a bank object from database (with ID).
     *
     * @param id ID.
     * @param name Name.
     */
    public BankObject(String id, String name) {
        setID(id);
        setName(name);
    }

    /**
     * Creates a new bank object and add it to database.
     *
     * @param name Name.
     */
    public BankObject(String name) {
        setName(name);
    }

    // public abstract

    /**
     * Returns information to save to database.
     */
    public abstract String saveString();

    /**
     * Sets ID for this object.
     *
     * @param id ID.
     */
    public void setID(String id){
        this.id = id;
    }

    /**
     * Returns ID of the object.
     *
     * @return ID.
     */
    public String getID(){
        return id;
    }

    /**
     * Returns this object's name.
     *
     * @return Name.
     */
    public String getName(){
        return name;
    }

    /**
     * Sets name for this object.
     *
     * @param name Name.
     */
    public void setName(String name){
        this.name = name;
    }
}
