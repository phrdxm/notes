import java.util.*;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

class Node {

    private String id;

    private Node left;

    private Node right;

    public Node () {
    }

    public Node (String id, Node left, Node right) {
        this.id = id;
        this.left = left;
        this.right = right;
    }

    public String getId() {
        return id;
    }

    public Node getLeft() {
        return left;
    }

    public Node getRight() {
        return right;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setLeft(Node left) {
        this.left = left;
    }

    public void setRight(Node right) {
        this.right = right;
    }
}

class Demo {
    public static void main(String[] args) {
        Node binaryTreeRoot = new Node (
            "1",
            new Node("2", new Node("4", null, null), null),
            new Node("3", 
                new Node("5",
                    new Node("7", null, null),
                    new Node("8", null, null)
                ),
                new Node("6", null, null)
            )
        );

        printBinaryTreeInLine(binaryTreeRoot);
    }

    private static void printBinaryTreeInLine(Node root) {
        Node currentLineEnd = root;
        Queue<Node> queue = new LinkedBlockingQueue<>();
        queue.add(root);
        while (queue.size() != 0) {
            Node currentNode = queue.poll();
            System.out.print(currentNode.getId() + " ");
            if (currentNode.getLeft() != null) {
                queue.add(currentNode.getLeft());
            }
            if (currentNode.getRight() != null) {
                queue.add(currentNode.getRight());
            }
            if (currentNode == currentLineEnd) {
                System.out.println();
                currentLineEnd = currentNode.getRight();
            }
        }
        System.out.println();
    }
}