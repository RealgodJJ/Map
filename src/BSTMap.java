public class BSTMap<K extends Comparable<K>, V> implements Map<K, V> {
    private class Node {
        K key;
        V value;
        Node left, right;
        int size;   //该节点之下的所有节点总数

        private Node(K key, V value) {
            this.key = key;
            this.value = value;
            left = null;
            right = null;
            size = 1;
        }
    }

    private Node root;
    private int size;

    public BSTMap() {
        root = null;
        size = 0;
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public void add(K key, V value) {
        root = add(root, key, value);
    }

    private Node add(Node node, K key, V value) {
        if (node == null) {
            size++;
            return new Node(key, value);
        }

        if (node.key.compareTo(key) > 0) {
            node.left = add(node.left, key, value);
            node.size++;
        } else if (node.key.compareTo(key) < 0) {
            node.right = add(node.right, key, value);
            node.size++;
        } else  //如果存在这个key，则将对应的value值进行更新
            node.value = value;

        return node;
    }

    @Override
    public V get(K key) {
        return getNode(root, key).value;
    }

    private Node getNode(Node node, K key) {
        if (node == null)
            return null;

        if (node.key.compareTo(key) < 0)
            return getNode(node.right, key);
        else if (node.key.compareTo(key) > 0)
            return getNode(node.left, key);
        else
            return node;
    }

    @Override
    public void set(K key, V newValue) {
        Node node = getNode(root, key);

        if (node == null)
            throw new IllegalArgumentException(key + " doesn't exist!");

        node.value = newValue;
    }

    @Override
    public boolean contains(K key) {
//        return getNode(root, key) != null;
        return contains(root, key);
    }

    private boolean contains(Node node, K key) {
        if (node == null)
            return false;

        if (node.key.compareTo(key) == 0)
            return true;
        else if (node.key.compareTo(key) > 0)
            return contains(node.left, key);
        else
            return contains(node.right, key);
    }

    @Override
    public V remove(K key) {
        Node deleteNode = getNode(root, key);
        if (deleteNode != null) {
            root = remove(root, key);
            return deleteNode.value;
        }
        return null;
    }

    private Node minimum(Node node) {
        if (node.left == null)
            return node;
        return minimum(node.left);
    }

    private Node removeMin(Node node) {

        if (node.left == null) {
            Node rightNode = node.right;
            node.right = null;
            size--;
            return rightNode;
        }

        node.left = removeMin(node.left);
        return node;
    }

    private Node remove(Node node, K key) {
        if (node == null)
            return null;

        if (key.compareTo(node.key) < 0) {
            node.left = remove(node.left, key);
            return node;
        } else if (key.compareTo(node.key) > 0) {
            node.right = remove(node.right, key);
            return node;
        } else {    //e.compareTo(node.e) == 0
            //左子树为空的情况
            if (node.left == null) {
                Node rightNode = node.right;
                node.right = null;
                size--;
                return rightNode;
            }

            //右子树为空的情况
            if (node.right == null) {
                Node leftNode = node.left;
                node.left = null;
                size--;
                return leftNode;
            }

            /*待删除节点左右都不为空的情况
            （1）找到比待删除节点大的最小节点，即待删除节点右子树的最小节点
            （2）用这个节点代替待删除节点的位置*/
            Node successor = minimum(node.right);
            successor.right = removeMin(node.right);
            size++;
            successor.left = node.left;

            node.left = node.right = null;
            size--;
            return successor;
        }
    }
}
