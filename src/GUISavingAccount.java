import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class GUISavingAccount extends Frame implements GUIsetup, ActionListener
{

	private String uid;

	private JButton buttonGoBack;
	private JButton buttonDeposit;
	private JButton buttonWithdraw;
	private JButton buttonTransaction;
	private JButton buttonForeignCurrency;

	private JLabel labelTitle;

	public GUISavingAccount(String uid)
	{
		super();
		super.addPanel();
		super.frame.setVisible(true);

		this.uid = uid;

		this.labelTitle = new JLabel();

		this.buttonGoBack = new JButton("Back");
		this.buttonDeposit = new JButton("Deposit");
		this.buttonForeignCurrency = new JButton("Foreign Currency");
		this.buttonTransaction = new JButton("Transaction");
		this.buttonWithdraw = new JButton("Withdraw Cash");

		this.setLabel();
		this.setButton();
		this.setPanel();
	}

	@Override
	public void setLabel()
	{
		this.labelTitle.setText("Saving Account");
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

		this.buttonDeposit.addActionListener(this);
		this.buttonDeposit.setFont(new Font(null,Font.BOLD,25));
		this.buttonDeposit.setBounds(150,250,300,100);
		this.buttonDeposit.setFocusable(false);

		this.buttonWithdraw.addActionListener(this);
		this.buttonWithdraw.setFont(new Font(null,Font.BOLD,25));
		this.buttonWithdraw.setBounds(150, 400, 300, 100);
		this.buttonWithdraw.setFocusable(false);

		this.buttonTransaction.addActionListener(this);
		this.buttonTransaction.setFont(new Font(null,Font.BOLD,25));
		this.buttonTransaction.setBounds(150, 550, 300, 100);
		this.buttonTransaction.setFocusable(false);

		this.buttonForeignCurrency.addActionListener(this);
		this.buttonForeignCurrency.setFont(new Font(null,Font.BOLD,25));
		this.buttonForeignCurrency.setBounds(150, 700, 300, 100);
		this.buttonForeignCurrency.setFocusable(false);
	}

	@Override
	public void setPanel()
	{
		// TODO Auto-generated method stub
		super.panel.add(this.labelTitle);
		super.panel.add(this.buttonGoBack);
		super.panel.add(this.buttonDeposit);
		super.panel.add(this.buttonWithdraw);
		super.panel.add(this.buttonTransaction);
		super.panel.add(this.buttonForeignCurrency);
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
		if(e.getSource() == this.buttonDeposit)
		{
			String type = GUIInputUtil.getInstance().currencySelect();
			if(type != null)
			{
				double money = GUIInputUtil.getInstance().moneyAmount("Enter the amount of money to be deposited");
				if(money > 0)
				{
					System.out.println(money+" : " + type);
					c.getSavingAccount().deposit(money, type);
					JOptionPane.showMessageDialog(null, "You have successfully deposited " + money + " " + type + " into your saving account","Confirmation",JOptionPane.INFORMATION_MESSAGE);
				}
			}
			//
		}
		else if(e.getSource() == this.buttonWithdraw)
		{
			//withdraw action
			String type = GUIInputUtil.getInstance().currencySelect();
			if(type != null)
			{
				double money = GUIInputUtil.getInstance().moneyAmount("Enter the amount of money to be withdrawn");
				if(money > 0)
				{
					if(c.getSavingAccount().withdraw(money, type))
					{
						JOptionPane.showMessageDialog(null, "<html>Confirmation:<br>"
								+ "With draw complete!<br>"
								+ "remaining balance:<br>"
								+ "______CNY: " + c.getSavingAccount().getCNYBalance()+"<br>"
								+ "______USD: " + c.getSavingAccount().getUSDBalance()+"<br>"
								+ "______HKD: " + c.getSavingAccount().getHKDBalance()+"<br>");
					}
					else
					{
						JOptionPane.showMessageDialog(null, "Not enough money in the account","Warning",JOptionPane.WARNING_MESSAGE);
					}
				}
			}
		}
		else if(e.getSource() == this.buttonTransaction)
		{
			String result = "<html>";
			for (Log s : LogDao.getInstance().queryByAccount(uid, "saving")) {
				result += s.displayString() + "<br>";
			}
			result += "</html>";
			//view transaction
			JOptionPane.showMessageDialog(null, result,"information",JOptionPane.INFORMATION_MESSAGE);
		}
		else if(e.getSource() == this.buttonForeignCurrency)
		{
			//purchase foreign currency
			String target = GUIInputUtil.getInstance().currencySelect("Which currency do you wish to purchase?");
			if(target != null)
			{
				String withWhat = GUIInputUtil.getInstance().currencySelect("Which currency are you going to use to make this purchase?");
				if(withWhat != null)
				{
					double howmuch = GUIInputUtil.getInstance().moneyAmount("How much " + target + " are you going to buy?");
					if(howmuch > 0)
					{
						boolean valid = false;
						if(withWhat.equals("hkd")&&target.equals("usd"))
						{
							valid = c.getSavingAccount().HKD2USD(howmuch);
						}
						else if(withWhat.equals("hkd")&&target.equals("cny"))
						{
							valid = c.getSavingAccount().HKD2CNY(howmuch);
						}
						else if(withWhat.equals("cny")&&target.equals("hkd"))
						{
							valid = c.getSavingAccount().CNY2HKD(howmuch);
						}
						else if(withWhat.equals("cny")&&target.equals("usd"))
						{
							valid = c.getSavingAccount().CNY2USD(howmuch);
						}
						else if(withWhat.equals("usd")&&target.equals("cny"))
						{
							valid = c.getSavingAccount().USD2CNY(howmuch);
						}
						else if(withWhat.equals("usd")&&target.equals("hkd"))
						{
							valid = c.getSavingAccount().USD2HKD(howmuch);
						}
						if(valid)
						{
							JOptionPane.showMessageDialog(null, "Purchase succeed","Confirmation",JOptionPane.INFORMATION_MESSAGE);
						}
						else
						{
							JOptionPane.showMessageDialog(null, "Purchase failed, Please check your balance or your choice","Causion",JOptionPane.INFORMATION_MESSAGE);
						}
					}
				}
			}
		}
	}

}
