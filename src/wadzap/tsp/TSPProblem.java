package wadzap.tsp;


import java.awt.Graphics;
import java.util.Random;



/*
 *  ласс, представл€ющий абстракцию проблемы коммиво€жера
 * —одержит необходимые исходные данные - набор городов и рассто€ний,
 * методы дл€ задани€ и генерации исходных данных проблемы
 */
public class TSPProblem {
	
	protected int citiesNum; // „исло городов
	protected CityXY[] cities; //ћатрица городов
	protected boolean isPrecalcedDistance;//‘лаг, задающий предварительный просчет дистанций
	protected double[][] distMatrix; //ћатрица стоимости путешестви€ между городами
	protected Random rnd;
	protected int firstCity;
	
	public TSPProblem(int citiesNum, int maxX, int maxY)
	{
		this.citiesNum = citiesNum; //число городов
		cities = new CityXY[citiesNum];
		rnd = new Random();
		createRNDCities(maxX, maxY); //случайно генерируем города
		isPrecalcedDistance = false;
		if (isPrecalcedDistance)
		{
			distMatrix = new double[citiesNum][citiesNum];
			calcDistMatix();
		}
	}
	
	/*
	 *  —оздание городов со случайными адресами
	 */
	private void createRNDCities(int maxX, int maxY)
	{
		for (int i = 0; i < citiesNum; i++)
		{
			cities[i] = new CityXY(rnd.nextInt(maxX), rnd.nextInt(maxY));
		}
		cities[0].setFirst();
	}
	
	/*
	 * ¬ычисление матрицы рассто€ний
	 */
	private void calcDistMatix()
	{
		for (int i = 0;  i < citiesNum; i++)
		{
			CityXY cityI = cities[i];  
			for(int j = 0; j < citiesNum; j++)
			{
				distMatrix[i][j] = cityI.distanceTo(cities[j]);
			}
		}
	}
	
	public double getDist(int cityAIndex, int cityBIndex)
	{
		return cities[cityAIndex].distanceTo(cities[cityBIndex]);
	}
	
	public int getCityNum()
	{
		return citiesNum;
	}
	
	public void draw(Graphics g)
	{
		for (CityXY city : cities)
		{
			city.draw(g);
		}
	}
	
	public CityXY getCity(int index)
	{
		CityXY returnCity = null;
		if ( index >= 0 && index <= citiesNum)
		{
			returnCity = cities[index];
		}
		return returnCity;
	}
}
