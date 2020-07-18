package creatures;
import org.junit.Test;
import static org.junit.Assert.*;
import java.util.HashMap;
import java.awt.Color;
import huglife.Direction;
import huglife.Action;
import huglife.Occupant;
import huglife.Impassible;
import huglife.Empty;

public class TestClorus {
    @Test
    public void testBasics() {
        Clorus p = new Clorus(0.05);
        assertEquals("clorus", p.name());
        p.move();
        assertEquals(0.02, p.energy(), 0.001);
        p.stay();
        assertEquals(0.01, p.energy(), 0.001);
        p.move();
        assertEquals(0, p.energy(), 0.001);
        p = new Clorus(1);
        Plip q = new Plip(1);
        p.attack(q);
        assertEquals(2, p.energy(), 0.001);
       // assertEquals(0, q.energy(), 0.001);
    }

    @Test
    public void testReplicate() {
        Clorus p1 = new Clorus(2);
        double expected = p1.energy() / 2;
        Clorus p2 = p1.replicate();
        assertEquals(expected, p1.energy(), 0.01);
        assertEquals(expected, p2.energy(), 0.01);
    }

    @Test
    public void testBehavior() {
        // No empty adjacent spaces; stay.
        Clorus p = new Clorus(1.2);
        HashMap<Direction, Occupant> surrounded = new HashMap<Direction, Occupant>();
        surrounded.put(Direction.TOP, new Impassible());
        surrounded.put(Direction.BOTTOM, new Impassible());
        surrounded.put(Direction.LEFT, new Impassible());
        surrounded.put(Direction.RIGHT, new Impassible());

        Action actual = p.chooseAction(surrounded);
        Action expected = new Action(Action.ActionType.STAY);

        assertEquals(expected, actual);

        // attach Top Plip
        p = new Clorus(1.2);
        HashMap<Direction, Occupant> topClorus = new HashMap<Direction, Occupant>();
        topClorus.put(Direction.TOP, new Plip());
        topClorus.put(Direction.BOTTOM, new Empty());
        topClorus.put(Direction.LEFT, new Impassible());
        topClorus.put(Direction.RIGHT, new Impassible());

        actual = p.chooseAction(topClorus);
        expected = new Action(Action.ActionType.ATTACK, Direction.TOP);

        assertEquals(expected, actual);

        // Energy >= 1; replicate towards an empty space.
        p = new Clorus(1.2);
        HashMap<Direction, Occupant> topEmpty = new HashMap<Direction, Occupant>();
        topEmpty.put(Direction.TOP, new Empty());
        topEmpty.put(Direction.BOTTOM, new Impassible());
        topEmpty.put(Direction.LEFT, new Impassible());
        topEmpty.put(Direction.RIGHT, new Impassible());

        actual = p.chooseAction(topEmpty);
        expected = new Action(Action.ActionType.REPLICATE, Direction.TOP);

        assertEquals(expected, actual);


        // Energy >= 1; replicate towards an empty space.
        p = new Clorus(1.2);
        HashMap<Direction, Occupant> allEmpty = new HashMap<Direction, Occupant>();
        allEmpty.put(Direction.TOP, new Empty());
        allEmpty.put(Direction.BOTTOM, new Empty());
        allEmpty.put(Direction.LEFT, new Empty());
        allEmpty.put(Direction.RIGHT, new Empty());

        actual = p.chooseAction(allEmpty);
        Action unexpected = new Action(Action.ActionType.STAY);

        assertNotEquals(unexpected, actual);

        // Energy < 1, the top is empty. Move to the top.
        p = new Clorus(.99);

        actual = p.chooseAction(topEmpty);
        expected = new Action(Action.ActionType.MOVE, Direction.TOP);

        assertEquals(expected, actual);

        // Energy < 1, all is empty. Move to a random empty square
        actual = p.chooseAction(allEmpty);

        assertNotEquals(unexpected, actual);
    }
}
