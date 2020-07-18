package creatures;

import huglife.*;
import jdk.nashorn.internal.ir.EmptyNode;

import java.awt.*;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Map;

public class Clorus extends Creature {
    /**
     * red color.
     */
    private int r;
    /**
     * green color.
     */
    private int g;
    /**
     * blue color.
     */
    private int b;

    public Clorus(double e) {
        super("clorus");
        r = 0;
        g = 0;
        b = 0;
        energy = e;
    }

    public Clorus() {
        this(1);
    }
    @Override
    public void move() {
        if (this.energy - 0.03 < 0.001) {
            energy = 0;
        } else {
            energy -= 0.03;
        }
    }

    @Override
    public void attack(Creature c) {
        this.energy += c.energy();
        // c.energy = 0;
    }

    @Override
    public Clorus replicate() {
        Clorus p = new Clorus(energy / 2);
        this.energy /= 2;
        p.r = r;
        p.g = g;
        p.b = b;
        return p;
    }

    @Override
    public void stay() {
        if (this.energy - 0.01 < 0.001) {
            energy = 0;
        } else {
            energy -= 0.01;
        }
    }

    @Override
    public Action chooseAction(Map<Direction, Occupant> neighbors) {
        // rule 1
        Deque<Direction> emptyNeighbors = new ArrayDeque<>();
        Deque<Direction> plipNeighbors = new ArrayDeque<>();
        for (Map.Entry<Direction, Occupant> entry: neighbors.entrySet()) {
            if (entry.getValue().name().equals("empty")) {
                emptyNeighbors.addLast(entry.getKey());
            } else if (entry.getValue().name().equals("plip")) {
                plipNeighbors.addLast(entry.getKey());
            }
        }
        if (emptyNeighbors.size() == 0) {
            return new Action(Action.ActionType.STAY);
        }

        // rule 2
        if (plipNeighbors.size() != 0) {
            return new Action(Action.ActionType.ATTACK, HugLifeUtils.randomEntry(plipNeighbors));
        }

        // rule 3
        if (this.energy >= 1) {
            return new Action(Action.ActionType.REPLICATE, HugLifeUtils.randomEntry(emptyNeighbors));
        }
        // rule 4
        return new Action(Action.ActionType.MOVE, HugLifeUtils.randomEntry(emptyNeighbors));
    }

    @Override
    public Color color() {
        r = 34;
        g = 0;
        b = 231;
        return color(r, g, b);
    }
}
