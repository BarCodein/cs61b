package hw4.puzzle;

import org.junit.Test;

import java.awt.desktop.SystemEventListener;
import java.util.*;

public class Solver {
    PriorityQueue<SearchNode> pq = new PriorityQueue<>();
    SearchNode answer;
    private class SearchNode implements Comparable<SearchNode>{
        WorldState state;
        int move;
        SearchNode previousNode;
        private SearchNode(WorldState state,SearchNode previousNode,int move){
            this.state = state;
            this.previousNode = previousNode;
            this.move = move;
        }
        @Override
        public int compareTo(SearchNode o){
            int Mstep = move + state.estimatedDistanceToGoal();
            int Ostep = o.move + o.state.estimatedDistanceToGoal();
            return Mstep-Ostep;
        }
    }
    public Solver(WorldState initial){
        SearchNode initialNode = new SearchNode(initial,null,0);
        pq.add(initialNode);
        while(!pq.isEmpty()){
            SearchNode node = pq.remove();
            for (WorldState neighborState : node.state.neighbors()){
                //System.out.println(neighborState+" "+neighborState.estimatedDistanceToGoal());
                SearchNode neighbor = new SearchNode(neighborState,node,node.move+1);
                if (node.previousNode != null && neighborState.equals(node.previousNode.state))
                    continue;
                pq.add(neighbor);

                if (neighborState.estimatedDistanceToGoal()==0){
                    answer = neighbor;
                    break;
                }

            }
            if (answer != null)
                break;
        }
        //System.out.println("solved");
    }
    public int moves(){
        return answer.move;
    }
    public Iterable<WorldState> solution(){
        List<WorldState> list = new ArrayList<>();
        SearchNode node = answer;
        while (node!=null){
            list.addFirst(node.state);
            node = node.previousNode;
        }
        class worldStateIterable implements Iterable<WorldState>{
            @Override
            public Iterator<WorldState> iterator() {
                return list.iterator();
            }
        }
        return new worldStateIterable();
    }

}
