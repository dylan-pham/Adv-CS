public class BinarySearchTree<E extends Comparable<E>> {
    private Node<E> root;
    private int passCounter;

    public BinarySearchTree() {
        root = null;
        passCounter = 0;
    }

    public int getPassCounter() {
        return passCounter;
    }

    public void resetPassCounter() {
        passCounter = 0;
    }

    public void add(E data) {
        if (root == null) {
            root = new Node<E>(data);
        } else {
            add(data, root);
        }
    }

    private void add(E data, Node<E> startNode) {
        Node<E> newNode = new Node<E>(data);
        if (data.compareTo(startNode.get()) > 0) {
            if (startNode.getRight() == null) {
                startNode.setRight(newNode);
                newNode.setParent(startNode);
                passCounter++;
            } else {
                passCounter++;
                add(data, startNode.getRight());
            }
        } else if (data.compareTo(startNode.get()) < 0) {
            if (startNode.getLeft() == null) {
                startNode.setLeft(newNode);
                newNode.setParent(startNode);
                passCounter++;
            } else {
                passCounter++;
                add(data, startNode.getLeft());
            }
        }
    }

    public String toString() {
        return inOrderString(root);
    }

    private String inOrderString(Node<E> startNode) {
        String s  = "";

        if (startNode != null) {
            s += inOrderString(startNode.getLeft());
            s += startNode.get();
            s += inOrderString(startNode.getRight());
        }

        return s;
    }

    public String toStringPreOrder() {
        return toStringPreOrder(root);
    }

    private String toStringPreOrder(Node<E> startNode) {
        String s = "";

        if (startNode != null) {
            s += startNode.get() + " ";
            s += toStringPreOrder(startNode.getLeft());
            s += toStringPreOrder(startNode.getRight());
        }

        return s;
    }

    public boolean contains(E data) {
        if (root == null) {
            return false;
        }

        return contains(data, root);
    }

    private boolean contains(E data, Node<E> startNode) {
        if (startNode == null) {
            return false;
        } else if (startNode.get().equals(data)) {
            return true;
        } else if (data.compareTo(startNode.get()) < 0) {
            return contains(data, startNode.getLeft());
        } else if (data.compareTo(startNode.get()) > 0) {
            return contains(data, startNode.getRight());
        }

        return false; //default
    }

    public E findData(E data) {
        if (root == null) {
            return data;
        }

        return findData(data, root);
    }

    public E findData(E data, Node<E> startNode) {
        if (startNode == null) {
            passCounter++;
            return null;
        } else if (startNode.get().equals(data)) {
            passCounter++;
            return startNode.get();
        } else if (data.compareTo(startNode.get()) < 0) {
            passCounter++;
            return findData(data, startNode.getLeft());
        } else if (data.compareTo(startNode.get()) > 0) {
            passCounter++;
            return findData(data, startNode.getRight());
        }

        return null; //default
    }

    public void remove(E data){
        remove(data,root,null);
    }

    private void remove(E data, Node<E> node, Node<E> parent) {
       if (node == null) {
       } else if (data.compareTo(node.get()) > 0) {
           remove(data, node.getRight(), node);
       } else if (data.compareTo(node.get()) < 0) {
           remove(data, node.getLeft(), node);
       } else if (data.compareTo(node.get()) == 0) {
           if (node.getLeft() == null && node.getRight() == null) { // NO CHILDREN
               if (parent == null) { //root
                   node = null;
               } else {
                   if (parent.getRight() == node) {
                       parent.setRight(null);
                   } else if (parent.getLeft() == node ){
                       parent.setLeft(null);
                   }
               }
           } else if (node.getLeft() == null && node.getRight() != null) { // ONLY RIGHT CHILD
               if (parent == null) { //root
                   root = root.getRight();
                   node = null; //delete the current node;
               } else {
                   if (parent.getRight() == node) {
                       parent.setRight(node.getRight());
                   } else if (parent.getLeft() == node ){
                       parent.setLeft(node.getRight());
                   }
               }
           } else if (node.getLeft() != null && node.getRight() == null) { // ONLY LEFT CHILD
               if (parent == null) { //root
                   root = root.getLeft();
                   node = null; //delete the current node
               } else {
                   if (parent.getRight() == node) {
                       parent.setRight(node.getLeft());
                   } else if (parent.getLeft() == node ){
                       parent.setLeft(node.getLeft());
                   }
               }
           } else if (node.getLeft() != null && node.getRight() != null) { // BOTH CHILDREN EXIST
               if (parent == null) { //root
                   Node<E> lowest = lowest(node.getRight(), node); 
                   lowest.setRight(root.getRight());
                   lowest.setLeft(root.getLeft());
                   root = lowest;
                   node = null; //delete the current node;
               } else {
                   Node<E> lowest = lowest(node.getRight(), node); //get the lowest node to the right
                   lowest.setRight(node.getRight());
                   lowest.setLeft(node.getLeft());
                  
                   if (parent.getRight() == node) {
                       parent.setRight(lowest);
                   } else if (parent.getLeft() == node ){
                       parent.setLeft(lowest);
                   }
               }
           } else {
               System.out.println("");
           }
          
       }
   }
   private Node<E> lowest(Node<E> node, Node<E> parent) {
       if (node.getLeft() == null) {
          
           if (parent.getRight() == node) {
               parent.setRight(null);
           } else if (parent.getLeft() == node) {
               parent.setLeft(null);
           }
          
           return node;
       } else {
           return lowest(node.getLeft(), node);
       }
   }
}