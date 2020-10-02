package lab11.graphs;

import java.util.LinkedList;
import java.util.Queue;

/**
 *  @author Josh Hug, Chen Shangheng
 */
public class MazeBreadthFirstPaths extends MazeExplorer {
    /* Inherits public fields:
    public int[] distTo;
    public int[] edgeTo;
    public boolean[] marked;
    */
    private int s;
    private int t;
    private boolean targetFound = false;
    private Maze maze;

    public MazeBreadthFirstPaths(Maze m, int sourceX, int sourceY, int targetX, int targetY) {
        super(m);
        s = m.xyTo1D(sourceX, sourceY);
        t = m.xyTo1D(targetX, targetY);
        distTo[s] = 0;
        edgeTo[s] = s;
        maze = m;
    }

    /** Conducts a breadth first search of the maze starting at the source. */
    private void bfs() {
        Queue<Integer> fringe = new LinkedList<>();
        fringe.add(s);
        marked[s] = true;
        announce();

        while (!fringe.isEmpty()) {
            Integer parent = fringe.remove();
            for (int child: maze.adj(parent)) {
                if (!marked[child]) {
                    fringe.add(child);
                    distTo[child] = 1 + distTo[parent];
                    edgeTo[child] = parent;
                    marked[child] = true;
                    announce();
                    if (child == t) {
                        return;
                    }
                }
            }
        }
    }


    @Override
    public void solve() {
         bfs();
    }
}

