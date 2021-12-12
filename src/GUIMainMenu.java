import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class GUIMainMenu extends Frame implements GUIsetup, ActionListener 
{
	private String uid;
	
	private JPanel up;
	private JPanel down;
	
	private JButton buttonGoBack;
	private JButton buttonOpenAccount;
	private JButton buttonCheckBalance;
	private JButton buttonSavingAccount;
	private JButton buttonCheckingAccount;
	private JButton buttonStocks;
	private JButton buttonTransfer;
	private JButton buttonCloseAccount;
	
	private JLabel labelTitle;
	
	public GUIMainMenu(String uid)
	{
		super();
		
		this.uid = uid;
		
		this.up = new JPanel();
		this.down = new JPanel();
		this.addPanel();
		super.frame.setVisible(true);
		
		this.buttonGoBack = new JButton("Back");
		this.buttonOpenAccount = new JButton("Open Account");
		this.buttonCheckBalance = new JButton("Balance");
		this.buttonSavingAccount = new JButton("Saving Account");
		this.buttonCheckingAccount = new JButton("Checking Account");
		this.buttonStocks = new JButton("Stocks");
		this.buttonTransfer = new JButton("Transfer");
		this.buttonCloseAccount = new JButton("Close Account");
		
		this.labelTitle = new JLabel();
		
		this.setLabel();
		this.setButton();
		this.setPanel();
		
		super.frame.setVisible(true);
	}

	@Override
	public void setLabel() 
	{
		this.labelTitle.setText("Main Menu");
		this.labelTitle.setFont(new Font(null, Font.BOLD, 50));
		this.labelTitle.setBounds(180, 50, 600, 100);
	}

	@Override
	public void setButton() 
	{
		this.buttonGoBack.addActionListener(this);
		this.buttonGoBack.setFont(new Font(null,Font.BOLD,25));
		this.buttonGoBack.setFocusable(false);
		this.buttonGoBack.setBounds(0, 0, 100, 100);
		
		this.buttonOpenAccount.setFont(new Font(null,Font.BOLD,25));
		this.buttonOpenAccount.setFocusable(false);
		this.buttonOpenAccount.addActionListener(this);
	
		this.buttonCheckBalance.setFont(new Font(null,Font.BOLD,25));
		this.buttonCheckBalance.setFocusable(false);
		this.buttonCheckBalance.addActionListener(this);
		
		this.buttonSavingAccount.setFont(new Font(null,Font.BOLD,25));
		this.buttonSavingAccount.setFocusable(false);
		this.buttonSavingAccount.addActionListener(this);
		
		this.buttonCheckingAccount.setFont(new Font(null,Font.BOLD,25));
		this.buttonCheckingAccount.setFocusable(false);
		this.buttonCheckingAccount.addActionListener(this);
		
		this.buttonStocks.setFont(new Font(null,Font.BOLD,25));
		this.buttonStocks.setFocusable(false);
		this.buttonStocks.addActionListener(this);
		
		this.buttonTransfer.setFont(new Font(null,Font.BOLD,25));
		this.buttonTransfer.setFocusable(false);
		this.buttonTransfer.addActionListener(this);
		
		this.buttonCloseAccount.setFont(new Font(null,Font.BOLD,25));
		this.buttonCloseAccount.setFocusable(false);
		this.buttonCloseAccount.addActionListener(this);
	}
	@Override
	public void addPanel()
	{
		this.up.setBounds(0,0,600,200);
		this.up.setLayout(null);
		this.up.setBackground(new Color(241,247,255));
		
		
		this.down.setBounds(0, 200, 600,800);
		this.down.setLayout(new GridLayout(4,2,10,10));
		this.down.setBackground(new Color(241,247,255));
		
		super.frame.add(up);
		super.frame.add(down);
	}
	@Override
	public void setPanel() 
	{
		this.up.add(this.labelTitle);
		this.up.add(this.buttonGoBack);
		
		this.down.add(this.buttonOpenAccount);
		this.setButtonAppear();
	}
	public void setButtonAppear()
	{
		if(SavingAccountDao.getInstance().queryByUserId(uid) != null||CheckingAccountDao.getInstance().queryByUserId(uid) != null)
		{
			if(SavingAccountDao.getInstance().queryByUserId(uid) != null)
			{
				this.down.add(this.buttonSavingAccount);
			}
			if(CheckingAccountDao.getInstance().queryByUserId(uid) != null)
			{
				this.down.add(this.buttonCheckingAccount);
			}
			if(StockAccountDao.getInstance().queryByUserId(uid) != null)
			{
				this.down.add(this.buttonStocks);
			}
			this.down.add(this.buttonCheckBalance);
			this.down.add(this.buttonTransfer);
			this.down.add(this.buttonCloseAccount);
		}
	}
	@Override
	public void actionPerformed(ActionEvent e) 
	{
		super.frame.dispose();
		if(e.getSource() == this.buttonGoBack)
		{
			new GUILoginPage();
		}
		else if(e.getSource() == this.buttonOpenAccount)
		{
			new GUIOpenAccount(this.uid);
		}
		else if(e.getSource() == this.buttonCheckBalance)
		{
			//go to balance page
			new GUIBalance(this.uid);
		}
		else if(e.getSource() == this.buttonSavingAccount)
		{
			//go to saving account
			new GUISavingAccount(this.uid);
		}
		else if(e.getSource() == this.buttonCheckingAccount)
		{
			//go to checking account
			new GUICheckingAccount(this.uid);
		}
		else if(e.getSource() == this.buttonStocks)
		{
			//go to stocks page
			new GUIStock(this.uid);
		}
		else if(e.getSource() == this.buttonTransfer)
		{
			//go to transfer page
			new GUITransfer(this.uid);
		}
		else if(e.getSource() == this.buttonCloseAccount)
		{
			//go to close account page
			new GUICloseAccount(this.uid);
		}
	}
}
