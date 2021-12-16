import java.util.List;
import java.util.ArrayList;
import java.io.File;

/**
 * LoanDao class use Data Access Object Pattern to provide a data accessing
 * API for saving and loading loan data.
 *
 * It also uses Singleton Pattern to ensure it has only one instance.
 */

public final class LoanDao extends Dao<Loan> {
    private static LoanDao loanDao = new LoanDao();

    private LoanDao() {
        setFileProxy(new DatabaseFileProxy(path + "loans.csv"));
        setList(readFromDatabase());
        setMaxId(recoverMaxIdFromDatabase());
    }

    public static LoanDao getInstance() {
        return loanDao;
    }

    @Override
    public List<Loan> readFromDatabase() {
        File file = fileProxy.getFile();
        List<List<String>> lines = FileIo.readDatabaseFile(file);
        List<Loan> loanList = new ArrayList<>();

        for (int i = 1; i < lines.size(); i++) {  // skip table title
            List<String> info = lines.get(i);
            loanList.add(new Loan(info.get(0).trim(), info.get(1).trim(), Double.valueOf(info.get(2).trim()), Double.valueOf(info.get(3).trim())));
        }

        return loanList;
    }

    @Override
    public String getTableTitle() {
        return "id,name,interest rate,value";
    }
}
