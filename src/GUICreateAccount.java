import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class GUICreateAccount extends Frame implements GUIsetup, ActionListener
{
	private String firstName;
	private String lastName;
	private String passward;
	
	private JButton buttonGoBack;
	private JButton buttonSubmit;
	
	private JLabel labelTitle;
	private JLabel labelFirstName;
	private JLabel labelLastName;
	private JLabel labelPassward;
	
	private JTextField textFieldFirstName;
	private JTextField textFieldLastName;
	private JTextField textFieldPassward;
	
	GUICreateAccount()
	{
		super();
		super.addPanel();
		super.frame.setVisible(true);
		
		this.buttonGoBack = new JButton("Back");
		this.buttonSubmit = new JButton("Submit");
		
		this.labelTitle = new JLabel();
		this.labelFirstName = new JLabel();
		this.labelLastName = new JLabel();
		this.labelPassward = new JLabel();
		
		this.textFieldFirstName = new JTextField();
		this.textFieldLastName = new JTextField();
		this.textFieldPassward = new JTextField();
		
		this.setLabel();
		this.setTextField();
		this.setButton();
		this.setPanel();
	}
	public void setTextField()
	{
		this.textFieldFirstName.setPreferredSize(new Dimension(350,40));
		this.textFieldFirstName.setBounds(130, 200, 350, 40);
		this.textFieldFirstName.setFont(new Font(null, Font.PLAIN,20));
		this.textFieldFirstName.setBackground(Color.LIGHT_GRAY);
		
		this.textFieldLastName.setPreferredSize(new Dimension(350, 40));
		this.textFieldLastName.setBounds(130, 300, 350, 40);
		this.textFieldLastName.setFont(new Font(null, Font.PLAIN,20));
		this.textFieldLastName.setBackground(Color.LIGHT_GRAY);
		
		this.textFieldPassward.setPreferredSize(new Dimension(350, 40));
		this.textFieldPassward.setBounds(130, 400, 350, 40);
		this.textFieldPassward.setFont(new Font(null, Font.PLAIN, 20));
		this.textFieldPassward.setBackground(Color.LIGHT_GRAY);
	}

	@Override
	public void setLabel() 
	{
		this.labelTitle.setText("Create Account");
		this.labelTitle.setFont(new Font(null, Font.BOLD, 50));
		this.labelTitle.setBounds(120,50,600,100);
		
		this.labelFirstName.setText("First Name");
		this.labelFirstName.setFont(new Font(null, Font.PLAIN, 20));
		this.labelFirstName.setBounds(10, 200, 100, 20);
		
		this.labelLastName.setText("Last Name");
		this.labelLastName.setFont(new Font(null, Font.PLAIN, 20));
		this.labelLastName.setBounds(10, 300, 100, 20);
		
		this.labelPassward.setText("Passward");
		this.labelPassward.setFont(new Font(null, Font.PLAIN, 20));
		this.labelPassward.setBounds(10, 400, 100, 20);
	}


	@Override
	public void setButton() 
	{
		this.buttonGoBack.addActionListener(this);
		this.buttonGoBack.setFont(new Font(null,Font.BOLD,25));
		this.buttonGoBack.setBounds(0, 0, 100, 100);
		this.buttonGoBack.setFocusable(false);
		
		this.buttonSubmit.addActionListener(this);
		this.buttonSubmit.setFont(new Font(null,Font.BOLD,25));
		this.buttonSubmit.setBounds(150, 600, 300, 100);
		this.buttonSubmit.setFocusable(false);
	}


	@Override
	public void setPanel() 
	{
		// TODO Auto-generated method stub
		super.panel.add(this.labelTitle);
		super.panel.add(this.labelFirstName);
		super.panel.add(this.labelLastName);
		super.panel.add(this.labelPassward);
		
		super.panel.add(this.textFieldFirstName);
		super.panel.add(this.textFieldLastName);
		super.panel.add(this.textFieldPassward);
		
		super.panel.add(this.buttonGoBack);
		super.panel.add(this.buttonSubmit);
	}


	@Override
	public void actionPerformed(ActionEvent e) 
	{
		
		// TODO Auto-generated method stub
		if(e.getSource() == this.buttonGoBack)
		{
			super.frame.dispose();
			new GUIATM_MainPage();
		}
		else if(e.getSource() == this.buttonSubmit)
		{
			if(this.validateInput())
			{
				this.recordInformation();
			}
		}
	}
	
	public boolean validateInput()
	{
		firstName = this.textFieldFirstName.getText();
		lastName = this.textFieldLastName.getText();
		passward = this.textFieldPassward.getText();
		if(firstName.equals(""))
		{
			JOptionPane.showMessageDialog(null, "Invalid First Name", "Warning", JOptionPane.WARNING_MESSAGE);
			return false;
		}
		else if(lastName.equals(""))
		{
			JOptionPane.showMessageDialog(null, "Invalid Last Name", "Warning", JOptionPane.WARNING_MESSAGE);
			return false;
		}
		else if(passward.equals(""))
		{
			JOptionPane.showMessageDialog(null, "Invalid Passward", "Warning", JOptionPane.WARNING_MESSAGE);
			return false;
		}
		return true;
	}
	
	public void recordInformation()
	{
		String userName = this.firstName + " " + this.lastName;
		//check duplicate user name
		if(!this.checkDuplicateUserName(userName))
		{	
			new Customer(userName, this.passward);
			JOptionPane.showMessageDialog(null,
					"<html>Information has been saved<br> Your user name is: " + userName + "</html>",
					"Confirmation", 
					JOptionPane.INFORMATION_MESSAGE);
			super.frame.dispose();
			new GUIATM_MainPage();
			
		}
		else
		{
			JOptionPane.showMessageDialog(null,
					"<html>This user name has been taken<br>Try another one</html>",
					"Warning", 
					JOptionPane.WARNING_MESSAGE);
		}
	}
	
	public boolean checkDuplicateUserName(String userName)
	{
		if(CustomerDao.getInstance().queryByName(userName) == null) {return false;}
		return true;
	}
}
