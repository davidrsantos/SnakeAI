package snake;

import java.awt.*;

public class Tail {
    public static final Color COLOR = Color.BLACK;

    private Cell cell;

    public Tail(Cell cell) {
        this.cell = cell;
        this.cell.setTail(this);
    }

    public Color getColor() {
        return COLOR;
    }

    public Cell getCell() {
        return cell;
    }

    public void setCell(Cell cell) {
        this.cell.setTail(null);
        this.cell = cell;
        this.cell.setTail(this);

    }
}