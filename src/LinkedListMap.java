public class LinkedListMap<K, V> implements Map<K, V> {
    private class Node {
        K key;
        V value;
        Node next;

        public Node(K key, V value, Node next) {
            this.key = key;
            this.value = value;
            this.next = next;
        }

        public Node(K key) {
            this(key, null, null);
        }

        public Node() {
            this(null, null, null);
        }

        @Override
        public String toString() {
            return key.toString() + ": " + value.toString();
        }
    }

    private Node dummyHead;
    private int size;

    public LinkedListMap() {
        dummyHead = new Node();
        size = 0;
    }

    @Override
    public int getSize() {
        return size;
    }

    private Node getNode(K key) {
        Node currentNode = dummyHead.next;
        while (currentNode != null) {
            if (currentNode.key.equals(key)) {
                return currentNode;
            }

            currentNode = currentNode.next;
        }

        return null;
    }

    @Override
    public void add(K key, V value) {
        Node node = getNode(key);

        if (node == null) { //不包含这个键值对，则直接添加到链表头
            dummyHead.next = new Node(key, value, dummyHead.next);
            size++;
        } else {    //如果已经包含这个key，则更新value值
            node.value = value;
        }
    }

    @Override
    public V remove(K key) {
        Node prev = dummyHead;
        while (prev.next != null) {
            if (prev.next.key.equals(key))
                break;
            prev = prev.next;
        }

        if (prev.next != null) {
            Node delNode = prev.next;
            prev.next = delNode.next;
            delNode.next = null;
            size--;
            return delNode.value;
        }
        return null;
    }

    @Override
    public V get(K key) {
        Node node = getNode(key);
        return node == null ? null : node.value;
    }

    @Override
    public void set(K key, V newValue) {
        Node node = getNode(key);

        if (node == null)
            throw new IllegalArgumentException(key + "doesn't exist.");

        node.value = newValue;
    }

    @Override
    public boolean contains(K key) {
        return getNode(key) != null;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }
}
