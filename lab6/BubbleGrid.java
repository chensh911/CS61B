public class BubbleGrid {
    int WIDTH, HEIGHT;
    UnionFind unionFind;
    int [][] grid;
    public BubbleGrid(int [][] g) {
        grid = g;
        WIDTH = g[0].length;
        HEIGHT = g.length;
    }
    public int[] popBubbles(int[][] darts) {
        unionFind = new UnionFind(WIDTH * HEIGHT);
        return null;
    }
    private int findIndex(int x, int y) {
        if (x < 0 || x >= WIDTH || y < 0 || y >= HEIGHT) {
            return -1;
        }
        return x * WIDTH + y;
    }
}
