## Set
Set是Java常用列表集合之一，具有无序、不可重复的特性。

### Set的接口的继承及实现

![](https://note-1251738180.cos.ap-beijing.myqcloud.com/blog/201904/Set.png)

### 主要实现
- HashSet
- TreeSet
- LinkedHashSet
- ConcurrentSkipListSet
- CopyOnWriteArraySet

#### HashSet源码分析

```Java
public class HashSet<E>
    extends AbstractSet<E>
    implements Set<E>, Cloneable, java.io.Serializable

//构造器

public HashSet() {
    map = new HashMap<>();
}

public HashSet(Collection<? extends E> c) {
    map = new HashMap<>(Math.max((int) (c.size()/.75f) + 1, 16));
    addAll(c);
}

public HashSet(int initialCapacity, float loadFactor) {
    map = new HashMap<>(initialCapacity, loadFactor);
}

public HashSet(int initialCapacity) {
    map = new HashMap<>(initialCapacity);
}

HashSet(int initialCapacity, float loadFactor, boolean dummy) {
    map = new LinkedHashMap<>(initialCapacity, loadFactor);
}

//内部变量
private transient HashMap<E,Object> map;//Set的底层实现是Map的key
private static final Object PRESENT = new Object();//Map的value;

//主要方法
public boolean add(E e) {
    return map.put(e, PRESENT)==null;
}
public boolean remove(Object o) {
    return map.remove(o)==PRESENT;
}
public boolean contains(Object o) {
    return map.containsKey(o);
}
public boolean isEmpty() {
    return map.isEmpty();
}
public int size() {
    return map.size();
}

```

#### TreeSet源码分析

```Java
public class TreeSet<E> extends AbstractSet<E>
    implements NavigableSet<E>, Cloneable, java.io.Serializable

//构造器
TreeSet(NavigableMap<E,Object> m) {
    this.m = m;
}

public TreeSet() {
    this(new TreeMap<>());
}

public TreeSet(Comparator<? super E> comparator) {
    this(new TreeMap<>(comparator));
}

public TreeSet(Collection<? extends E> c) {
    this();
    addAll(c);
}

public TreeSet(SortedSet<E> s) {
    this(s.comparator());
    addAll(s);
}



//内部变量
    private transient NavigableMap<E,Object> m;//
    private static final Object PRESENT = new Object();

//主要方法

```
#### LinkedHashSet源码分析

```Java
public class HashSet<E>
    extends AbstractSet<E>
    implements Set<E>, Cloneable, java.io.Serializable

//构造器

//内部变量

//主要方法

```

#### ConcurrentSkipListSet源码分析

```Java
public class HashSet<E>
    extends AbstractSet<E>
    implements Set<E>, Cloneable, java.io.Serializable

//构造器

//内部变量

//主要方法

```

#### Set对比
