import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class GUIEditLoan extends Frame
{
	private String uid;
	private JComboBox cbLoanList;
	private JTextField tfInterest;
	private JTextField tfAmount;

	private JLabel lInterest;
	private JLabel lAmount;

	private JButton bSubmit;

	public GUIEditLoan(String uid)
	{
		super();
		super.addPanelResize(7, 1, 10, 10);

		this.uid = uid;

		this.cbLoanList = new JComboBox(this.getLoanList());
		this.cbLoanList.setFont(new Font(null, Font.BOLD, 25));
		this.cbLoanList.addActionListener(this);
		this.cbLoanList.setPreferredSize(new Dimension(50,50));

		this.tfAmount = new JTextField();
		this.tfInterest = new JTextField();

		this.lAmount = new JLabel();
		this.lInterest = new JLabel();

		this.bSubmit = new JButton("Submit");

		this.setLabel();
		this.setButton();
		this.setPanel();

		super.frame.setVisible(true);
	}
	public String[] getLoanList()
	{
		String[] loanList = new String[LoanDao.getInstance().getList().size()];
		for(int i = 0; i < loanList.length; i++)
		{
			loanList[i] = LoanDao.getInstance().getList().get(i).getName();
		}
		return loanList;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource() == super.buttonGoBack)
		{
			super.frame.dispose();
			new GUIManagerMenu(this.uid);
		}
	}

	@Override
	public void setLabel() {
		// TODO Auto-generated method stub
		super.lTitle.setText("          Edit Loan");

		this.lInterest.setText("Set a new interest");
		this.lInterest.setFont(new Font(null, Font.BOLD, 25));

		this.lAmount.setText("Set new amount");
		this.lAmount.setFont(new Font(null, Font.BOLD, 25));
	}

	@Override
	public void setButton() {
		// TODO Auto-generated method stub
		this.bSubmit.addActionListener(this);
		this.bSubmit.setFont(new Font(null,Font.BOLD,25));
		this.bSubmit.setFocusable(false);
		this.bSubmit.setPreferredSize(new Dimension(300,100));
	}

	@Override
	public void setPanel() {
		// TODO Auto-generated method stub
		super.down.add(this.cbLoanList);
		super.down.add(this.lInterest);
		super.down.add(this.tfInterest);
		super.down.add(this.lAmount);
		super.down.add(this.tfAmount);
		super.down.add(bSubmit);
	}

}
