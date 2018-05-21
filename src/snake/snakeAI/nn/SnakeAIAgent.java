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
    public void setWeights(double[] weights) {//preenche o genoma
        int x = 0;
        for (int i = 0; i < w1.length; i++) { //em vez de w1.length pode ser inputLayerSize
            for (int j = 0; j < w1[i].length; j++) { //em de w1[i].lenght pode ser w1[0].length ou hiddenLayerSize
                this.w1[i][j] = weights[x];
                x++;
            }
        }

        for (int i = 0; i < w2.length; i++) { //ou hiddenLayerSize+1
            for (int j = 0; j < w2[i].length; j++) {
                this.w2[i][j] = weights[x];
                x++;
            }
        }
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
        double maior=Double.MIN_VALUE;
        int pos=0;
        for (int i = 0; i < outputLayerSize; i++) { //ou w2[0].length
            sum = 0;
            for (int j = 0; j < hiddenLayerSize + 1; j++) { //ou hiddenLayerOutput.length ou w2.length
                sum += hiddenLayerOutput[j] * w2[j][i];

            }
            output[i]=0;
            if( sum >maior){
                maior=sum;
               pos=i;
            }

            //aplicar a função sigmoide a outputLayerOutput[i]
            //TODO ERRO output[i] = 0ou1 temos de dedicder como vamos escolher os outputs conforme o que a sum nos dá
        }
        output[pos]=1;

        //todo erro return outputLayerOutput;
    }

    @Override

    //preenche os inputs....sao relativos a posicao atual da snake por ex:
    //verificar onde esta a comida copiar do snake adock
    //só uma direção pode ter 1 e o resto zeros


    protected Action decide(Perception perception) {
        // TODO
        //convem ter 8 ou mais inputs
   //     temos de daar mais ifs tambem referentes a comida // e a qualquer outra coisa que se no lembremos de para ajudar a cobra a aprender;
        if(perception.getN() !=null && !perception.getN().hasTail())
            inputs[0]=1;
        else inputs[0]=0;
        if(perception.getE() !=null && !perception.getE().hasTail())
            inputs[1]=1;
        else inputs[1]=0;
        if(perception.getS() !=null && !perception.getS().hasTail())
            inputs[2]=1;
        else inputs[2]=0;
        if(perception.getW() !=null && !perception.getW().hasTail())
            inputs[3]=1;
        else inputs[3]=0;

        if (environment.getFood().getCell().getLine() < cell.getLine())
            inputs[4]=1;
        else inputs[4]=0;

        if (environment.getFood().getCell().getLine() > cell.getLine())
            inputs[5]=1;
        else inputs[5]=0;

        if (environment.getFood().getCell().getColumn() > cell.getColumn())
            inputs[6]=1;
        else inputs[6]=0;

        if (environment.getFood().getCell().getColumn() < cell.getColumn())
            inputs[7]=1;
        else inputs[7]=0;

        // todo fazer o forward
    forwardPropagation();

        if(output[0]==1)
            return Action.NORTH;
        if(output[1]==1)
            return Action.EAST;
        if(output[2]==1)
            return Action.SOUTH;
        if(output[3]==1)
            return Action.WEST;
        return null;
    }
}
