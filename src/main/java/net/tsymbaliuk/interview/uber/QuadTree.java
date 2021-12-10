package net.tsymbaliuk.interview.uber;

import java.util.Arrays;

/*
Quadtree is a tree data structure in which each internal node either is a leaf node or has exactly 4 children. Quadtrees are most often used to partition a two-dimensional space by recursively subdividing it into four quadrants or regions. One of the common use cases of quadtree is image compression, for example:
Input Image:
+---------+--------+
|  2 |  2 |  3 | 3 |
+----|----|----|---|
|  2 |  2 |  3 | 3 |
+----+----|--------|
|  4 |  2 |  5 | 5 |
+----|----|----|---|
|  2 |  3 |  5 | 5 |
+----+----+--------+

Quadtree representation:
+---------+--------+
|         |        |
|    2    |    3   |
+----+----|--------|
| 4  | 2  |        |
+----|----|    5   |
| 2  | 3  |        |
+----+----+--------+

Design the quadtree data structure and write a function that builds a quadtree for an input image, where image will be given as a two-d array of integers.

Corner cases:
If dimensions not divided evenly by 2, add empty nodes to align to 2
For rectangular images, no shild nodes



Time complexity:

Memory O(M*N)

CPU: O( log(M*N) * log(M*N) )
*/
public class QuadTree {
    public static class Node {
        Integer val;

        Node topLeft;
        Node topRight;
        Node bottomLeft;
        Node bottomRight;

        public String toString() {

            return "{"
                    + ((val == null) ?
                    ((topLeft == null) ? "" : topLeft.toString())
                    + "," + ((topRight == null) ? "" : topRight.toString())
                    + "," + ((bottomLeft == null) ? "" : bottomLeft.toString())
                    + "," + ((bottomRight == null) ? "" : bottomRight.toString())
                    : val)
                    + "}";
        }
    }

    public static Node calculateQuadTree(int [][] image) {
        if (image == null || image.length == 0 || image[0].length == 0) {
            return null;
        }

        Node[][] nodes = new Node[image.length + image.length % 2][];
        for (int i = 0; i < image.length; i++) {
            nodes[i] = new Node[image[i].length + image[i].length % 2];
            for (int j = 0; j < image[i].length; j++) {
                nodes[i][j] = new Node();
                nodes[i][j].val = image[i][j];
            }
        }

        System.out.println("initial");
        for (Node[] n : nodes) {
            System.out.println(Arrays.toString(n));
        }
        System.out.println();
        return aggregateNodes(nodes);
    }

    public static Node aggregateNodes(Node[][] nodes) {
        int N = nodes.length;
        int M = nodes[0].length;

        while (N > 1 || M > 1) {
            Node[][] newNodes = new Node[N / 2][];

            for (int i = 0, ii = 0; i < N; i += 2, ii++) {
                newNodes[ii] = new Node[M / 2];
                for (int j = 0, jj = 0; j < M; j += 2, jj++) {
                    newNodes[ii][jj] = tryAggregate(
                            checkBoundry(nodes, i, j, N, M),
                            checkBoundry(nodes, i, j + 1, N, M),
                            checkBoundry(nodes, i + 1, j, N, M),
                            checkBoundry(nodes, i + 1, j + 1, N, M));
                }
            }
            nodes = newNodes;

            System.out.printf("N: %d, M: %d\n", N, M);
            for (Node[] nn : nodes) {
                System.out.println(Arrays.toString(nn));
            }
            System.out.println();
            N /= 2;
            M /= 2;
        }
        return nodes[0][0];
    }

    private static Node checkBoundry(Node [][]nodes, int i, int j, int N, int M) {
        if (i < N && j < M) {
            return nodes[i][j];
        }
        return null;
    }

    private static Node tryAggregate(Node topLeft, Node topRight, Node bottomLeft, Node bottomRight) {
        if (topLeft == null && topRight == null && bottomLeft == null && bottomRight == null) {
            return null;
        }

        Node res = new Node();

        if (topLeft != null && topRight != null && bottomLeft != null && bottomRight != null
                && topLeft.val == topRight.val && bottomLeft.val == bottomRight.val && topLeft.val == bottomRight.val) {
            res.val = topLeft.val;
            return res;
        }
        res.topLeft = topLeft;
        res.topRight = topRight;
        res.bottomLeft = bottomLeft;
        res.bottomRight = bottomRight;
        return res;
    }

    public static void main(String[] args) {
        int[][] src = new int[][]{
                {2,  2,  3, 3},
                {2,  2,  3, 3},
                {4,  2,  5, 5},
                {2,  3,  5, 5}};
        Node res = calculateQuadTree(src);

        System.out.println(res.toString());
    }
}
