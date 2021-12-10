import java.util.List;
import java.util.ArrayList;
import java.io.File;

/**
 * ManagerDao class use Data Access Object Pattern to provide a data accessing
 * API for saving and loading manager data.
 *
 * It also uses Singleton Pattern to ensure it has only one instance.
 */
public class ManagerDao extends UserDao<Manager> {
    private static ManagerDao managerDao = new ManagerDao();

    private ManagerDao() {
        setFileProxy(new DatabaseFileProxy(path + "managers.csv"));
        setList(readFromDatabase());
        setMaxId(recoverMaxIdFromDatabase());
    }

    public static ManagerDao getInstance() {
        return managerDao;
    }

    @Override
    public List<Manager> readFromDatabase() {
        File file = fileProxy.getFile();
        List<List<String>> lines = FileIo.readDatabaseFile(file);
        List<Manager> managerList = new ArrayList<>();

        for (int i = 1; i < lines.size(); i++) {  // skip table title
            List<String> info = lines.get(i);
            managerList.add(new Manager(info.get(0).trim(), info.get(1).trim(), info.get(2).trim()));
        }

        return managerList;
    }
}
