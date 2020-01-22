public class SLList {
    private Node head;

    public SLList() {
        head = null;
    }

    public void add(Item item) {
        Node current = head;
        if (head == null) {
            head = new Node(item);
        } else {
            while (true) {
                if (current.get().equals(item)) {
                    current.get().incrementQuantity();
                    current.get().setTimestamp(item.getTimestamp());
                    break;
                } else if (current.next() == null) {
                    current.setNext(new Node(item));
                    break;
                }

                current = current.next();
            }
        }
    }

    public String toString() {
        Node current = head;
        String s = "";
        while (current != null) {
            s += current.get().toString() + "\n";
            current = current.next();
        }

        return s;
    }

    public void remove(Item data) {
        Node current = head;

        if (head.get().equals(data)) {
            head = head.next();
        } else {
            while (current.next() != null) {
                if (current.next().get().equals(data)) {
                    current.setNext(current.next().next());
                    break;
                }
                current = current.next();
            }
        }

    }

    public double getTotalPrice() {
        Node current = head;
        double totalPrice = 0;
        while (current != null) {
            Item i = (Item)current.get();
            double price = i.getPrice() * i.getQuantity();
            totalPrice += price;
            current = current.next();
        }

        return totalPrice;
    }

    public Item get(int index) {
        if (index == 0) {
            return head.get();
        } else {
            Node current = head;
            int count = 0;

            while (current != null) {
                Item i = (Item)current.get();

                if (index == count) {
                    return i;
                }

                current = current.next();
                count++;
            }
        }

        return null;
    }

    public void set(int index, Item i) {
        if (index == 0) {
            head.setData(i);
        } else {
            Node current = head;
            int count = 0;

            while (current != null) {
                // Item i = (Item)current.get();

                if (index == count) {
                    current.setData(i);
                }

                current = current.next();
                count++;
            }
        }
    }

    public int size(){
		int count = 0;
        Node current = head;
        
		while(current != null){
            current = current.next();
			count++;
		}

		return count;
	}
}