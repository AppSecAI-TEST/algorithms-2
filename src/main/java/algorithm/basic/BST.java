package algorithm.basic;

import java.util.Iterator;
import java.util.Stack;

public class BST {
  
  public static class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    
    public TreeNode(int val) {
      this.val = val;
    }
  }
  
  private TreeNode root;
  
  public BST() {
    root = null;
  }
  
  public void insert(int val) {
    if (root == null) {
      root = new TreeNode(val);
    } else {
      insertHelper(root, val);
    }
  }
  
  private TreeNode insertHelper(TreeNode node, int val) {
    if (node == null) {
      return new TreeNode(val);
    } else if (node.val < val) {
      node.right = insertHelper(node.right, val);
    } else {
      node.left = insertHelper(node.left, val);
    }
    return node;
  }
  
  public int size() {
    return size(root);
  }
  
  private int size(TreeNode node) {
    if (node == null) {
      return 0;
    }
    return 1 + size(node.left) + size(node.right);
  }
  
  public int height() {
    return height(root);
  }
  
  private int height(TreeNode node) {
    if (node == null) {
      return 0;
    }
    return 1 + Math.max(height(node.left), height(node.right));
  }
  
  public int diameter() {
    return diameter(root);
  }
  
  public int diameter(TreeNode node) {
    if (node == null) {
      return 0;
    }
    
    int leftHeight = height(node.left);
    int rightHeight = height(node.right);
    
    int leftDiameter = diameter(node.left);
    int rightDiameter = diameter(node.right);
    
    return Math.max(1 + leftHeight + rightHeight, Math.max(leftDiameter, rightDiameter));
  }
  
  public boolean search(int val) {
    TreeNode cur = root;
    
    while (cur != null) {
      if (cur.val == val) {
        return true;
      }
      else if (cur.val < val) {
        cur = cur.right;
      }
      else {
        cur = cur.left;
      }
    }
    return false;
  }
  
  public void delete(TreeNode node) {
    // assume the node is in the tree
    delete(root, node);
  }
  
  private TreeNode getMinNode(TreeNode node) {
    TreeNode cur = node;
    while (cur.left != null) {
      cur = cur.left;
    }

    return cur;
  }

  private TreeNode deleteMin(TreeNode node) {
    if (node.left == null) {
      return node.right;
    } else {
      node.left = deleteMin(node.left);
      return node;
    }
  }

  public void delete(TreeNode node, int key) {
    if (node.val == key) {
      if (node.left == null) {
        node = node.right;
      } else if (node.right == null) {
        node = node.left;
      } 
      TreeNode tmp = node;
      node = getMinNode(node.right);
      node.right = deleteMin(tmp.right);
      node.left = tmp.left;
    } else if (node.val < key) {
      node.right = delete(node.right, key);
    } else {
      node.left = delete(node.left, key);
    }
  }
  
  public class TreeIterator implements Iterator<TreeNode> {

    private Stack<TreeNode> stack;
    private TreeNode cur;
    
    public TreeIterator() {
      cur = root;
    }
    
    @Override
    public boolean hasNext() {
      return (cur != null || !stack.isEmpty());
    }

    @Override
    public TreeNode next() {
      while (cur != null) {
        stack.push(cur);
        cur = cur.left;
      }
      
      TreeNode node = stack.pop();
      cur = node.right;
      
      return node;
    }

    @Override
    public void remove() {
      throw new UnsupportedOperationException();
    }
    
  }

}
