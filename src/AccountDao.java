import java.util.List;

/**
 * AccountDao class use Data Access Object Pattern to provide a data accessing
 * API for saving and loading account data.
 *
 * It also uses Singleton Pattern to ensure it has only one instance. It's the
 * super class of all instances of account related DAO class.
 */

public abstract class AccountDao<T extends Account> extends Dao<T> {
    @Override
    public String getTableTitle() {
        return "id,userid,cny,usd,hkd";
    }

    public T queryByUserId(String userId) {
        List<T> accountList = getList();
        for (T obj : accountList) {
            if (obj.getUserId().equals(userId)) return obj;
        }
        return null;
    }
}
