
public class ArrayDeque <T> {
    private T[] TArray;
    private int size;

    /**  Creates an empty array deque. */
    public ArrayDeque(){
        TArray = (T[]) new Object[100];
        size = 0;
    }
    /** resize an Array by multiply a factor*/
    public void resize(int configure){
        T[] NewTArray = (T[]) new Object[configure];
        System.arraycopy(NewTArray,0,TArray,0,size);
        TArray = NewTArray;
    }
    /** Adds an item of type T to the front of the deque. */
    public void addFirst(T item){
        if(this.size()+1 > TArray.length) {
            this.resize(size * 2);
        }
        int index = size;
        while (index > 0){
            TArray[index] = TArray[index - 1];
            index --;
        }
        TArray[index] = item;
        size ++;
    }
    /** Adds an item of type T to the back of the deque. */
    public void addLast(T item){
        if(this.size()+1 > TArray.length) {
            this.resize(size * 2);
        }
        TArray[size] = item;
        size ++;
    }
    /** Returns true if deque is empty, false otherwise. */
    public boolean isEmpty(){
        return (size == 0);
    }
    /** Returns the number of items in the deque. */
    public int size(){
        return size;
    }
    /** Prints the items in the deque from first to last,
     *  separated by a space. */
    public void printDeque(){
        int index = 0;
        while(index < size){
            System.out.print(TArray[index] + " ");
            size ++;
        }
    }
    /** Removes and returns the item at the front of the deque.
     * If no such item exists, returns null. */
    public T removeFirst(){
        if(size == 0) return null;

        int index = 0;
        T temp = TArray[0];
        while(index < this.size()){
            TArray[index] = TArray[index +1];
        }
        TArray[size - 1] = null;
        size--;
        if(TArray.length / 4 > this.size()){
            resize(size);
        }
        return temp;
    }
    /** Removes and returns the item at the back of the deque.
     * If no such item exists, returns null. */
    public T removeLast(){
        if(size == 0) return null;

        T temp = TArray[size - 1];
        TArray[size - 1] = null;
        size--;
        if(TArray.length / 4 > this.size()){
            resize(size);
        }
        return temp;
    }
    /** Gets the item at the given index, where 0 is the front,
     * 1 is the next item, and so forth. If no such item exists,
     * returns null. Must not alter the deque! */
    public T get(int index){
        if(index >= size) return null;
        else return TArray[index];
    }
}
