import java.util.Scanner;

public class Problem2 {
    public static void main(String[] args) {
        BST tree = new BST();
        Scanner sc = new Scanner(System.in);

        double r = sc.nextDouble();
        double size = 0;

        do {
            size++;
            int x = sc.nextInt();
            tree.Insert(x, size);
            if (size / (size - tree.count) >= r) {
                System.out.println("\nratio : " + size / (size - tree.count));
                System.out.println("many repetitions");
                break;
            }
            if (x == -1) {
                size--;
                System.out.println("ratio : " + (size / (size - tree.count)));
                System.out.println("only few repetitions");
                break;
            }
        } while (r > 1);
    }
}

class BST {

    static class Node {

        int key;
        Node left, right;

        public Node(int item) {
            key = item;
            left = right = null;
        }
    }
    Node root;
    int count;

    BST() {
        root = null;
    }

    public void Insert(int key, double size) {
        root = insert(key, root, size);
    }

    public Node insert(int key, Node root, double size)
    {
        if (root == null) {
            root = new Node(key);
            return root;
        } else if (key == root.key) {
            count++;
            return root;
        } else if (key < root.key) {
            root.left = insert(key, root.left, size);

        } else {
            root.right = insert(key, root.right, size);

        }
        return root;

    }

    void printInorder(Node node) {
        if (node == null) {
            return;
        }

        printInorder(node.left);

        System.out.println(node.key);

        printInorder(node.right);

    }
}

