package hw4.puzzle;

import edu.princeton.cs.algs4.MinPQ;
import java.util.Comparator;
import java.util.Stack;
import java.util.ArrayList;

public class Solver {
    private static class SearchNode {
        private WorldState world;
        private int movements;
        private int priority;
        private int distance;
        private SearchNode parent;

        private SearchNode(WorldState w, SearchNode p) {
            this.world = w;
            this.parent = p;
            this.distance = w.estimatedDistanceToGoal();
            if (p == null) {
                this.priority = this.distance;
                this.movements = 0;
            } else {
                this.movements = p.movements + 1;
                this.priority = p.movements + this.distance;
            }
        }
    }

    private static class SearchNodeComparator implements Comparator<SearchNode> {
        @Override
        public int compare(SearchNode o1, SearchNode o2) {
            return o1.priority - o2.priority;
        }
    }

    private Stack<WorldState> path = new Stack<>();
    /** Constructor which solves the puzzle, computing
     everything necessary for moves() and solution() to
     not have to solve the problem again. Solves the
     puzzle using the A* algorithm. Assumes a solution exists. */
    public Solver(WorldState initial) {
        SearchNode node = new SearchNode(initial, null);
        MinPQ<SearchNode> pq = new MinPQ<>(new SearchNodeComparator());
        while (!node.world.isGoal()) {
            for (WorldState w: node.world.neighbors()) {
                if (node.parent == null || !w.equals(node.parent.world)) {
                    pq.insert(new SearchNode(w, node));
                }
            }
            node = pq.delMin();
        }

        while (node != null) {
            path.push(node.world);
            node = node.parent;
        }
    }

    /** Returns the minimum number of moves to solve the puzzle starting
     at the initial WorldState. */
    public int moves() {
        return path.size() - 1;
    }

    /**  Returns a sequence of WorldStates from the initial WorldState
     to the solution. */
    public Iterable<WorldState> solution() {
        ArrayList<WorldState> ret = new ArrayList<>();
        while (path.size() != 0) {
            ret.add(path.pop());
        }
        return ret;
    }
}
