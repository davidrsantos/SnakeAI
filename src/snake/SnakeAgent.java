package snake;

        import java.awt.*;
        import java.util.LinkedList;

public abstract class SnakeAgent {



    public int numeroComidas;
    protected Cell cell;
    protected Color color;
    public Environment environment;
    private LinkedList<Tail> tails = new LinkedList<>();

    public SnakeAgent(Cell cell, Color color, Environment environment) {
        this.cell = cell;
        if (cell != null) {
            this.cell.setAgent(this);
        }
        this.color = color;
        this.environment = environment;
        this.numeroComidas=0;
    }


    public void act(Environment environment) {
        Perception perception = buildPerception(environment);
        Action action = decide(perception);
        execute(action, environment);
    }

    protected Perception buildPerception(Environment environment) {
        return new Perception(
                environment.getNorthCell(cell),
                environment.getSouthCell(cell),
                environment.getEastCell(cell),
                environment.getWestCell(cell));


    }

    protected void execute(Action action, Environment environment) {


        Cell nextCell = null;
        if (action == Action.NORTH && cell.getLine() != 0) {
            nextCell = environment.getNorthCell(cell);
        } else if (action == Action.SOUTH && cell.getLine() != environment.getNumLines() - 1) {
            nextCell = environment.getSouthCell(cell);
        } else if (action == Action.WEST && cell.getColumn() != 0) {
            nextCell = environment.getWestCell(cell);
        } else if (action == Action.EAST && cell.getColumn() != environment.getNumColumns() - 1) {
            nextCell = environment.getEastCell(cell);
        }
        if (nextCell != null&& !nextCell.hasAgent() && !nextCell.hasTail() ) {

            if (nextCell.hasFood()) {
                Tail tail = new Tail(cell);
                tails.addFirst(tail);
                environment.setNumComidas();

                numeroComidas++;
            } else {
                if (!tails.isEmpty()) {
                    tails.addFirst(new Tail(cell));
                    tails.getLast().getCell().setTail(null);
                    tails.removeLast();
                }
            }
            setCell(nextCell);
        }else

            environment.setStop(true);

    }


    protected abstract Action decide(Perception perception);

    public Cell getCell() {
        return cell;
    }

    public void setCell(Cell newCell) {
        if (this.cell != null) {
            this.cell.setAgent(null);
        }
        this.cell = newCell;
        if (newCell != null) {
            newCell.setAgent(this);
        }
        if (cell.hasFood()) {
            cell.setFood(null);
            environment.placeFood();

        }
    }

    public Color getColor() {
        return color;
    }

    public void limparTails() {
        for (Tail t: tails){
            t.getCell().setTail(null);
        }
        tails.clear();
    }
    public int getNumeroComidas() {
        return numeroComidas;
    }
}
