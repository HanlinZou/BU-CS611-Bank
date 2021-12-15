import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class GUIAddStock extends Frame
{
	
	private JTextField tfName;
	private JTextField tfPrice;
	
	private JLabel lName;
	private JLabel lPrice;
	
	private JButton bSubmit;
	
	private String uid;
	
	public GUIAddStock(String uid)
	{
		super();
		super.addPanelResize(6, 1, 20,20);
		
		this.uid = uid;
		this.tfName = new JTextField();
		this.tfPrice = new JTextField();
		
		this.lName = new JLabel();
		this.lPrice = new JLabel();
		
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
			if(this.tfName.getText() == null || this.tfName.getText().equals("") || this.tfName.getText().charAt(0) == ' ')
			{
				JOptionPane.showMessageDialog(null, "Please fill out stock name first and make sure it does not start with space", "Warning", JOptionPane.WARNING_MESSAGE);
			}
			else
			{
				if(this.tfPrice.getText() != null)
				{
					try
					{
						double price = Double.parseDouble(this.tfPrice.getText());
						
						boolean found = false;
						for(int i = 0; i < StockDao.getInstance().getList().size();i++)
						{
							if(this.tfName.getText().equals(StockDao.getInstance().getList().get(i).getName()))
							{
								found = true;
							}
						}
						if(!found)
						{
							ManagerDao.getInstance().queryById(uid).addStock(this.tfName.getText(), price);
							JOptionPane.showMessageDialog(null, "<html>Stock name: " + this.tfName.getText() + "<br>Stock price: $" + price + "<br>Has been registered into the system</html>", "Warning", JOptionPane.WARNING_MESSAGE);
						}
						else
						{
							JOptionPane.showMessageDialog(null, "Cannot add a stock that is in the system already", "Warning", JOptionPane.WARNING_MESSAGE);
						}
						this.tfName.setText("");
						this.tfPrice.setText("");
					}
					catch(NumberFormatException ex)
					{
						JOptionPane.showMessageDialog(null, "Please fill out a valid stock price", "Warning", JOptionPane.WARNING_MESSAGE);
					}
				}
				else
				{
					JOptionPane.showMessageDialog(null, "Please fill out stock price", "Warning", JOptionPane.WARNING_MESSAGE);
				}
			}
		}
	}
	
	public void setTextField()
	{
		this.tfName.setPreferredSize(new Dimension(350,40));
		this.tfName.setFont(new Font(null, Font.PLAIN,20));
		this.tfName.setBackground(Color.LIGHT_GRAY);
	
		this.tfPrice.setPreferredSize(new Dimension(350,40));
		this.tfPrice.setFont(new Font(null, Font.PLAIN,20));
		this.tfPrice.setBackground(Color.LIGHT_GRAY);
	}

	@Override
	public void setLabel() 
	{
		// TODO Auto-generated method stub
		super.lTitle.setText("             Add Stock");
		
		this.lName.setText("Stock Name");
		this.lName.setFont(new Font(null, Font.BOLD, 25));
		this.lName.setPreferredSize(new Dimension(350,25));
		
		this.lPrice.setText("Stock Price");
		this.lPrice.setFont(new Font(null, Font.BOLD, 25));
		this.lPrice.setPreferredSize(new Dimension(350,25));
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
	public void setPanel() {
		super.down.add(this.lName);
		super.down.add(this.tfName);
		super.down.add(this.lPrice);
		super.down.add(this.tfPrice);
		super.down.add(this.bSubmit);
		
	}

}
