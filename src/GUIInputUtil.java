import javax.swing.JOptionPane;

public class GUIInputUtil {
	
	private final String[] currencyType = {"usd","cny","hkd"};
	private static GUIInputUtil inputUtil;
	private String[] stockLis;
	private GUIInputUtil()
	{
		
	}
	
	public static GUIInputUtil getInstance()
	{
		if(inputUtil == null)
		{
			inputUtil = new GUIInputUtil();
		}
		return inputUtil;
	}
	
	public String currencySelect(String message)
	{
		return (String)JOptionPane.showInputDialog(null,message,"Currency Selection", JOptionPane.QUESTION_MESSAGE, null, this.currencyType,this.currencyType[0]);
	}
	public String currencySelect()
	{
		return this.currencySelect("Select a currency");
	}
	public double moneyAmount(String message)
	{
		String input = JOptionPane.showInputDialog(null,message);
		if(input!=null&&!input.equals(""))
		{
			try
			{
				double amount = Double.parseDouble(input);
				if(amount > 0)
				{
					return amount;
				}
				JOptionPane.showMessageDialog(null, "Please enter a valid number","Warning",JOptionPane.WARNING_MESSAGE);
				return 0;
			}
			catch(NumberFormatException e)
			{
				JOptionPane.showMessageDialog(null, "Please enter a valid number","Warning",JOptionPane.WARNING_MESSAGE);
			}
		}
		return 0;
	}
	
	public String stockSelect(String message)
	{
		String[] stockList = new String[StockDao.getInstance().getList().size()];
		for(int i = 0; i < stockList.length; i++)
		{
			stockList[i] = StockDao.getInstance().getList().get(i).getName() + " : " + StockDao.getInstance().getList().get(i).getPrice() + "/Share";
		}
		String result = (String) JOptionPane.showInputDialog(null,"Please select a stock to purchase","Stock",JOptionPane.QUESTION_MESSAGE,null,stockList,stockList[0]);
		int index = -1;
		if(result != null)
		{
			for(String sl:stockList)
			{
				index += 1;
				if(result.equals(sl))
				{
					return StockDao.getInstance().getList().get(index).getName();
				}
			}
			return null;
		}
		return null;
	}
	
	public String customerStockSelect(String message, String uid)
	{
		String[] stockList = new String[CustomerDao.getInstance().queryById(uid).getStockAccount().getStock2Share().size()];
		
		int i = 0;
		for(Stock stock:CustomerDao.getInstance().queryById(uid).getStockAccount().getStock2Share().keySet())
		{
			stockList[i] = stock.getName();
			i++;
		}
		String result = (String) JOptionPane.showInputDialog(null,message,"Stock",JOptionPane.QUESTION_MESSAGE,null,stockList,stockList[0]);
		if(result == null || result.equals(""))
		{
			return null;
		}
		return result;
	}

}
