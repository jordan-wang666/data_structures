package com.summer.algorithm.list;

import java.util.ArrayList;
import java.util.List;

/**
 *  迭代器和集合中remove方法对比
 *
 *  1.collection中的remove方法必须首先找出要被删除的项，
 *   Iterator中的remove方法可以删除next返回最新的项
 *
 *  2.增强for循环调用collection中的remove会报错
 *    Iterator去进行迭代删除不会报错
 *
 *
 * @author Jordan
 */
public class IteratorAndCollectionRemoveTest {
    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();

        // 赋值
        for(int i=1;i<10;i++){
            list.add(i);
        }

        // 此处会报错
        // Exception in thread "main" java.util.ConcurrentModificationException
//        for (Integer i : list) {
//            if(i==3){
//                list.remove(i);
//            }
//        }

        //此处不会报错
//        Iterator<Integer> iterator = list.iterator();
//        while (iterator.hasNext()) {
//            if (iterator.next() == 2) {
//                iterator.remove();
//            }
//        }

        // 新写法
        list.removeIf(integer -> integer == 2);
        System.out.println(list.toString());
    }
}
