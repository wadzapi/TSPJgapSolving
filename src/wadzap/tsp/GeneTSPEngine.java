package wadzap.tsp;

import org.jgap.event.EventManager;
import org.jgap.impl.BestChromosomesSelector;
import org.jgap.impl.ChromosomePool;
import org.jgap.impl.GreedyCrossover;
import org.jgap.impl.IntegerGene;
import org.jgap.impl.StockRandomGenerator;
import org.jgap.impl.SwappingMutationOperator;
import org.jgap.impl.salesman.Salesman;
import org.jgap.Chromosome;
import org.jgap.Configuration;
import org.jgap.DefaultFitnessEvaluator;
import org.jgap.Gene;
import org.jgap.IChromosome;
import org.jgap.InvalidConfigurationException;

public class GeneTSPEngine extends Salesman 
{
	private static final long serialVersionUID = -5031702371770223349L;
	protected static int DEFAULT_MUT_RATE = 20;
	protected static double DEFAULT_CROSS_RATE = 0.8d;
	protected static int DEFAULT_POP_SIZE = 100;
	protected int mutationRateDenom;
	protected double crossoverRate;
	protected TSPProblem tsp;
	IChromosome optimal;
	
	public GeneTSPEngine(TSPProblem tsp)
	{
		setTSP(tsp);
		/*
		 * установка стандартных значений
		 */
		this.mutationRateDenom = DEFAULT_MUT_RATE;
		this.crossoverRate = DEFAULT_CROSS_RATE;
	}
	
	public Configuration createConfiguration(final Object a_initial_data)
			throws InvalidConfigurationException {
		/* —копировано из DefaultConfiguration,
		 * ƒобавл€ет возможность настройки
		 * параметры частоты кроссинговера и мутации
		 */
		Configuration config = new Configuration();
		BestChromosomesSelector bestChromsSelector =
				new BestChromosomesSelector(config, crossoverRate);
		bestChromsSelector.setDoubletteChromosomesAllowed(false);
		config.addNaturalSelector(bestChromsSelector, true);
		config.setRandomGenerator(new StockRandomGenerator());
		config.setMinimumPopSizePercent(0);
		config.setEventManager(new EventManager());
		config.setFitnessEvaluator(new DefaultFitnessEvaluator());
		config.setChromosomePool(new ChromosomePool());
		config.addGeneticOperator(new GreedyCrossover(config));
		config.addGeneticOperator(new SwappingMutationOperator(config, mutationRateDenom));
		return config;
	}
	
	public double distance(Gene a_from, Gene a_to)
	{
		IntegerGene a = (IntegerGene)a_from;
		IntegerGene b = (IntegerGene)a_to;
		
		int cityAIndex = a.intValue();
		int cityBIndex = b.intValue();
		
		double dist = tsp.getDist(cityAIndex, cityBIndex);
	
		return dist;
	}
	
	
	
	public void setTSP(TSPProblem tsp)
	{
		Configuration.reset();
		this.tsp = tsp;
	}
	
	
	public int getCrossoverRate() {
		int crossProc = (int)(100 * crossoverRate);
		return crossProc;
	}

	public void setCrossoverRate(int crossoverRate) {
		this.crossoverRate = (double)crossoverRate/100.0d;
	}

	public int getPopSize() {
		return super.getPopulationSize();
	}

	public void setPopSize(int popSize) {
		super.setPopulationSize(popSize);
	}

	public int getMutationRate() {
		return 100/mutationRateDenom;
	}

	public void setMutationRate(int mutationPercent) {
		
		this.mutationRateDenom = 100/mutationPercent;
	}
	

	public Path getBestPath()
	{
		Path bestPath = new Path();
		try {
			optimal = findOptimalPath(null);
		} catch (Exception e) {
			e.printStackTrace();
		}
		bestPath = convert2Path(optimal);
		return bestPath;
	}
	
	
	public Path convert2Path(IChromosome chromo)
	{
		Path resultPath = new Path();
		if (chromo != null)
		{
			Gene[] bestGenes = chromo.getGenes();
			for (int i = 0; i < bestGenes.length; i++)
			{
				int genValue = ((IntegerGene)bestGenes[i]).intValue();
				resultPath.add(tsp.getCity(genValue));
			}
		}
		return resultPath;
	}

	@Override
	public IChromosome createSampleChromosome(Object arg0) {
		int genesLen = tsp.getCityNum();
		Gene[] genes = new Gene[genesLen];
		try
		{
			for (int i = 0; i < genesLen; i++ )
			{
				genes[i] = new IntegerGene(this.getConfiguration(), 0, genesLen - 1);
				genes[i].setAllele(new Integer(i));
			}
			IChromosome sample = new Chromosome(this.getConfiguration(), genes);
			return sample;
		}
		catch (InvalidConfigurationException iex)
		{
			throw new IllegalStateException(iex.getMessage());
		}
	}

	
	public double getFitness()
	{
		double fitVal = Double.NaN;
		if (optimal != null)
			fitVal = optimal.getFitnessValue();
		return fitVal;
	}
}
