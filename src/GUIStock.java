import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class GUIStock extends Frame implements GUIsetup, ActionListener
{
	private String uid;
	
	private JButton buttonGoBack;
	private JButton buttonPurchase;
	private JButton buttonSell;
	private JButton buttonCheckStock;
	private JButton buttonDeposit;
	
	private JLabel labelTitle;
	
	public GUIStock(String uid)
	{
		super();
		super.addPanel();
		super.frame.setVisible(true);
		
		this.uid = uid;
		
		this.labelTitle = new JLabel();
		
		this.buttonGoBack = new JButton("Back");
		this.buttonPurchase = new JButton("Purchase Stock Share");
		this.buttonSell = new JButton("Sell Stock Share");
		this.buttonCheckStock = new JButton("Check Stock/Profit");
		this.buttonDeposit = new JButton("Deposit");
		
		this.setLabel();
		this.setButton();
		this.setPanel();
	}
	@Override
	public void setLabel() 
	{
		this.labelTitle.setText("Stock Account");
		this.labelTitle.setFont(new Font(null, Font.BOLD, 50));
		this.labelTitle.setBounds(120,50,600,100);
	}

	@Override
	public void setButton() 
	{
		// TODO Auto-generated method stub
		this.buttonGoBack.addActionListener(this);
		this.buttonGoBack.setFont(new Font(null, Font.BOLD, 25));
		this.buttonGoBack.setBounds(0, 0, 100, 100);
		this.buttonGoBack.setFocusable(false);
		
		this.buttonPurchase.addActionListener(this);
		this.buttonPurchase.setFont(new Font(null,Font.BOLD,25));
		this.buttonPurchase.setBounds(150,250,300,100);
		this.buttonPurchase.setFocusable(false);
		
		this.buttonCheckStock.addActionListener(this);
		this.buttonCheckStock.setFont(new Font(null,Font.BOLD,25));
		this.buttonCheckStock.setBounds(150, 550, 300, 100);
		this.buttonCheckStock.setFocusable(false);
		
		this.buttonSell.addActionListener(this);
		this.buttonSell.setFont(new Font(null,Font.BOLD,25));
		this.buttonSell.setBounds(150, 400, 300, 100);
		this.buttonSell.setFocusable(false);
		
		this.buttonDeposit.addActionListener(this);
		this.buttonDeposit.setFont(new Font(null,Font.BOLD,25));
		this.buttonDeposit.setBounds(150, 700, 300, 100);
		this.buttonDeposit.setFocusable(false);
	}

	@Override
	public void setPanel() 
	{
		// TODO Auto-generated method stub
		super.panel.add(this.labelTitle);
		super.panel.add(this.buttonGoBack);
		super.panel.add(this.buttonPurchase);
		super.panel.add(this.buttonCheckStock);
		super.panel.add(this.buttonSell);
		super.panel.add(this.buttonDeposit);
	}

	@Override
	public void actionPerformed(ActionEvent e) 
	{
		
		if(e.getSource() == this.buttonGoBack)
		{
			super.frame.dispose();
			new GUIMainMenu(this.uid);
		}
		Customer c = CustomerDao.getInstance().queryById(uid);
		
		if(e.getSource() == this.buttonPurchase)
		{
			//go to deposit
			String stockToPurchase = GUIInputUtil.getInstance().stockSelect("Please select a Stock to purchase");
			if(stockToPurchase != null)
			{
				double numOfShare = GUIInputUtil.getInstance().moneyAmount("How many share do you wish to buy?");
				if(numOfShare > 0)
				{
					
					if(c.getStockAccount().buyStock(StockDao.getInstance().queryByName(stockToPurchase).getID(),numOfShare))
					{
						JOptionPane.showMessageDialog(null, "Confirmation! you have bought " + numOfShare + " shares of " + stockToPurchase + " stock","Purchase Successfully",JOptionPane.WARNING_MESSAGE);
					}
					else
					{
						JOptionPane.showMessageDialog(null, "Not enough money in the account","Purchase failed",JOptionPane.WARNING_MESSAGE);
					}
				}
			}
			
		}
		else if(e.getSource() == this.buttonCheckStock)
		{
			//view stock info
			if(c.getStockAccount().getDao().getList().size() > 0)
			{
				JOptionPane.showMessageDialog(null, c.getStockAccount().displayStocks(),"Warning",JOptionPane.WARNING_MESSAGE);
			}
			else
			{
				JOptionPane.showMessageDialog(null, "You don't own any stock","Warning",JOptionPane.WARNING_MESSAGE);
			}
		}
		else if(e.getSource() == this.buttonSell)
		{
			//go to sell stock
			String stockToSell = GUIInputUtil.getInstance().stockSelect("Please select a Stock to sell");
			if(stockToSell != null)
			{
				double numOfShare = GUIInputUtil.getInstance().moneyAmount("How many share do you wish to buy?");
				if(numOfShare > 0)
				{
					if(c.getStockAccount().sellStock(StockDao.getInstance().queryByName(stockToSell).getID(),numOfShare))
					{
						JOptionPane.showMessageDialog(null, "Confirmation! you have bought " + numOfShare + " shares of " + stockToSell + " stock","Sold Successfully",JOptionPane.INFORMATION_MESSAGE);
					}
					else
					{
						JOptionPane.showMessageDialog(null, "Failed, make sure you have these shares","Warning",JOptionPane.WARNING_MESSAGE);
					}
				}
			}
		}
		else if(e.getSource() == this.buttonDeposit)
		{
			double amount = GUIInputUtil.getInstance().moneyAmount("<html>Your saving account has " + c.getSavingAccount().getUSDBalance() + " USD<br>"
					+ "How much do you wish to transfer to Stock account?");
			if(amount > 0)
			{
				if(c.depositStockAccount(amount))
				{
					JOptionPane.showMessageDialog(null, "<html>You have successfully deposited " + amount + " USD into Stock account<br>"
							+ "Current Stock account balance: " + c.getStockAccount().getBalance() + " USD<html>","Confirmation",JOptionPane.INFORMATION_MESSAGE);
				}
				else
				{
					JOptionPane.showMessageDialog(null, "Failed, make sure you have enough money in your stock account","Warning",JOptionPane.WARNING_MESSAGE);
				}
			}
		}
	}
	
	
}
