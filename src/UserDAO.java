import java.util.List;
import java.util.ArrayList;
import java.io.File;

/**
 * UserDAO class is a super class for all user DAO classes.
 *
 * User DAO classes use Data Access Object Pattern to provide a data accessing
 * API for saving and loading user data.
 */
public abstract class UserDAO {
    protected String path = System.getProperty("user.dir") + "/src/database/";
    protected DatabaseFileProxy fileProxy;
    protected int maxID = 0;
    protected List<User> userList;

    public abstract void setFileProxy();
    public abstract List<User> readFromDatabase();

    protected int recoverMaxIdFromDatabase() {
        int id = 0;
        for (User user : userList) {
            int curID = Integer.parseInt(user.getID());
            if (curID > id) id = curID;
        }
        return id;
    }

    public String getNewID() {
        maxID++;
        return String.valueOf(maxID);
    }

    public List<User> getList() {
        return userList;
    }

    public User query(String name) {
        for (User user : userList) {
            if (user.getName().equals(name)) return user;
        }
        return null;
    }

    public void addToDatabase(User user) {
        if (query(user.getName()) != null) return;  // avoid duplicate name

        userList.add(user);

        // save to database
        File file = fileProxy.getFile();
        String title = "id name password";

        List<String> infoList = new ArrayList<>();
        for (User u : userList) infoList.add(u.saveString());

        FileIo.writeDatabaseFile(file, title, infoList);
    }
}
