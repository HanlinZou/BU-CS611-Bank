import java.io.File;

/**
 * We use Proxy Pattern to handle the real DatabaseFile object for avoiding
 * creating File instance duplicately.
 */
public final class DatabaseFileProxy implements FileInterface {
    private String path;
    private DatabaseFile file;

    public DatabaseFileProxy(String path) {
        setPath(path);
    }

    @Override
    public File getFile() {
        if (file == null) file = new DatabaseFile(path);
        return file.getFile();
    }

    /**
     * Returns path of the file.
     *
     * @return File's path.
     */
    public String getPath(){
        return path;
    }

    /**
     * Sets path for the file.
     *
     * @param path File's path.
     */
    public void setPath(String path){
        this.path = path;
    }
}
