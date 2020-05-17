public class MyHashMap<K, V> {
    private DLList<K> keys;
    private DLList<V>[] values;

    public MyHashMap() {
        keys = new DLList<K>();
        values = new DLList[102301]; // maximum hashcode is 310 * 330
    }

    public void put(K key, V value) {
        if (values[key.hashCode()] == null) {
            keys.add(key);
            values[key.hashCode()] = new DLList<V>();
        }

        values[key.hashCode()].add(value);
    }

    public boolean contains(K key) {
        if (values[key.hashCode()] == null) {
            return false;
        }

        return true;
    }

    public DLList<V> get(K key) {
        return values[key.hashCode()];
    }

	public DLList<K> getKeys() {
		return keys;
	}
}
