package com.learn.javareflect;

import com.learn.javareflect.equals.StringNew;
import com.learn.javareflect.handler.AnimalHandler;
import com.learn.javareflect.handler.TargetHandler;
import com.learn.javareflect.handler.TargetHandler2;
import com.learn.javareflect.interceptor.Interceptor;
import com.learn.javareflect.interceptor.InterceptorChain;
import com.learn.javareflect.interceptor.LogInterceptor;
import com.learn.javareflect.interceptor.TransactionInterceptor;
import com.learn.javareflect.model.SonObject;
import com.learn.javareflect.model.TargetObject;
import com.learn.javareflect.service.Animal;
import com.learn.javareflect.service.impl.Cat;
import com.learn.javareflect.service.impl.Dog;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.function.Predicate;

@SpringBootApplication
public class JavaReflectApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(JavaReflectApplication.class, args);
    }

    /**
     * 反射获取类属性
     * @param args
     * @throws Exception
     */
//    @Override
//    public void run(String... args) throws Exception {
//        Class clazz = Class.forName("com.learn.javareflect.model.TargetObject");
//        // 公有的构造方法
//        Constructor[] cons = clazz.getConstructors();
//        Arrays.asList(cons).forEach(System.out::println);
//
//        System.out.println("===================");
//        // 声明的全部构造方法
//        Constructor[] cons2 = clazz.getDeclaredConstructors();
//        Arrays.asList(cons2).forEach(System.out::println);
//
//        System.out.println("===================");
//        // 获取某个构造方法
//        // Constructor c1 = clazz.getConstructor(int.class);// 报错，修饰符为private
//        Constructor c1 = clazz.getDeclaredConstructor(int.class);
//        c1.setAccessible(true);
//        // 初始化一个对象 newInstance()
//        Object obj = c1.newInstance(20);
//        TargetObject t = (TargetObject)obj;
//        System.out.println(obj.toString());
//        System.out.println(t.toString());
//
//        Method method = clazz.getDeclaredMethod("publicMethod");
//        method.invoke(obj, args);
//    }


    /**
     *
     * @param args
     * @throws Exception
     */
    @Override
    public void run(String... args) throws Exception {
//        testProxy();
//        testInterceptorInProxy();
        testInterceptorChainInProxy();
//        this.testOverrideEquals();
    }

    /**
     * 动态代理模式
     */
    void testProxy() {
        Animal cat = new Cat();
        AnimalHandler handler = new AnimalHandler(cat);
        Animal animal = (Animal) Proxy.newProxyInstance(handler.getClass().getClassLoader(), cat.getClass().getInterfaces(), handler);
        System.out.println(animal.getClass().getName());
        animal.hello("");
        animal.play();
    }

    void testInterceptorInProxy() {
        Animal cat = new Cat();
        List<Interceptor> interceptors = new ArrayList<>();
        interceptors.add(new LogInterceptor());
        interceptors.add(new TransactionInterceptor());
//        TargetHandler handler = new TargetHandler(cat, interceptors);
//        Animal animal = (Animal)Proxy.newProxyInstance(handler.getClass().getClassLoader(), cat.getClass().getInterfaces(), handler);
        Animal animal = (Animal)TargetHandler.wrap(cat, interceptors);
        animal.play();
    }

    void testInterceptorChainInProxy() {
        Animal cat = new Cat();
//        (1)
//        cat = (Animal) TargetHandler2.wrap(cat, new LogInterceptor());
//        cat = (Animal) TargetHandler2.wrap(cat, new TransactionInterceptor());
//        (2) 优化: (1)这样调用看着很别扭，对应目标类来说，只需要了解对他插入了什么拦截就好。
//        cat = (Animal) new LogInterceptor().plugin(cat);
//        cat = (Animal) new TransactionInterceptor().plugin(cat);
//        (3) 优化: 添加责任链
//        InterceptorChain chain = new InterceptorChain();
//        chain.addInterceptor(new LogInterceptor());
//        chain.addInterceptor(new TransactionInterceptor());
//        cat = (Animal) chain.pluginAll(cat);
        cat.play();
    }
//
//6. 分析
//        $Proxy0 ： 可能我以为返回的这个代理对象会是Subject类型的对象，或者是InvocationHandler的对象，结果却不是，首先我们解释一下为什么我们这里可以将其转化为Subject类型的对象？原因就是在newProxyInstance这个方法的第二个参数上，我们给这个代理对象提供了一组什么接口，那么我这个代理对象就会实现了这组接口，这个时候我们当然可以将这个代理对象强制类型转化为这组接口中的任意一个，因为这里的接口是Subject类型，所以就可以将其转化为Subject类型了。
//        同时我们一定要记住，通过 Proxy.newProxyInstance 创建的代理对象是在jvm运行时动态生成的一个对象，它并不是我们的InvocationHandler类型，也不是我们定义的那组接口的类型，而是在运行是动态生成的一个对象，并且命名方式都是这样的形式，以$开头，proxy为中，最后一个数字表示对象的标号。
//        接着我们来看看这两句
//        animal.hello("");
//        animal.play();
//        这里是通过代理对象来调用实现的那种接口中的方法，这个时候程序就会跳转到由这个代理对象关联到的 handler 中的invoke方法去执行，而我们的这个 handler 对象又接受了一个 Cat类型的参数，表示我要代理的就是这个真实对象，所以此时就会调用 handler 中的invoke方法去执行。
//        实际就是委托由其关联到的 handler 对象的invoke方法中来调用，并不是自己来真实调用，而是通过代理的方式来调用的。
//        代理类一定是public和final。如果其实现的所有接口都是public ，代理类就不属于某个特定包。否则，所有非公有接口都必须属于同一个包，同时代理类也属于这个包。
//        这就是我们的java动态代理机制


    /**
     * 3.3 hashCode（）与 equals（）的相关规定
     * 哈希码也就是散列码
     *
     * 1、如果两个对象相等（==），则 hashcode 一定也是相同的
     * 2、两个对象相等（==）,对两个对象分别调用 equals 方法都返回 true
     * 3、两个对象有相同的 hashcode 值，它们也不一定是相等的
     * 4、因此，equals 方法被覆盖过，则 hashCode 方法也必须被覆盖("必须"这一点不理解。"用法规范"？)
     *      在每个覆盖了equals方法的类中,也必须覆盖hashCode方法。如果不这样做的话，
     *      就会违反Object.hashCode的通用约定,从而导致该类无法结合所有基于散列的集合一起正常工作，
     *      这样的集合包括HashMap、HashSet和Hashtable。
     * 5、hashCode()的默认行为是对堆上的对象产生独特值。如果没有重写 hashCode()，则该 class 的两个对象的hashCode()无论如何都不会相等（即使这两个对象指向相同的数据）
     *
     * 作者：Guide哥
     * 链接：https://juejin.im/post/5e18879e6fb9a02fc63602e2
     * 来源：掘金
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     */
    void testOverrideEquals() {
        StringNew s1 = new StringNew(new char[]{'a','b'});
        StringNew s2 = new StringNew(new char[]{'a','b'});
        String s3 = new String(new char[]{'a','b'});
        String s4 = new String(new char[]{'a','b'});
        System.out.println(s1.equals(s2));// true
        System.out.println(s3.equals(s4));// true
        System.out.println(s1.hashCode() == s2.hashCode());// false
        System.out.println(s3.hashCode() == s4.hashCode());// true

        // HashSet 先比较hashcode(),相同再比较equals()
        HashSet set = new HashSet();
        set.add(s1);
        set.add(s2);
        set.add(s3);
        set.add(s4);
        System.out.println(set.toString());
    }

    /**
     * 我们以“HashSet 如何检查重复”为例子来说明为什么要有 hashCode：
     * 当你把对象加入 HashSet 时，HashSet 会先计算对象的 hashcode 值来判断对象加入的位置，
     * 同时也会与其他已经加入的对象的 hashcode 值作比较，
     * 如果没有相符的 hashcode，HashSet 会假设对象没有重复出现。
     * 但是如果发现有相同 hashcode 值的对象，这时会调用 equals（）方法来检查 hashcode 相等的对象是否真的相同。
     * 如果两者相同，HashSet 就不会让其加入操作成功。如果不同的话，就会重新散列到其他位置。（
     * 摘自我的 Java 启蒙书《Head fist java》第二版）。这样我们就大大减少了 equals 的次数，相应就大大提高了执行速度。
     *
     */

}

