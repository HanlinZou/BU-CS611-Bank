public final class Log extends BankObject {
    private LogDao logDao = LogDao.getInstance();
    private String time;
    private String log;
    private String userId;

    /**
     * Loads a log from database (with ID).
     *
     * @param id Log id.
     * @param time Log time.
     * @param log Log text.
     */
    public Log(String id, String userId, String time, String log) {
        super(id, id + "-" + time);
        setUserId(userId);
        setTime(time);
        setLog(log);
    }

    /**
     * Creates a new stock, generates a new ID and add it to database.
     *
     * @param time Log time.
     * @param log Log text.
     */
    public Log(String userId, String time, String log) {
        super();
        setUserId(userId);
        setTime(time);
        setLog(log);
        setID(logDao.getNewId());  // generates a new id
        setName(getID() + "-" + time);
        logDao.addToDatabase(this);  // add to database
    }

    /**
     * Sets log time.
     *
     * @param time Log time.
     */
    public void setTime(String time) {
        this.time = time;
    }

    /**
     * Returns log time.
     *
     * @return Log time.
     */
    public String getTime() {
        return time;
    }

    /**
     * Sets log text.
     *
     * @param log Log text.
     */
    public void setLog(String log) {
        this.log = log;
    }

    /**
     * Returns log text.
     *
     * @return Log text.
     */
    public String getLog() {
        return log;
    }

    /**
     * Sets user ID.
     *
     * @param userId User ID.
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }

    /**
     * Returns user ID.
     *
     * @return User ID.
     */
    public String getUserId() {
        return userId;
    }

    @Override
    public String saveString() {
        return getID() + "," + userId + "," + time + "," + log;
    }
}
