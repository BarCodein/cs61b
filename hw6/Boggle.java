import org.junit.Test;

import java.util.*;

public class Boggle {
    
    // File path of dictionary file
    static String dictPath = "words.txt";
    private static String[] oup;
    private static int num=0;
    private static boolean[][] map;
    private static char[][] board;
    private static int k;
    static int count=0;

    private static void print(char[][] board){
        int m = board.length;
        int n = board[1].length;
        for(char[] s:board){
            for (char c:s){
                System.out.print(c);
                System.out.print(' ');
            }
            System.out.println();
        }
    }
    private static char[][] read_file(String filename){
        In in = new In(filename);
        Queue<String> queue = new ArrayDeque<>();
        while (in.hasNextLine()){
            String line = in.readLine();
            queue.add(line);
        }
        in.close();
        int m = queue.size();
        int n = queue.peek().length();
        char[][] board = new char[m][n];
        int count = 0;
        while (!queue.isEmpty()){
            String s = queue.poll();
            for(int i=0;i<s.length();i++){
                board[count][i] = s.charAt(i);
            }
            count++;
        }
        return board;
    }

    private static Trie read_dic(String dictPath){
        In in = new In(dictPath);
        Trie dic = new Trie();
        while(in.hasNextLine()){
            String s = in.readLine();
            dic.add_word(s);
        }
        return dic;
    }

    private static int compare(String a,String b){
        if (a.length()!=b.length())
            return a.length()-b.length();
        return a.compareTo(b);
    }

    private static void update(String word){
        count++;
        //System.out.println(word);
        if (num<k){
            oup[num] = word;
            num++;
            return;
        }
        int index = k-1;
        for(;index>=0;index--){
            if (word.compareTo(oup[index])==0){
                return;
            }
            if (compare(word,oup[index])<0){
                break;
            }
        }
        for (int i=k-1;i>index;i--){
            oup[i+1] = oup[i];
        }
        oup[index+1] = word;

    }
    private static List<position> initial(){
        int m = board.length;
        int n = board[1].length;
        map = new boolean[m][n];
        List<position> positions = new ArrayList<>();
        for (int i=0;i<m;i++){
            for (int j=0;j<n;j++){
                map[i][j] = false;
                positions.add(new position(i,j));
            }
        }
        return positions;
    }

    private static class position {
        int x;
        int y;

        public position(int x, int y) {
            this.x = x;
            this.y = y;
        }
        @Override
        public String toString(){
            return x+","+y;
        }
    }
    private static boolean check(int x,int y){
        int m = map.length;
        int n = map[1].length;
        if (x<0 || y<0)return false;
        if (x>=m || y>=n)return false;
        if (map[x][y])return false;
        return true;
    }

    private static List<position> neighbor(int x, int y){
        List<position> list = new ArrayList<>();
        for (int i=-1;i<=1;i++){
            for (int j=-1;j<=1;j++){
                int dx = x+i;
                int dy = y+j;
                if (check(dx,dy))
                    list.add(new position(dx,dy));
            }
        }
        return list;
    }

    private static boolean pruning(Trie.Node node, String prefix){
        int length = prefix.length()+1+node.length;
        if (num==0)
            return false;
        String min = oup[num-1];
        if (length<min.length())
            return true;
        if (length==min.length())
            if (prefix.compareTo(min)<0)
                return true;
        return false;
    }

    private static void finder(List<position> neighbors, String prefix, Trie.Node node){
        for (position pos:neighbors){
            int x=pos.x,y=pos.y;
            if (node.item == board[x][y]){
                String newPrefix = prefix+ node.item;
                if (node.end){
                    update(newPrefix);
                }
                map[x][y] = true;
                List<position> newNeighbors = neighbor(x,y);
                for (char key:node.subnodes()){
                    Trie.Node newnode = node.subnodes.get(key);
                    if (pruning(newnode,newPrefix))
                        continue;
                    finder(newNeighbors,newPrefix,newnode);
                }
                map[x][y] = false;
            }
        }
    }

    private static void travers(String prefix,Trie.Node node){
        String newPrefix = prefix+node.item;
        if (node.end)
            if (newPrefix.equals("thumbtacks"))
                System.out.println(newPrefix);
        for (char k: node.subnodes())
            travers(newPrefix,node.subnodes.get(k));
    }

    /**
     * Solves a Boggle puzzle.
     * 
     * @param k The maximum number of words to return.
     * @param boardFilePath The file path to Boggle board file.
     * @return a list of words found in given Boggle board.
     *         The Strings are sorted in descending order of length.
     *         If multiple words have the same length,
     *         have them in ascending alphabetical order.
     */
    public static List<String> solve(int k, String boardFilePath) {
        // YOUR CODE HERE
        //initialize,read files
        Boggle.k = k;
        board = read_file(boardFilePath);
        Trie dic = read_dic(dictPath);
        //travers("",dic.root.subnodes.get('t'));
        oup = new String[k+1];
        List<position> positions = initial();
        Trie.Node node = dic.root;
        for (char key: node.subnodes()){
            finder(positions,"",node.subnodes.get(key));
        }


        List<String> ret = new ArrayList<>();
        for (int i=0;i<num;i++) {
            ret.add(oup[i]);
        }
        return ret;
    }


    @Test
    public void testBoggle(){
        solve(3,"smallBoard.txt");
        System.out.println(count);
        System.out.println("aaaaa".compareTo("aba"));
    }
}
