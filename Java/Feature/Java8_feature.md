## Java8新特性

#### Lambda表达式
Lambda 表达式，也可称为闭包，它是推动 Java 8 发布的最重要新特性。
Lambda 允许把函数作为一个方法的参数（函数作为参数传递进方法中）。Lambda 表达式免去了使用匿名类的麻烦，并且给予Java简单但是强大的函数化的编程能力。

lambda的语法格式如下：
```java
(parameters) -> expression
(parameters) -> {statements;}
//Demo
() -> 5
x -> 2 * x
(x, y) -> x – y

//代码示例：
public interface UserService{
  String query(String id);
}

public class BaseService(){
  static String method(String id){
    return "Hello";
  }
}
//Lambda表达式
UserService userService1 = s -> "Hello"+s;
//Lambda与::引用结合使用
UserService userService2 = BaseService::method;

```
重要特征：

- **可选类型声明**：不需要声明参数类型，编译器可以统一识别参数值。
- **可选的参数圆括号**：一个参数无需定义圆括号，但没有参数或者多个参数需要定义圆括号。
- **可选的大括号**：如果主体包含了一个语句，就不需要使用大括号。
- **可选的返回关键字**：如果主体只有一个表达式返回值则编译器会自动返回值，大括号需要指定明表达式返回了一个数值。

#### ::引用
方法引用通过方法的名字来指向一个方法。方法引用可以使语言的构造更紧凑简洁，减少冗余代码。方法引用使用一对冒号`::`。方法引用是简写的lambda表达式

- **构造器引用**： 构造器参数列表要与接口中抽象方法的参数列表一致。
- **静态方法引用**：语法:`Class::static_method`
- **特定类的任意对象的方法引用**：语法:`Class::method`
- **特定对象的方法引用**：语法:`instance::method`


  ```Java
  //引用语法结构：
  Class/object::new/method

  public class TestReference {

    public static void main(String[] args) {
        //1. 构造器的引用
        Supplier<Student> studentSupplier = Student::new;//()-> new Student();
        Student s = Student.get(Student::new);// studentSupplier.get();
        Func4 func4 = String::new;//等价于 s -> new String(s)
        Func5 func5 = int []::new;  //等价于 x->new int[x];

        Student st1 = new Student();
        Student st2 = new Student();
        List<Student> students = Arrays.asList(st1,st2);

        //2. 指定类的静态方法引用
        students.forEach(Student::test1);
        Func1 func1 = String::valueOf;

        //3. 指定类的无参数方法引用
        students.forEach(Student::test2);
        Func2 func2 = String::length;

        //4. 指定类实例的方法引用
        students.forEach(s::test3);
        String str = "aaa";
        Arrays.asList("aaa","bbb").forEach(s::test4);
        Arrays.asList("aaa","bbb").forEach(str::equals);
    }

    interface Func1{String test(double x);}

    interface Func2{int test(String str);}

    interface Func3{boolean test(Object obj);}

    interface Func4{String test(String str);}

    interface Func5{int [] test(int n);}
  }
  ```
#### 函数式接口

函数式接口(Functional Interface)就是一个有且仅有一个抽象方法，但是可以有多个非抽象方法的接口。函数式接口可以被隐式转换为 lambda 表达式。

```Java
@FunctionalInterface
public interface TestService {
    void test(String message);
}

public class TestFunctionInterface {

    public static void main(String[] args) {
        TestService service = System.out::println;
        service.test("test");

        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9);
        test(list,n->true); // return true;
        test(list,n->n%2==0);//偶数
        test(list,n->n>3);//大于3
    }

    public static void test(List<Integer> list, Predicate<Integer> predicate){
        list.forEach(n ->{
            if(predicate.test(n)){
                System.out.println(n);
            }
        });
    }
}
```

#### 默认方法
默认方法就是接口可以有实现方法，而且不需要实现类去实现其方法。我们只需在方法名前面加个 `default/static` 关键字即可实现默认方法。

```Java
public interface Test1Interface{

  default void test2(){
      System.out.println("test1 default method");
  }

  static void test3(){
      System.out.println("test1 static method");
  }
}

public class TestClazz implements Test1Interface,Test2Interface {
    @Override
    public void test2() {
        Test1Interface.super.test2();
    }

    public static void  main(String [] args){
        TestClazz test = new TestClazz();
        test.test2();

        Test1Interface.test3();

        Test2Interface.test3();
    }
}
```
一个接口有默认方法，考虑这样的情况，一个类实现了多个接口，且这些接口有相同的默认方法，需要实现类重写该默认方法。或者指定父类接口的方法。

#### Stream API
Java 8 API添加了一个新的抽象称为流Stream，可以让你以一种声明的方式处理数据。这种风格将要处理的元素集合看作一种流， 流在管道中传输， 并且可以在管道的节点上进行处理， 比如筛选， 排序，聚合等。
元素流在管道中经过中间操作（intermediate operation）的处理，最后由最终操作(terminal operation)得到前面处理的结果。

Stream（流）是一个来自数据源的元素队列并支持聚合操作

- 元素是特定类型的对象，形成一个队列。 Java中的Stream并不会存储元素，而是按需计算。
- 数据源 流的来源。 可以是集合，数组，I/O channel， 产生器generator 等。
- 聚合操作 类似SQL语句一样的操作， 比如filter, map, reduce, find, match, sorted等

特征
- Pipelining: 中间操作都会返回流对象本身。 这样多个操作可以串联成一个管道， 如同流式风格（fluent style）。 这样做可以对操作进行优化， 比如延迟执行(laziness)和短路( short-circuiting)。
- 内部迭代： 以前对集合遍历都是通过Iterator或者For-Each的方式, 显式的在集合外部进行迭代， 这叫做外部迭代。 Stream提供了内部迭代的方式， 通过访问者模式(Visitor)实现。

```Java
//生成流的两种方式
stream()   //为集合创建串行流。
parallelStream()  //为集合创建串行流。



```

#### Date Time API



#### Optional类
