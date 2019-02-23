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
        String value = binaryTreeRoot.serializeDLR();
        System.out.println(value);
        System.out.println(binaryTreeRoot.equals(Node.deserializeDLR(value)));
    }
}