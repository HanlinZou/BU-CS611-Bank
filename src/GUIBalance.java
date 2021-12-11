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
	private JLabel labelBalance;
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
		this.labelBalance = new JLabel();
		this.labelTitle = new JLabel();
		
		this.setLabel();
		this.setButton();
		this.setPanel();
	}
	@Override
	public void setLabel() 
	{
		this.labelTitle.setText("Balance");
		this.labelTitle.setFont(new Font(null, Font.BOLD, 50));
		this.labelTitle.setBounds(200,50,600,100);
		
		this.labelBalance.setText(this.balance + "");
		this.labelBalance.setFont(new Font(null, Font.BOLD, 50));
		this.labelBalance.setBounds(200,250,600,100);
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
		super.panel.add(labelBalance);
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
