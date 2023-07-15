// BinaryTree.java
// Kevin Liu
// Uses binary tree functionality

public class BinaryTree {
    public static void main(String[] args) {
        // BTree oak = new BTree();
        // oak.add(43);
        // oak.add(53);
        // oak.add(40);
        // oak.add(41);
        // System.out.println(oak);
        // System.out.println(oak.depth(41));
        // System.out.println(oak.depth(0));

        BTree elm = new BTree();
        elm.add(50);
        elm.add(30);
        elm.add(80);
        elm.add(20);
        elm.add(40);
        elm.add(90);
        elm.add(85);
        // elm.add(423);
        // elm.add(13);
        // elm.add(93);
        // elm.display();
        // elm.display(BTree.IN);
        // elm.display(BTree.PRE);
        elm.display(BTree.POST);

        // BTree ash = new BTree();
        // ash.add(1);
        // ash.add(2);
        // ash.add(3);
        // ash.add(4);
        // System.out.println(ash.countLeaves());

        // BTree maple = new BTree();
        // maple.add(5);
        // maple.add(4);
        // maple.add(3);
        // maple.add(6);
        // System.out.println(maple.height());

        // BTree birch = new BTree();
        // birch.add(10);
        // birch.add(9);
        // birch.add(13);
        // birch.add(12);
        // birch.add(14);
        // System.out.println(birch.isAncestor(13, 12));

        // BTree pine = new BTree();
        // pine.add(50);
        // pine.add(40);
        // pine.add(60);
        // pine.add(30);
        // pine.add(45);
        // pine.add(55);
        // pine.add(54);
        // pine.add(56);
        // pine.add(70);
        // pine.display(BTree.PRE);

        // pine.delete(60);
        // pine.display(BTree.PRE);

        // BTree alpha = new BTree();
        // alpha.add(50);
        // alpha.add(60);
        // alpha.add(40);
        // alpha.add(70);
        // alpha.add(30);
        // alpha.add(80);
        // alpha.add(20);
        // alpha.display(BTree.PRE);

        // BTree beta = new BTree();
        // beta.add(55);
        // beta.add(45);
        // beta.add(35);
        // beta.add(65);
        // beta.add(25);
        // beta.add(75);
        // beta.display(BTree.PRE);

        // alpha.add(beta);
        // alpha.display(BTree.PRE);
        // alpha.display(BTree.IN);

        // BTree spruce = new BTree();
        // spruce.add(10);
        // spruce.add(5);
        // spruce.add(2);

        // spruce.add(3);
        // spruce.add(15);
        // spruce.add(20);
        // spruce.add(22);
        // spruce.add(24);

        // spruce.display(BTree.PRE);
        // System.out.println(spruce.isBalanced());

    }
}
