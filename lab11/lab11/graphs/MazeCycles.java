package lab11.graphs;

/**
 *  @author Josh Hug
 */
public class MazeCycles extends MazeExplorer {
    /* Inherits public fields:
    public int[] distTo;
    public int[] edgeTo;
    public boolean[] marked;
    */
    private boolean foundCircle = false;
    private int[] save;

    public MazeCycles(Maze m) {
        super(m);
        save = new int[m.V()];
    }

    @Override
    public void solve() {
        int s = 0;
        save[s] = s;
        dfs(s);
        announce();
    }

    // Helper methods go here
    private void dfs(int parent) {
        marked[parent] = true;
        announce();

        if (foundCircle) {
            return;
        }

        for (int child : maze.adj(parent)) {
            if (!marked[child]) {
                dfs(child);
                save[child] = parent;
            } else if (distTo[child] == parent) {
                foundCircle = true;
                save[child] = parent;
                edgeTo[child] = parent;
                int temp = parent;
                while (temp != child) {
                    edgeTo[temp] = save[temp];
                    temp = save[temp];
                }
                announce();
            }
        }
    }
}

