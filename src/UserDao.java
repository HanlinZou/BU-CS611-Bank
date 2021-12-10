import java.util.List;
import java.util.ArrayList;
import java.io.File;

/**
 * UserDao class is a super class for all user DAO classes.
 *
 * User DAO classes use Data Access Object Pattern to provide a data accessing
 * API for saving and loading user data.
 */
public abstract class UserDao<T extends User> {
    protected String path = System.getProperty("user.dir") + "/src/database/";
    protected int maxID = 0;
    protected List<T> userList;
    protected DatabaseFileProxy fileProxy;

    public abstract List<T> readFromDatabase();

    protected int recoverMaxIdFromDatabase() {
        int id = 0;
        for (T user : userList) {
            int curID = Integer.parseInt(user.getID());
            if (curID > id) id = curID;
        }
        return id;
    }

    protected void setFileProxy(DatabaseFileProxy fileProxy) {
        this.fileProxy = fileProxy;
    }

    public String getNewID() {
        maxID++;
        return String.valueOf(maxID);
    }

    public List<T> getList() {
        return userList;
    }

    public T query(String name) {
        for (T user : userList) {
            if (user.getName().equals(name)) return user;
        }
        return null;
    }

    public void addToDatabase(T user) {
        if (query(user.getName()) != null) return;  // avoid duplicate name

        userList.add(user);

        // save to database
        File file = fileProxy.getFile();
        String title = "id name password";

        List<String> infoList = new ArrayList<>();
        for (T u : userList) infoList.add(u.saveString());

        FileIo.writeDatabaseFile(file, title, infoList);
    }
}
