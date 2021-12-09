import java.util.List;
import java.util.ArrayList;
import java.io.File;

/**
 * ManagerDAO class use Data Access Object Pattern to provide a data accessing
 * API for saving and loading manager data.
 *
 * It also uses Singleton Pattern to ensure it has only one instance.
 */
public class ManagerDAO extends UserDAO {
    private static ManagerDAO managerDao = new ManagerDAO();

    private ManagerDAO() {
        setFileProxy();
        userList = readFromDatabase();
        maxID = recoverMaxIdFromDatabase();
    }

    public static ManagerDAO getInstance() {
        return managerDao;
    }

    public void setFileProxy() {
        fileProxy = new DatabaseFileProxy(path + "managers.csv");
    }

    @Override
    public List<User> readFromDatabase() {
        File file = fileProxy.getFile();
        List<List<String>> lines = FileIo.readDatabaseFile(file);
        List<User> managerList = new ArrayList<>();

        for (int i = 1; i < lines.size(); i++) {  // skip table title
            List<String> info = lines.get(i);
            managerList.add(new Manager(info.get(0), info.get(1), info.get(2)));
        }

        return managerList;
    }
}
