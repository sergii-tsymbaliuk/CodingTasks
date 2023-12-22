package net.tsymbaliuk.leetcode;

/*
 * Definition for a binary tree node.
 */


import org.junit.jupiter.api.Test;

import java.util.LinkedList;
import java.util.Queue;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode() {}
    TreeNode(int val) { this.val = val; }
    TreeNode(int val, TreeNode left, TreeNode right) {
     this.val = val;
     this.left = left;
     this.right = right;
    }
}


public class CountCompleteTreeNodes {
    class Solution {
        int treeDepth(TreeNode root) {
            int depth = 0;
            while (root != null) {
                depth++;
                root = root.left;
            }
            return --depth;
        }

        boolean isExists(int pos, TreeNode root, int d) {
            int left = 0; int right = (1 << d) - 1;
            while (left < right) {
                int  p = (left + right) / 2;
                if (pos <= p) {
                    right = p;
                    root = root.left;
                } else {
                    left = p + 1;
                    root = root.right;
                }
            }
            return root != null;
        }

        public int countNodes(TreeNode root) {

            int d = treeDepth(root);
            if (d <= 0) return d + 1;

            int l = 0, r = (1 << d) - 1;
            while (l <= r) {
                int p = (l + r) / 2;

                if (isExists(p, root, d)) {
                    l = p + 1;
                } else {
                    r = p - 1;
                }
            }
            return (1 << d) - 1 + l;
        }
    }

    private TreeNode buildTree(int[] vals) {
        if (vals == null || vals.length == 0) return null;

        TreeNode[] tree = new TreeNode[vals.length];
        int insPos = 0;
        tree[insPos++] = new TreeNode();
        for (int i = 0; i < vals.length; i++) {
            tree[i].val = vals[i];
            if (insPos < vals.length) {
                tree[i].left = new TreeNode();
                tree[insPos++] = tree[i].left;
            }
            if (insPos < vals.length) {
                tree[i].right = new TreeNode();
                tree[insPos++] = tree[i].right;
            }
        }
        return tree[0];
    }

    @Test
    public void testCase() {
        Solution s = new Solution();

        assertEquals(3, s.countNodes(buildTree(new int[]{1,2,3})));
        assertEquals(6, s.countNodes(buildTree(new int[]{1,2,3,4,5,6})));
        assertEquals(0, s.countNodes(buildTree(new int[]{})));
        assertEquals(1, s.countNodes(buildTree(new int[]{1})));
    }


    @Test
    public void testBuildTree() {
        TreeNode root = buildTree(new int[]{1,2,3,4,5,6});
        StringBuilder sb = new StringBuilder();
        sb.append(root.val);
        TreeNode a = root.left;
        while (a != null) {
            sb.append(',').append(a.val);
            a = a.left;
        }
        a = root.right;
        while (a != null) {
            sb.append(',').append(a.val);
            a = a.left;
        }
        assertEquals("1,2,4,3,6", sb.toString());
    }

    @Test
    public void testTreeDepth() {
        Solution s = new Solution();

        assertEquals(2, s.treeDepth(buildTree(new int[]{1,2,3,4,5,6})));
        assertEquals(3, s.treeDepth(buildTree(new int[]{1,2,3,4,5,6,7,8})));
    }

    @Test
    public void testIsExists() {
        Solution s = new Solution();
        TreeNode root = buildTree(new int[]{1,2,3,4,5,6});
        int depth = s.treeDepth(root);
        assertEquals(true, s.isExists(0, root, depth));
        assertEquals(true, s.isExists(1, root, depth));
        assertEquals(true, s.isExists(1 << depth - 1, root, depth));
    }
}
