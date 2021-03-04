package com.summer.algorithm.adt.list.codes;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * 手写LinkedList
 *
 * @author Jordan
 */
public class MyLinkedList<T> implements Iterable<T> {

    /**
     * 容量
     */
    private int theSize;

    /**
     * 数量
     */
    private int modCount = 0;

    /**
     * 前节点
     */
    private Node<T> beginMarker;

    /**
     * 后节点
     */
    private Node<T> endMarker;

    /**
     * 初始化清除
     */
    public MyLinkedList() {
        doClear();
    }

    public void clear() {
        doClear();
    }

    /**
     * 清除时让开始节点等于空，并将开始节点指向空节点,结束节点指向开始节点
     */
    public void doClear() {
        beginMarker = new Node<>(null, null, null);
        endMarker = new Node<>(null, beginMarker, null);
        beginMarker.next = endMarker;

        theSize = 0;
        modCount++;
    }

    public int size() {
        return theSize;
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    public boolean add(T x) {
        add(size(), x);
        return true;
    }

    public void add(int idx, T x) {
        addBefore(getNode(idx, 0, size()), x);
    }

    public T get(int idx) {
        return getNode(idx).data;
    }

    public T set(int idx, T newVal) {
        Node<T> p = getNode(idx);
        T oldVal = p.data;
        p.data = newVal;
        return oldVal;
    }

    /**
     * 通过获取一个新节点，然后按所指示的顺序改变指针而完成向一个双链表中添加数据
     *
     * @param p 添加元素前面的节点
     * @param x 添加元素
     */
    public void addBefore(Node<T> p, T x) {
        Node<T> newNode = new Node<>(x, p.prev, p);
        newNode.prev.next = newNode;
        p.prev = newNode;
        theSize++;
        modCount++;
    }

    private T remove(int idx) {
        return remove(getNode(idx));
    }

    /**
     * 删除时将删除元素前面的节点的下一个节点指向删除元素后面的节点
     * 后面的节点指向删除元素前面的节点
     *
     * @param p 删除元素
     * @return 删除元素
     */
    //TODO 有问题
    private T remove(Node<T> p) {
        p.next.prev = p.prev;
        p.prev.next = p.next;
        theSize--;
        modCount++;
        return p.data;
    }

    private Node<T> getNode(int idx) {
        return getNode(idx, 0, size() - 1);
    }

    private Node<T> getNode(int idx, int lower, int upper) {
        Node<T> p;

        if (idx < lower || idx > upper) {
            throw new IndexOutOfBoundsException();
        }

        if (idx < size() / 2) {
            p = beginMarker.next;
            for (int i = 0; i < idx; i++) {
                p = p.next;
            }
        } else {
            p = endMarker;
            for (int i = size(); i > idx; i--) {
                p = p.prev;
            }
        }
        return p;
    }

    @Override
    public Iterator<T> iterator() {
        return new LinkedListIterator();
    }

    private static class Node<E> {
        E data;
        Node<E> prev;
        Node<E> next;

        public Node(E d, Node<E> begin, Node<E> end) {
            data = d;
            prev = begin;
            next = end;
        }
    }

    /**
     * 有问题
     */
    private class LinkedListIterator implements Iterator<T> {

        @Override
        public boolean hasNext() {
            return beginMarker.next != endMarker;
        }

        @Override
        public T next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }

            T nextItem = endMarker.next.data;
            Node<T> next = endMarker.next.next;
            return null;
        }
    }

    public static void main(String[] args) {
        MyLinkedList<Integer> linkedList = new MyLinkedList<>();
        System.out.println("是否为空：" + linkedList.isEmpty());

        linkedList.add(1);
        linkedList.add(2);
        linkedList.add(3);
        linkedList.add(4);
        linkedList.add(5);
        for (int i = 0; i < linkedList.size(); i++) {
            System.out.println("第" + (i + 1) + "个元素为：" + linkedList.get(i));
        }
        System.out.println();
        System.out.println();
        System.out.println("删除操作");
        linkedList.remove(2);
        for (int i = 0; i < linkedList.size(); i++) {
            System.out.println("第" + (i + 1) + "个元素为：" + linkedList.get(i));
        }
    }
}
