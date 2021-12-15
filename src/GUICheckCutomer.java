import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

public class GUICheckCutomer extends Frame
{
	private String uid;
	private JComboBox cbCustomerSelect;
	private String[] customerList;
	private Customer c;
	private JLabel lcustomerInfo;
	public GUICheckCutomer(String uid)
	{
		super();
		super.addPanelResize(6, 1, 20, 20);
		
		this.c = null;
		this.uid = uid;
		this.lcustomerInfo = new JLabel();
		this.cbCustomerSelect = new JComboBox(this.getCustomerList());
		this.cbCustomerSelect.setFont(new Font(null, Font.BOLD, 25));
		this.cbCustomerSelect.addActionListener(this);
		this.cbCustomerSelect.setPreferredSize(new Dimension(50,50));
		this.setLabel();
		this.setButton();
		this.setPanel();
		
		super.frame.setVisible(true);
	}
	public String[] getCustomerList()
	{
		customerList = new String[CustomerDao.getInstance().getList().size()];
		for(int i = 0; i < customerList.length; i++)
		{
			customerList[i] = CustomerDao.getInstance().getList().get(i).getName();
		}
		return this.customerList;
	}
	@Override
	public void actionPerformed(ActionEvent e) 
	{
		if(e.getSource() == super.buttonGoBack)
		{
			super.frame.dispose();
			new GUIManagerMenu(this.uid);
		}
		else if(e.getSource() == this.cbCustomerSelect)
		{
			String customer = (String) this.cbCustomerSelect.getSelectedItem();
			JOptionPane.showMessageDialog(null, ManagerDao.getInstance().queryById(uid).getCustomerInfoByName(customer),"Information",JOptionPane.INFORMATION_MESSAGE);
			SwingUtilities.updateComponentTreeUI(frame);
		}
	}

	@Override
	public void setLabel() 
	{
		// TODO Auto-generated method stub
		super.lTitle.setText("    Check Customers");
		
		this.lcustomerInfo.setFont(new Font(null, Font.BOLD, 50));
		this.lcustomerInfo.setPreferredSize(new Dimension(600,750));
	}

	@Override
	public void setButton() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setPanel() {
		// TODO Auto-generated method stub
		super.down.add(cbCustomerSelect);
		super.down.add(this.lcustomerInfo);
	}
}
