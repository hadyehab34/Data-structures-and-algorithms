public class Stack {

    StackNode top;

    class StackNode {
        char data;
        StackNode next;

        StackNode(char d) {
            this.data = d;
        }
    }

    public void push(char data) {
        StackNode newNode = new StackNode(data);

        if (top == null) {
            top = newNode;
        } else {
            StackNode temp = top;
            top = newNode;
            newNode.next = temp;
        }
    }

    public char pop() {
        char popped = ' ';
        if (top == null) {
            System.out.println("Stack is Empty");
        } else {
            popped = top.data;
            top = top.next;
        }
        return popped;
    }
}