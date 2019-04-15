## List

List是有序集合,可以重复、也可以存`null`值。是Java最常用的有序集合。

- AbstractList

主要实现类
- ArrayList
- LinkedList

#### ArrayList源码分析

```Java
/*  
  ArrayList 底层实现方式基于 数组elementData
*/
// 父类和接口实现关系
public class ArrayList<E> extends AbstractList<E>
        implements List<E>, RandomAccess, Cloneable, java.io.Serializable


// 1. 构造器
ArrayList()
ArrayList(int initialCapacity)
ArrayList(Collection (? extends E) c)

// 2. 主要变量
private static final int DEFAULT_CAPACITY = 10;//默认存储容量
transient Object[] elementData; //List的元素数据存储
//transient 关键字可以不被序列化或者反序列的标识
private int size; //ArrayList的元素个数

  /**
  * trimToSize、ensureCapacity、
  * add、clear、retainAll、removeAll、
  * addAll、removeIf、replaceAll、sort
  * 操作会触发modCount值改变
  */
protected transient int modCount = 0;//list元素数量顺序或者数量变化

//  3. 主要方法

 /**
  * 数组扩容和数组拷贝
  *
  */
  private Object[] grow(int minCapacity) {
      return elementData = Arrays.copyOf(elementData,
                                         newCapacity(minCapacity));
  }

  private int newCapacity(int minCapacity) {
        // overflow-conscious code
        int oldCapacity = elementData.length;
        int newCapacity = oldCapacity + (oldCapacity >> 1);
        if (newCapacity - minCapacity <= 0) {
            if (elementData == DEFAULTCAPACITY_EMPTY_ELEMENTDATA)
                return Math.max(DEFAULT_CAPACITY, minCapacity);
            if (minCapacity < 0) // overflow
                throw new OutOfMemoryError();
            return minCapacity;
        }
        return (newCapacity - MAX_ARRAY_SIZE <= 0)
            ? newCapacity
            : hugeCapacity(minCapacity);
    }

//retainAll/removeAll实现
boolean batchRemove(Collection<?> c, boolean complement,
                        final int from, final int end) {
    Objects.requireNonNull(c);
    final Object[] es = elementData;
    int r;
    // Optimize for initial run of survivors
    for (r = from;; r++) {
        if (r == end)
            return false;
        if (c.contains(es[r]) != complement)
            break;
    }
    int w = r++;
    try {
        for (Object e; r < end; r++)
            if (c.contains(e = es[r]) == complement)
                es[w++] = e;
    } catch (Throwable ex) {
        // Preserve behavioral compatibility with AbstractCollection,
        // even if c.contains() throws.
        System.arraycopy(es, r, es, w, end - r);
        w += end - r;
        throw ex;
    } finally {
        modCount += end - w;
        shiftTailOverGap(es, w, end);
    }
    return true;
}

/**
  * equals  //类型必须是List类型，而且每个元素都必须equals
  */
  public boolean equals(Object o) {
      if (o == this) {
          return true;
      }

      if (!(o instanceof List)) {
          return false;
      }

      final int expectedModCount = modCount;
      // ArrayList can be subclassed and given arbitrary behavior, but we can
      // still deal with the common case where o is ArrayList precisely
      boolean equal = (o.getClass() == ArrayList.class)
          ? equalsArrayList((ArrayList<?>) o)
          : equalsRange((List<?>) o, 0, size);

      checkForComodification(expectedModCount);
      return equal;
  }

  boolean equalsRange(List<?> other, int from, int to) {
      final Object[] es = elementData;
      if (to > es.length) {
          throw new ConcurrentModificationException();
      }
      var oit = other.iterator();
      for (; from < to; from++) {
          if (!oit.hasNext() || !Objects.equals(es[from], oit.next())) {
              return false;
          }
      }
      return !oit.hasNext();
  }

  private boolean equalsArrayList(ArrayList<?> other) {
      final int otherModCount = other.modCount;
      final int s = size;
      boolean equal;
      if (equal = (s == other.size)) {
          final Object[] otherEs = other.elementData;
          final Object[] es = elementData;
          if (s > es.length || s > otherEs.length) {
              throw new ConcurrentModificationException();
          }
          for (int i = 0; i < s; i++) {
              if (!Objects.equals(es[i], otherEs[i])) {
                  equal = false;
                  break;
              }
          }
      }
      other.checkForComodification(otherModCount);
      return equal;
  }

 /**
  *   hashCode  // hashCode = 31 * hashCode + (e == null ? 0 : e.hashCode());
  */
  public int hashCode() {
      int expectedModCount = modCount;
      int hash = hashCodeRange(0, size);
      checkForComodification(expectedModCount);
      return hash;
  }

  int hashCodeRange(int from, int to) {
      final Object[] es = elementData;
      if (to > es.length) {
          throw new ConcurrentModificationException();
      }
      int hashCode = 1;
      for (int i = from; i < to; i++) {
          Object e = es[i];
          hashCode = 31 * hashCode + (e == null ? 0 : e.hashCode());
      }
      return hashCode;
  }
```
#### LinkedList源码分析
LinkedList底层实现是双向链表,顺序访问会非常高效，而随机访问效率比较低。

```Java
//LinkedList的继承关系
java.lang.Object
    java.util.AbstractCollection<E>
        java.util.AbstractList<E>
            java.util.AbstractSequentialList<E>
                java.util.LinkedList<E>

//实现接口:
List<E>, Deque<E>, Cloneable, Serializable


//链表节点
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

//构造器
public LinkedList() {}

public LinkedList(Collection<? extends E> c) {
    this();
    addAll(c);
}

//全局变量
protected transient int modCount = 0;//修改次数
transient int size = 0;//元素个数
transient Node<E> first;//链表第一个元素
transient Node<E> last;//链表最后一个元素


//主要方法
addFirst|add、addLast、addAll

//从头部添加节点
public void addFirst(E e);
public boolean offerFirst(E e);
public void push(E e);

//从尾部添加节点
public boolean add(E e);
public boolean offer(E e);
public boolean offerLast(E e);
public void addLast(E e);
public boolean addAll(Collection<? extends E> c);
public boolean addAll(int index, Collection<? extends E> c);

//随机访问操作元素
public E get(int index);
public E set(int index, E element);
public void add(int index, E element);
public E remove(int index);

//定位元素
public boolean remove(Object o);
public int indexOf(Object o);
public int lastIndexOf(Object o);

//查询第一个节点值
public E peek();
public E element();
public E getFirst();
public E peekFirst();

//获取最后一个节点值
public E getLast();
public E peekLast();

//删除第一个节点
public E removeFirst();
public E pollFirst();
public E pop();
public E poll()
public E remove();

//删除最后一个节点
public E removeLast();
public E pollLast();

private void linkFirst(E e) {
    final Node<E> f = first;
    final Node<E> newNode = new Node<>(null, e, f);
    first = newNode;
    if (f == null)
        last = newNode;
    else
        f.prev = newNode;
    size++;
    modCount++;
}

public E getFirst() {
    final Node<E> f = first;
    if (f == null)
        throw new NoSuchElementException();
    return f.item;
}

public E getLast() {
    final Node<E> l = last;
    if (l == null)
        throw new NoSuchElementException();
    return l.item;
}

public E removeFirst() {
    final Node<E> f = first;
    if (f == null)
        throw new NoSuchElementException();
    return unlinkFirst(f);
}

public E removeLast() {
    final Node<E> l = last;
    if (l == null)
        throw new NoSuchElementException();
    return unlinkLast(l);
}
```

#### ArrayList与LinkedList的对比

- ArrayList是实现了基于动态数组的数据结构，LinkedList基于链表的数据结构。
- ArrayList个数个有限（Int的最大值），LinkedList长度无限制。
- ArrayList遍历效率高，快速随机访问元素
- LinkedList的新增、删除效率高
