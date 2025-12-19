public class LinkedListDeque<T> {
    private class nodes{
        //a private class that represent the node;
        public T value;
        public nodes last;
        public nodes next;
        public nodes(){
            this.value = null;
            this.next = null;
            this.last = null;
        }
        public nodes(T v,nodes l,nodes n){
            this.value = v;
            this.last = l;
            this.next = n;
        }
        public T getitem(int index){
            if (index==0){
                return this.value;
            }
            return this.next.getitem(index-1);
        }
    }

    private int size=0;
    private nodes senti;

    public LinkedListDeque(){
        this.senti = new nodes();
        this.senti.last = this.senti;
        this.senti.next = this.senti;
    }
    public void addFirst(T item){
        size++;
        nodes tmp = new nodes(item,this.senti,this.senti.next);
        this.senti.next.last = tmp;
        this.senti.next = tmp;
    }
    public void addLast(T item) {
        size++;
        nodes tmp = new nodes(item, this.senti.last, this.senti);
        this.senti.last.next = tmp;
        this.senti.last = tmp;
    }
    public boolean isEmpty(){
        if (size == 0){
            return true;
        }
        return false;
    }
    public int size(){
        return size;
    }
    public void printDeque(){
        if (this.size == 0)
            return;
        nodes tmp = this.senti.next;
        while (tmp != this.senti){
            System.out.print(tmp.value+" ");
            tmp = tmp.next;
        }
    }
    public T removeFirst(){
        nodes tmp = this.senti.next;
        this.senti.next = tmp.next;
        tmp.next.last = this.senti;
        size--;
        return tmp.value;
    }
    public T removeLast(){
        nodes tmp = this.senti.last;
        this.senti.last = tmp.last;
        tmp.last.next = this.senti;
        size--;
        return tmp.value;
    }
    public T get(int index){
        int pos = 0;
        nodes tmp = this.senti.next;
        while (pos!=index){
            if (tmp == this.senti){
                return null;
            }
            pos++;
            tmp = tmp.next;
        }
        return tmp.value;
    }
    public T getRecursive(int index){
        if (index>=size){
            return null;
        }
        return this.senti.next.getitem(index);
    }
    /**
    public static void main(String[] args){
        LinkedListDeque<Integer> quee = new LinkedListDeque<>();
        quee.addLast(6);
        quee.addLast(2);
        System.out.println(quee.size());
        quee.printDeque();
        System.out.println(quee.getRecursive(1));
        System.out.println(quee.removeFirst());
        System.out.println(quee.removeLast());
    }*/
}
