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
方法引用通过方法的名字来指向一个方法。方法引用可以使语言的构造更紧凑简洁，减少冗余代码。方法引用使用一对冒号`::`


- **构造器引用**： 构造器参数列表要与接口中抽象方法的参数列表一致。

  ```Java
  //语法:
  Class::new`或者`Class<T>::new

  //传统Lambda方式
  Supplier<Student> studentSupplier = ()-> new Student();
  Student student = studentSupplier.get();

  //构造器引用
  Supplier<Student> studentSupplier = Student::new;

  public Student(){}
  ```
- **静态方法引用**：语法:`Class::static_method`
- **特定类的任意对象的方法引用**：语法:`Class::method`
- **特定对象的方法引用**：语法:`instance::method`


#### Stream API

#### 默认方法和静态方法

#### Date Time API

#### Optional类
