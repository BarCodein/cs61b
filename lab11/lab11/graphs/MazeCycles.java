package lab11.graphs;

import java.util.HashMap;
import java.util.Map;

/**
 *  @author Josh Hug
 */
public class MazeCycles extends MazeExplorer {
    /* Inherits public fields:
    public int[] distTo;
    public int[] edgeTo;
    public boolean[] marked;
    */
    private int s;
    private boolean cycleFound = false;
    private Maze maze;
    private Map<Integer,Integer> map = new HashMap<>();
    public MazeCycles(Maze m) {
        super(m);
        maze = m;
        s = maze.xyTo1D(1, 1);
        distTo[s] = 0;
        edgeTo[s] = s;
    }
    private void cycle(int v) {
        marked[v] = true;
        announce();


        if (cycleFound) {
            return;
        }

        for (int w : maze.adj(v)) {

            if (marked[w] && w!=map.get(v)){
                cycleFound = true;
                return;
            }
            if (!marked[w]) {
                map.put(w,v);
                edgeTo[w] = v;
                announce();
                distTo[w] = distTo[v] + 1;
                cycle(w);
                map.remove(w);
                if (cycleFound) {
                    return;
                }
            }

        }
        return;
    }
    @Override
    public void solve() {
        // TODO: Your code here!
        cycle(0);
    }

    // Helper methods go here
}

