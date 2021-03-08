package com.summer.algorithm.tree.codes;

import java.nio.BufferUnderflowException;
import java.util.Comparator;

/**
 * 二叉查找树
 *
 * @author Jordan
 */
public class BinarySearchTree<T extends Comparable<? super T>> {

    private BinaryNode<T> root;

    private Comparator<? super T> cmp;

    public BinarySearchTree(BinaryNode<T> root) {
        this.root = root;
    }

    public void makeEmpty() {
        root = null;
    }

    public boolean isEmpty() {
        return root == null;
    }

    public boolean contains(T x) {
        return contains(x, root);
    }

    public T findMin() {
        if (isEmpty()) {
            throw new BufferUnderflowException();
        }
        return findMin(root).element;
    }

    public T findMax() {
        if (isEmpty()) {
            throw new BufferUnderflowException();
        }
        return findMax(root).element;
    }

    public void insert(T x) {
        root = insert(x, root);
    }

    public void remove(T x) {
        root = remove(x, root);
    }

    public void printTree() {

    }

    /**
     * 树中是否包含某节点
     *
     * @param x is item to search for
     * @param t the node that roots the subtree
     * @return true if the item is found；false otherwise
     */
    private boolean contains(T x, BinaryNode<T> t) {
        if (t == null) {
            return false;
        }
        int compareResult = x.compareTo(t.element);
        if (compareResult < 0) {
            return contains(x, t.left);
        } else if (compareResult > 0) {
            return contains(x, t.right);
        }
        return true;
    }

    /**
     * 重写compareTo方法
     *
     * @param lhs
     * @param rhs
     * @return
     */
    private int myCompare(T lhs, T rhs) {
        if (cmp != null) {
            return cmp.compare(lhs, rhs);
        }
        return lhs.compareTo(rhs);
    }

    /**
     * 查找树中最小的值
     *
     * @param root 根节点
     * @return 最小的节点
     */
    private BinaryNode<T> findMin(BinaryNode<T> root) {
        if (root == null) {
            return null;
        } else if (root.left == null) {
            return root;
        }
        return findMin(root.left);
    }

    /**
     * 查询最大的节点
     *
     * @param root 根节点
     * @return 最大的节点
     */
    private BinaryNode<T> findMax(BinaryNode<T> root) {
        if (root != null) {
            while (root.right != null) {
                root = root.right;
            }
            return root;
        }
        return null;
    }

    /**
     * 在子树中插入元素
     *
     * @param x 插入元素 the item to insert
     * @param t 插入的节点 the node that roots the subtree
     * @return 新插入的节点 the new root of the subtree
     */
    private BinaryNode<T> insert(T x, BinaryNode<T> t) {
        if (t == null) {
            return new BinaryNode<>(x, null, null);
        }
        int compareResult = x.compareTo(t.element);
        if (compareResult < 0) {
            t.left = insert(x, t.left);
        } else if (compareResult > 0) {
            t.right = insert(x, t.right);
        }
        return t;
    }

    /**
     * 从子树中删除某一元素 Internal method to remove from a subtree0
     *
     * @param x 删除的元素 the item to remove
     * @param t 子树的根节点 the node that roots the subtree
     * @return 子树的新节点 the new root of the subtree
     */
    private BinaryNode<T> remove(T x, BinaryNode<T> t) {
        if (t == null) {
            return t;
        }
        int compareResult = x.compareTo(t.element);
        if (compareResult < 0) {
            t.left = remove(x, t.left);
        } else if (compareResult > 0) {
            t.right = remove(x, t.right);
        } else if (t.left != null && t.right != null) {
            t.element = findMin(t.right).element;
            t.right = remove(t.element, t.right);
        } else {
            t = (t.left != null) ? t.left : t.right;
        }
        return t;
    }

    private void printTree(BinaryNode<T> t) {
    }


    private static class BinaryNode<T> {

        public BinaryNode() {
        }

        public BinaryNode(T element, BinaryNode<T> left, BinaryNode<T> right) {
            this.element = element;
            this.left = left;
            this.right = right;
        }

        T element;

        BinaryNode<T> left;

        BinaryNode<T> right;
    }
}
