import javax.swing.plaf.synth.SynthRadioButtonMenuItemUI;
import java.io.*;
import java.util.*;
import java.util.LinkedList;


public class Dictionary
{
    public static void main(String[] arg) throws Exception
    {
        BufferedReader bufferIn = new BufferedReader(new FileReader("src/dictionary.txt"));
        String nextLine;
        BinaryTreeNode tree = new BinaryTreeNode(bufferIn.readLine());

        while (((nextLine = bufferIn.readLine()) != null)) {
            tree.insert(tree, nextLine);
        }
        bufferIn.close();

        boolean again = false;
        Scanner sc = new Scanner(System.in);

        while (!again) {
            choice(tree);
            System.out.println("Do you want to choose again ? yes or no");
            String choose = sc.next();
            if (choose.equals("yes"))
                again = false;
            else
                again = true;
        }
    }





    static void choice(BinaryTreeNode root)
    {
        System.out.println("Choose number from menu:\n" +
                "1. Print tree using in order traversal\n" +
                "2. Print tree using post order traversal\n" +
                "3. Print tree using pre order traversal\n" +
                "4. Print tree size (number of words loaded in the tree)\n" +
                "5. Print tree using BFS Traversal\n" +
                "6. Print tree height\n" +
                "7. Search for a word\n" +
                "8. Insert new word in the tree.");

        Scanner sc = new Scanner(System.in);
        int choice = sc.nextInt();

        switch (choice)
        {
            case 1 :
                TreeTraversale.inOrder(root);
                break;

            case 2 :
                TreeTraversale.postOrder(root);
                break;

            case 3 :
                TreeTraversale.preOrder(root);
                break;

            case 4 :
                System.out.println(BST.countNode(root));
                break;

            case 5 :
                TreeTraversale.breadthFirstSearch(root);
                break;

            case 6 :
                System.out.println(BST.height(root));
                break;

            case 7 :
                System.out.println("Enter word to search for");
                String word = sc.next();
                BST.search(root , word);
                break;

            case 8 :
                System.out.println("Enter word to search for");
                String word2 = sc.next();
                BST.insert(root , word2);
                break;
        }
    }
}




    class BinaryTreeNode extends BST
    {
        String data;
        BinaryTreeNode left;
        BinaryTreeNode right;

        public BinaryTreeNode(String data) {
            this.data = data;
        }
    }


    class BST
    {
        public static BinaryTreeNode insert(BinaryTreeNode root, String key)
        {
            /* If the tree is empty, return a new node */
            if (root == null) return new BinaryTreeNode(key);

            if (key.equals(root.data)) {
                System.out.println("Word exists in tree.");
                return root;
            }

            else if (key.compareToIgnoreCase(root.data) < 0)
                root.left = insert(root.left, key);

            else if (key.compareToIgnoreCase(root.data) > 0)
                root.right = insert(root.right, key);

            return root;
        }


        public static void search (BinaryTreeNode root, String key)
        {
            BinaryTreeNode temp = root;
            while (temp != null) {

                if (temp.data.equals(key)) {
                    System.out.println("Word is correct it is in the tree");
                    return;
                }

                if (key.compareToIgnoreCase(temp.data) > 0) {
                    if (temp.right == null) {
                        System.out.println("Leaf Node Word :" + temp.data);
                        InorderSuccessorPredecessors i = new InorderSuccessorPredecessors();
                        i.successorPredecessor(root, temp.data);
                        System.out.println("Inorder Successor is : " + i.successor+"\n"
                                + "Inorder Predecessor is : " + i.predecessor);
                    }
                        temp = temp.right;
                    continue;
                    }

                if (key.compareToIgnoreCase(temp.data) < 0) {
                    if (temp.left == null) {
                        System.out.println("Leaf Node :" + temp.data);
                        InorderSuccessorPredecessors i = new InorderSuccessorPredecessors();
                        i.successorPredecessor(root, temp.data);
                        System.out.println("Inorder Successor is : " + i.successor+"\n"
                                + "Inorder Predecessor is : " + i.predecessor);
                    }
                    temp = temp.left;
                    continue;
                }
                  break;
            }
        }


        public static int countNode(BinaryTreeNode root)
        {
            if(root == null) //base case
                return 0;

            return 1 + countNode(root.left) + countNode(root.right);
        }


        public static int height(BinaryTreeNode root)
        {
            // Base case: empty tree has height 0
            if (root == null) {
                return 0;
            }

            // recursive call for left and right subtree and consider maximum depth
            return 1 + Math.max(height(root.left), height(root.right));
        }
    }


    class InorderSuccessorPredecessors
    {
        String successor, predecessor;

        public void successorPredecessor(BinaryTreeNode root, String key)
        {
            if (root != null) {

            if (root.data.equals(key)) {
                // go to the right most element in the left subtree, it will the predecessor.
                if (root.left != null) {
                    BinaryTreeNode t = root.left;
                    while (t.right != null) {
                        t = t.right;
                    }
                    predecessor = t.data;
                }

            if (root.right != null) {
                // go to the left most element in the right subtree, it will the successor.
                BinaryTreeNode t = root.right;
                while (t.left != null) {
                    t = t.left;
                }
                successor = t.data;
            }
            } else if (key.compareToIgnoreCase(root.data) < 0){
                 // we make the root as successor because we might have a
                 // situation when value matches with the root, it wont have
                 // right subtree to find the successor, in that case we need
                 // parent to be the successor
                successor = root.data;
                successorPredecessor(root.left, key);
            }
            else if (key.compareToIgnoreCase(root.data) > 0) {
                predecessor = root.data;
                successorPredecessor(root.right, key);
              }
            }
        }
    }


    class TreeTraversale
    {

        public static void preOrder(BinaryTreeNode root)
        {
            if (root == null) return;

            System.out.println(root.data); //visit current
            preOrder(root.left);
            preOrder(root.right);
        }


        public static void inOrder(BinaryTreeNode root)
        {
            if (root == null) return;

            inOrder(root.left);
            System.out.println(root.data); //visit current
            inOrder(root.right);
        }


        public static void postOrder(BinaryTreeNode root)
        {
            if (root == null) return;

            postOrder(root.left);
            postOrder(root.right);
            System.out.println(root.data); //visit current
        }


        public static void breadthFirstSearch(BinaryTreeNode root)
        {
            Queue<BinaryTreeNode> queue = new LinkedList<>();
            queue.add(root);

            while (!queue.isEmpty()) {
                BinaryTreeNode visitingNode = queue.poll();
                if (visitingNode == null) continue;
                System.out.println(visitingNode.data);
                queue.add(visitingNode.left);
                queue.add(visitingNode.right);
            }
        }
    }


