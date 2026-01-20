import java.util.*;

public class Trie {
    /*
      a data structure to store dictionary
      */
    public class Node implements Comparator<Node> {
        char item;
        int length;
        SortedMap<Character,Node> subnodes = new TreeMap<>();
        boolean end;
        char[] key;


        public Node(){
            this.length = 0;
            this.end = false;
        }
        @Override
        public int compare(Node o1, Node o2) {
            return o1.length-o2.length;
        }
        public Node(char item){
            this.item = item;
            this.length = 0;
            this.end = false;
        }

        public void add(char c){
            add(c,new Node(c));
        }

        public void add(char c, Node subnode){
            this.subnodes.put(c,subnode);
            int newlength = subnode.length+1;
            if (newlength>this.length) {
                this.length = newlength;
            }

        }

        public char[] subnodes(){
            int size = subnodes.size();
            char[] table = new char[size];
            int i=0;
            for (char key:subnodes.keySet()){
                int length1 = subnodes.get(key).length;
                boolean flag = false;
                for (int j=0;j<i;j++){
                    int length2 = subnodes.get(table[j]).length;
                    if (length2<length1 || (length1==length2 && key<table[j])) {
                        for (int k=i;k>j;k--)
                            table[k] = table[k-1];
                        table[j] = key;
                        i++;
                        flag = true;
                        break;
                    }
                }
                if (flag)
                    continue;
                table[i] = key;
                i++;
            }
            return table;
        }

    }

    Node root;

    public Trie(){
        this.root = new Node();
    }

    public void add_word(String word){
        word = word.toLowerCase(Locale.ROOT);
        Node node = root;
        for(int i=0;i<word.length();i++){
            char c = word.charAt(i);
            int length = word.length()-i-1;
            if (length>node.length){
                node.length = length;
            }
            if(!node.subnodes.containsKey(c)){
                node.add(c);
            }
            node = node.subnodes.get(c);
        }
        node.end = true;
    }

    public void print(int k){
        Node node = this.root;
        System.out.println(node.length);
        System.out.println(node.subnodes());
        node = node.subnodes.get('a');
        System.out.println(node.end);
        System.out.println(node.length);
        node = node.subnodes.get('p');
        System.out.println(node.end);
        node = node.subnodes.get('p');
        System.out.println(node.end);
        node = node.subnodes.get('l');
        System.out.println(node.end);
        node = node.subnodes.get('e');
        System.out.println(node.end);

    }
}
