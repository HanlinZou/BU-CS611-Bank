import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;

public class GUICloseAccount extends Frame implements GUIsetup, ActionListener
{
	private String uid;
	
	private JButton buttonGoBack;
	private JButton buttonSaving;
	private JButton buttonChecking;
	private JButton buttonStock;
	
	private JLabel labelTitle;
	
	public GUICloseAccount(String uid)
	{
		super();
		super.addPanel();
		super.frame.setVisible(true);
		
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
		super.frame.dispose();
		if(e.getSource() == this.buttonGoBack)
		{
			new GUIMainMenu(this.uid);
		}
		else if(e.getSource() == this.buttonSaving)
		{
			//close saving account
		}
		else if(e.getSource() == this.buttonStock)
		{
			//close stock account
		}
		else if(e.getSource() == this.buttonChecking)
		{
			//close checking account
		}
	}

}