public class LinkedListDeque<T> {
    private class TNode{
        private T item;
        private TNode pre;
        private TNode next;
        private TNode(T x, TNode p, TNode n) {
            item = x;
            pre = p;
            next = n;
        }
        public TNode() {
            item = null;
            pre = this;
            next = this;
        }
    }
    private TNode sentinel;
    private int size;

    /** Creates an empty linked list deque. */
    public LinkedListDeque() {
        sentinel = new TNode();
        size = 0;
    }
    /** Adds an item of type T to the front of the deque. */
    public void addFirst(T item) {
        TNode temp = sentinel.next;
        sentinel.next = new TNode(item, sentinel, temp);
        temp.pre = sentinel.next;
        if (sentinel.pre == sentinel) {
            sentinel.pre = sentinel.next;
        }
        size++;
    }
    /** Adds an item of type T to the back of the deque. */
    public void addLast(T item) {
        TNode temp = sentinel.pre;
        sentinel.pre = new TNode(item, temp, sentinel);
        temp.next = sentinel.pre;
        size++;
    }
    /** Returns true if deque is empty, false otherwise. */
    public boolean isEmpty() {
        if (size == 0) {
            return true;
        } else {
            return false;
        }
    }
    /** Returns the number of items in the deque. */
    public int size() {
        return size;
    }
    /** Prints the items in the deque from first to last, separated by a space. */
    public void printDeque() {
        if (this.size() == 0) {
            return;
        }

        TNode last = sentinel.pre;
        TNode ptr = sentinel.next;
        while (ptr != last) {
            System.out.print(ptr.item + " ");
            ptr = ptr.next;
        }
        System.out.println(last.item);
    }
    /** Removes and returns the item at the front of the deque.
     * If no such item exists, returns null. */
    public T removeFirst() {
        if (this.size() == 0) {
            return null;
        }

        TNode ret = sentinel.next;
        TNode temp = sentinel.next.next;
        sentinel.next = temp;
        temp.pre = sentinel;
        size--;
        return ret.item;
    }
    /** Removes and returns the item at the back of the deque.
     * If no such item exists, returns null. */
    public T removeLast() {
        if (this.size() == 0) {
            return null;
        }

        TNode ret = sentinel.pre;
        TNode temp = sentinel.pre.pre;
        sentinel.pre = temp;
        temp.next = sentinel;
        size--;
        return ret.item;
    }
    /** Gets the item at the given index, where 0 is the front,
     * 1 is the next item, and so forth. If no such item exists,
     * returns null.Must not alter the deque! */
    public T get(int index) {
        if (this.size() < index - 1) {
            return null;
        }

        int nowIndex = 0;
        TNode ptr = sentinel.next;
        while(nowIndex != index) {
            nowIndex++;
            ptr = ptr.next;
        }
        return ptr.item;
    }
    /** Same as get, but uses recursion. */
    private T getRecursiveMock(int index, TNode node) {
        if (index == 0) {
            return node.item;
        } else {
            return getRecursiveMock(index - 1, node.next);
        }
    }
    public T getRecursive(int index) {
        if (this.size() < index - 1) {
            return null;
        }

        return getRecursiveMock(index, sentinel.next);
    }
}
