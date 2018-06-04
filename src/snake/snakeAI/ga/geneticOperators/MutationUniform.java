package snake.snakeAI.ga.geneticOperators;

import snake.snakeAI.ga.GeneticAlgorithm;
import snake.snakeAI.ga.RealVectorIndividual;

//PLEASE, MODIFY THE CLASS NAME
public class MutationUniform<I extends RealVectorIndividual> extends Mutation<I> {


    public MutationUniform(double probability) {
        super(probability);
        // TODO pode haver outro tipo de mutacao
    }

    @Override
    public void run(I ind) {
        // TODO


        for (int g = 0; g < ind.getNumGenes(); g++) {
            if (GeneticAlgorithm.random.nextDouble() < probability) {
                // Get new gene position
                int newGenePos = (int) (GeneticAlgorithm.random.nextDouble() * ind.getNumGenes());
                // Get genes to swap
                double gene1 = ind.getGene(newGenePos);
                double gene2 = ind.getGene(g);

                ind.setGene(g, gene1);
                ind.setGene(newGenePos, gene2);
            }
        }
    }


    @Override
    public String toString() {
        return "Mutation Uniformm  (" + probability + ")"/* +  TODO?*/;
    }
}