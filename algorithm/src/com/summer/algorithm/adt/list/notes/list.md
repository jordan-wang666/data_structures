# 表

### 链表

1.单链表（linked list）:每一个节点均含有表元素和到包含该元素后继元的节点的链（link），
我们称之为next链，最后一个单元的next链引用null。
<br>2.双链表（doubly linked list）：让每一个节点持有一个指向它在表中的前驱节点的链

### Collecion接口与Iterator接口

Collecion接口与Iterator接口都含有remove方法

1.collection中的remove方法必须首先找出要被删除的项，<br>Iterator中的remove方法可以删除next返回最新的项 
<br>2.增强for循环（foreach）调用collection中的remove会报java.util.ConcurrentModificationException
<br>Iterator去进行迭代remove不会报错

### ArrayList与LinkedList

1. ArrayList（可增长数组）：
+ 优点:对get和set调用花费时间短
+ 缺点:新项的插入和现有的项删除代价昂贵， 除非变动是在末端进行
2. LinkedList(双链表)：
+ 优点:新项插入和现有项的删除均开销很小
+ 缺点:使用LinkedList的缺点是它不容易作索引，因此对get的调用是昂贵的，除非接近表的端点

### ListIterator接口
ListIterator扩展了Iterator的功能。方法previous和hasPrevious使得表从后向前的便利得以完成
