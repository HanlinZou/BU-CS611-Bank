import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;

import javax.swing.JButton;
import javax.swing.JOptionPane;

public class GUILoan extends Frame
{
	private String uid;

	private JButton bApplyLoanl;
	private JButton bPayLoan;


	public GUILoan(String uid)
	{
		super();
		super.addPanelResize(6, 1, 10, 10);

		this.uid = uid;

		this.bApplyLoanl = new JButton("Apply loan");
		this.bPayLoan = new JButton("Pay loan");

		this.setLabel();
		this.setButton();
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
			new GUIMainMenu(this.uid);
		}
		else if(e.getSource() == this.bApplyLoanl)
		{
			if(LoanDao.getInstance().getList() != null && LoanDao.getInstance().getList().size() > 0)
			{
				String[] loanList = new String[LoanDao.getInstance().getList().size()];
				for(int i = 0; i < loanList.length; i++)
				{
					loanList[i] = LoanDao.getInstance().getList().get(i).getName();
				}
				String select = (String)JOptionPane.showInputDialog(null,"Select a loan","loan",JOptionPane.QUESTION_MESSAGE,null,loanList,loanList[0]);
				if(select != null && !select.equals(""))
				{
					//proceed on grand the loan to customer
					String collateral = JOptionPane.showInputDialog("Please provide a collateral");
					if(collateral == null || collateral.equals(""))
					{

					}
					else if(!GUIInputUtil.getInstance().inputStringSpaceCheck(collateral))
					{
						JOptionPane.showMessageDialog(null, "Please make sure it does not contain space", "Warning", JOptionPane.WARNING_MESSAGE);
					}
					else
					{
						CustomerDao.getInstance().queryById(this.uid).buyLoan(select, collateral);
						JOptionPane.showMessageDialog(null, "Your loan request has been granded","Confirmation",JOptionPane.INFORMATION_MESSAGE);
					}

				}
			}
			else
			{
				JOptionPane.showMessageDialog(null, "Our bank does not offer any loan at this point","Warning",JOptionPane.WARNING_MESSAGE);
			}
		}

		else if (e.getSource() == this.bPayLoan) {
			if (CustomerDao.getInstance().queryById(uid).getLoanNum() != 0) {
				String[] loanList = new String[CustomerDao.getInstance().queryById(uid).getLoanList().size()];

                for(int i = 0; i < loanList.length;i++) {
					loanList[i] = CustomerDao.getInstance().queryById(uid).getLoanList().get(i).getName();
				}

                String select = (String)JOptionPane.showInputDialog(null, "Select a loan", "loan", JOptionPane.QUESTION_MESSAGE, null, loanList, loanList[0]);

                if (select != null && !select.equals("")) {
					if(CustomerDao.getInstance().queryById(this.uid).sellLoan(select)) {
						JOptionPane.showMessageDialog(null, "You have paid off the loan", "Confirmation", JOptionPane.INFORMATION_MESSAGE);
					}
					else {
						JOptionPane.showMessageDialog(null, "Please make sure you have enough money in your account to pay off your loan", "Warning", JOptionPane.WARNING_MESSAGE);
					}
				}
			} else {
				JOptionPane.showMessageDialog(null, "You don't have loans", "Warning", JOptionPane.WARNING_MESSAGE);
			}
		}
	}

	@Override
	public void setLabel() {
		// TODO Auto-generated method stub
		super.lTitle.setText("              Loan");
	}


	@Override
	public void setButton()
	{
		// TODO Auto-generated method stub
		this.bApplyLoanl.addActionListener(this);
		this.bApplyLoanl.setFont(new Font(null,Font.BOLD,25));
		this.bApplyLoanl.setFocusable(false);
		this.bApplyLoanl.setPreferredSize(new Dimension(300,100));

		this.bPayLoan.addActionListener(this);
		this.bPayLoan.setFont(new Font(null,Font.BOLD,25));
		this.bPayLoan.setFocusable(false);
		this.bPayLoan.setPreferredSize(new Dimension(300,100));
	}


	@Override
	public void setPanel() {
		// TODO Auto-generated method stub
		super.down.add(this.bApplyLoanl);
		super.down.add(this.bPayLoan);
	}
}
