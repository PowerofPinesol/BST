import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * The program BST allows a user to insert values into a self-balancing binary search tree (BST)
 * implemented as an AVL tree. Upon the user completing their insertions the program recursively prints
 * the tree in pre-order, in-order, and post-order traversal.
 *
 * @author  Aric Schroeder
 * @version 1.0
 * @since   2021-11-07
 */

public class BST {
    public static void main (String[] args) throws IOException {
        System.out.println("Type integers, press return after each one");
        System.out.println("Type 'end' when done");

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        boolean finished = false;
        while (!finished) {
            String userInput = br.readLine();
            if(userInput.equals("end")) {
                finished = true;
            } else {
                BST.insert(Integer.parseInt(userInput));
            }

        }
        System.out.print("Inorder\t\t: ");
        BST.printInOrder(overallRoot);
        System.out.println();
        System.out.print("Preorder\t: ");
        BST.printPreOrder(overallRoot);
        System.out.println();
        System.out.print("Postorder\t: ");
        BST.printPostOrder(overallRoot);
    }


    private static String inOrderTraverseStr = "";
    private static Node overallRoot;

    public BST() {
        overallRoot = null;
    }

    public void updateRoot(Node node) {
        overallRoot = node;
    }

    public static void inOrderTraverse(Node node){
        if(node != null) {
            if(node.getLeftChild() == null) {
                System.out.print(node.getValue() + "n");
            }
            printInOrder(node.getLeftChild());
            System.out.print(node.getValue() + "");
            printInOrder(node.getRightChild());
        } else {
            return;
        }

// recursive call to left child
// Get the "value" of the left child using the getLeftChild function
// in Node. If the "value" of the left child is null, append "n" to
// inOrderTraverseStr, else append the left node’s value.
// Get the "value" of node that was passed as an argument, and
// append the value to inOrderTraverseStr.
// Get the "value" of the right child using the getRightChild
// function in Node. If the "value" of the right child is null,
// append "n" to inOrderTraverseStr, else append the right node’s
// value.
// recursive call to the right child
    }

    /**
     * Insert value into BST
     * @param value
     */
    public static void insert(int value) {
        if(overallRoot == null) {
            overallRoot = new Node(value);
        } else {
            insertNode(overallRoot, value, null);
        }
    }

    /**
     * Inorder print on BST
     * @param node
     */
    public static void printInOrder(Node node) {
        if(node != null) {
            printInOrder(node.left);
            System.out.print(node.getValue() + " ");
            printInOrder(node.right);
        } else {
            return;
        }
    }

    /**
     * PreOrder print on BST
     * @param node
     */
    public static void printPreOrder(Node node) {
        if(node != null) {
            System.out.print(node.getValue() + " ");
            printInOrder(node.left);
            printInOrder(node.right);
        } else {
            return;
        }
    }

    /**
     * PostOrder print on BST
     * @param node
     */
    public static void printPostOrder(Node node) {
        if(node != null) {
            printInOrder(node.left);
            printInOrder(node.right);
            System.out.print(node.getValue() + " ");
        } else {
            return;
        }
    }

    /**
     * Recursively implementation for insert.
     * @param root
     * @param value
     * @param parent
     */
    public static void insertNode(Node root, int value, Node parent) {
        if(root.left == null && value < root.getValue()) {
            root.left = new Node(value);
            root.updateHeight();
        } else if (root.right == null && value >= root.getValue()) {
            root.right = new Node(value);
            root.updateHeight();
        } else if(value < root.getValue()) {
            insertNode(root.left, value, root);
            root.updateHeight();
        } else if (value >= root.getValue()) {
            insertNode(root.right, value, root);
            root.updateHeight();
        }
        rebalanced(root, parent);
    }



    public static Node rightRotate(Node pivot) {
        if(pivot.left.left == null || pivot.left.rightHeight > pivot.left.leftHeight) {
            Node a = pivot;
            Node b = pivot.left;
            Node c = pivot.left.right;
            Node d = c.left;

            a.left = c;
            c.left = b;
            b.right = d;
        }
        Node a = pivot;
        Node b = pivot.left;
        Node c = pivot.left.left;
        Node d = b.right;

        a.left = d;
        b.right = a;


        overallRoot = b;
        return pivot;
    }

    /**
     * Code to rebalanced tree to fulfil AVL requirements.
     * @param root
     * @param parent
     */

    public static void rebalanced(Node root, Node parent) {
        if(!(Math.abs(root.leftHeight - root.rightHeight) > 1)) return;

        //left block
        if (root.leftHeight - root.rightHeight > 1) {

            if(root.left.left == null || root.left.rightHeight > root.left.leftHeight) {
                Node a = root;
                Node b = root.left;
                Node c = root.left.right;
                Node d = c.left;

                a.left = c;
                c.left = b;
                b.right = d;
            }
            Node a = root;
            Node b = root.left;
            Node c = root.left.left;
            Node d = b.right;

            a.left = d;
            b.right = a;


            if(a == overallRoot) {
                overallRoot = b;
            } else if (parent.left == a) {
                parent.left = b;
            } else {
                parent.right = b;
            }
            c.updateHeight();
            a.updateHeight();
            b.updateHeight();
            if (parent != null) parent.updateHeight();
        }

        //right block
        if(root.rightHeight - root.leftHeight > 1) {
            if(root.right.right == null || root.right.leftHeight > root.right.rightHeight) {
                Node a = root;
                Node b = root.right;
                Node c = root.right.left;
                Node d = c.right;

                a.right = c;
                c.right = b;
                b.left = d;
            }
            Node a = root;
            Node b = root.right;
            Node c = root.right.right;
            Node d = b.left;

            a.right = d;
            b.left = a;

            if(a == overallRoot) {
                overallRoot = b;
            } else if (parent.left == a) {
                parent.left = b;
            } else {
                parent.right = b;
            }
            c.updateHeight();
            a.updateHeight();
            b.updateHeight();
            if (parent != null) parent.updateHeight();
        }
    }
}