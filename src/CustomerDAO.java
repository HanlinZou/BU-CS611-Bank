import java.util.List;
import java.util.ArrayList;
import java.io.File;

/**
 * CustomerDAO class use Data Access Object Pattern to provide a data accessing
 * API for saving and loading customer data.
 *
 * It also uses Singleton Pattern to ensure it has only one instance.
 */
public final class CustomerDAO extends UserDAO {
    private static CustomerDAO customerDao = new CustomerDAO();

    private CustomerDAO() {
        setFileProxy(new DatabaseFileProxy(path + "customers.csv"));
        userList = readFromDatabase();
        maxID = recoverMaxIdFromDatabase();
    }

    public static CustomerDAO getInstance() {
        return customerDao;
    }

    @Override
    public List<User> readFromDatabase() {
        File file = fileProxy.getFile();
        List<List<String>> lines = FileIo.readDatabaseFile(file);
        List<User> customerList = new ArrayList<>();

        for (int i = 1; i < lines.size(); i++) {  // skip table title
            List<String> info = lines.get(i);
            customerList.add(new Customer(info.get(0), info.get(1), info.get(2)));
        }

        return customerList;
    }
}
