import java.util.List;
import java.util.ArrayList;
import java.io.File;

/**
 * CustomerDao class use Data Access Object Pattern to provide a data accessing
 * API for saving and loading customer data.
 *
 * It also uses Singleton Pattern to ensure it has only one instance.
 */
public final class CustomerDao extends UserDao<Customer> {
    private static CustomerDao customerDao = new CustomerDao();

    private CustomerDao() {
        setFileProxy(new DatabaseFileProxy(path + "customers.csv"));
        setList(readFromDatabase());
        setMaxId(recoverMaxIdFromDatabase());
    }

    public static CustomerDao getInstance() {
        return customerDao;
    }

    @Override
    public List<Customer> readFromDatabase() {
        File file = fileProxy.getFile();
        List<List<String>> lines = FileIo.readDatabaseFile(file);
        List<Customer> customerList = new ArrayList<>();

        for (int i = 1; i < lines.size(); i++) {  // skip table title
            List<String> info = lines.get(i);
            customerList.add(new Customer(info.get(0).trim(), info.get(1).trim(), info.get(2).trim()));
        }

        return customerList;
    }
}
