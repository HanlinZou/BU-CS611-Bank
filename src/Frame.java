import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public abstract class Frame implements ActionListener, GUIsetup
{
	protected JFrame frame;
	protected JPanel panel;
	protected JPanel up;
	protected JPanel down;
	
	protected JLabel lTitle;
	protected JButton buttonGoBack;
	public Frame()
	{
		frame = new JFrame();
		frame.setTitle("Shady Bank.Co");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(true);
		frame.setSize(600,1000);
		frame.setLayout(null);
		frame.getContentPane().setBackground(new Color(241,247,255));
		frame.setLocationRelativeTo(null);
		
		this.lTitle = new JLabel();
		this.buttonGoBack = new JButton("Back");
	}
	
	public void addPanel()
	{
		panel = new JPanel();
		panel.setBounds(0, 0, 600,1000);
		panel.setLayout(null);
		panel.setBackground(new Color(241,247,255));
		frame.add(panel);
	}
	public void addPanelResize(int row, int column, int horGap, int verGap)
	{
		this.frame.setLayout(new BorderLayout());
		
		this.up = new JPanel();
		this.down = new JPanel();
		this.up.setBackground(new Color(241,247,255));
		this.down.setBackground(new Color(241,247,255));
		
		this.up.setPreferredSize(new Dimension(600,200));
		this.down.setPreferredSize(new Dimension(500,700));
		
		this.up.setLayout(new BorderLayout());
		this.down.setLayout(new GridLayout(row,column,horGap,verGap));
		
		JPanel partition1 = new JPanel();
		JPanel partition2 = new JPanel();
		JPanel partition3 = new JPanel();
		partition1.setBackground(new Color(241,247,255));
		partition2.setBackground(new Color(241,247,255));
		partition3.setBackground(new Color(241,247,255));
		partition1.setPreferredSize(new Dimension(50,650));
		partition2.setPreferredSize(new Dimension(50,650));
		partition3.setPreferredSize(new Dimension(600,50));
		
		frame.add(partition1,BorderLayout.WEST);
		frame.add(partition2,BorderLayout.EAST);
		frame.add(partition3,BorderLayout.SOUTH);
		
		frame.add(this.up,BorderLayout.NORTH);
		frame.add(this.down,BorderLayout.CENTER);
		
		this.addTitle();
		this.addBackButton();
	}
	
	public void addTitle()
	{
		//this.lTitle.setText("         Manager Menu");
		this.lTitle.setFont(new Font(null, Font.BOLD, 50));
		this.lTitle.setPreferredSize(new Dimension(600,100));
		this.up.add(this.lTitle, BorderLayout.SOUTH);
	}
	
	public void addBackButton()
	{
		this.buttonGoBack.addActionListener(this);
		this.buttonGoBack.setFont(new Font(null, Font.BOLD, 25));
		this.buttonGoBack.setPreferredSize(new Dimension(100,100));
		this.buttonGoBack.setFocusable(false);
		this.up.add(this.buttonGoBack, BorderLayout.WEST);
	}
}
