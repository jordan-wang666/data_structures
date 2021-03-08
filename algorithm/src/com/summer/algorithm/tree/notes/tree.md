#树
对于大量的输入数据，链表的线性访问时间太慢，不宜使用。二叉查找树的查询时间为O（logN），时两种集合类TreeSet和TreeMap实现的基础。
###树
树可以用几种方式定义，定义树的一种自然的方式是递归的方式，一棵树是一些节点的集合，这个集合可以是空集，若不是空集，则树由根节点和子节点链接
###二叉树
二叉树（binary tree）是一颗树，其中每个节点都不能多于两个儿子，而对于特殊类型的二叉树，即二叉查找树（binary search tree）
#####二叉查找树
二叉树的一个重要应用是他们在查找中的使用
######重写compareTo方法
     private int myCompare(T lhs, T rhs) {
            if (cmp != null) {
                return cmp.compare(lhs, rhs);
            }
            return lhs.compareTo(rhs);
        }
######contains方法
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
######findMin和findMax方法
为执行findMin，从根开始并且只要有左儿子就向左进行，中止点就是最小的元素。<br>
findMax例程除朝右儿子外其余过程相同。

     private BinaryNode<T> findMin(BinaryNode<T> root) {
            if (root == null) {
                return null;
            } else if (root.left == null) {
                return root;
            }
            return findMin(root.left);
        }
     private BinaryNode<T> findMax(BinaryNode<T> root) {
            if (root != null) {
                while (root.right != null) {
                    root = root.right;
                }
                return root;
            }
            return null;
        }                
######insert方法
为了将X插入到树T中，可以像用contains那样延树查找。如果找到X，则什么都不用做（或做一些更新）。否则，将X插入到遍历的路径上的最后一点上。
    
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
######remove方法
如果节点是一片树叶，那么它可以被立即删除。如果节点有一个儿子，则该节点可以在其父节点调整自己的链以绕过该节点后被删除。<br>
复杂的情况是处理具有两个儿子的节点。一般的删除策略是用其右子树的最小的数据（很容易找到）代替该节点的数据并递归地删除那个节点（现在它是空的）

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