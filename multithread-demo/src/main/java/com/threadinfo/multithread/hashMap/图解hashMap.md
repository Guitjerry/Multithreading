# 图解hashMap

## 简介

如果单单去看hashMap的源码，大部分代码我们可能都不明白是什么意思。何不把它看成是一个把它理解成为一种产品需求,在工作中，在开发产品时，我们都需要一个清晰明了的产品需求图。

## hashMap到底做什么的

1. hashMap是存储键值对的，存储数据的

2. 既然是存储数据的,就应该涉及到数据结构,常用的数据结构 数组,链表,队列,树

## 常用数据结构举例

* ArrayList-->数组
* LinkedList-->链表

##### 分析:

1. 结构图

![image-20200419205527652](C:\Users\Admin\Desktop\文档图片\hashmap\数组链表数据结构图.png)

2.实践与思考

​	 既然要查看ArrayList的数据结构,我们只需看每次做put操作的实际过程就行了

```java
public boolean add(E e) {
    ensureCapacityInternal(size + 1);  // Increments modCount!!
    elementData[size++] = e;
    return true;
}
```

其中elementData就是一个数组对象

```
transient Object[] elementData; // non-private to
```

同样,要查看LinkedList的数据结构,只需要看add方法

```
public boolean add(E e) {
    linkLast(e);
    return true;
}
```

```
/**
 * Links e as last element.
 */
void linkLast(E e) {
    final Node<E> l = last;
    final Node<E> newNode = new Node<>(l, e, null);
    last = newNode;
    if (l == null)
        first = newNode;
    else
        l.next = newNode;
    size++;
    modCount++;
}
```

```
 private static class Node<E> {
        E item;
        Node<E> next;
        Node<E> prev;

        Node(Node<E> prev, E element, Node<E> next) {
            this.item = element;
            this.next = next;
            this.prev = prev;
        }
    }
```

可以看到,其数据结构包含了一个内部类Node,链表的结构 

## hashMap的数据结构

在上面我们知道数组跟链接的数据结构,从图中可知道,

* 数组如果需要增加的话,需要整体移动,故插入比较慢
* 链表查询需要遍历每一个节点,故查询比较慢
* hashMap应该结合两者的优点 数组+链表

我们假设最开始的结构图是这样的

### 结构图

![image-20200419205527652](C:\Users\Admin\Desktop\文档图片\hashmap\hashMap数据结构图v1.png)

用面向对象的思维去思考,基本结构应该是什么样的?

数组的表示: Node table[]

单向链表的表示:

​	Class Node{

​		key

​		value

​		Node next

  }

> 我们带着这个推断去查看下我们hashMap的源码,用Node去搜索，可以找到这个变量

```
/**
 * The table, initialized on first use, and resized as
 * necessary. When allocated, length is always a power of two.
 * (We also tolerate length zero in some operations to allow
 * bootstrapping mechanics that are currently not needed.)
 */
transient Node<K,V>[] table;
```



> 数组应该有最大值,默认值

```
   /**初始化值
     * The default initial capacity - MUST be a power of two.
     */
    static final int DEFAULT_INITIAL_CAPACITY = 1 << 4; // aka 16
```

```
/**最大值
 * The maximum capacity, used if a higher value is implicitly specified
 * by either of the constructors with arguments.
 * MUST be a power of two <= 1<<30.
 */
static final int MAXIMUM_CAPACITY = 1 << 30;
```

> 数组大小不够用了怎么办?

应该有一个变量是来确定到什么值的时候开始扩容的

```
/**
 * The load factor used when none specified in constructor.
 */
static final float DEFAULT_LOAD_FACTOR = 0.75f;
```

我们习惯把它叫做加载因子,到了数组长度的0.75是扩容,也就是12

> 数组下面的链表长度有限制吗?

如果没限制,数组链表下面可能挂载太多数据,链表查询速度本来就慢,影响性能

```
/**
 * The bin count threshold for using a tree rather than list for a
 * bin.  Bins are converted to trees when adding an element to a
 * bin with at least this many nodes. The value must be greater
 * than 2 and should be at least 8 to mesh with assumptions in
 * tree removal about conversion back to plain bins upon
 * shrinkage.
 */
static final int TREEIFY_THRESHOLD = 8;
```

```
/**
 * The bin count threshold for untreeifying a (split) bin during a
 * resize operation. Should be less than TREEIFY_THRESHOLD, and at
 * most 6 to mesh with shrinkage detection under removal.
 */
static final int UNTREEIFY_THRESHOLD = 6;
```

如果链表的节点小于6则要变成链表的形式,如果链表超过8个节点,则要变成红黑树,因为链表和红黑树有一个适合自身效率的节点数

### 数据存放

当有数据来时,我们应该把数据放到什么位置才能保证效率呢?

这时候我们需要这样一个算法

1. 生成出一个int得到下标依赖key或者value
2. 范围是0-15  数组大小的范围内
3. 尽可能充分利用数组的每一个位置

针对第一点我们可以使用 Object.hashcode

针对第二点 hash%16   取模

针对第三点  我们先放一放

### 初始化

现在我们来看源码

1.数组先进行初始化,初始化oldCap为0

```
final Node<K,V>[] resize() {
    Node<K,V>[] oldTab = table;
    int oldCap = (oldTab == null) ? 0 : oldTab.length;
    int oldThr = threshold;
    int newCap, newThr = 0;
    if (oldCap > 0) {
        if (oldCap >= MAXIMUM_CAPACITY) {
            threshold = Integer.MAX_VALUE;
            return oldTab;
        }
        else if ((newCap = oldCap << 1) < MAXIMUM_CAPACITY &&
                 oldCap >= DEFAULT_INITIAL_CAPACITY)
            newThr = oldThr << 1; // double threshold
    }
    else if (oldThr > 0) // initial capacity was placed in threshold
        newCap = oldThr;
    else {               // zero initial threshold signifies using defaults
        newCap = DEFAULT_INITIAL_CAPACITY;
        newThr = (int)(DEFAULT_LOAD_FACTOR * DEFAULT_INITIAL_CAPACITY);
    }
    if (newThr == 0) {
        float ft = (float)newCap * loadFactor;
        newThr = (newCap < MAXIMUM_CAPACITY && ft < (float)MAXIMUM_CAPACITY ?
                  (int)ft : Integer.MAX_VALUE);
    }
    threshold = newThr;
    @SuppressWarnings({"rawtypes","unchecked"})
    Node<K,V>[] newTab = (Node<K,V>[])new Node[newCap];
    table = newTab;
    if (oldTab != null) {
        for (int j = 0; j < oldCap; ++j) {
            Node<K,V> e;
            if ((e = oldTab[j]) != null) {
                oldTab[j] = null;
                if (e.next == null)
                    newTab[e.hash & (newCap - 1)] = e;
                else if (e instanceof TreeNode)
                    ((TreeNode<K,V>)e).split(this, newTab, j, oldCap);
                else { // preserve order
                    Node<K,V> loHead = null, loTail = null;
                    Node<K,V> hiHead = null, hiTail = null;
                    Node<K,V> next;
                    do {
                        next = e.next;
                        if ((e.hash & oldCap) == 0) {
                            if (loTail == null)
                                loHead = e;
                            else
                                loTail.next = e;
                            loTail = e;
                        }
                        else {
                            if (hiTail == null)
                                hiHead = e;
                            else
                                hiTail.next = e;
                            hiTail = e;
                        }
                    } while ((e = next) != null);
                    if (loTail != null) {
                        loTail.next = null;
                        newTab[j] = loHead;
                    }
                    if (hiTail != null) {
                        hiTail.next = null;
                        newTab[j + oldCap] = hiHead;
                    }
                }
            }
        }
    }
    return newTab;
}
```

因为初始化时,oldCap=0所以我们暂时先只看这段，会默认初始化16*0.75的一个数组

```
  else {               // zero initial threshold signifies using defaults
            newCap = DEFAULT_INITIAL_CAPACITY;
            newThr = (int)(DEFAULT_LOAD_FACTOR * DEFAULT_INITIAL_CAPACITY);
        }
```

```
Node<K,V>[] newTab = (Node<K,V>[])new Node[newCap];
```

```
return newTab;
```

2.我们再来思考下存放的各种情况

* 数组原本的位置为空
* 数组原本的位置不为空,下面是链表的形式
* 数组原本的位置不为空,下面是红黑树的形式 



数组原本位置上为空时

```
if ((p = tab[i = (n - 1) & hash]) == null)
    tab[i] = newNode(hash, key, value, null);
```

这里源码使用n-1&hash(n为16)  跟n%16是否一样

16-1=15                     01111

​						0101010101   &

-----------------------------------------------------------------------------------------------

可以发现  					 1111  (最大值)

​									   0000（最小值）

二进制&运算时,0&上任何都为0，所以源码有这句话MUST be a power of two.如果不是2的n次方就不能使用这种方法

```
/**
 * The default initial capacity - MUST be a power of two.
 */
```

### 如何让数组存放的位置尽量散列呢

在put的时候会先hash(key)

```
public V put(K key, V value) {
    return putVal(hash(key), key, value, false, true);
}
```

```
static final int hash(Object key) {
    int h;
    return (key == null) ? 0 : (h = key.hashCode()) ^ (h >>> 16);
}
```

由于哈希表的容量都是 2 的 N 次方，在当前，元素的 hashCode() 在很多时候下低位是相同的，这将导致冲突（碰撞），因此 1.8 以后做了个移位操作：将元素的 hashCode() 和自己右移 16 位后的结果求异或。

由于 int 只有 32 位，无符号右移 16 位相当于把高位的一半移到低位：

![](https://img-blog.csdn.net/20161030215139729)

![](https://img-blog.csdn.net/20161031000139666)

这样可以避免只靠低位数据来计算哈希时导致的冲突，计算结果由高低位结合决定，可以避免哈希值分布不均匀。

而且，采用位运算效率更高。

### 为什么哈希表的容量一定要是 2的整数次幂?

> 首先，capacity 为 2的整数次幂的话，计算桶的位置 h&(length-1) 就相当于对 length 取模，提升了计算效率；
>
> 其次，capacity 为 2 的整数次幂的话，为偶数，这样 capacity-1 为奇数，奇数的最后一位是 1，这样便保证了 h&(capacity-1) 的最后一位可能为 0，也可能为 1（这取决于h的值），即与后的结果可能为偶数，也可能为奇数，这样便可以保证散列的均匀性；
>
> 而如果 capacity 为奇数的话，很明显 capacity-1 为偶数，它的最后一位是 0，这样 h&(capacity-1) 的最后一位肯定为 0，即只能为偶数，这样任何 hash 值都只会被散列到数组的偶数下标位置上，这便浪费了近一半的空间。
>

###  数组的扩容过程

当数组需要扩容时怎么保证每一个值散列均匀的分布在数组里?



### hashmap流程图

![](https://pic2.zhimg.com/80/v2-01f15216a7e125561fb97af530dfd571_720w.jpg)