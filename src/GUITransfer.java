import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;

public class GUITransfer extends Frame implements GUIsetup, ActionListener 
{
	private String uid;
	
	private JLabel labelTitle;
	
	private JButton buttonGoBack;
	
	public GUITransfer(String uid)
	{
		super();
		super.addPanel();
		super.frame.setVisible(true);
		
		this.uid = uid;
		
		this.labelTitle = new JLabel();
		
		this.buttonGoBack = new JButton("Back");
		
		this.setButton();
		this.setLabel();
		this.setPanel();
	}
	@Override
	public void setLabel() 
	{
		// TODO Auto-generated method stub
		this.labelTitle.setText("Transfer");
		this.labelTitle.setFont(new Font(null, Font.BOLD, 50));
		this.labelTitle.setBounds(180,50,600,100);
	}

	@Override
	public void setButton() 
	{
		// TODO Auto-generated method stub
		this.buttonGoBack.addActionListener(this);
		this.buttonGoBack.setFont(new Font(null, Font.BOLD, 25));
		this.buttonGoBack.setBounds(0, 0, 100, 100);
		this.buttonGoBack.setFocusable(false);
	}

	@Override
	public void setPanel()
	{
		// TODO Auto-generated method stub
		super.panel.add(this.labelTitle);
		super.panel.add(this.buttonGoBack);
	}

	@Override
	public void actionPerformed(ActionEvent e) 
	{
		// TODO Auto-generated method stub
		super.frame.dispose();
		if(e.getSource() == this.buttonGoBack)
		{
			new GUIMainMenu(this.uid);
		}
	}

}
