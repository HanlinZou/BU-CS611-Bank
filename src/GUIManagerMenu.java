import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class GUIManagerMenu extends Frame
{
	private String uid;
	
	private JButton bCheckCust;
	private JButton bCheckPoorCustReport;
	private JButton bAddStock;
	private JButton bAddLoan;
	private JButton bEditStock;
	private JButton bEditLoan;
	
	private JLabel lTitle;
	
	public GUIManagerMenu(String uid)
	{
		super();
		

		this.uid = uid;
		
		super.addPanelResize(3,2,10,10);
		
		this.bCheckCust = new JButton("<html>Check<br>Customers</html>");
		this.bCheckPoorCustReport = new JButton("<html>Check Customers<br>Transaction</html>");
		this.bAddStock = new JButton("Add Stock");
		this.bAddLoan = new JButton("Add Loan");
		this.bEditStock = new JButton("Edit Stock");
		this.bEditLoan = new JButton("Edit Loan");
		this.lTitle = new JLabel();
		
		
		
		this.setLabel();
		this.setButton();
		this.setPanel();
		
		super.frame.setVisible(true);
	}
	

	@Override
	public void setLabel() 
	{
		// TODO Auto-generated method stub
		super.lTitle.setText("         Manager Menu");
	}

	@Override
	public void setButton() 
	{
		// TODO Auto-generated method stub
		this.bCheckCust.setFont(new Font(null,Font.BOLD,25));
		this.bCheckCust.setFocusable(false);
		this.bCheckCust.addActionListener(this);
		
		this.bCheckPoorCustReport.setFont(new Font(null,Font.BOLD,25));
		this.bCheckPoorCustReport.setFocusable(false);
		this.bCheckPoorCustReport.addActionListener(this);
		
		this.bAddStock.setFont(new Font(null,Font.BOLD,25));
		this.bAddStock.setFocusable(false);
		this.bAddStock.addActionListener(this);
	
		this.bAddLoan.setFont(new Font(null,Font.BOLD,25));
		this.bAddLoan.setFocusable(false);
		this.bAddLoan.addActionListener(this);
		
		this.bEditStock.setFont(new Font(null,Font.BOLD,25));
		this.bEditStock.setFocusable(false);
		this.bEditStock.addActionListener(this);
	
		this.bEditLoan.setFont(new Font(null,Font.BOLD,25));
		this.bEditLoan.setFocusable(false);
		this.bEditLoan.addActionListener(this);
	}

	@Override
	public void setPanel() 
	{
		// TODO Auto-generated method stub
		
		this.down.add(this.bCheckCust);
		this.down.add(this.bCheckPoorCustReport);
		this.down.add(this.bAddStock);
		this.down.add(this.bAddLoan);
		this.down.add(this.bEditStock);
		this.down.add(this.bEditLoan);
	}

	@Override
	public void actionPerformed(ActionEvent e) 
	{
		// TODO Auto-generated method stub
		super.frame.dispose();
		if(e.getSource() == super.buttonGoBack)
		{
			new GUILoginPage();
		}
		else if(e.getSource() == this.bCheckCust)
		{
			new GUICheckCutomer(this.uid);
		}
		else if(e.getSource() == this.bCheckPoorCustReport)
		{
			new GUICheckTransaction(this.uid);
		}
		else if(e.getSource() == this.bAddStock)
		{
			new GUIAddStock(this.uid);
		}
		else if(e.getSource() == this.bAddLoan)
		{
			new GUIAddLoan(this.uid);
		}
		else if(e.getSource() == this.bEditStock)
		{
			new GUIEditStock(this.uid);
		}
		else if(e.getSource() == this.bEditLoan)
		{
			new GUIEditLoan(this.uid);
		}
	}

}
