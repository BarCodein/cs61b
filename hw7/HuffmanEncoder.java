import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HuffmanEncoder {
    public static Map<Character, Integer> buildFrequencyTable(char[] inputSymbols){
        Map<Character,Integer> frequencyTable = new HashMap<>();
        for (char c:inputSymbols){
            if (frequencyTable.containsKey(c)){
                frequencyTable.compute(c, (k, frequency) -> frequency + 1);
            }
            else{
                frequencyTable.put(c,1);
            }
        }
        return frequencyTable;
    }
    public static void main(String[] args){
        char[] symbols = FileUtils.readFile(args[0]);
        Map<Character,Integer> frequencyTable = buildFrequencyTable(symbols);
        BinaryTrie huffmanTrie = new BinaryTrie(frequencyTable);
        BitSequence file = new BitSequence();
        List<BitSequence> codes = new ArrayList<>();
        Map<Character,BitSequence> encoder = huffmanTrie.buildLookupTable();
        for (char c:symbols){
            BitSequence code = encoder.get(c);
            codes.add(code);
        }
        file = BitSequence.assemble(codes);
        ObjectWriter ow = new ObjectWriter(args[0]+".huf");
        ow.writeObject(huffmanTrie);
        ow.writeObject(file);
    }
}