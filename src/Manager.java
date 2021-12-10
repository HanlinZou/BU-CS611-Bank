public final class Manager extends User {
    private ManagerDao managerDao = ManagerDao.getInstance();

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
}
