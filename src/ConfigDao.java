import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.io.File;

public final class ConfigDao {
    private String path = System.getProperty("user.dir") + "/src/configs/";
    private DatabaseFileProxy fileProxy = new DatabaseFileProxy(path + "config.csv");
    private static Map<String, String> configMap;
    private static ConfigDao configDao = new ConfigDao();

    private ConfigDao() {
        configMap = readFromDatabase();
    }

    public static ConfigDao getInstance() {
        return configDao;
    }

    public Map<String, String> readFromDatabase() {
        File file = fileProxy.getFile();
        List<List<String>> lines = FileIo.readDatabaseFile(file);
        Map<String, String> map = new HashMap<>();

        for (List<String> info : lines) {
            map.put(info.get(0).trim(), info.get(1).trim());
        }

        return map;
    }

    public String getConfig(String key, String defaultValue) {
        return configMap.get(key) == null ? defaultValue: configMap.get(key);
    }

    public double getConfigDouble(String key, double defaultValue) {
        return configMap.get(key) == null ? defaultValue: Double.parseDouble(configMap.get(key));
    }

    public int getConfigInt(String key, int defaultValue) {
        return (int) getConfigDouble(key, Double.valueOf(defaultValue));
    }
}
