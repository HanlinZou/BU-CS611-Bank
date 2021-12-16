import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class GUIAddLoan extends Frame
{
	private String uid;

	private JTextField tfLoanName;
	private JLabel lLoanName;
	
	private JTextField tfInterest;
	private JLabel lInterest;
	
	private JTextField tfHowMuch;
	private JLabel lHowMuch;
	
	private JButton bSubmit;
	public GUIAddLoan(String uid)
	{
		super();
		super.addPanelResize(7, 1, 20, 20);
		this.uid = uid;
		this.tfLoanName = new JTextField();
		this.tfInterest = new JTextField();
		this.tfHowMuch = new JTextField();
		
		this.lLoanName = new JLabel();
		this.lInterest = new JLabel();
		this.lHowMuch = new JLabel();
		
		this.bSubmit = new JButton("Submit");
		
		this.setLabel();
		this.setButton();
		this.setTextField();
		this.setPanel();
		
		super.frame.setVisible(true);
	}
	@Override
	public void actionPerformed(ActionEvent e) 
	{
		// TODO Auto-generated method stub
		if(e.getSource() == super.buttonGoBack)
		{
			super.frame.dispose();
			new GUIManagerMenu(this.uid);
		}
		else
		{
			if(e.getSource() == this.bSubmit)
			{
				if(this.tfLoanName.getText() == null || this.tfLoanName.getText().equals("")||this.tfLoanName.getText().charAt(0) == ' ' || !GUIInputUtil.getInstance().inputStringSpaceCheck(this.tfLoanName.getText()))
				{
					JOptionPane.showMessageDialog(null, "Please fill out loan name first and make sure it does not contain space", "Warning", JOptionPane.WARNING_MESSAGE);
				}
				else 
				{
					if(this.tfInterest.getText() == null || this.tfInterest.getText().equals("")||this.tfInterest.getText().charAt(0) == ' ')
					{
						JOptionPane.showMessageDialog(null, "Please fill out interest first and make sure it does not start with space", "Warning", JOptionPane.WARNING_MESSAGE);
					}
					else
					{
						String sInterest = this.tfInterest.getText();
						try
						{
							double interest = Double.parseDouble(sInterest)/100;
							if(this.tfHowMuch.getText() == null || this.tfHowMuch.getText().equals("")||this.tfHowMuch.getText().charAt(0) == ' ')
							{
								JOptionPane.showMessageDialog(null, "Please fill out amount first and make sure it does not start with space", "Warning", JOptionPane.WARNING_MESSAGE);
							}
							else
							{
								String sHowMuch = this.tfHowMuch.getText();
								try
								{
									double howMuch = Double.parseDouble(sHowMuch);
									if(interest <= 0 || interest >= 1)
									{
										JOptionPane.showMessageDialog(null, "Invalid interest rate, make sure its 100 > interest rate > 0", "Warning", JOptionPane.WARNING_MESSAGE);
									}
									else if(howMuch < 0 || howMuch > 100000000)
									{
										JOptionPane.showMessageDialog(null, "Invalid amount, make sure its 100000000 > amount > 0", "Warning", JOptionPane.WARNING_MESSAGE);
									}
									else
									{
										ManagerDao.getInstance().queryById(this.uid).addLoan(this.tfLoanName.getText(), interest, howMuch);
										JOptionPane.showMessageDialog(null, "New loan has been added", "Confirmation", JOptionPane.INFORMATION_MESSAGE);
										this.tfHowMuch.setText("");
										this.tfInterest.setText("");
										this.tfLoanName.setText("");
									}
								}
								catch(NumberFormatException ex2)
								{
									JOptionPane.showMessageDialog(null, "The Amount is invalid", "Warning", JOptionPane.WARNING_MESSAGE);
								}
							}
						}
						catch(NumberFormatException ex)
						{
							JOptionPane.showMessageDialog(null, "The interest rate is invalid", "Warning", JOptionPane.WARNING_MESSAGE);
						}
					}
				}
			}
		}
		
	}
	
	public void setTextField()
	{
		this.tfLoanName.setPreferredSize(new Dimension(350,40));
		this.tfLoanName.setFont(new Font(null, Font.PLAIN,20));
		this.tfLoanName.setBackground(Color.LIGHT_GRAY);
		
		this.tfInterest.setPreferredSize(new Dimension(350,40));
		this.tfInterest.setFont(new Font(null, Font.PLAIN,20));
		this.tfInterest.setBackground(Color.LIGHT_GRAY);
		
		this.tfHowMuch.setPreferredSize(new Dimension(350,40));
		this.tfHowMuch.setFont(new Font(null, Font.PLAIN,20));
		this.tfHowMuch.setBackground(Color.LIGHT_GRAY);
	}
	@Override
	public void setLabel() 
	{
		// TODO Auto-generated method stub
		super.lTitle.setText("            Add Loan");
	
		this.lLoanName.setText("Set Loan Name");
		this.lLoanName.setFont(new Font(null, Font.BOLD, 25));
		this.lLoanName.setPreferredSize(new Dimension(350,25));
		
		this.lInterest.setText("Set loan interest(%)");
		this.lInterest.setFont(new Font(null, Font.BOLD, 25));
		this.lInterest.setPreferredSize(new Dimension(350,25));
		
		this.lHowMuch.setText("Loan amount");
		this.lHowMuch.setFont(new Font(null, Font.BOLD, 25));
		this.lHowMuch.setPreferredSize(new Dimension(350,25));
		
	}

	@Override
	public void setButton() 
	{
		// TODO Auto-generated method stub
		this.bSubmit.addActionListener(this);
		this.bSubmit.setFont(new Font(null,Font.BOLD,25));
		this.bSubmit.setFocusable(false);
		this.bSubmit.setPreferredSize(new Dimension(300,100));
	}

	@Override
	public void setPanel() 
	{
		super.down.add(this.lLoanName);
		super.down.add(this.tfLoanName);
		super.down.add(this.lInterest);
		super.down.add(this.tfInterest);
		super.down.add(this.lHowMuch);
		super.down.add(this.tfHowMuch);
		super.down.add(this.bSubmit);
	}
	
}
