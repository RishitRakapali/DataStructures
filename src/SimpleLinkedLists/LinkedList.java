package SimpleLinkedLists;

public class LinkedList<T extends Comparable<T>> implements List<T> {
    private Node<T> root;
    private int numOfItems;

    @Override
    public void insert(T data) {
        if(root==null){
            //First item
            root = new Node<>(data);
        } else {
            // we know that this is not the first item
        }
    }

    @Override
    public void remove(T data) {

    }

    @Override
    public void traverse() {

    }

    @Override
    public int size() {
        return numOfItems;
    }
}
