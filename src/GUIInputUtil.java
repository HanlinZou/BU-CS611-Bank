import javax.swing.JOptionPane;

public class GUIInputUtil {
	
	private final String[] currencyType = {"usd","cny","hkd"};
	private static GUIInputUtil inputUtil;
	
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
}
