import java.util.List;
import java.util.ArrayList;
import java.io.File;

/**
 * Dao class is a super class for all BankObject DAO classes.
 *
 * BankObject DAO classes use Data Access Object Pattern to provide a data
 * accessing API for saving and loading bank object data.
 */

public abstract class Dao<T extends BankObject> {
    protected String path = System.getProperty("user.dir") + "/src/database/";
    protected DatabaseFileProxy fileProxy;
    private int maxId = 0;
    private List<T> objectList;

    public abstract List<T> readFromDatabase();
    public abstract String getTableTitle();

    protected int recoverMaxIdFromDatabase() {
        int id = 0;
        for (T obj : objectList) {
            int curID = Integer.parseInt(obj.getID());
            if (curID > id) id = curID;
        }
        return id;
    }

    protected void setFileProxy(DatabaseFileProxy fileProxy) {
        this.fileProxy = fileProxy;
    }

    public String getNewId() {
        maxId++;
        return String.valueOf(maxId);
    }

    public void setMaxId(int maxId) {
        this.maxId = maxId;
    }

    public List<T> getList() {
        return objectList;
    }

    public void setList(List<T> objectList) {
        this.objectList = objectList;
    }

    public T queryByName(String name) {
        for (T obj : objectList) {
            if (obj.getName() != null && obj.getName().equals(name)) return obj;
        }
        return null;
    }

    public T queryById(String id) {
        for (T obj : objectList) {
            if (obj.getID().equals(id)) return obj;
        }
        return null;
    }

    /**
     * Saves the current object list to database, will re-write the old one.
     */
    public void saveToDatabase() {
        File file = fileProxy.getFile();

        List<String> infoList = new ArrayList<>();
        for (T obj : objectList) infoList.add(obj.saveString());

        FileIo.writeDatabaseFile(file, getTableTitle(), infoList);
    }

    public boolean addToDatabase(T object) {
        if (queryByName(object.getName()) != null) return false;  // avoid duplicate name
        objectList.add(object);  // update object list
        saveToDatabase();  // save to database
        return true;
    }

    /**
     * Deletes the given object from the database.
     *
     * @param object An object that will be deleted.
     */
    public void deleteFromDatabase(T object) {
        if (object != null) {
            objectList.remove(object);   // update object list
            saveToDatabase();  // save to database
        }
    }

    /**
     * Deletes the object with the given ID from the database.
     *
     * @param id ID of the object that will be deleted.
     */
    public void deleteFromDatabaseById(String id) {
        T object = queryById(id);
        deleteFromDatabase(object);
    }
}
