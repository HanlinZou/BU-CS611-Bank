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
            logList.add(new Log(info.get(0).trim(), info.get(1).trim(), info.get(2).trim(), info.get(3).trim()));
        }

        return logList;
    }

    /**
     * Given an user ID, returns all logs related to it.
     *
     * @param userId User ID.
     *
     * @return A list of Log objects.
     */
    public List<Log> queryByUserId(String userId) {
        List<Log> allLogList = getList();
        List<Log> logList = new ArrayList<>();

        for (Log log : allLogList) {
            if (log.getUserId().equals(userId)) logList.add(log);
        }

        return logList;
    }

    @Override
    public String getTableTitle() {
        return "id,userId,time,log";
    }
}
