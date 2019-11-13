package net.tsymbaliuk.leetcode;

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */

 class TreeNode {
     int val;
     TreeNode left;
     TreeNode right;
     TreeNode(int x) { val = x; }
 }

class Solution {
  public int widthOfBinaryTree(TreeNode root) {
    int depth = findDepth(root, 0);
    int[][] levels = new int[depth][];
//     System.out.println("Depth: " + depth);
//     int [] tree = new int[(1<<depth)-1];
//     buildTree(root, 1, 0, tree);
//     System.out.println(Arrays.toString(tree));
    
    for(int i = 0; i < depth; i++) {
      levels[i] = new int[]{-1, -1};
    } 
    int maxWidth = findMaxWidth(root, 0, 0, levels);
//     maxWidth = levels[0][1] - levels[0][0]; 
    
//     for (int[] l : levels) {
//       maxWidth = Math.max(maxWidth, l[1] - l[0]);
//       System.out.println(Arrays.toString(l));
//     }
    return maxWidth + 1;  
  }
  
  private int findDepth(TreeNode root, int level) {
    if (root == null) {
      return level;
    }
    return Math.max(findDepth(root.left, level + 1), findDepth(root.right, level + 1));
  }
  
  private int findMaxWidth(TreeNode node, int level, int index, int[][] levels) {
    if (node == null) return 0;
    if (levels[level][0] == -1 || levels[level][0] > index) {
      levels[level][0] = index;
    }
    if (levels[level][1] == -1 || levels[level][1] < index) {
      levels[level][1] = index;
    }
    int childMax = Math.max(
      findMaxWidth(node.left, level + 1, 2 * index, levels),
      findMaxWidth(node.right, level + 1, 2 * index + 1, levels));
    return Math.max(levels[level][1] - levels[level][0], childMax);
  }
  
  // private void buildTree(TreeNode node, int level, int index, int[] tree) {
  //   int pos = (1<<(level - 1)) - 1 + index;
  //   System.out.println(String.format("l:%d, i:%d, p:%d, 2^(level - 1):%d", level, index, pos, 1<<(level - 1)));
  //   if (node == null || pos >= tree.length) {
  //     return;
  //   }
  //   tree[pos] = node.val;
  //   buildTree(node.left, level+1, 2*index, tree);
  //   buildTree(node.right, level+1, 2*index + 1, tree);
  // }
}
