package snake.snakeAI.ga.geneticOperators;

import snake.snakeAI.ga.GeneticAlgorithm;
import snake.snakeAI.ga.RealVectorIndividual;

//PLEASE, MODIFY THE CLASS NAME
public class MutationMUTATION_NAME<I extends RealVectorIndividual> extends Mutation<I> {


    public MutationMUTATION_NAME(double probability /*TODO?*/) {
        super(probability);
        // TODO
    }

    @Override
    public void run(I ind) {
        // TODO
        for (int i = 0; i < ind.getNumGenes(); i++) {
            if (GeneticAlgorithm.random.nextDouble()<probability){
                if(ind.getGene(i) != I.ONE) {
                    ind.setGene(i, ind.getGene(i));
                }
            }
        }
    }

    @Override
    public String toString() {
        return "Uniform distribution mutation (" + probability /* + TODO?*/;
    }
}