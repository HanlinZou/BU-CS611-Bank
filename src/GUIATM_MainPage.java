import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class GUIATM_MainPage extends Frame
{
	public JLabel credit;
	public JPanel mainPanel;
	public JPanel creditPanel;
	public JButton loginButton;
	public JButton createAccountButton;
	
	public GUIATM_MainPage()
	{
		super();
		this.mainPanel = new JPanel();
		this.creditPanel = new JPanel();
		this.loginButton = new JButton();
		
		
		this.createAccountButton = new JButton();
		this.loginButton.addActionListener(this);
		this.createAccountButton.addActionListener(this);
		
		this.setPanel();
		this.setCredit();
		this.setButton();
		
		super.frame.setVisible(true);
	}
	
	public void setCredit()
	{
		credit = new JLabel();
		credit.setText("<html>Shady Bank.co<br>CEO: Xiansong Li<br>CFO: Xiaohan Zou<br>COO: Hanlin Zou</html>");
		credit.setFont(new Font("",Font.ROMAN_BASELINE,13));
		credit.setVerticalAlignment(JLabel.CENTER);
		credit.setHorizontalAlignment(JLabel.CENTER);
		this.creditPanel.add(credit);
	}
	
	public void setPanel()
	{
		this.mainPanel.setBackground(new Color(241,247,255));
		this.creditPanel.setBackground(new Color(221,226,232));
		
		JLabel title = new JLabel();
		title.setText("Shady Bank");
		title.setFont(new Font("",Font.BOLD,50));
		title.setBounds(150, 50, 600, 200);
		
		this.mainPanel.setBounds(0, 0, 600, 700);
		this.creditPanel.setBounds(0,700,600,300);
		
		this.creditPanel.setLayout(new BorderLayout());
		this.mainPanel.setLayout(null);
		
		super.frame.add(this.mainPanel);
		super.frame.add(this.creditPanel);
		
		this.mainPanel.add(title);
	}
	
	public void setButton()
	{
		this.loginButton.setText("Login");
		this.loginButton.setFont(new Font("",Font.BOLD,25));
		this.loginButton.setBounds(150, 250, 300, 100);
		this.loginButton.setFocusable(false);
		
		this.createAccountButton.setText("Create Account");
		this.createAccountButton.setFont(new Font("",Font.BOLD,25));
		this.createAccountButton.setBounds(150, 400, 300, 100);
		this.createAccountButton.setFocusable(false);
		
		this.mainPanel.add(this.loginButton);
		this.mainPanel.add(this.createAccountButton);
	}

	@Override
	public void actionPerformed(ActionEvent arg0) 
	{
		super.frame.dispose();
		if(arg0.getSource() == this.loginButton)
		{
			new GUILoginPage();
		}
		else if(arg0.getSource() == this.createAccountButton)
		{
			new GUICreateAccount();
		}
		
	}

	@Override
	public void setLabel() {
		// TODO Auto-generated method stub
		
	}
}
