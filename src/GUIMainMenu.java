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

public class GUIMainMenu extends Frame 
{
	private String uid;
	
	
	
	private JButton buttonGoBack;
	private JButton buttonOpenAccount;
	private JButton buttonCheckBalance;
	private JButton buttonSavingAccount;
	private JButton buttonCheckingAccount;
	private JButton buttonStocks;
	private JButton buttonTransfer;
	private JButton buttonCloseAccount;
	private JButton buttonLoan;
	
	public GUIMainMenu(String uid)
	{
		super();
		//super.frame.setLayout(new BorderLayout());
		this.uid = uid;
		
		super.addPanelResize(4, 2, 10, 10);
		
		this.buttonOpenAccount = new JButton("Open Account");
		this.buttonCheckBalance = new JButton("Balance");
		this.buttonSavingAccount = new JButton("Saving Account");
		this.buttonCheckingAccount = new JButton("Checking Account");
		this.buttonStocks = new JButton("Stocks");
		this.buttonTransfer = new JButton("Transfer");
		this.buttonCloseAccount = new JButton("Close Account");
		this.buttonLoan = new JButton("Loan");
		
		this.setLabel();
		this.setButton();
		this.setPanel();
		
		super.frame.setVisible(true);
	}

	@Override
	public void setLabel() 
	{
		super.lTitle.setText("          Main Menu");
	}

	@Override
	public void setButton() 
	{
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
		
		this.buttonLoan.setFont(new Font(null,Font.BOLD,25));
		this.buttonLoan.setFocusable(false);
		this.buttonLoan.addActionListener(this);
	}

	@Override
	public void setPanel() 
	{
		super.down.add(this.buttonOpenAccount);
		this.setButtonAppear();
	}
	public void setButtonAppear()
	{
		if(SavingAccountDao.getInstance().queryByUserId(uid) != null||CheckingAccountDao.getInstance().queryByUserId(uid) != null)
		{
			if(SavingAccountDao.getInstance().queryByUserId(uid) != null)
			{
				super.down.add(this.buttonSavingAccount);
				super.down.add(this.buttonLoan);
			}
			if(CheckingAccountDao.getInstance().queryByUserId(uid) != null)
			{
				super.down.add(this.buttonCheckingAccount);
			}
			if(StockAccountDao.getInstance().queryByUserId(uid) != null)
			{
				super.down.add(this.buttonStocks);
			}
			super.down.add(this.buttonCheckBalance);
			super.down.add(this.buttonTransfer);
			super.down.add(this.buttonCloseAccount);
		}
	}
	@Override
	public void actionPerformed(ActionEvent e) 
	{
		super.frame.dispose();
		if(e.getSource() == super.buttonGoBack)
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
		else if(e.getSource() == this.buttonLoan)
		{
			new GUILoan(this.uid);
		}
	}
}
