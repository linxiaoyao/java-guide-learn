### 反射机制介绍
JAVA 反射机制是在运行状态中，对于任意一个类，都能够知道这个类的所有属性和方法；
对于任意一个对象，都能够调用它的任意一个方法和属性；
这种动态获取的信息以及动态调用对象的方法的功能称为 java 语言的反射机制。

#### 获取 Class 对象的两种方式
如果我们动态获取到这些信息，我们需要依靠 Class 对象。Class 类对象将一个类的方法、变量等信息告诉运行的程序。Java 提供了两种方式获取 Class 对象:

1.知道具体类的情况下可以使用：

>Class alunbarClass = TargetObject.class;

但是我们一般是不知道具体类的，基本都是通过遍历包下面的类来获取 Class 对象

2.通过 Class.forName()传入类的路径获取：
//注意此字符串必须是真实路径，就是带包名的类路径，包名.类名
>Class alunbarClass1 = Class.forName("com.learn.javareflect.model.TargetObject");

#### 操作
- [x] 获取构造方法与初始化对象
- [x] 获取成员变量
- [x] 获取成员方法与invoke调用
- [] 获取注解
- [] 获取修饰符
- [] 获取参数类型/返回类型



https://blog.csdn.net/sinat_38259539/article/details/71799078

https://zhuanlan.zhihu.com/p/80519709
