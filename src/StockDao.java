import java.util.List;
import java.util.ArrayList;
import java.io.File;

/**
 * StockDao class use Data Access Object Pattern to provide a data accessing
 * API for saving and loading stock data.
 *
 * It also uses Singleton Pattern to ensure it has only one instance.
 */

public final class StockDao extends Dao<Stock> {
    private static StockDao stockDao = new StockDao();

    private StockDao() {
        setFileProxy(new DatabaseFileProxy(path + "stocks.csv"));
        setList(readFromDatabase());
        setMaxId(recoverMaxIdFromDatabase());
    }

    public static StockDao getInstance() {
        return stockDao;
    }

    @Override
    public List<Stock> readFromDatabase() {
        File file = fileProxy.getFile();
        List<List<String>> lines = FileIo.readDatabaseFile(file);
        List<Stock> stockList = new ArrayList<>();

        for (int i = 1; i < lines.size(); i++) {  // skip table title
            List<String> info = lines.get(i);
            stockList.add(new Stock(info.get(0).trim(), info.get(1).trim(), Double.valueOf(info.get(2).trim())));
        }

        return stockList;
    }

    @Override
    public String getTableTitle() {
        return "id name price";
    }
}
