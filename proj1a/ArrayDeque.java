public class ArrayDeque<T> {
    private int size;
    private T[] object;
    private int head;
    private int end;
    public ArrayDeque(){
        size = 0;
        object = (T []) new Object[8];
        head = 0;
        end = 0;
    }
    public int size(){
        return size;
    }
    public boolean isEmpty(){
        if (size==0){
            return true;
        }
        return false;
    }
    public void resize(int capacity){
        T[] a = (T []) new Object[capacity];
        int flag = 0;
        if (this.head>=this.end){
            flag = 1;
        }
        int index = 0;
        int i = this.head;
        while (flag == 1 || i<this.end){
            if (i == this.object.length){
                i = 0;
                flag = 0;
            }
            a[index] = this.object[i];
            i++;
            index++;
        }
        this.object = a;
        this.head = 0;
        this.end = index;
    }

    public void printDeque(){
        int flag = 0;
        int index = this.head;
        if (this.head>=this.end){
            flag = 1;
        }
        while (flag ==1 || this.end>index){
            if (index == this.object.length){
                index = 0;
                flag =0;
            }
            System.out.print(this.object[index]+" ");
            index++;
        }
        System.out.println();
    }
    public void addFirst(T item){
        if (size == this.object.length){
            resize(size*2);
        }
        if (this.isEmpty()){
            this.object[this.head] = item;
            this.size++;
            return;
        }
        this.size++;
        this.head--;
        if(head<0){
            head = this.object.length-1;
        }
        this.object[head]=item;

    }
    public void addLast(T item){
        if (size == this.object.length){
            resize(size*2);
        }
        size++;
        this.object[this.end] = item;
        this.end++;
        if(this.end == this.object.length){
            this.end = 0;
        }
    }
    public T removeFirst(){
        T tmp = this.object[this.head];
        this.object[this.head] = null;
        head++;
        if (head == this.object.length){
            head = 0;
        }
        return tmp;
    }
    public T removeLast(){
        this.end--;
        if(end == -1){
            end = object.length-1;
        }
        T tmp = object[end];
        object[end] = null;
        return tmp;
    }
    public T get(int index){
        if (index>=size){
            return null;
        }
        if (head>=end){
            int remain = object.length-head+1;
            if (index>=remain){
                return object[index-remain];
            }
            return object[head+index];
        }
        else {
            return object[head+index];
        }
    }
    public static void main(String[] args){
        ArrayDeque<Integer> quee = new ArrayDeque<>();
        quee.addLast(2);
        quee.addLast(3);
        quee.addFirst(1);
        quee.addFirst(0);
        quee.printDeque();
        quee.resize(10);
        quee.printDeque();
        quee.addFirst(-1);
        quee.printDeque();
        System.out.println(quee.removeFirst());
        quee.printDeque();
        System.out.println(quee.removeLast());
        quee.printDeque();
        System.out.println(quee.get(10));
        quee.resize(14);
        quee.printDeque();

    }
}
