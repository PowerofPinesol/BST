public class Node {

    private int value;
    Node left;
    Node right;
    int leftHeight;
    int rightHeight;

    public Node(int value){
        this.value = value;
        this.left = null;
        this.right = null;
        this.leftHeight = 0;
        this.rightHeight = 0;
    }

    /**
     * Returns value of node
     * @return
     */
    public int getValue(){
        return value;
    }

    //Not required, cleared by professor
    public void insert(int value){
    }

    /**
     * Code for updating the height upon each re-balance
     */
    public void updateHeight() {
        int leftLeft = left == null ? 0 : left.leftHeight;
        int leftRight = left == null ? 0 : left.rightHeight;
        int rightLeft = right == null ? 0 : right.leftHeight;
        int rightRight = right == null ? 0 : right.rightHeight;

        if( left != null) {
            leftHeight = Math.max(leftLeft, leftRight) + 1;
        }

        if (right != null) {
            rightHeight = Math.max(rightLeft, rightRight) + 1;
        }

        if (left == null) {
            leftHeight = 0;
        }

        if (right == null) {
            rightHeight = 0;
        }
    }


    public Node getLeftChild() {
        return left;
    }

    public Node getRightChild() {
        return right;
    }
}
