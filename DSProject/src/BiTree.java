public class BiTree {

    class TreeNode {

        int data;
        TreeNode right;
        TreeNode left;

        TreeNode (int d)
        {this.data = d;}
    }

    boolean search (TreeNode root , int key)
    {
        if (root == null)
            return false;

        else if (root.data == key)
            return true;

        else if (root.data > key)
            return search(root.left,key);

        else
            return search(root.right , key);
    }

    TreeNode min (TreeNode root)
    {
        if (root == null)
            return root;

       if (root.left == null)
           return root;

       else
           return min(root.left);
    }

    int max (TreeNode root)
    {
        if (root == null)
            return -1;

        if (root.right == null)
            return root.data;

        else
            return max(root.right);
    }

    int findHeight (TreeNode root)
    {
        if(root == null)
            return -1;

        return Math.max(findHeight(root.left),findHeight(root.right))+1;
    }

    void insert (TreeNode root , int key)
    {
        if (root == null) {
            root = new TreeNode(key);
            return;
        }
        else if (key < root.data)
            insert(root.left,key);

        else if (key > root.data)
            insert(root.right,key);

        else
            return;
    }

    void delete (TreeNode root ,int key)
    {
        if (root == null)
            return;

        else if (key < root.data)
            delete(root.left,key);

        else if (key > root.data)
            delete(root.right,key);

        else {
            if (root.right == null && root.left == null) {
                root = null;
                return;
            }

            if (root.left == null){
                root = root.right;
                return;
            }

            if (root.right == null){
                root = root.left;
                return;
            }

            TreeNode temp = min(root.right);
            root.data = temp.data;
            delete(temp,temp.data);
        }
    }
}
