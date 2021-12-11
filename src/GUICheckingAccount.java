import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;

public class GUICheckingAccount extends Frame implements GUIsetup, ActionListener
{
	private String uid;
	
	private JButton buttonGoBack;
	private JButton buttonDeposit;
	private JButton buttonWithdraw;
	private JButton buttonTransaction;
	private JButton buttonForeignCurrency;
	private JButton buttonLoan;
	
	private JLabel labelTitle;
	
	public GUICheckingAccount(String uid)
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
		this.buttonLoan = new JButton("Loan");
		
		this.setLabel();
		this.setButton();
		this.setPanel();
	}
	@Override
	public void setLabel() 
	{
		this.labelTitle.setText("Checking Account");
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
		
		this.buttonLoan.addActionListener(this);
		this.buttonLoan.setFont(new Font(null,Font.BOLD,25));
		this.buttonLoan.setBounds(150, 850, 300, 100);
		this.buttonLoan.setFocusable(false);
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
		super.frame.dispose();
		if(e.getSource() == this.buttonGoBack)
		{
			new GUIMainMenu(this.uid);
		}
		else if(e.getSource() == this.buttonDeposit)
		{
			//go to deposit
		}
		else if(e.getSource() == this.buttonWithdraw)
		{
			//withdraw action
		}
		else if(e.getSource() == this.buttonTransaction)
		{
			//view transaction
		}
		else if(e.getSource() == this.buttonForeignCurrency)
		{
			//purchase foreign currency
		}
		else if(e.getSource() == this.buttonLoan)
		{
			//loan action
		}
	}

}
