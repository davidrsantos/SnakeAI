package snake.snakeAI.ga.geneticOperators;

import snake.snakeAI.ga.GeneticAlgorithm;
import snake.snakeAI.ga.RealVectorIndividual;

//PLEASE, MODIFY THE CLASS NAME
public class MutationInvation<I extends RealVectorIndividual> extends Mutation<I> {


    public MutationInvation(double probability) {
        super(probability);
        // TODO pode haver outro tipo de mutacao
    }

    @Override
    public void run(I ind) {
        // TODO
        for (int i = 0; i < ind.getNumGenes(); i++) {
            if (GeneticAlgorithm.random.nextDouble()<probability){



                double x = GeneticAlgorithm.random.nextDouble() * 2 - 1;
                double y = GeneticAlgorithm.random.nextDouble() * 2 - 1;
                double v = 3 * Math.pow(1-x ,2)* Math.pow(Math.E, Math.pow(-x,2)) -Math.pow(y+ 1, 2) -10 *((x/5)-(x/3)-(y/5));
                ind.setGene(i, v);
                System.out.println("Valor da mutacao = " + v  + "valor de x = " );
                }
            }

    }

    @Override
    public String toString() {
        return "Uniform distribution mutation (" + probability /* + TODO?*/;
    }
}