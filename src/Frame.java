import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Frame
{
	protected JFrame frame;
	protected JPanel panel;
	public Frame()
	{
		frame = new JFrame();
		frame.setTitle("Shady Bank.Co");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setSize(600,1000);
		frame.setLayout(null);
		frame.getContentPane().setBackground(Color.DARK_GRAY);
		frame.setLocationRelativeTo(null);
	}
	
	public void addPanel()
	{
		panel = new JPanel();
		panel.setBounds(0, 0, 600,1000);
		panel.setLayout(null);
		panel.setBackground(new Color(241,247,255));
		frame.add(panel);
	}
	
}
