public class OffByN implements CharacterComparator{
    public int n;
    public OffByN(int n){
        this.n = n;
    }

    @Override
    public boolean equalChars(char x, char y) {
        int diff = x - y;
        if(diff*diff == this.n*this.n){
            return true;
        }
        return false;
    }
}
