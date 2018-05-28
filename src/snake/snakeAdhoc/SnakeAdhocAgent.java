package snake.snakeAdhoc;

import snake.*;

import java.awt.*;


public class SnakeAdhocAgent extends SnakeAgent {

    public SnakeAdhocAgent(Cell cell, Color color, Environment environment) {
        super(cell, color, environment);


    }

    @Override
    protected Action decide(Perception perception) {
        if (perception.getN() != null
                && perception.getN().hasFood()) {
            return Action.NORTH;
        }
        if (perception.getS() != null
                &&
                perception.getS().hasFood()) {
            return Action.SOUTH;
        }
        if (perception.getE() != null
                &&
                perception.getE().hasFood()) {
            return Action.EAST;
        }
        if (perception.getW() != null
                &&
                perception.getW().hasFood()) {
            return Action.WEST;
        }
        if (perception.getN() != null && !perception.getN().hasAgent() && !perception.getN().hasTail() &&
                environment.getFood().getCell().getLine() < cell.getLine()) {
            return Action.NORTH;
        }
        if (perception.getS() != null && !perception.getS().hasAgent() && !perception.getS().hasTail() &&
                environment.getFood().getCell().getLine() > cell.getLine()) {
            return Action.SOUTH;
        }
        if (perception.getE() != null && !perception.getE().hasAgent() && !perception.getE().hasTail() &&
                environment.getFood().getCell().getColumn() > cell.getColumn()) {
            return Action.EAST;
        }
        if (perception.getW() != null && !perception.getW().hasAgent() && !perception.getW().hasTail() &&
                environment.getFood().getCell().getColumn() < cell.getColumn()) {
            return Action.WEST;
        }
        if (perception.getN() != null && !perception.getN().hasAgent() && !perception.getN().hasTail()) {
            return Action.NORTH;
        }
        if (perception.getS() != null && !perception.getS().hasAgent() && !perception.getS().hasTail()) {
            return Action.SOUTH;
        }
        if (perception.getE() != null && !perception.getE().hasAgent() && !perception.getE().hasTail()) {
            return Action.EAST;
        }
        if (perception.getW() != null && !perception.getW().hasAgent() && !perception.getW().hasTail()) {
            return Action.WEST;
        }
        return null;
    }

}
