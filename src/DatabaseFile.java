import java.io.File;
import java.io.IOException;

public final class DatabaseFile implements FileInterface {
    private String path;
    private File fileInstance;

    public DatabaseFile(String path) {
        setPath(path);
        createFile(path);
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

    /**
     * Creates a file on disk.
     *
     * @param path File's path.
     */
    private void createFile(String path) {
        fileInstance = new File(path);
        if(!fileInstance.exists()) {
            try {
                if (fileInstance.createNewFile()){
                    System.out.println("Created a file successfully.");
                } else {
                    System.out.println("Failed to create a file.");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Returns the file instance.
     *
     * @return File instance.
     */
    @Override
    public File getFile() {
        return fileInstance;
    }
}
