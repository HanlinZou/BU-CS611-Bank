import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;

public class GUIBalance extends Frame implements GUIsetup, ActionListener 
{
	private String uid;
	
	private double balance;
	
	private JButton buttonGoBack;

	private JLabel labelChecking;
	private JLabel labelSaving;
	private JLabel labelCheckingBal;
	private JLabel labelSavingBal;
	private JLabel labelStock;
	private JLabel labelStockBal;
	private JLabel labelTitle;
	public GUIBalance(String uid)
	{
		super();
		super.addPanel();
		super.frame.setVisible(true);
		
		this.uid = uid;
		
		this.balance = .00f;
		this.updateBalance();
		
		this.buttonGoBack = new JButton("Back");

		this.labelChecking = new JLabel();
		this.labelSaving = new JLabel();
		this.labelCheckingBal = new JLabel();
		this.labelSavingBal = new JLabel();
		this.labelTitle = new JLabel();
		this.labelStock = new JLabel();
		this.labelStockBal = new JLabel();
		
		this.setLabel();
		this.setButton();
		this.setPanel();
	}
	
	@Override
	public void setLabel() 
	{
		Customer c = CustomerDao.getInstance().queryById(uid);
		this.labelTitle.setText("Total Balance");
		this.labelTitle.setFont(new Font(null, Font.BOLD, 50));
		this.labelTitle.setBounds(140,50,600,100);
		
		
		
		if(SavingAccountDao.getInstance().queryByUserId(uid) != null)
		{
			this.labelSaving.setText("Saving Account");
			this.labelSaving.setFont(new Font(null, Font.BOLD, 50));
			this.labelSaving.setBounds(110,100,600,100);
			
			this.labelSavingBal.setText("<html>"
					+ "USD: " + c.getSavingAccount().getUSDBalance() + "<br>"
					+ "CNY: " + c.getSavingAccount().getCNYBalance() + "<br>"
					+ "HKD: " + c.getSavingAccount().getHKDBalance() + "<html>");
			this.labelSavingBal.setFont(new Font(null, Font.BOLD, 25));
			this.labelSavingBal.setBounds(160,80,600,300);
		}
		
		if(CheckingAccountDao.getInstance().queryByUserId(uid) != null)
		{
			this.labelChecking.setText("Checking Account");
			this.labelChecking.setFont(new Font(null, Font.BOLD, 50));
			this.labelChecking.setBounds(100,280,600,100);
			
			this.labelCheckingBal.setText("<html>"
					+ "USD: " + c.getCheckingAccount().getUSDBalance() + "<br>"
					+ "CNY: " + c.getCheckingAccount().getCNYBalance() + "<br>"
					+ "HKD: " + c.getCheckingAccount().getHKDBalance() + "<html>");
			this.labelCheckingBal.setFont(new Font(null, Font.BOLD, 25));
			this.labelCheckingBal.setBounds(160,370,600,100);
		}
		
		if(StockAccountDao.getInstance().queryByUserId(uid) != null)
		{
			this.labelStock.setText("Stock Account");
			this.labelStock.setFont(new Font(null, Font.BOLD, 50));
			this.labelStock.setBounds(120,480,600,100);
			
			
			this.labelStockBal.setText("USD: " + c.getStockAccount().getBalance());
			this.labelStockBal.setFont(new Font(null, Font.BOLD, 25));
			this.labelStockBal.setBounds(160,560,600,50);
		}
	}

	@Override
	public void setButton() 
	{
		this.buttonGoBack.addActionListener(this);
		this.buttonGoBack.setFont(new Font(null,Font.BOLD,25));
		this.buttonGoBack.setBounds(0, 0, 100, 100);
		this.buttonGoBack.setFocusable(false);
	}

	@Override
	public void setPanel() 
	{
		super.panel.add(buttonGoBack);
		super.panel.add(labelTitle);
		super.panel.add(this.labelSaving);
		super.panel.add(this.labelSavingBal);
		super.panel.add(this.labelChecking);
		super.panel.add(this.labelCheckingBal);
		super.panel.add(this.labelStock);
		super.panel.add(this.labelStockBal);
	}

	@Override
	public void actionPerformed(ActionEvent e) 
	{
		// TODO Auto-generated method stub
		if(e.getSource() == this.buttonGoBack)
		{
			super.frame.dispose();
			new GUIMainMenu(this.uid);
		}
	}
	
	public void updateBalance()
	{
		//get user's balance and update it to var:balance
	}
}
