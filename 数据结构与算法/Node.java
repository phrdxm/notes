import java.util.*;
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

    @Override
    public boolean equals(Object other) {
        if (other == null) {
            return false;
        }
        if (this == other) {
            return true;
        }
        if (!getClass().isAssignableFrom(other.getClass())) {
            return false;
        }
        Node otherNode = (Node) other;
        return Objects.equals(getId(), otherNode.getId()) && 
            Objects.equals(getLeft(), otherNode.getLeft()) &&
            Objects.equals(getRight(), otherNode.getRight());
    }

    @Override
    public int hashCode() {
        int result = getId() == null ? 0 : getId().hashCode();
        int leftHashCode = getLeft() == null ? 0 : getLeft().hashCode();
        int rightHashCode = getRight() == null ? 0 : getRight().hashCode();
        result = result * 31 + leftHashCode;
        result = result * 31 + rightHashCode;

        return result;
    }

    public String serializeDLR() {
        String str = getId() + "!";
        if (getLeft() == null) {
            str += "#!";
        } else {
            str += getLeft().serializeDLR();
        }
        if (getRight() == null) {
            str += "#!";
        } else {
            str += getRight().serializeDLR();
        }

        return str;
    }

    private static Node deserializeDLR(List<String> ids) {
        if ("#".equals(ids.get(0))) {
            ids.remove(0);
            return null;
        }

        return new Node(ids.remove(0), Node.deserializeDLR(ids), Node.deserializeDLR(ids));
    }

    public static Node deserializeDLR(String str) {
        List<String> ids = new ArrayList<>(Arrays.asList(str.split("!")));
        Iterator<String> iter = ids.iterator();
        while (iter.hasNext()) {
            String id = iter.next();
            if (id == null || id.equals("")) {
                iter.remove();
            }
        }
        
        return Node.deserializeDLR(ids);
    }

    public static void printBinaryTreeInLine(Node root) {
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