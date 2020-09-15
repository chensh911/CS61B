import org.junit.Assert;
import org.junit.Test;


public class TestBubbleGrid {
    @Test
    public void test() {
        int[][] grid = {{1, 1, 0}, {1, 0, 0},
                {1, 1, 0}, {1, 1, 1},};
        int[][] darts = {{2, 2}, {2, 0}};
        BubbleGrid p = new BubbleGrid(grid);
        Assert.assertArrayEquals(new int[]{0, 4}, p.popBubbles(darts));
    }
}
