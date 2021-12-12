import java.util.List;

public abstract class AccountDao<T extends Account> extends Dao<T> {
    @Override
    public String getTableTitle() {
        return "id userid cny usd hkd";
    }

    public T queryByUserId(String userId) {
        List<T> accountList = getList();
        for (T obj : accountList) {
            if (obj.getUserId().equals(userId)) return obj;
        }
        return null;
    }
}
