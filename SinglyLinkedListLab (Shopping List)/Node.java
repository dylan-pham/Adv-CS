public class Node {
    private Item data;
    private Node next;

    public Node(Item data) {
        this.data = data;
        this.next = null;
    }

    public Item get() {
        return data;
    }

    public Node next() {
        return next;
    }

    public void setNext(Node next) {
        this.next = next;
    }

    public void setData(Item data) {
        this.data = data;
    }
}