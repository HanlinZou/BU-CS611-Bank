import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.io.File;

/**
 * CustomerDao class use Data Access Object Pattern to provide a data accessing
 * API for saving and loading customer data.
 *
 * It also uses Singleton Pattern to ensure it has only one instance.
 */
public final class CustomerDao extends UserDao<Customer> {
    private static CustomerDao customerDao = new CustomerDao();
    private LoanDao loanDao = LoanDao.getInstance();

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
            Map<Loan, String> loan2collateral = new HashMap<>();

            String id = info.get(0).trim();
            String name = info.get(1).trim();
            String password = info.get(2).trim();

            // handle loan info
            if (info.size() > 3) {
                String loanInfo = info.get(3).trim();
                String[] parsedLoanInfo = loanInfo.split(";");

                for (String loanString : parsedLoanInfo) {
                    String loanId = loanString.split(":")[0];
                    String collateral = loanString.split(":")[1];

                    Loan loan = loanDao.queryById(loanId);

                    loan2collateral.put(loan, collateral);
                }
            }

            customerList.add(new Customer(id, name, password, loan2collateral));
        }

        return customerList;
    }

    @Override
    public String getTableTitle() {
        return "id,name,password,loans";
    }
}
