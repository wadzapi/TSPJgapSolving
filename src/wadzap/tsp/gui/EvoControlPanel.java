package wadzap.tsp.gui;


import java.awt.BorderLayout;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;

public class EvoControlPanel extends JPanel {
	/*
	 * Панель управления эволюцией
	 */
	JButton startButt;
	private static final long serialVersionUID = 1318051105605743803L;

	public EvoControlPanel()
	{
		init();
	}
	
	public void init()
	{
		startButt = new JButton("Эволюция!");
		startButt.setFont(new Font("sansserif", Font.BOLD, 32));
		this.setLayout(new BorderLayout());
		this.add(startButt, BorderLayout.CENTER);
		this.setBorder(BorderFactory.createTitledBorder("Управление эволюцией"));
	}
}
