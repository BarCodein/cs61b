public class Palindrome {
    public Deque<Character> wordToDeque(String word){
        Deque<Character> quee;
        quee = new ArrayDeque<>();
        for (int i=0;i<word.length();i++){
            quee.addLast(word.charAt(i));
        }
        return quee;
    }
    public boolean isPalindrome(String word){
        if (word.length()<=1){
            return true;
        }
        Deque<Character> quee = wordToDeque(word);
        for (int i=0;i<quee.size();i++){
            if (quee.get(i) != quee.get(quee.size()-1-i)){
                return false;
            }
        }
        return true;
    }
    public boolean isPalindrome(String word, CharacterComparator cc){
        if (word.length()<=1){
            return true;
        }
        Deque<Character> quee= wordToDeque(word);
        for (int i=0;i<quee.size()/2;i++){
            if (!cc.equalChars(quee.get(i),quee.get(quee.size()-i-1))){
                return false;
            }
        }
        return true;
    }
}
