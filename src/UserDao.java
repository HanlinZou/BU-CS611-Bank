/**
 * UserDao class is a super class for all user DAO classes.
 *
 * User DAO classes use Data Access Object Pattern to provide a data accessing
 * API for saving and loading user data.
 */
public abstract class UserDao<T extends User> extends Dao<T> {
    @Override
    public String getTableTitle() {
        return "id name password";
    }
}
