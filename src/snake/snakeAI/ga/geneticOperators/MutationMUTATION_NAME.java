package snake.snakeAI.ga.geneticOperators;

import snake.snakeAI.ga.GeneticAlgorithm;
import snake.snakeAI.ga.RealVectorIndividual;

//PLEASE, MODIFY THE CLASS NAME
public class MutationMUTATION_NAME<I extends RealVectorIndividual> extends Mutation<I> {


    public MutationMUTATION_NAME(double probability) {
        super(probability);
        // TODO pode haver outro tipo de mutacao
    }

    @Override
    public void run(I ind) {
        // TODO
        for (int i = 0; i < ind.getNumGenes(); i++) {
            if (GeneticAlgorithm.random.nextDouble() < probability) {


                double v = GeneticAlgorithm.random.nextDouble() * 2 - 1;
                ind.setGene(i, v);
                System.out.println("Valor da mutacao = " + v + "valor de x = ");
            }
        }

    }

    @Override
    public String toString() {
        return "Uniform distribution mutation (" + probability /* + TODO?*/;
    }
}