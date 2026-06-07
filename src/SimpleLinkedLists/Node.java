package SimpleLinkedLists;

public class Node<T extends Comparable<T>> {

    // data stored
    private T data;

    // Need more memory than arrays
    private Node<T> nextNode;

    public Node(T data){
        this.data = data;
    }

    // Getters and setters for data and nextNode
    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public Node<T> getNextNode() {
        return nextNode;
    }

    public void setNextNode(Node<T> nextNode) {
        this.nextNode = nextNode;
    }

    @Override
    public String toString() {
        return "Node{" +
                "data=" + data +
                '}';
    }

    public static void main(String[] args) {
    }
}
