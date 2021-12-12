import java.util.List;
import java.util.ArrayList;
import java.io.File;

public final class CheckingAccountDao extends AccountDao<CheckingAccount> {
    private static CheckingAccountDao checkingAccountDao = new CheckingAccountDao();

    private CheckingAccountDao() {
        setFileProxy(new DatabaseFileProxy(path + "checking_accounts.csv"));
        setList(readFromDatabase());
        setMaxId(recoverMaxIdFromDatabase());
    }

    public static CheckingAccountDao getInstance() {
        return checkingAccountDao;
    }

    @Override
    public List<CheckingAccount> readFromDatabase() {
        File file = fileProxy.getFile();
        List<List<String>> lines = FileIo.readDatabaseFile(file);
        List<CheckingAccount> accountList = new ArrayList<>();

        for (int i = 1; i < lines.size(); i++) {  // skip table title
            List<String> info = lines.get(i);

            String id = info.get(0).trim();
            String userId = info.get(1).trim();
            double cny = Double.parseDouble(info.get(2).trim());
            double usd = Double.parseDouble(info.get(3).trim());
            double hkd = Double.parseDouble(info.get(4).trim());

            accountList.add(new CheckingAccount(id, userId, cny, usd, hkd));
        }

        return accountList;
    }
}
