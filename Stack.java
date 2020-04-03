package eg.edu.alexu.csd.datastructure.stack.cs;

public class Stack implements IStack {
    private class Node {
        private Object data ;
        private Node next;
        public Node (Object data){
            this.data = data;
        }
    }
    private Node peak;
    @Override
    public Object pop()
    {
        if (isEmpty()){
            throw new RuntimeException("Stack is already empty!!");
        }
        Object element;
        element = peak.data;
        peak = peak.next;
        return element;
    }

    @Override
    public Object peek() {
        if (isEmpty()) {
            throw new RuntimeException("Stack is empty!!");
        }
        return peak.data;
    }

    @Override
    public void push(Object element) {
        Node node = new Node(element);
        if (peak != null) {
            node.next = peak;
            node.data = element;
        }
        peak = node;
    }

    @Override
    public boolean isEmpty() {
        return peak == null;
    }

    @Override
    public int size() {
        Node node = peak;
        int size = 0;
        while (node.next != null){
            size++;
            node = node.next;
        }
        size++;
        return size;
    }
}
