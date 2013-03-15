package wadzap.tsp.gui;

import java.awt.Dimension;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;

public class EvoStatusBar extends JPanel {
	
	private static final long serialVersionUID = -8038484159434011736L;
	protected JLabel statusLabel;
	public EvoStatusBar(int width)
	{
		this.setBorder(new BevelBorder(BevelBorder.LOWERED));
		this.setPreferredSize(new Dimension(width, 16));
		this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		statusLabel = new JLabel("Задайте параметры и нажмите на Эволюция!");
		statusLabel.setHorizontalAlignment(SwingConstants.LEFT);
		this.add(statusLabel);
	}
	
	public void setStatus(String status)
	{
		statusLabel.setText(status);
	}
	
	public void setFitnessStatus(double fitness)
	{
		statusLabel.setText("Значение фитнес-функции: " + Double.toString(fitness));
	}
}
