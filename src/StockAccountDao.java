import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.io.File;

public class StockAccountDao extends AccountDao<StockAccount> {
    private static StockAccountDao stockAccountDao = new StockAccountDao();
    private StockDao stockDao = StockDao.getInstance();

    private StockAccountDao() {
        setFileProxy(new DatabaseFileProxy(path + "stock_accounts.csv"));
        setList(readFromDatabase());
        setMaxId(recoverMaxIdFromDatabase());
    }

    public static StockAccountDao getInstance() {
        return stockAccountDao;
    }

    @Override
    public String getTableTitle() {
        return "id,userid,balance,stockinfo";
    }

    @Override
    public List<StockAccount> readFromDatabase() {
        File file = fileProxy.getFile();
        List<List<String>> lines = FileIo.readDatabaseFile(file);
        List<StockAccount> accountList = new ArrayList<>();

        for (int i = 1; i < lines.size(); i++) {  // skip table title
            Map<Stock, Double> stock2share = new HashMap<>();
            Map<Stock, Double> stock2money = new HashMap<>();

            List<String> info = lines.get(i);

            String id = info.get(0).trim();
            String userId = info.get(1).trim();
            double balance = Double.parseDouble(info.get(2).trim());
            double accumulatedProfit = Double.parseDouble(info.get(3).trim());

            // handle stock info
            if (info.size() > 4) {
                String stockInfo = info.get(4).trim();

                String[] stockList = stockInfo.split(";");
                for (String stockString : stockList) {
                    String stockId = stockString.split(":")[0];
                    double share = Integer.parseInt(stockString.split(":")[1].split("\\$")[0]);
                    double money = Double.parseDouble(stockString.split(":")[1].split("\\$")[1]);

                    Stock stock = stockDao.queryById(stockId);
                    stock2share.put(stock, share);
                    stock2money.put(stock, money);
                }
            }

            accountList.add(new StockAccount(id, userId, balance, accumulatedProfit, stock2share, stock2money));
        }

        return accountList;
    }
}
