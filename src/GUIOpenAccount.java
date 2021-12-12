import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class GUIOpenAccount extends Frame implements GUIsetup, ActionListener 
{
	private String uid;
	
	private JButton buttonOpenSaving;
	private JButton buttonOpenChecking;
	private JButton buttonOpenStock;
	private JButton buttonGoBack;
	
	private JLabel labelTitle;
	
	public GUIOpenAccount(String uid)
	{
		super();
		
		this.uid = uid;
		
		super.addPanel();
		super.frame.setVisible(true);
		
		this.labelTitle = new JLabel();
		
		this.buttonGoBack = new JButton("Back");
		this.buttonOpenSaving = new JButton("Saving Account");
		this.buttonOpenChecking = new JButton("Checking Account");
		this.buttonOpenStock = new JButton("Stock");
		
		this.setLabel();
		this.setButton();
		this.setPanel();
	}
	@Override
	public void setLabel() 
	{
		this.labelTitle.setText("Create Account");
		this.labelTitle.setFont(new Font(null, Font.BOLD, 50));
		this.labelTitle.setBounds(120,50,600,100);
		
	}

	@Override
	public void setButton() 
	{
		this.buttonGoBack.addActionListener(this);
		this.buttonGoBack.setFont(new Font(null, Font.BOLD, 25));
		this.buttonGoBack.setBounds(0, 0, 100, 100);
		this.buttonGoBack.setFocusable(false);
		
		this.buttonOpenChecking.addActionListener(this);
		this.buttonOpenChecking.setFont(new Font(null,Font.BOLD,25));
		this.buttonOpenChecking.setBounds(150,250,300,100);
		this.buttonOpenChecking.setFocusable(false);
		
		this.buttonOpenSaving.addActionListener(this);
		this.buttonOpenSaving.setFont(new Font(null,Font.BOLD,25));
		this.buttonOpenSaving.setBounds(150, 400, 300, 100);
		this.buttonOpenSaving.setFocusable(false);
		
		this.buttonOpenStock.addActionListener(this);
		this.buttonOpenStock.setFont(new Font(null,Font.BOLD,25));
		this.buttonOpenStock.setBounds(150, 550, 300, 100);
		this.buttonOpenStock.setFocusable(false);
	}

	@Override
	public void setPanel() 
	{
		super.panel.add(this.labelTitle);
		
		super.panel.add(this.buttonGoBack);
		super.panel.add(this.buttonOpenSaving);
		super.panel.add(this.buttonOpenChecking);
		super.panel.add(this.buttonOpenStock);
	}

	@Override
	public void actionPerformed(ActionEvent e) 
	{
		if(e.getSource() == this.buttonGoBack)
		{
			super.frame.dispose();
			new GUIMainMenu(this.uid);
		}
		else if(e.getSource() == this.buttonOpenSaving)
		{
			//open account
			this.createSavingAccount();
		}
		else if(e.getSource() == this.buttonOpenChecking)
		{
			//open account
			this.createCheckingAccount();
		}
		else if(e.getSource() == this.buttonOpenStock)
		{
			//open account
		}
	}
	public void createSavingAccount()
	{
		if(SavingAccountDao.getInstance().queryByUserId(uid) == null)
		{
			//create account
			new SavingAccount(this.uid);
			JOptionPane.showMessageDialog(null, "<html>Saving account created!<br>We will take 10$ as service fee</html>", "Thank you", JOptionPane.INFORMATION_MESSAGE);
		}
		else
		{
			JOptionPane.showMessageDialog(null, "You already have a saving account", "Warning", JOptionPane.WARNING_MESSAGE);
		}
	}
	public void createCheckingAccount()
	{
		if(CheckingAccountDao.getInstance().queryByUserId(this.uid) == null)
		{
			//create account
			new CheckingAccount(this.uid);
			JOptionPane.showMessageDialog(null, "<html>Checking account created!<br>We will take 10$ as service fee</html>", "Thank you", JOptionPane.INFORMATION_MESSAGE);
		}
		else
		{
			JOptionPane.showMessageDialog(null, "You already have a checking account", "Warning", JOptionPane.WARNING_MESSAGE);
		}
	}
	public void createStockAccount()
	{
		
	}
}
