import java.util.LinkedList;
import java.util.Iterator;
import java.util.ArrayList;

public class HashTable<E> {
    private DLList<E>[] cars;
    private ArrayList<String> uniqueMakes;

    @SuppressWarnings("unchecked")
    public HashTable() {
        cars = new DLList[1000000];
        uniqueMakes = new ArrayList<String>();
    }

    public void add(E data) {
        if (cars[data.hashCode()] == null) {
            cars[data.hashCode()] = new DLList<E>();
            Car c = (Car)data;
            if (!uniqueMakes.contains(c.getMake()))
                uniqueMakes.add(c.getMake());
        }

        cars[data.hashCode()].add(data);
    }

    public String toString(String make) {
        String s = "";

        for (int i = 0; i < cars.length; i++) {
            if (cars[i] != null && cars[i].size() != 0) {
                Car c = (Car)cars[i].get(0);

                if (c.getMake().equals(make)) {
                    String t = "";
    
                    for (int j = 0; j < cars[i].size(); j++) {
                        t += j + 1 + ". " + cars[i].get(j).toString() + "\n";
                    }
    
                    s += t + "\n";
                }
            }
        }

        return s;
    }

    public void removeEntry(String make, int index) {
        for (int i = 0; i < cars.length; i++) {
            if (cars[i] != null && cars[i].size() != 0) {
                Car c = (Car)cars[i].get(0);

                if (c.getMake().equals(make)) {
                    cars[i].remove(index);
                }
            }
        }
    }

    public E getEntry(String make, int index) {
        for (int i = 0; i < cars.length; i++) {
            if (cars[i] != null && cars[i].size() != 0) {
                Car c = (Car)cars[i].get(0);

                if (c.getMake().equals(make)) {
                    return cars[i].get(index);
                }
            }
        }

        return null;
    }

    public ArrayList<String> getUniqueMakes() {
        return uniqueMakes;
    }
}