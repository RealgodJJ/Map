public interface Map<K, V> {
    int getSize();

    void add(K key, V value);

    V remove(K key);

    V get(K key);

    void set(K key, V newValue);

    boolean contains(K key);

    boolean isEmpty();
}
