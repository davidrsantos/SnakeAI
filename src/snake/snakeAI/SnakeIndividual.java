package snake.snakeAI;

import snake.Environment;
import snake.snakeAI.ga.RealVectorIndividual;

public class SnakeIndividual extends RealVectorIndividual<SnakeProblem, SnakeIndividual> {
    private int numComidas;
    private int numMovimentos;
    private int numComidaAgente1;
    private int numComidaAgente2;
    boolean isDoubleSnakeHeterogeneous;

    //declarar o numero de comidas e mov e mostrar com o tostring para ter uma nocao
    public SnakeIndividual(SnakeProblem problem, int size) {
        super(problem, size);
    }

    public SnakeIndividual(SnakeIndividual original) {
        super(original);
        this.fitness = original.fitness;
        this.numMovimentos = original.numMovimentos;
        this.numComidas = original.numComidas;
        this.numComidaAgente1 = original.numComidaAgente1;
        this.numComidaAgente2 = original.numComidaAgente2;

    }

    @Override
    //coloca 500x a cobra a mexer com os mesmoa pesos, só muda os inputs
    //implemntar um for //parecido com o botao simulate
    public double computeFitness() {
        int numeroSimulacoes = problem.getNumEvironmentSimulations();
        numComidas = 0;
        numMovimentos = 0;
        fitness = 0;
        numComidaAgente2 = 0;
        numComidaAgente1 = 0;
        int penalty = 0;
        for (int i = 0; i < numeroSimulacoes; i++) {

            Environment environment = problem.getEnvironment();


            environment.initialize(i);
            if (environment.tipo == 4) {
                environment.setWeightsEspecial(genome);
                isDoubleSnakeHeterogeneous = true;
            } else {
                environment.setWeights(genome);
            }


            environment.simulate();

            numComidaAgente1 += environment.numeroComidaAgente1;
            numComidaAgente2 += environment.numeroComidaAgente2;
            numComidas += environment.getComidas();
            numMovimentos += environment.getMovimentos();

        }
        if (isDoubleSnakeHeterogeneous) {
            penalty += Math.abs(numComidaAgente2 - numComidaAgente1) * 10000;
            fitness = (numComidas * 10000 + numMovimentos * 0.5) - penalty;
        } else {

            fitness = numComidas * 10000 + numMovimentos * 0.01;
        }
        return fitness;
    }

    public double[] getGenome() {
        return super.genome;
    }

    @Override

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("\nfitness: ");
        sb.append(fitness);
        sb.append("\nComidas: ");
        sb.append((double) numComidas / problem.getNumEvironmentSimulations());
        sb.append("\nMovimetos: ");
        sb.append((double) numMovimentos / problem.getNumEvironmentSimulations());
        if(isDoubleSnakeHeterogeneous) {
            sb.append("\nComidas Azul: ");
            sb.append((double) numComidaAgente1 / problem.getNumEvironmentSimulations());
            sb.append("\nComidas Laranja: ");
            sb.append((double) numComidaAgente2 / problem.getNumEvironmentSimulations());
        }
        return sb.toString();
    }

    @Override
    public int compareTo(SnakeIndividual i) {
        if (fitness < i.fitness)
            return -1;
        if (fitness > i.fitness)
            return 1;
        return 0;
    }

    @Override
    public SnakeIndividual clone() {
        return new SnakeIndividual(this);
    }
}
