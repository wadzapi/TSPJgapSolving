package wadzap.tsp.gui;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

public class TSPCanvas extends JPanel
{
	private static final long serialVersionUID = 3311498454102321518L;
	
	protected Dimension workSpaceSize;
	protected Color drawingColor;
	protected int lineWidth;
	protected int cityRad;

	
	public TSPCanvas(int width, int height)
	{
		this.workSpaceSize = new Dimension(width, height);
		this.setPreferredSize(this.workSpaceSize);
		this.setBorder(BorderFactory.createLineBorder(Color.black, 2));
		this.setVisible(true);
	}
	
	public void paintComponent(Graphics g)
	{
		super.paintComponents(g);
	}
	
	
	public void setDrawingColor(Color drawingColor)
	{
		this.drawingColor = drawingColor;
	}
	
	public void setLineWidth(int width)
	{
		this.lineWidth = width;
	}
	
}
