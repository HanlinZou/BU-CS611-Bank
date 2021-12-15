import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class GUICloseAccount extends Frame implements GUIsetup, ActionListener
{
	private String uid;
	
	private JButton buttonGoBack;
	private JButton buttonSaving;
	private JButton buttonChecking;
	private JButton buttonStock;
	
	private JLabel labelTitle;
	
	private int count;
	public GUICloseAccount(String uid)
	{
		super();
		super.addPanel();
		super.frame.setVisible(true);
		this.count = 0;
		this.uid = uid;
		
		this.labelTitle = new JLabel();
		
		this.buttonGoBack = new JButton("Back");
		this.buttonSaving = new JButton("Saving");
		this.buttonChecking = new JButton("Checking");
		this.buttonStock = new JButton("Stock");
		
		
		this.setLabel();
		this.setButton();
		this.setPanel();
	}
	@Override
	public void setLabel() 
	{
		this.labelTitle.setText("Close Account");
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
		
		this.buttonSaving.addActionListener(this);
		this.buttonSaving.setFont(new Font(null,Font.BOLD,25));
		this.buttonSaving.setBounds(150,250,300,100);
		this.buttonSaving.setFocusable(false);
		
		this.buttonStock.addActionListener(this);
		this.buttonStock.setFont(new Font(null,Font.BOLD,25));
		this.buttonStock.setBounds(150, 550, 300, 100);
		this.buttonStock.setFocusable(false);
		
		this.buttonChecking.addActionListener(this);
		this.buttonChecking.setFont(new Font(null,Font.BOLD,25));
		this.buttonChecking.setBounds(150, 400, 300, 100);
		this.buttonChecking.setFocusable(false);
	}

	@Override
	public void setPanel() 
	{
		// TODO Auto-generated method stub
		super.panel.add(this.labelTitle);
		super.panel.add(this.buttonGoBack);
		super.panel.add(this.buttonSaving);
		super.panel.add(this.buttonStock);
		super.panel.add(this.buttonChecking);
	}

	@Override
	public void actionPerformed(ActionEvent e) 
	{
		
		if(e.getSource() == this.buttonGoBack)
		{
			super.frame.dispose();
			new GUIMainMenu(this.uid);
			JOptionPane.showMessageDialog(null, "It was a bad move to go there","Causion",JOptionPane.INFORMATION_MESSAGE);
		}
		else
		{
			Customer c = CustomerDao.getInstance().queryById(uid);
			
			switch(count)
			{
			case 0:
			
				JOptionPane.showMessageDialog(null, "This is a dangerous thought","Causion",JOptionPane.INFORMATION_MESSAGE);
				break;
			case 1:
				
				JOptionPane.showMessageDialog(null, "Don't do this please","Causion",JOptionPane.INFORMATION_MESSAGE);
				break;
			default:
				int result;
				if(e.getSource() == this.buttonSaving)
				{
					//close saving account
					if(SavingAccountDao.getInstance().queryByUserId(uid) != null)
					{
						result = c.closeSavingAccount();
						if(result == 1)
						{
							JOptionPane.showMessageDialog(null, "Saving account closed","Causion",JOptionPane.INFORMATION_MESSAGE);
						}
						else if(result == 0)
						{
							JOptionPane.showMessageDialog(null, "You idiot owe me money. I can't let you close your account.","Causion",JOptionPane.INFORMATION_MESSAGE);
						}
						else if(result == -1)
						{
							JOptionPane.showMessageDialog(null, "You need to close your stock account first","Causion",JOptionPane.INFORMATION_MESSAGE);
						}
						
					}
					else
					{
						JOptionPane.showMessageDialog(null, "Nice try, you don't have this account opened yet","Causion",JOptionPane.INFORMATION_MESSAGE);
					}
				}
				else if(e.getSource() == this.buttonStock)
				{
					//close stock account
					if(StockAccountDao.getInstance().queryByUserId(uid) != null)
					{
						c.closeStockAccount();
						JOptionPane.showMessageDialog(null, "Stock account closed","Causion",JOptionPane.INFORMATION_MESSAGE);
					}
					else
					{
						JOptionPane.showMessageDialog(null, "Nice try, you don't have this account opened yet","Causion",JOptionPane.INFORMATION_MESSAGE);
					}
				}
				else if(e.getSource() == this.buttonChecking)
				{
					//close checking account
					result = c.closeCheckingAccount();
					if(CheckingAccountDao.getInstance().queryByUserId(uid) != null)
					{
						if(result == 1)
						{
							JOptionPane.showMessageDialog(null, "Checking account closed","Causion",JOptionPane.INFORMATION_MESSAGE);
						}
						else if(result == 0)
						{
							JOptionPane.showMessageDialog(null, "You idiot owe me money. I can't let you close your account.","Causion",JOptionPane.INFORMATION_MESSAGE);
						}
						
					}
					else
					{
						JOptionPane.showMessageDialog(null, "Nice try, you don't have this account opened yet","Causion",JOptionPane.INFORMATION_MESSAGE);
					}
				}
				count = -1;
				break;
			}
			count++;
		}
	}

}