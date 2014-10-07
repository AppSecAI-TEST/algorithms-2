package algorithm.guge;

import java.util.*;

class TreeNode {
  int val;
  TreeNode left;
  TreeNode right;

  public TreeNode(int val) {
    this.val = val;
  }
}

/**
 *	Find the right deepest leaf.
 *	If two or more nodes have the same depth, pick the right one.
 *
 */
public class RightDeepestLeaf {

  public TreeNode findRightDeepestLeaf(TreeNode root) {
    if (root == null) {
      return null;
    }
    Queue<TreeNode> queue = new LinkedList<TreeNode>();
    queue.offer(root);

    TreeNode cur = null;
    while (!queue.isEmpty()) {
      cur = queue.poll();
      if (cur.left != null) {
        queue.offer(cur.left);
      }
      if (cur.right != null) {
        queue.offer(cur.right);
      }
    }
    return cur;
  }
}
