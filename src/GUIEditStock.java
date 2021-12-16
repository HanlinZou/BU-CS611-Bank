import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class GUIEditStock extends Frame
{
	private String uid;
	private JComboBox cbStockList;
	private JTextField tfPrice;
	private JButton bSubmit;
	private String[] stockList;
	private JLabel lnewPrice;

	public GUIEditStock(String uid)
	{
			super();
			super.addPanelResize(6, 1, 10, 10);

			this.uid = uid;

			this.tfPrice = new JTextField();
			this.bSubmit = new JButton("Submit");
			this.lnewPrice = new JLabel();
			this.cbStockList = new JComboBox(this.getStockList());
			this.cbStockList.setFont(new Font(null, Font.BOLD, 25));
			this.cbStockList.addActionListener(this);
			this.cbStockList.setPreferredSize(new Dimension(50,50));

			this.setLabel();
			this.setButton();
			this.setPanel();

			super.frame.setVisible(true);
	}
	public String[] getStockList()
	{
		this.stockList = new String[StockDao.getInstance().getList().size()];
		for(int i = 0; i < stockList.length; i++)
		{
			this.stockList[i] = StockDao.getInstance().getList().get(i).getName();
		}
		return this.stockList;
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
		else if(e.getSource() == this.bSubmit)
		{
			if(this.cbStockList.getSelectedItem() == null)
			{
				JOptionPane.showMessageDialog(null, "Please select a stock first","Warning",JOptionPane.WARNING_MESSAGE);
			}
			else
			{
				if(this.tfPrice.getText() == null)
				{
					JOptionPane.showMessageDialog(null, "Please enter a new price","Warning",JOptionPane.WARNING_MESSAGE);
				}
				else
				{
					try
					{
						double price = Double.parseDouble(this.tfPrice.getText());
						System.out.println(this.cbStockList.getSelectedItem());
						if(ManagerDao.getInstance().queryById(uid).adjustStockPriceByName((String)this.cbStockList.getSelectedItem(), price))
						{
							JOptionPane.showMessageDialog(null, "The new price has been registered into the system","Confirmation",JOptionPane.INFORMATION_MESSAGE);
						}
						else
						{
							JOptionPane.showMessageDialog(null, "Please enter a value greater than 0 and less than 10000","Warning",JOptionPane.WARNING_MESSAGE);
						}
						this.tfPrice.setText("");
					}
					catch(NumberFormatException ex)
					{
						JOptionPane.showMessageDialog(null, "Please enter a valid value","Warning",JOptionPane.WARNING_MESSAGE);
					}
				}
			}
		}
	}

	@Override
	public void setLabel()
	{
		// TODO Auto-generated method stub
		super.lTitle.setText("           Edit Stock");

		this.lnewPrice.setText("Set a new price");
		this.lnewPrice.setFont(new Font(null, Font.BOLD, 25));
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
		// TODO Auto-generated method stub
		super.down.add(this.cbStockList);
		super.down.add(this.lnewPrice);
		super.down.add(this.tfPrice);
		super.down.add(bSubmit);
	}
}
