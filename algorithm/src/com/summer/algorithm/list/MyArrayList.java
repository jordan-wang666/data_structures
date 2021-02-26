package com.summer.algorithm.list;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Spliterator;
import java.util.function.Consumer;

/**
 * 手写 ArrayList
 *
 * @author Jordan
 */
public class MyArrayList<T extends Object> implements Iterable<T> {

    /**
     * 默认容量为 10
     */
    private static final int DEFAULT_CAPACITY = 10;

    /**
     * 当前项数
     */
    private int theSize;

    /**
     * 数组
     */
    private T[] theItems;

    /**
     * 初始化时恢复默认状态
     */
    public MyArrayList() {
        doClean();
    }

    /**
     * 手动清除
     */
    public void clean() {
        doClean();
    }

    /**
     * 清除
     */
    private void doClean() {
        theSize = 0;
        ensureCapacity(DEFAULT_CAPACITY);
    }

    /**
     * 获取项数
     *
     * @return 项数
     */
    public int size() {
        return theSize;
    }

    /**
     * 是否List为空
     *
     * @return true 空
     */
    public boolean isEmpty() {
        return theSize == 0;
    }

    /**
     * 缩容
     */
    public void trimToSize() {
        ensureCapacity(size());
    }

    /**
     * 获取元素
     *
     * @param index 下标
     * @return 查询对象
     */
    public T get(int index) {
        if (index < 0 || index > size()) {
            throw new ArrayIndexOutOfBoundsException();
        }
        return theItems[index];
    }

    /**
     * 添加元素
     *
     * @param index  下表
     * @param newVal 插入对象
     */
    public T set(int index, T newVal) {
        if (index < 0 || index > size()) {
            throw new ArrayIndexOutOfBoundsException();
        }
        T old = theItems[index];
        theItems[index] = newVal;
        return old;
    }

    /**
     * 扩容
     *
     * @param newCapacity 新容量
     */
    public void ensureCapacity(int newCapacity) {
        if (newCapacity > theSize) {
            T[] old = theItems;
            theItems = (T[]) new Object[newCapacity];
            for (int i = 0; i < size(); i++) {
                theItems[i] = old[i];
            }
        }
    }

    /**
     * 添加
     *
     * @return true 成功
     */
    public boolean add(T newVal) {
        add(size(), newVal);
        return true;
    }

    /**
     * 添加
     *
     * @param index  下标
     * @param newVal 新元素
     */
    public void add(int index, T newVal) {
        if (theItems.length == size()) {
            ensureCapacity(size() * 2 + 1);
        }
        for (int i = theSize; i > index; i--) {
            theItems[i] = theItems[i - 1];
        }
        theItems[index] = newVal;
        theSize++;
    }

    /**
     * 删除元素
     *
     * @param index 下标
     */
    public T remove(int index) {
        T removeItem = theItems[index];
        for (int i = index; i < size(); i++) {
            theItems[i] = theItems[i + 1];
        }
        theSize--;
        return removeItem;
    }

    @Override
    public Iterator<T> iterator() {
        return new ArrayListIterator();
    }

    private class ArrayListIterator<T> implements Iterator<T> {

        private int current = 0;

        @Override
        public boolean hasNext() {
            return current < size();
        }

        @Override
        public T next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            return (T) theItems[current++];
        }

        @Override
        public void remove() {
            MyArrayList.this.remove(--current);
        }

        @Override
        public void forEachRemaining(Consumer action) {

        }
    }

    @Override
    public void forEach(Consumer<? super T> action) {

    }

    @Override
    public Spliterator<T> spliterator() {
        return null;
    }

    public static void main(String[] args) {
        MyArrayList<Integer> list = new MyArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);
        System.out.println("List size=" + list.size());
        for (int i = 0; i < list.size(); i++) {
            System.out.println("第" + i + "个元素=" + list.get(i));
        }
        System.out.println("****************删除操作************************");
        list.remove(3);
        for (int i = 0; i < list.size(); i++) {
            System.out.println("第" + i + "个元素=" + list.get(i));
        }

        System.out.println("迭代器测试");
        Iterator<Integer> iterator = list.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }
}
