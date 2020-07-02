
public class ArrayDeque<T> {
    private T[] arrayList;
    private int size;
    private int capacity;
    private int first;
    private int last;

    private int forward(int index) {
        index = (index + 1) % capacity;
        return index;
    }
    private int back(int index) {
        index = (index - 1) % capacity;
        if (index < 0) {
            index += capacity;
        }
        return index;
    }
    /**  Creates an empty array deque. */
    public ArrayDeque() {
        capacity = 2;
        arrayList = (T[]) new Object[capacity];
        size = 0;
        first = last = -1;
    }
    /** resize an Array by multiply a factor*/
    private void resize(int newCapacity) {
        T[] newTArray = (T[]) new Object[newCapacity];
        if (first == last) {
            newTArray[0] = arrayList[first];
        } else if (first < last) {
            System.arraycopy(arrayList, 0, newTArray, first, size);
            first = 0;
            last = size - 1;
        } else {
            System.arraycopy(arrayList, first, newTArray, 0, size - first);
            System.arraycopy(arrayList, 0, newTArray, size - first, last + 1);
            first = 0;
            last = size - 1;
        }
        capacity = newCapacity;
        arrayList = newTArray;
    }
    /** Adds an item of type T to the front of the deque. */
    public void addFirst(T item) {
        if (this.size() == arrayList.length) {
            this.resize(size * 2);
        }
        if (size != 0) {
            first = back(first);
            arrayList[first] = item;
        } else {
            arrayList[0] = item;
            first = last = 0;
        }
        size++;
    }
    /** Adds an item of type T to the back of the deque. */
    public void addLast(T item) {
        if (this.size() == arrayList.length) {
            this.resize(size * 2);
        }
        if (size != 0) {
            last = forward(last);
            arrayList[last] = item;
        } else {
            arrayList[0] = item;
            first = last = 0;
        }
        size++;
    }
    /** Returns true if deque is empty, false otherwise. */
    public boolean isEmpty() {
        return (size == 0);
    }
    /** Returns the number of items in the deque. */
    public int size() {
        return size;
    }
    /** Prints the items in the deque from first to last,
     *  separated by a space. */
    public void printDeque() {
        if (last == -1) {
            return;
        }
        int index = first;
        while (index != last) {
            System.out.print(arrayList[index] + " ");
            index = (index + 1) % capacity;
        }
        System.out.print(arrayList[last]);
    }
    /** Removes and returns the item at the front of the deque.
     * If no such item exists, returns null. */
    public T removeFirst() {
        if (size == 0) {
            return null;
        }

        T temp = arrayList[first];
        if (size == 1) {
            arrayList[first] = null;
            first = last = -1;
        } else {
            arrayList[first] = null;
            first = forward(first);
        }
        size--;
        if (capacity / 4 > size) {
            resize(capacity / 4);
        }
        return temp;
    }
    /** Removes and returns the item at the back of the deque.
     * If no such item exists, returns null. */
    public T removeLast() {
        if (size == 0) {
            return null;
        }

        T temp = arrayList[last];
        if (size == 1) {
            arrayList[last] = null;
            first = last = -1;
        } else {
            arrayList[last] = null;
            last = back(last);
        }
        size--;
        if (capacity / 4 > size) {
            resize(capacity / 4);
        }
        return temp;
    }
    /** Gets the item at the given index, where 0 is the front,
     * 1 is the next item, and so forth. If no such item exists,
     * returns null. Must not alter the deque! */
    public T get(int index) {
        if (index >= size) {
            return null;
        } else {
            return arrayList[Math.abs((index + first) % capacity)];
        }
    }
///** test */
//    public static void main(String[] args) {
//        ArrayDeque<Integer> a = new ArrayDeque<>();
//        a.addFirst(0);
//        System.out.println(a.get(0));      //==> 0
//        a.removeFirst();//     ==> 0
//        a.addFirst(3);
//        a.addLast(4);
//        a.removeLast();//      ==> 4
//        a.removeFirst();//     ==> 3
//        a.addLast(7);
//        a.removeLast();   //   ==> 7
//        a.addLast(9);
//        a.removeLast();//      ==> 9
//        a.addFirst(11);
//        a.addFirst(12);
//        a.addLast(13);
//        System.out.println(a.get(1));    //  ==> 11
//        a.removeLast();//      ==> 13
//        a.removeFirst();//     ==> 12
//        a.addFirst(17);
//        a.addFirst(18);
//        System.out.println(a.get(2));//      ==> 11
//        a.addLast(20);
//        a.addLast(21);
//        System.out.println(a.get(0));
//    }
}
