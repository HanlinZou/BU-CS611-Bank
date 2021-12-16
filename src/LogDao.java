import java.util.List;
import java.util.ArrayList;
import java.io.File;

/**
 * LogDao class use Data Access Object Pattern to provide a data accessing
 * API for saving and loading log data.
 *
 * It also uses Singleton Pattern to ensure it has only one instance.
 */

public final class LogDao extends Dao<Log> {
    private static LogDao logDao = new LogDao();
    private BankTimer timer = BankTimer.getInstance();

    private LogDao() {
        setFileProxy(new DatabaseFileProxy(path + "logs.csv"));
        setList(readFromDatabase());
        setMaxId(recoverMaxIdFromDatabase());
    }

    public static LogDao getInstance() {
        return logDao;
    }

    @Override
    public List<Log> readFromDatabase() {
        File file = fileProxy.getFile();
        List<List<String>> lines = FileIo.readDatabaseFile(file);
        List<Log> logList = new ArrayList<>();

        for (int i = 1; i < lines.size(); i++) {  // skip table title
            List<String> info = lines.get(i);
            logList.add(new Log(info.get(0).trim(), info.get(1).trim(), info.get(2).trim(), info.get(3).trim(), info.get(4).trim()));
        }

        return logList;
    }

    /**
     * Given an user ID and type, returns all logs related to it.
     *
     * @param userType User type.
     * @param userId User ID.
     *
     * @return A list of Log objects.
     */
    public List<Log> queryByUserTypeAndId(String userType, String userId) {
        List<Log> allLogList = getList();
        List<Log> logList = new ArrayList<>();

        for (Log log : allLogList) {
            if (log.getUserId().equals(userId) && log.getLogType().equals(userType)) logList.add(log);
        }

        return logList;
    }

    /**
     * Given an user ID and type, returns all logs related to it.
     *
     * @param userId User ID.
     * @param accountType Account type.
     *
     * @return A list of Log objects.
     */
    public List<Log> queryByAccount(String userId, String accountType) {
        List<Log> allLogList = getList();
        List<Log> logList = new ArrayList<>();

        for (Log log : allLogList) {
            if (log.getUserId().equals(userId) && log.getLogType().equals("customer")) {
                String[] parsedLog = log.getLog().split(":");
                if (parsedLog.length > 0 && parsedLog[0].equalsIgnoreCase(accountType)) logList.add(log);
            }
        }

        return logList;
    }

    /**
     * Returns all logs generated in today.
     *
     * @return A list of Log objects.
     */
    public List<Log> queryToday() {
        String today = timer.getDateStr();
        List<Log> allLogList = getList();
        List<Log> logList = new ArrayList<>();

        for (Log log : allLogList) {
            if (log.getTime().substring(0, 10).equals(today)) logList.add(log);
        }

        return logList;
    }

    @Override
    public String getTableTitle() {
        return "type,id,userId,time,log";
    }
}
