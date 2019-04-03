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



#### Stream API

#### Date Time API

#### Optional类
