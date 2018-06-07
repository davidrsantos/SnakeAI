package snake;

import snake.snakeAI.nn.SnakeAIAgent;
import snake.snakeAI.nn.SnakeAIAgent1;
import snake.snakeAdhoc.SnakeAdhocAgent;
import snake.snakeRandom.SnakeRandomAgent;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Environment {

    private final Cell[][] grid;
    private final List<SnakeAgent> agents;
    private final int maxIterations;
    //listeners
    private final ArrayList<EnvironmentListener> listeners = new ArrayList<>();
    public Random random;
    private Food food;
    public int tipo; //ramdom, adock, IAagent
    private boolean stop;
    protected int numMovimentos;
    protected int numComidas;
    public int numeroComidaAgente1;
    public int numeroComidaAgente2;
    private int numInputs;
    private int numOutputs;
    private int numHiddenUnits;


    public Environment(
            int size,
            int maxIterations, int tipo) {
        this.maxIterations = maxIterations;
        this.tipo = tipo;
        this.grid = new Cell[size][size];
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid.length; j++) {
                grid[i][j] = new Cell(i, j);
            }
        }

        this.agents = new ArrayList<>();
        this.random = new Random();
    }

    public Environment(int environmentSize, int maxIterations, int tipo, int numInputs, int numOutputs, int numHiddenUnits) {
        this(environmentSize, maxIterations, tipo);
        this.numInputs = numInputs;
        this.numOutputs = numOutputs;
        this.numHiddenUnits = numHiddenUnits;
    }

    public void initialize(int seed) {
        random.setSeed(seed);
        for (SnakeAgent snakeAgent : agents) {
            snakeAgent.getCell().setAgent(null);
            snakeAgent.limparTails();

        }
        agents.clear();
        if (food != null) {
            food.getCell().setFood(null);
            food = null;
        }
        placeAgents();
        placeFood();
    }

    private void placeAgents() {
        agents.clear();
        switch (tipo) {

            case 0:
                SnakeRandomAgent snakeRandomAgent = new SnakeRandomAgent(getCell(random.nextInt(grid.length), random.nextInt(grid.length)), Color.GREEN, this);
                agents.add(snakeRandomAgent);
                break;
            case 1:
                SnakeAdhocAgent snakeAdhocAgent = new SnakeAdhocAgent(getCell(random.nextInt(grid.length), random.nextInt(grid.length)), Color.GREEN, this);
                agents.add(snakeAdhocAgent);
                break;
            case 2:
                //só uma cobra
                SnakeAIAgent snakeAIAgent = new SnakeAIAgent(getCell(random.nextInt(grid.length), random.nextInt(grid.length)), numInputs, numHiddenUnits, numOutputs, this);
                agents.add(snakeAIAgent);

                break;
            case 3:
                //duas cobras iguais
                SnakeAIAgent snakeAIAgent1 = new SnakeAIAgent(getCell(random.nextInt(grid.length), random.nextInt(grid.length)), numInputs, numHiddenUnits, numOutputs, this);
                agents.add(snakeAIAgent1);

                SnakeAIAgent snakeAIAgent2 = new SnakeAIAgent(getCell(random.nextInt(grid.length), random.nextInt(grid.length)), numInputs, numHiddenUnits, numOutputs, this);
                agents.add(snakeAIAgent2);
                break;
            case 4://Duas Cobras diferentes

                SnakeAIAgent snakeAIAgent3 = new SnakeAIAgent(getCell(random.nextInt(grid.length), random.nextInt(grid.length)), numInputs, numHiddenUnits, numOutputs, this);
                agents.add(snakeAIAgent3); //Azul

                SnakeAIAgent1 snakeAIAgent4 = new SnakeAIAgent1(getCell(random.nextInt(grid.length), random.nextInt(grid.length)), numInputs, numHiddenUnits, numOutputs, this);
                agents.add(snakeAIAgent4); // Laranja

                break;
            case 5://segunda Snake

                SnakeAIAgent1 snakeAIAgentDumb = new SnakeAIAgent1(getCell(random.nextInt(grid.length), random.nextInt(grid.length)), numInputs, numHiddenUnits, numOutputs, this);
                agents.add(snakeAIAgentDumb);
        }
        //melhorar a existencia.
        //adicionar outra AI Snake com resultados diferente

    }

    public void placeFood() {
        int l, c;
        Cell cell;
        do {
            l = random.nextInt(grid.length);
            c = random.nextInt(grid.length);
            cell = getCell(l, c);
        } while (cell.hasAgent() || cell.hasTail());

        food = new Food(cell);
    }

    public Food getFood() {
        return food;
    }

    public void simulate() {
        stop = false;
        numMovimentos = 0;
        numComidas = 0;
        numeroComidaAgente1 = 0;
        numeroComidaAgente2 = 0;
        int i;
        for (i = 0; i < maxIterations && !stop; i++) {
            //System.out.println(i + 1);

            for (SnakeAgent agent : agents) {
                agent.act(this);

                fireUpdatedEnvironment();   //redesenha o painel
            }
            if (tipo == 4) {
                numeroComidaAgente1 = agents.get(0).getNumeroComidas(); //Azul
                numeroComidaAgente2 = agents.get(1).getNumeroComidas(); // Laranja

            }
        }

        numMovimentos = i;
    }

    public int getSize() {
        return grid.length;
    }

    public Cell getNorthCell(Cell cell) {
        if (cell.getLine() > 0) {
            return grid[cell.getLine() - 1][cell.getColumn()];
        }
        return null;
    }

    public Cell getSouthCell(Cell cell) {
        if (cell.getLine() < grid.length - 1) {
            return grid[cell.getLine() + 1][cell.getColumn()];
        }
        return null;
    }

    public Cell getEastCell(Cell cell) {
        if (cell.getColumn() < grid[0].length - 1) {
            return grid[cell.getLine()][cell.getColumn() + 1];
        }
        return null;
    }

    public Cell getWestCell(Cell cell) {
        if (cell.getColumn() > 0) {
            return grid[cell.getLine()][cell.getColumn() - 1];
        }
        return null;
    }

    public int getNumLines() {
        return grid.length;
    }

    public int getNumColumns() {
        return grid[0].length;
    }

    public final Cell getCell(int linha, int coluna) {
        return grid[linha][coluna];
    }

    public Color getCellColor(int linha, int coluna) {
        return grid[linha][coluna].getColor();
    }


    public void setStop(boolean stop) {
        this.stop = stop;
    }


    public synchronized void addEnvironmentListener(EnvironmentListener l) {
        if (!listeners.contains(l)) {
            listeners.add(l);
        }
    }

    public synchronized void removeEnvironmentListener(EnvironmentListener l) {
        listeners.remove(l);
    }

    public void fireUpdatedEnvironment() {
        for (EnvironmentListener listener : listeners) {
            listener.environmentUpdated();
        }
    }



    public void setWeights(double[] genome) {

        for (SnakeAgent agent : agents) {
            if (agent instanceof SnakeAIAgent)
                ((SnakeAIAgent) agent).setWeights(genome);
            if (agent instanceof SnakeAIAgent1)
                ((SnakeAIAgent1) agent).setWeights(genome);
        }
    }


    public void setWeightsEspecial(double[] genomeDuplo) {
        double[] genome1 = new double[genomeDuplo.length / 2];
        double[] genome2 = new double[genomeDuplo.length / 2];


        System.arraycopy(genomeDuplo, 0, genome1, 0, genome1.length);
        System.arraycopy(genomeDuplo, genomeDuplo.length / 2, genome2, 0, genome2.length);


        ((SnakeAIAgent) agents.get(0)).setWeights(genome1);
        ((SnakeAIAgent1) agents.get(1)).setWeights(genome2);
    }


    public void setNumComidas() {
        this.numComidas++;
    }


    public int getComidas() {
        return numComidas;
    }


    public int getMovimentos() {
        return numMovimentos;
    }


}
