package wadzap.tsp;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

public class Path extends ArrayList<CityXY>
{
	protected static int DEFAULT_LINE_WIDTH = 2;
	
	
	private static final long serialVersionUID = 4739835765847062512L;
	private static Color DEFAULT_COLOR = Color.blue;
	private int dotRad = 4;
	private Color drawColor;
	
	public Path()
	{
		super();
		this.drawColor = DEFAULT_COLOR;
	}
	
	/*
	 * Расчет стоимости маршрута
	 */
	public double getLength()
	{
		double pathLength = 0.0;
		int maxIter = this.size() - 1;
		CityXY cityA;
		CityXY cityB;
		for (int i = 0; i < maxIter; i++)
		{
			cityA = this.get(i);
			cityB = this.get(i + 1);
			pathLength += cityA.distanceTo(cityB);
		}
		return pathLength;
	}
	
	public void draw(Graphics g)
	{
		if (this.size() > 1)
		{
		Color oldColor = g.getColor();
		g.setColor(this.drawColor);
		int city1x, city1y;
		int city2x, city2y;
		int offset = dotRad/2;
		int maxIter = this.size() - 1;
		for (int i = 0; i < maxIter; i++)
		{
			city1x = this.get(i).getX();
			city1y = this.get(i).getY();
			city2x = this.get(i+1).getX();
			city2y = this.get(i+1).getY();
			g.fillOval(city1x - offset, city1y - offset, dotRad, dotRad);
			g.fillOval(city2x - offset, city2y - offset, dotRad, dotRad);
			g.drawLine(city1x, city1y, city2x, city2y);
		}
		g.drawLine(this.get(maxIter).x, this.get(maxIter).y, this.get(0).x, this.get(0).y);
		g.setColor(oldColor);
		}
	}

}
