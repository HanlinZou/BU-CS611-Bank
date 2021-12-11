import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;

public class GUIStock extends Frame implements GUIsetup, ActionListener
{
	private String uid;
	
	private JButton buttonGoBack;
	private JButton buttonPurchase;
	private JButton buttonSell;
	private JButton buttonCheckStock;
	
	private JLabel labelTitle;
	
	public GUIStock(String uid)
	{
		super();
		super.addPanel();
		super.frame.setVisible(true);
		
		this.uid = uid;
		
		this.labelTitle = new JLabel();
		
		this.buttonGoBack = new JButton("Back");
		this.buttonPurchase = new JButton("Purchase Stock Share");
		this.buttonSell = new JButton("Sell Stock Share");
		this.buttonCheckStock = new JButton("Check Stock/Profit");
		
		
		this.setLabel();
		this.setButton();
		this.setPanel();
	}
	@Override
	public void setLabel() 
	{
		this.labelTitle.setText("Saving Account");
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
		
		this.buttonPurchase.addActionListener(this);
		this.buttonPurchase.setFont(new Font(null,Font.BOLD,25));
		this.buttonPurchase.setBounds(150,250,300,100);
		this.buttonPurchase.setFocusable(false);
		
		this.buttonCheckStock.addActionListener(this);
		this.buttonCheckStock.setFont(new Font(null,Font.BOLD,25));
		this.buttonCheckStock.setBounds(150, 550, 300, 100);
		this.buttonCheckStock.setFocusable(false);
		
		this.buttonSell.addActionListener(this);
		this.buttonSell.setFont(new Font(null,Font.BOLD,25));
		this.buttonSell.setBounds(150, 400, 300, 100);
		this.buttonSell.setFocusable(false);
	}

	@Override
	public void setPanel() 
	{
		// TODO Auto-generated method stub
		super.panel.add(this.labelTitle);
		super.panel.add(this.buttonGoBack);
		super.panel.add(this.buttonPurchase);
		super.panel.add(this.buttonCheckStock);
		super.panel.add(this.buttonSell);
	}

	@Override
	public void actionPerformed(ActionEvent e) 
	{
		super.frame.dispose();
		if(e.getSource() == this.buttonGoBack)
		{
			new GUIMainMenu(this.uid);
		}
		else if(e.getSource() == this.buttonPurchase)
		{
			//go to deposit
		}
		else if(e.getSource() == this.buttonCheckStock)
		{
			//view transaction
		}
		else if(e.getSource() == this.buttonSell)
		{
			//purchase foreign currency
		}
	}

}
