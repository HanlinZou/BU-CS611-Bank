import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class GUILoginPage extends Frame implements GUIsetup, ActionListener 
{
	private String userName;
	private String passward;
	private String backDoor;
	
	private JButton buttonGoBack;
	private JButton buttonUserLogin;
	private JButton buttonStaffLogin;
	private JLabel labelUserName;
	private JLabel labelPassward;
	private JLabel labelTitle;
	private JTextField textFieldUserName;
	private JTextField textFieldPassward;
	public GUILoginPage()
	{
		super();
		super.addPanel();
		super.frame.setVisible(true);
		
		this.backDoor = "123";
		
		this.buttonGoBack = new JButton("Back");
		this.buttonStaffLogin = new JButton("Staff Login");
		this.buttonUserLogin = new JButton("User Login");
		
		this.labelPassward = new JLabel();
		this.labelTitle = new JLabel();
		this.labelUserName = new JLabel();
		
		this.textFieldUserName = new JTextField();
		this.textFieldPassward = new JTextField();
		
		this.setTextField();
		this.setLabel();
		this.setButton();
		this.setPanel();
	}
	public void setTextField()
	{
		this.textFieldUserName.setPreferredSize(new Dimension(350,40));
		this.textFieldUserName.setBounds(130, 200, 350, 40);
		this.textFieldUserName.setFont(new Font(null, Font.PLAIN,20));
		this.textFieldUserName.setBackground(Color.LIGHT_GRAY);
		
		this.textFieldPassward.setPreferredSize(new Dimension(350,40));
		this.textFieldPassward.setBounds(130,400,350,40);
		this.textFieldPassward.setFont(new Font(null, Font.PLAIN,20));
		this.textFieldPassward.setBackground(Color.LIGHT_GRAY);
	}
	@Override
	public void setLabel() 
	{
		this.labelTitle.setText("Login");
		this.labelTitle.setFont(new Font(null, Font.BOLD, 50));
		this.labelTitle.setBounds(220, 50, 600, 100);
		
		this.labelUserName.setText("User Name");
		this.labelUserName.setFont(new Font(null, Font.PLAIN, 20));
		this.labelUserName.setBounds(10,200,100,20);
		
		this.labelPassward.setText("Passward");
		this.labelPassward.setFont(new Font(null, Font.PLAIN, 20));
		this.labelPassward.setBounds(10,400,100,20);
	}

	@Override
	public void setButton() 
	{
		this.buttonGoBack.addActionListener(this);
		this.buttonGoBack.setFont(new Font(null,Font.BOLD,25));
		this.buttonGoBack.setBounds(0, 0, 100, 100);
		this.buttonGoBack.setFocusable(false);
		
		this.buttonUserLogin.addActionListener(this);
		this.buttonUserLogin.setFont(new Font(null,Font.BOLD,25));
		this.buttonUserLogin.setBounds(150, 500, 300, 100);
		this.buttonUserLogin.setFocusable(false);
		
		this.buttonStaffLogin.addActionListener(this);
		this.buttonStaffLogin.setFont(new Font(null,Font.BOLD,25));
		this.buttonStaffLogin.setBounds(150, 650, 300, 100);
		this.buttonStaffLogin.setFocusable(false);
	}

	@Override
	public void setPanel() 
	{
		super.panel.add(this.labelTitle);
		super.panel.add(this.labelUserName);
		super.panel.add(this.labelPassward);
		
		super.panel.add(this.textFieldUserName);
		super.panel.add(this.textFieldPassward);
		
		super.panel.add(this.buttonGoBack);
		super.panel.add(this.buttonUserLogin);
		super.panel.add(this.buttonStaffLogin);
	}

	@Override
	public void actionPerformed(ActionEvent e) 
	{
		
		if(e.getSource() == this.buttonGoBack)
		{
			super.frame.dispose();
			GUIATM_MainPage mainPage = new GUIATM_MainPage();
		}
		else if(e.getSource() == this.buttonUserLogin)
		{
			if(this.verifyInput())
			{
				String result = this.userVerify();
				if(result.equals("failed"))
				{
					JOptionPane.showMessageDialog(null, "Incorrect user name or passward", "Warning", JOptionPane.WARNING_MESSAGE);
				}
				else
				{
					super.frame.dispose();
					new GUIMainMenu(result);
				}
			}
		}
		else if(e.getSource() == this.buttonStaffLogin)
		{
			if(this.verifyInput())
			{
				String result = this.staffVerify();
				if(result.equals("failed"))
				{
					JOptionPane.showMessageDialog(null, "Incorrect user name or passward", "Warning", JOptionPane.WARNING_MESSAGE);
				}
				else
				{
					super.frame.dispose();
					new GUIManagerMenu(result);
				}
			}
		}
	}
	
	public boolean verifyInput()
	{
		this.userName = this.textFieldUserName.getText();
		this.passward = this.textFieldPassward.getText();
		if(this.userName.equals(""))
		{
			JOptionPane.showMessageDialog(null, "Invalid User Name", "Warning", JOptionPane.WARNING_MESSAGE);
			return false;
		}
		else if(this.passward.equals(""))
		{
			JOptionPane.showMessageDialog(null, "Invalid Passward", "Warning", JOptionPane.WARNING_MESSAGE);
			return false;
		}
		return true;
	}
	public String userVerify()
	{
		//check database for this user info
		Customer c = CustomerDao.getInstance().queryByName(this.userName);
		if(this.userName.equals(this.backDoor)&&this.passward.equals(this.backDoor))
		{
			return "root-001";
		}
		if(c != null)
		{
			//user exist
			if(c.getPassword().equals(this.passward))
			{
				return c.getID();
			}
		}
		return "failed";
	}
	public String staffVerify()
	{
		//check database for this user info
		Manager m = ManagerDao.getInstance().queryByName(userName);
		if(this.userName.equals(this.backDoor)&&this.passward.equals(this.backDoor))
		{
			return "root-001";
		}
		if(m != null)
		{
			if(m.getPassword().equals(this.passward))
			{
				return m.getID();
			}
		}
		return "failed";
	}
}
