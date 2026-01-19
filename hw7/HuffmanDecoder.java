import java.util.ArrayList;
import java.util.List;

public class HuffmanDecoder {
    public static void main(String[] args){
        ObjectReader or = new ObjectReader(args[0]);
        BinaryTrie huffmanTrie = (BinaryTrie) or.readObject();
        BitSequence codes = (BitSequence) or.readObject();
        List<Character> list = new ArrayList<>();
        while(codes.length()>0){
            Match match = huffmanTrie.longestPrefixMatch(codes);
            list.add(match.getSymbol());
            BitSequence decode = match.getSequence();
            codes = codes.allButFirstNBits(decode.length());
        }
        char[] file= new char[list.size()];
        for (int i=0;i<list.size();i++){
            file[i] = list.get(i);
        }
        FileUtils.writeCharArray(args[1],file);
    }
}