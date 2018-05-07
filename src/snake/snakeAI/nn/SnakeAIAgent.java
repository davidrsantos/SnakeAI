package snake.snakeAI.nn;

import snake.*;

import java.awt.*;

public class SnakeAIAgent extends SnakeAgent {

    final private int inputLayerSize;
    final private int hiddenLayerSize;
    final private int outputLayerSize;

    /**
     * Network inputs array.
     */
    final private int[] inputs;
    /**
     * Hiddden layer weights.
     */
    final private double[][] w1;
    /**
     * Output layer weights.
     */
    final private double[][] w2;
    /**
     * Hidden layer activation values.
     */
    final private double[] hiddenLayerOutput;
    /**
     * Output layer activation values.
     */
    final private int[] output;

    public SnakeAIAgent(
            Cell cell,
            int inputLayerSize,
            int hiddenLayerSize,
            int outputLayerSize, Environment environment) {
        super(cell, Color.BLUE, environment);
        this.inputLayerSize = inputLayerSize;
        this.hiddenLayerSize = hiddenLayerSize;
        this.outputLayerSize = outputLayerSize;
        inputs = new int[inputLayerSize];
        inputs[inputs.length - 1] = -1; //bias entry
        w1 = new double[inputLayerSize][hiddenLayerSize]; // the bias entry for the hidden layer neurons is already counted in inputLayerSize variable
        w2 = new double[hiddenLayerSize + 1][outputLayerSize]; // + 1 due to the bias entry for the output neurons
        hiddenLayerOutput = new double[hiddenLayerSize + 1];
        hiddenLayerOutput[hiddenLayerSize] = -1; // the bias entry for the output neurons
        output = new int[outputLayerSize];
    }


    /**
     * Initializes the network's weights
     *
     * @param weights vector of weights comming from the individual.
     */
    public void setWeights(double[] weights) {
        // TODO
    }

    /**
     * Computes the output of the network for the inputs saved in the class
     * vector "inputs".
     */
    private void forwardPropagation() {
        // TODO
        double sum;
        for (int i = 0; i < hiddenLayerSize; i++) { //ou w1[0].length
            sum = 0;
            for (int j = 0; j < inputLayerSize; j++) { //ou instance.length
              sum += inputs[j] * w1[j][i]; //TODO coloquei os inputs Luana
            }
            //aplicar a função sigmoide a hiddenLayerOutput[i] (função de ativação)
            hiddenLayerOutput[i] = 1 / (1 + Math.exp(-sum)); //ou Math.pow(Math.E, -sum)
        }

        for (int i = 0; i < outputLayerSize; i++) { //ou w2[0].length
            sum = 0;
            for (int j = 0; j < hiddenLayerSize + 1; j++) { //ou hiddenLayerOutput.length ou w2.length
                sum += hiddenLayerOutput[j] * w2[j][i];
            }
            //aplicar a função sigmoide a outputLayerOutput[i]
          output[i] = 1 / (1 + Math.exp(-sum));
        }

       //todo erro return outputLayerOutput;
    }

    @Override
    protected Action decide(Perception perception) {
        // TODO
        return null;
    }
}
