package snake.snakeAI;

import snake.Environment;
import snake.snakeAI.ga.RealVectorIndividual;

public class SnakeIndividual extends RealVectorIndividual<SnakeProblem, SnakeIndividual> {
    int numComidas;
    int numMovimentos;
//declarar o numero de comidas e mov e mostrar com o tostring para ter uma nocao
    public SnakeIndividual(SnakeProblem problem, int size /*TODO?*/) {
        super(problem, size);
        //TODO?
    }

    public SnakeIndividual(SnakeIndividual original) {
        super(original);
        //TODO
        this.fitness=original.fitness;
    }

    @Override
    //coloca 500x a cobra a mexer com os mesmoa pesos, só muda os inputs
    //implemntar um for //parecido com o botao simulate
    public double computeFitness() {
        int numeroSimulacoes = problem.getNumEvironmentSimulations(); //todo é para começar por aqui
      //todo call setWeitghs
       numComidas=0;
       numMovimentos=0;
        for(int i=0;i<numeroSimulacoes;i++){
            //todo luana
            Environment environment = problem.getEnvironment();
            environment.initialize(i);
            environment.setWeights(genome);
            environment.simulate();
            //devemos obter quantas comidas sa cobra comeu e os movimento antes de morrer
            numComidas += environment.getComidas(); //tem que valer muito masi que os mov
            numMovimentos += environment.getMovimentos();
            fitness = numComidas*10000+numMovimentos*10;
            //se tiver essas var no ambient colocar sempre a 0 em cada interaçao

        }
        return 0;
    }

    public double[] getGenome() {

        return super.genome; //todo verificar
    }

    @Override

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("\nfitness: ");
        sb.append(fitness);
        sb.append("\nComidas: ");
        sb.append(numComidas);
        sb.append("\nMovimetos: ");
        sb.append(numMovimentos);
        return sb.toString();
    }

    /**
     * @param i
     * @return 1 if this object is BETTER than i, -1 if it is WORST than I and
     * 0, otherwise.
     */
    @Override
    public int compareTo(SnakeIndividual i) {
        //TODO
        return 0;
    }

    @Override
    public SnakeIndividual clone() {
        return new SnakeIndividual(this);
    }
}
