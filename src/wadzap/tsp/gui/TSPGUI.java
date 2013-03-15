package wadzap.tsp.gui;

import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import wadzap.tsp.GeneTSPEngine;
import wadzap.tsp.Path;
import wadzap.tsp.TSPProblem;


public class TSPGUI  extends JFrame implements ActionListener
{
	protected boolean isTSPInit = false;
	protected boolean isEngineInit = false;
	
	protected static int DEFAULT_CITY_NUM = 30;
	protected final int DEFAULT_WIDTH = 800;
	protected final int DEFAULT_HEIGHT = 600;
	protected final String APPTITLE = "Генетический алгоритм для решения проблемы коммивояжера";
	private static final long serialVersionUID = -2632471208095066651L;
	
	TSPProblem tsp;
	GeneTSPEngine engineTSP;
	Path bestPath;
	TSPCanvas canvas;
	TSPConfigPanel tspCfg;
	EvoConfigPanel evoCfg;
	EvoControlPanel evoCtrl;
	EvoStatusBar evoStatus;
	JPanel settingsPanel;

	
	public TSPGUI() {
		init();
		initPanels();
		setDefaultTSP();
		setDefaultEngine();

	}
	
	private void init()
	{
		this.setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
		this.setTitle(APPTITLE);
		this.setResizable(false);
		ImageIcon tspIcon = new ImageIcon("tspIcon.jpg");
		this.setIconImage(tspIcon.getImage());
		this.setLayout(new BorderLayout());
		this.bestPath = new Path();
	}
	
	private void initPanels()
	{
		tspCfg = new TSPConfigPanel();
		tspCfg.generateCities.addActionListener(this);
		evoCfg = new EvoConfigPanel();
		evoCtrl = new EvoControlPanel();
		evoCtrl.startButt.addActionListener(this);
		settingsPanel = new JPanel();
		settingsPanel.setLayout(new GridLayout(3, 1, 2, 10 ));
		settingsPanel.add(tspCfg);
		settingsPanel.add(evoCfg);
		settingsPanel.add(evoCtrl);
		this.add(settingsPanel, BorderLayout.EAST);
		canvas = new TSPCanvas(DEFAULT_HEIGHT, DEFAULT_HEIGHT);
		this.add(canvas, BorderLayout.WEST);
		evoStatus = new EvoStatusBar(this.getWidth());
		this.add(evoStatus, BorderLayout.SOUTH);
	}
	
	private void setDefaultTSP()
	{
		tsp = new TSPProblem(DEFAULT_CITY_NUM, DEFAULT_HEIGHT - 20 , DEFAULT_HEIGHT - 20);
		this.tspCfg.setCityNum(DEFAULT_CITY_NUM);
	}
	
	private void setDefaultEngine()
	{
		engineTSP = new GeneTSPEngine(tsp);
		this.evoCfg.setCrossRate(engineTSP.getCrossoverRate());
		this.evoCfg.setMutRate(engineTSP.getMutationRate());
		this.evoCfg.setPopSize(engineTSP.getPopSize());
	}
	
	
	public void paint(Graphics g)
	{
		super.paintComponents(g);
		Graphics canvasGr = canvas.getGraphics();
		tsp.draw(canvasGr);
		bestPath.draw(canvasGr);
	}
	
	private static void createAndShowGUI()
	{
		TSPGUI panel = new TSPGUI(); 
		panel.setDefaultCloseOperation(EXIT_ON_CLOSE);
		panel.pack();
		panel.setVisible(true);
	}
	
	public static void main(String[] args)
	{
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		switch(e.getActionCommand()) {
		case "Сгенерировать": 
			tsp = new TSPProblem(tspCfg.getCitiesNum(), canvas.getWidth(), canvas.getHeight());
			bestPath.clear();
			this.repaint();
		break;
		case "Эволюция!": 
			this.evoStatus.setStatus("Подождите, эволюция идет...");
			this.evoStatus.repaint();
			this.engineTSP.setCrossoverRate(this.evoCfg.getCrossRate());
			this.engineTSP.setMutationRate(this.evoCfg.getMutRate());
			this.engineTSP.setTSP(tsp);
			this.bestPath = this.engineTSP.getBestPath();
			this.evoStatus.setFitnessStatus(engineTSP.getFitness());
			this.repaint();
		break;
		}
		
		
	}

}
