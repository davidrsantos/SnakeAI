package snake.snakeRandom;

import snake.*;

import java.awt.*;
import java.util.Random;

public class SnakeRandomAgent extends SnakeAgent {


    public SnakeRandomAgent(Cell cell, Color color, Environment environment) {

        super(cell, color, environment);
    }

    @Override
    protected Action decide(Perception perception) {
        Random random =  new Random();

        switch(random.nextInt(4)) {
            case 0:
                if (perception.getN() != null && !perception.getN().hasAgent() && !perception.getN().hasTail()) {
                    return Action.NORTH;
                }

                if (perception.getE() != null && !perception.getE().hasAgent() && !perception.getE().hasTail()) {
                    return Action.EAST;
                }
                if (perception.getS() != null && !perception.getS().hasAgent() && !perception.getS().hasTail()) {
                    return Action.SOUTH;
                }
                if (perception.getW() != null && !perception.getW().hasAgent() && !perception.getW().hasTail()) {
                    return Action.WEST;
                }
                break;
            case 1:

                if (perception.getE() != null && !perception.getE().hasAgent() && !perception.getE().hasTail()) {
                    return Action.EAST;
                }
                if (perception.getS() != null && !perception.getS().hasAgent() && !perception.getS().hasTail()) {
                    return Action.SOUTH;
                }
                if (perception.getW() != null && !perception.getW().hasAgent() && !perception.getW().hasTail()) {
                    return Action.WEST;
                }

                if (perception.getN() != null && !perception.getN().hasAgent() && !perception.getN().hasTail()) {
                    return Action.NORTH;
                }
                break;
            case 2:


                if (perception.getS() != null && !perception.getS().hasAgent() && !perception.getS().hasTail()) {
                    return Action.SOUTH;
                }
                if (perception.getW() != null && !perception.getW().hasAgent() && !perception.getW().hasTail()) {
                    return Action.WEST;
                }

                if (perception.getN() != null && !perception.getN().hasAgent() && !perception.getN().hasTail()) {
                    return Action.NORTH;
                }
                if (perception.getE() != null && !perception.getE().hasAgent() && !perception.getE().hasTail()) {
                    return Action.EAST;
                }
                break;
            case 3:


                if (perception.getW() != null && !perception.getW().hasAgent() && !perception.getW().hasTail()) {
                    return Action.WEST;
                }

                if (perception.getN() != null && !perception.getN().hasAgent() && !perception.getN().hasTail()) {
                    return Action.NORTH;
                }
                if (perception.getE() != null && !perception.getE().hasAgent() && !perception.getE().hasTail()) {
                    return Action.EAST;
                }
                if (perception.getS() != null && !perception.getS().hasAgent() && !perception.getS().hasTail()) {
                    return Action.SOUTH;
                }
                break;

        }
        return null;
    }
}
