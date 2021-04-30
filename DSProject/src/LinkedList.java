
class Node {
    int data;
    Node next;


    Node(int d) {
        data = d;
    }
}

public class LinkedList {
    Node head;

    LinkedList insert(LinkedList list, int dataa) {
        Node newNode = new Node(dataa);
        newNode.next = null;

        if (list.head == null) {
            list.head = newNode;
        } else {
            Node last = list.head;
            while (last.next != null) {
                last = last.next;
            }
            last.next = newNode;
        }
        return list;
    }

    void printList(LinkedList list) {
        Node c = list.head;
        System.out.print("LinkedList: ");

        while (c != null) {
            System.out.print(c.data+"\t");
            c = c.next;
        }


    }
}

