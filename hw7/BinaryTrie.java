import edu.princeton.cs.introcs.StdRandom;

import java.io.Serializable;
import java.io.StringReader;
import java.util.HashMap;
import java.util.Map;

public class BinaryTrie implements Serializable {
    private static class Node implements Serializable{
        boolean end;
        char item;
        Node left,right;

        public Node(boolean end){
            this.end = end;
        }

        public Node(boolean end,char item){
            this.end = end;
            this.item = item;
        }

    }
    private Node root;

    private static char[] tableSorter(Map<Character, Integer> frequencyTable){
        int size = frequencyTable.size();
        char[] table = new char[size];
        int i=0;
        for (char key:frequencyTable.keySet()){
            int freq = frequencyTable.get(key);
            boolean flag = false;
            for (int j=0;j<i;j++){
                int f = frequencyTable.get(table[j]);
                if (f>freq) {
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
    private static class splitterData {
        private Map<Character, Integer> lTable,rTable;
        private char[] lKey,rKey;
        public splitterData(Map<Character, Integer> lTable, Map<Character, Integer>rTable, char[] lkey, char[] rKey){
            this.lKey = lkey;
            this.rKey = rKey;
            this.lTable = lTable;
            this.rTable = rTable;
        }
    }
    private static splitterData splitter(Map<Character, Integer> frequencyTable, char[] sortedTable, int index){
        int size = frequencyTable.size();
        Map<Character, Integer> lTable = new HashMap<>();
        Map<Character, Integer> rTable = new HashMap<>();
        char[] lKey = new char[index];
        char[] rKey = new char[size-index];
        for (int i=0;i<index;i++){
            char key = sortedTable[i];
            lKey[i] = key;
            lTable.put(key,frequencyTable.get(key));
        }
        for (int i=index;i<size;i++){
            char key = sortedTable[i];
            rKey[i-index] = key;
            rTable.put(key,frequencyTable.get(key));
        }
        splitterData data = new splitterData(lTable,rTable,lKey,rKey);
        return data;
    }
    private static Node trieBuilder(Map<Character, Integer> frequencyTable,char[] sortedTable,int sum){
        int size = sortedTable.length;
        int index = 0;
        int halfsum = 0,minner=0;
        if (size == 1)
            return new Node(true,sortedTable[0]);
        if (size == 2){
            Node node = new Node(false);
            node.left = new Node(true,sortedTable[0]);
            node.right = new Node(true,sortedTable[1]);
            return node;
        }
        Map<Character, Integer> halfFrequencyTable = new HashMap<>();
        while(halfsum<=sum/2){
            minner = halfsum;
            halfsum += frequencyTable.get(sortedTable[index]);
            halfFrequencyTable.put(sortedTable[index],frequencyTable.get(sortedTable[index]));
            index++;
        }
        Node node = new Node(false);
        if (sum/2-minner>halfsum-sum/2){
            splitterData data = splitter(frequencyTable,sortedTable,index);
            node.left = trieBuilder(data.lTable, data.lKey, halfsum);
            node.right = trieBuilder(data.rTable, data.rKey,sum-halfsum);
        }
        else{
            index--;
            splitterData data = splitter(frequencyTable,sortedTable,index);
            node.left = trieBuilder(data.lTable, data.lKey, minner);
            node.right = trieBuilder(data.rTable, data.rKey,sum-minner);
        }
        return node;
    }


    public BinaryTrie(Map<Character, Integer> frequencyTable){
        this.root = null;
        char[] table = tableSorter(frequencyTable);
        int sum = 0;
        for(char key:table)
            sum+=frequencyTable.get(key);
        this.root = trieBuilder(frequencyTable,table,sum);
    }
    public Match longestPrefixMatch(BitSequence querySequence){
        Node node = root;
        int index = 0;
        Match match;
        String prefix = "";
        while(true){
            if (node == null){
                throw new RuntimeException("Sequence is not encode in Huffman Code");
            }
            if (node.end){
                return new Match(new BitSequence(prefix),node.item);
            }
            if (querySequence.bitAt(index)==1){
                prefix+="1";
                node = node.right;
            }
            else{
                prefix+="0";
                node = node.left;
            }
            index++;
        }
    }
    private Map<Character, BitSequence> traverse(Node node,String prefix){
        Map<Character, BitSequence> table = new HashMap<>();
        Map<Character, BitSequence> left;
        Map<Character, BitSequence> right;
        if (node.end){
            table.put(node.item,new BitSequence(prefix));
            return table;
        }
        left = traverse(node.left,prefix+'0');
        right = traverse(node.right,prefix+'1');
        table.putAll(left);
        table.putAll(right);
        return table;
    }
    public Map<Character, BitSequence> buildLookupTable(){
        return traverse(this.root,"");
    }

    static void main(String[] args){

        Map<Character, Integer> frequencyTable = new HashMap<>();
        frequencyTable.put('a',3);
        frequencyTable.put('b',1);
        frequencyTable.put('c',5);
        frequencyTable.put('d',5);
        frequencyTable.put('e',0);
        frequencyTable.put('f',10);
        char[] table = tableSorter(frequencyTable);
        Node node = trieBuilder(frequencyTable,table,24);
        BinaryTrie t = new BinaryTrie(frequencyTable);
        System.out.println(table);
        Map<Character, BitSequence> oup = t.buildLookupTable();
        Match match = t.longestPrefixMatch(new BitSequence("000001"));
    }
}