package wadzap.tsp.gui;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTextField;

public class EvoConfigPanel extends JPanel{
	
	private static final long serialVersionUID = -7019778958428557298L;
	private GridBagConstraints c;
	JLabel popSizeLabel;
	JTextField  popSizeText;
	JLabel mutRateLabel;
	JSlider mutRateSlider;
	JLabel crossRateLabel;
	JSlider crossRateSlider;

	/*
	 * Панель настроек параметров эволюции
	 */
	public EvoConfigPanel()
	{
		init();
	}
	
	private void init()
	{
		c = new GridBagConstraints();
		c.fill = GridBagConstraints.HORIZONTAL;
		popSizeLabel = new JLabel("Размер популяции");
		popSizeText = new JTextField(3);
		mutRateLabel = new JLabel("Частота мутации");
		mutRateSlider = new JSlider(0, 100, 10);
		crossRateLabel = new JLabel("Частота кроссовера");
		crossRateSlider = new JSlider(0, 100, 10);
		this.setLayout(new GridBagLayout());
		c.gridx = 0;
		c.gridy = 0;
		this.add(popSizeLabel, c);
		c.gridx = 1;
		c.gridy = 0;
		this.add(popSizeText, c);
		c.gridx = 0;
		c.gridy = 1;
		this.add(mutRateLabel, c);
		c.gridx = 1;
		c.gridy = 1;
		this.add(mutRateSlider, c);
		c.gridx = 0;
		c.gridy = 2;
		this.add(crossRateLabel, c);
		c.gridx = 1;
		c.gridy = 2;
		this.add(crossRateSlider, c);
		this.setBorder(BorderFactory.createTitledBorder("Параметры генетического алгоритма"));
	}
	
	public int getMutRate()
	{
		return this.mutRateSlider.getValue(); 
	}
	
	public void setMutRate(int rate)
	{
		if ( (rate >= mutRateSlider.getMinimum()) && (rate <= mutRateSlider.getMaximum()) )
			this.mutRateSlider.setValue(rate);
	}
	
	public void setCrossRate(int rate)
	{
		if ((rate >= crossRateSlider.getMinimum()) && (rate <= crossRateSlider.getMaximum()))
		{
			this.crossRateSlider.setValue(rate);
		}
	}
	
	public int getCrossRate()
	{
		return this.crossRateSlider.getValue();
	}
	
	public int getPopSize()
	{
		int popSize = Integer.parseInt(this.popSizeText.getText());
		return popSize;
	}
	
	public void setPopSize(int popSize)
	{
		this.popSizeText.setText(Integer.toString(popSize));
	}

}
