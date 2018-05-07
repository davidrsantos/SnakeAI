package snake.snakeAI.ga;

public abstract class RealVectorIndividual<P extends Problem, I extends RealVectorIndividual> extends Individual<P, I> {
    // TODO verificar

   // public static final boolean ONE = true; TODO tem de se decidir para que valor é true e para que valor é false
   // public static final boolean ZERO = false;

    protected float[] genome;

    public RealVectorIndividual(P problem, int size) {
        super(problem);
        // TODO este muda referente a ficha o genoma é constituido por pesos, na ficha é 01has
        genome = new float[size];
        for (int g = 0; g < genome.length; g++) {
            //todo aplicar abaixo mas em real!!!
          //  genome[g] = (GeneticAlgorithm.random.nextDouble() < prob1s) ? ONE : ZERO;
        }
    }

    public RealVectorIndividual(RealVectorIndividual<P, I> original) {
        super(original);
        // TODO verificar
        this.genome = new float[original.genome.length];
        System.arraycopy(original.genome, 0, genome, 0, genome.length);
    }

    @Override
    public int getNumGenes() {
        // TODO verificaar
        return genome.length;
    }

    public double getGene(int index) {
        // TODO verificar
        return genome[index];
    }

    public void setGene(int index, double newValue) {
        // TODO
    }

    @Override
    public void swapGenes(RealVectorIndividual other, int index) {
        // TODO
    }
}
