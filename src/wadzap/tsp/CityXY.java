package wadzap.tsp;

import java.awt.Color;
import java.awt.Graphics;

/*
 * ����� ����� ��� �������� ������������
 * ����� ������� ����������� � ���� ����� �� ���������,
 * �������� ���������� ������������
 */
public class CityXY
{	
	protected static int DEFAULT_RADIUS = 10;
	protected static Color DEFAULT_COLOR = Color.green;
	protected static Color FIRST_COLOR = Color.red;
	protected boolean isFirst = false;
	
	/*
	 * ����� ��������� ������ 
	 */
	protected int x; 
	protected int y;
	
	public double distanceTo(CityXY oth)
	{
		double dx = oth.x  - this.x;
		double dy = oth.y - this.y;
		return Math.sqrt(dx*dx + dy*dy);
	}
	
	/*
	 * ����������� ������ �����
	 */
	public CityXY()	{}
	
	/*
	 * ����������� ������ �����
	 */
	public CityXY(int x, int y)
	{
		this.x = x;
		this.y = y;
	}
	
	public void draw(Graphics g)
	{
		Color oldCol = g.getColor();
		if (!isFirst)
			g.setColor(DEFAULT_COLOR);
		else
			g.setColor(FIRST_COLOR);
		g.fillOval(x - DEFAULT_RADIUS/2, y - DEFAULT_RADIUS/2, DEFAULT_RADIUS, DEFAULT_RADIUS);
		g.setColor(oldCol);
	}
	
	public static void setCityRadius(int rad)
	{
		DEFAULT_RADIUS = rad;
	}
	
	public void setFirst()
	{
		this.isFirst = true;
	}
	
	public void setX(int x) {
		this.x = x;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getX()
	{
		return this.x;
	}
	
	public int getY()
	{
		return this.y;
	}
}
