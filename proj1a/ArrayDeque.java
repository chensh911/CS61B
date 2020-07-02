
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
        index = (index -1) % capacity;
        if (index < 0) {
            index += capacity;
        }
        return index;
    }
    /**  Creates an empty array deque. */
    public ArrayDeque() {
        capacity = 100;
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
            System.arraycopy(arrayList, 0, newTArray, first, size - first);
            System.arraycopy(arrayList, size - first, newTArray, 0, last + 1);
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
/** test */
//    public static void main(String[] args) {
//        ArrayDeque<Integer> a = new ArrayDeque<>();
//        System.out.println(a.size());
//        a.addFirst(-1);
//        a.addFirst(-2);
//        a.removeFirst();
//        a.removeFirst();
//        System.out.println(a.isEmpty());
//        a.addFirst(-1);
//        System.out.println(a.size());
//        a.printDeque();
//    }
}
