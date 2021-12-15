import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class GUITransfer extends Frame implements GUIsetup, ActionListener 
{
	private String uid;
	
	private JLabel labelTitle;
	
	private JButton buttonGoBack;
	private JButton bSaving2Checking;
	private JButton bChecking2Saving;
	
	public GUITransfer(String uid)
	{
		super();
		super.addPanel();
		super.frame.setVisible(true);
		
		this.uid = uid;
		
		this.labelTitle = new JLabel();
		
		this.buttonGoBack = new JButton("Back");
		this.bSaving2Checking = new JButton("Saving to Checking");
		this.bChecking2Saving = new JButton("Checking to Saving");
		
		this.setButton();
		this.setLabel();
		this.setPanel();
	}
	@Override
	public void setLabel() 
	{
		// TODO Auto-generated method stub
		this.labelTitle.setText("Transfer");
		this.labelTitle.setFont(new Font(null, Font.BOLD, 50));
		this.labelTitle.setBounds(180,50,600,100);
	}

	@Override
	public void setButton() 
	{
		// TODO Auto-generated method stub
		this.buttonGoBack.addActionListener(this);
		this.buttonGoBack.setFont(new Font(null, Font.BOLD, 25));
		this.buttonGoBack.setBounds(0, 0, 100, 100);
		this.buttonGoBack.setFocusable(false);
		
		this.bSaving2Checking.addActionListener(this);
		this.bSaving2Checking.setFont(new Font(null,Font.BOLD,25));
		this.bSaving2Checking.setBounds(150,250,300,100);
		this.bSaving2Checking.setFocusable(false);

		this.bChecking2Saving.addActionListener(this);
		this.bChecking2Saving.setFont(new Font(null,Font.BOLD,25));
		this.bChecking2Saving.setBounds(150, 400, 300, 100);
		this.bChecking2Saving.setFocusable(false);
	}

	@Override
	public void setPanel()
	{
		// TODO Auto-generated method stub
		super.panel.add(this.labelTitle);
		super.panel.add(this.buttonGoBack);
		super.panel.add(this.bSaving2Checking);
		super.panel.add(this.bChecking2Saving);
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
		else
		{
			Customer c = CustomerDao.getInstance().queryById(uid);
			if(c.getCheckingAccount() != null && c.getSavingAccount() != null)
			{
				if(e.getSource() == this.bSaving2Checking)
				{
					String type = GUIInputUtil.getInstance().currencySelect("Please Select what currency you wish to transfer to checking account");
					if(type != null)
					{
						double amount = GUIInputUtil.getInstance().moneyAmount("How much do you wish to transfer?");
						if(amount > 0)
						{
							if(c.getSavingAccount().transfer(c.getCheckingAccount(), amount, type))
							{
								JOptionPane.showMessageDialog(null, "Transfer succeed","Confirmation",JOptionPane.INFORMATION_MESSAGE);
							}
							else
							{
								JOptionPane.showMessageDialog(null, "Transfer failed, Please check your balance","Causion",JOptionPane.INFORMATION_MESSAGE);
							}
						}
					}
				}
				else if(e.getSource() == this.bChecking2Saving)
				{
					String type = GUIInputUtil.getInstance().currencySelect("Please Select what currency you wish to transfer to Saving account");
					if(type != null)
					{
						double amount = GUIInputUtil.getInstance().moneyAmount("How much do you wish to transfer?");
						
						if(amount > 0)
						{
							if(c.getCheckingAccount().transfer(c.getSavingAccount(), amount, type))
							{
								JOptionPane.showMessageDialog(null, "Transfer succeed","Confirmation",JOptionPane.INFORMATION_MESSAGE);
							}
							else
							{
								JOptionPane.showMessageDialog(null, "Transfer failed, Please check your balance","Causion",JOptionPane.INFORMATION_MESSAGE);
							}
						}
					}
				}
			}
			else
			{
				JOptionPane.showMessageDialog(null, "Please make sure you have both saving and checking account open","Causion",JOptionPane.INFORMATION_MESSAGE);
			}
		}
		
	}

}
