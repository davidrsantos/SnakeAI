package snake.snakeAI.ga.experiments;

//import jdk.javadoc.internal.doclets.formats.html.SourceToHTMLConverter;
import snake.snakeAI.ga.GeneticAlgorithm;
import snake.snakeAI.ga.Problem;

import java.util.ArrayList;
import java.util.List;

public class Experiment<E extends ExperimentsFactory, P extends Problem> {

    private final E factory;
    private final int numRuns;
    private final P problem;
    private final String textualRepresentation;
    private int contExperiencias=0;
    //listeners
    final private List<ExperimentListener> listeners = new ArrayList<>(10);
    private GeneticAlgorithm ga;

    public Experiment(E factory, int numRuns, P problem, String textualRepresentation) {
        this.factory = factory;
        this.numRuns = numRuns;
        this.problem = problem;
        this.textualRepresentation = textualRepresentation;
    }

    public void run() {
        System.out.println("--------------Experiemcias Iniciadas Cuidado Não Fechar Este Main--------------");
        for (int run = 0; run < numRuns; run++) {
            ga = factory.generateGAInstance(run + 1);
            ga.run(problem);
            System.out.println((run + 1) + " - " + ga.getBestInRun());
            System.out.println("\n" + ga.toString());
        }
        System.out.println("----------------------------");
        fireExperimentEnded();
        contExperiencias++;
        System.out.println("Foi a experiencia numero: " + contExperiencias);
        System.out.println("--------------Cuidado Não Fechar Este Main--------------");
    }

    public synchronized void addExperimentListener(ExperimentListener listener) {
        if (!listeners.contains(listener)) {
            listeners.add(listener);
        }
    }

    public void fireExperimentEnded() {
        for (ExperimentListener listener : listeners) {
            listener.experimentEnded(new ExperimentEvent(this));
        }
    }

    @Override
    public String toString() {
        return textualRepresentation;
    }
}
