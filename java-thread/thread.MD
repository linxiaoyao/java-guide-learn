#### 1、线程的生命周期和状态

>1. NEW
>1. RUNNABLE
>1. BLOCK
>1. WAIT
>1. TIME_WAITING
>1. TERMINATED
>线程创建之后它将处于 NEW（新建） 状态，调用 start() 方法后开始运行，线程这时候处于 READY（可运行） 状态。可运行状态的线程获得了 CPU 时间片（timeslice）后就处于 RUNNING（运行） 状态。

>当线程执行 wait()方法之后，线程进入 WAITING（等待） 状态。进入等待状态的线程需要依靠其他线程的通知才能够返回到运行状态，而 TIME_WAITING(超时等待) 状态相当于在等待状态的基础上增加了超时限制，比如通过 sleep（long millis）方法或 wait（long millis）方法可以将 Java 线程置于 TIMED WAITING 状态。当超时时间到达后 Java 线程将会返回到 RUNNABLE 状态。当线程调用同步方法时，在没有获取到锁的情况下，线程将会进入到 BLOCKED（阻塞） 状态。线程在执行 Runnable 的run()方法之后将会进入到 TERMINATED（终止） 状态。

#### 2、多线程带来的问题
>1. 内存泄漏
>2. 死锁
>3. 线程不安全

#### 3、死锁的条件与解决办法
>1. 互斥条件：该资源任意一个时刻只由一个线程占用。
>1. 请求与保持条件：一个进程因请求资源而阻塞时，对已获得的资源保持不放。
>1. 不剥夺条件:线程已获得的资源在末使用完之前不能被其他线程强行剥夺，只有自己使用完毕后才释放资源。
>1. 循环等待条件:若干进程之间形成一种头尾相接的循环等待资源关系。

为了避免死锁，我们只要破坏产生死锁的四个条件中的其中一个就可以了
>1. 破坏互斥条件 ：这个条件我们没有办法破坏，因为我们用锁本来就是想让他们互斥的（临界资源需要互斥访问）。
>1. 破坏请求与保持条件 ：一次性申请所有的资源。
>1. 破坏不剥夺条件 ：占用部分资源的线程进一步申请其他资源时，如果申请不到，可以主动释放它占有的资源。
>1. 破坏循环等待条件 ：靠按序申请资源来预防。按某一顺序申请资源，释放资源则反序释放。破坏循环等待条件。


#### 4、说说 sleep() 方法和 wait() 方法区别和共同点?
>1. 两者最主要的区别在于：sleep 方法没有释放锁，而 wait 方法释放了锁 。
>1. 两者都可以暂停线程的执行。
>1. Wait 通常被用于线程间交互/通信，sleep 通常被用于暂停执行。
>1. wait() 方法被调用后，线程不会自动苏醒，需要别的线程调用同一个对象上的 notify() 或者 notifyAll() 方法。sleep() 方法执行完成后，线程会自动苏醒。或者可以使用 wait(long timeout)超时后线程会自动苏醒。
#### 5、为什么我们调用 start() 方法时会执行 run() 方法，为什么我们不能直接调用 run() 方法？
>这是另一个非常经典的 java 多线程面试问题，而且在面试中会经常被问到。很简单，但是很多人都会答不上来！

>new 一个 Thread，线程进入了新建状态;调用 start() 方法，会启动一个线程并使线程进入了就绪状态，当分配到时间片后就可以开始运行了。 start() 会执行线程的相应准备工作，然后自动执行 run() 方法的内容，这是真正的多线程工作。 而直接执行 run() 方法，会把 run 方法当成一个 main 线程下的普通方法去执行，并不会在某个线程中执行它，所以这并不是多线程工作。

>总结： 调用 start 方法方可启动线程并使线程进入就绪状态，而 run 方法只是 thread 的一个普通方法调用，还是在主线程里执行。


##### 阅读原文: https://gitee.com/SnailClimb/JavaGuide/blob/master/docs/java/Multithread/JavaConcurrencyBasicsCommonInterviewQuestionsSummary.md

### Advanced
#### 1、synchronized 关键字
>1. 修饰实例方法
>1. 修饰静态方法
>1. 修饰代码块
#### 2、volatile 关键字
>把变量声明为volatile，这就指示 JVM，这个变量是不稳定的，每次使用它都到主存中进行读取。
>说白了， volatile 关键字的主要作用就是**保证变量的可见性**然后还有一个作用是**防止指令重排序**。

#### 3、ThreadLocal
#### 4、线程池
#### 5、Atomic原子类
#### 6、AQS
##### https://gitee.com/SnailClimb/JavaGuide/blob/master/docs/java/Multithread/JavaConcurrencyAdvancedCommonInterviewQuestions.md





First Header | Second Header
:------------: | :-------------:
Content from cell 1 | Content from cell 2
Content in the first column | Content in the second column