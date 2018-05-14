package snake;

import snake.snakeAI.nn.SnakeAIAgent;
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
    protected int numMovimentos; //todo confirmar prof luana
    protected int numComidas;

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
        //todo falta o resto do construtor correspondente ao snakeIA

        this(environmentSize,maxIterations,tipo);

    }

    public void initialize(int seed) {
        random.setSeed(seed);
        numComidas=0; //todo confirmar prof luana
        numMovimentos=0;
        for (SnakeAgent snakeAgent : agents) {
            snakeAgent.getCell().setAgent(null);
            snakeAgent.limparTails();
        }
        agents.clear();

        if (food != null){
            food.getCell().setFood(null);
            food = null;
    }

        placeAgents();
        placeFood();

    }

    private void placeAgents() {
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
                //TODO não esquecer de ativar a  snake IA
            //    SnakeAIAgent snakeAIAgent = new SnakeAIAgent(getCell(random.nextInt(grid.length), random.nextInt(grid.length)), Color.GREEN);
                break;
        }
    }

    public void placeFood() {
        int l,c;
        Cell cell;
        do {
            l=random.nextInt(grid.length);
            c=random.nextInt(grid.length);
            cell=getCell(l,c);
        }while (cell.hasAgent() || cell.hasTail());

        food = new Food(cell);
    }

    public Food getFood() {
        return food;
    }
    public void simulate() {
        stop=false;
        for (int i = 0; i < maxIterations && !stop; i++) {
            System.out.println(i + 1);

            for (SnakeAgent agent : agents) {
                agent.act(this);
                fireUpdatedEnvironment();   //redesenha o painel
            }
        }
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

    public boolean isStop() {
        return stop;
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

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }


    public void setWeights(double[] genome) {
       // snakeAIAgent
    }

    public int getComidas() {
        //TODO luana
        return numComidas;
    }

    public int getMovimentos() {
        //todo luana
        return numMovimentos;
    }
}
