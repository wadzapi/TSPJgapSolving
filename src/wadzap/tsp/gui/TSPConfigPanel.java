package wadzap.tsp.gui;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class TSPConfigPanel extends JPanel{
	/*
	 * Панель настройки начальной конфигурации
	 */

	private static final long serialVersionUID = -4906583158245423837L;
	GridBagConstraints c;
	JTextField citiesNumTextBox;
	JLabel citiesNumLabel;
	JButton generateCities;
	
	public TSPConfigPanel()
	{
		init();
	}
	
	private void init()
	{
		c = new GridBagConstraints();
		c.fill = GridBagConstraints.HORIZONTAL;
		citiesNumLabel = new JLabel("Количество городов: ");
		citiesNumTextBox = new JTextField(3);
		generateCities = new JButton("Сгенерировать");
		this.setLayout(new GridBagLayout());
		c.gridx = 0;
		c.gridy = 0;
		c.gridheight = 1;
		c.gridwidth = 1;
		this.add(citiesNumLabel, c);
		c.gridx = 1;
		c.gridy = 0;
		this.add(citiesNumTextBox, c);
		c.gridx = 0;
		c.gridy = 1;
		c.gridwidth = 2;
		this.add(generateCities, c);
		this.setBorder(BorderFactory.createTitledBorder("Исходные данные задачи коммивояжера"));
	}
	
	public int getCitiesNum()
	{
		int citiesNum = Integer.parseInt(this.citiesNumTextBox.getText());
		return citiesNum;
	}
	
	public void setCityNum(int num)
	{
		this.citiesNumTextBox.setText(Integer.toString(num));
	}
	
}
