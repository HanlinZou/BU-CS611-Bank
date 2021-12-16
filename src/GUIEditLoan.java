import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JOptionPane;

public class GUIEditLoan extends Frame {
	private String uid;
	private JComboBox cbLoanList;
	private JTextField tfInterest;
	private JTextField tfAmount;

	private JLabel lInterest;
	private JLabel lAmount;

	private JButton bSubmit;

	public GUIEditLoan(String uid) {
		super();
		super.addPanelResize(7, 1, 10, 10);

		this.uid = uid;

		this.cbLoanList = new JComboBox(this.getLoanDisplayList());
		this.cbLoanList.setFont(new Font(null, Font.BOLD, 15));
		this.cbLoanList.addActionListener(this);
		this.cbLoanList.setPreferredSize(new Dimension(50, 50));

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

	public String[] getLoanList() {
		String[] loanList = new String[LoanDao.getInstance().getList().size()];
		for(int i = 0; i < loanList.length; i++) {
			loanList[i] = LoanDao.getInstance().getList().get(i).getName();
		}
		return loanList;
	}

    public String[] getLoanDisplayList() {
		String[] loanDisplayList = new String[LoanDao.getInstance().getList().size()];
		for(int i = 0; i < loanDisplayList.length; i++) {
			loanDisplayList[i] = LoanDao.getInstance().getList().get(i).displayString();
		}
		return loanDisplayList;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() == super.buttonGoBack) {
			super.frame.dispose();
			new GUIManagerMenu(this.uid);
		}

        else if(e.getSource() == this.bSubmit) {
			if (this.cbLoanList.getSelectedItem() == null) {
				JOptionPane.showMessageDialog(null, "Please select a loan first", "Warning", JOptionPane.WARNING_MESSAGE);
			}

			else {
				if (this.tfAmount.getText() == null) {
					JOptionPane.showMessageDialog(null, "Please enter a new amount", "Warning", JOptionPane.WARNING_MESSAGE);
				}

                else if (this.tfInterest.getText() == null) {
					JOptionPane.showMessageDialog(null, "Please enter a new interest", "Warning", JOptionPane.WARNING_MESSAGE);
				}

				else {
					try {
						double amount = Double.parseDouble(this.tfAmount.getText());
                        double interest = Double.parseDouble(this.tfInterest.getText());

						System.out.println(this.cbLoanList.getSelectedItem());

                        Manager manager = ManagerDao.getInstance().queryById(uid);
                        String loanDisplayName = (String) this.cbLoanList.getSelectedItem();

                        String[] loanDisplayList = getLoanDisplayList();
                        String[] loadList = getLoanList();

                        for (int i = 0; i < loadList.length; i++) {
                            if (loanDisplayName.equals(loanDisplayList[i])) {
                                if (manager.adjustLoan(loadList[i], amount, interest)) {
                                    JOptionPane.showMessageDialog(null, "The new amount and interest has been registered into the system", "Confirmation", JOptionPane.INFORMATION_MESSAGE);
                                }

                                else {
                                    JOptionPane.showMessageDialog(null, "Invalid amount or interest, please re-inter it", "Warning", JOptionPane.WARNING_MESSAGE);
                                }

                                this.tfAmount.setText("");
                                this.tfInterest.setText("");
                            }
                        }
					}
					catch (NumberFormatException ex) {
						JOptionPane.showMessageDialog(null, "Please enter a valid value", "Warning", JOptionPane.WARNING_MESSAGE);
					}
				}
			}
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
