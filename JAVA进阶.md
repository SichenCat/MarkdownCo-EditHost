# JAVA进阶

**注意：** 之前的内容在石墨文档——JAVA表格中



### 1.线程Thread

线程(thread)是一个程序内部的一条执行路径。

我们之前启动程序执行后，main方法的执行其实就是一条单独的执行路径。

程序中如果只有一条执行路径，那么这个程序就是单线程的程序。

**多线程：**从软硬件上实现多条执行流程的技术

#### 1.1多线程的实现及其方法

 1. **继承Thread类,自定义线程**

    · 自定义类继承Thread类

    · 重写run方法

    · 创建线程实例 ` Thread t = new MyThread();`

    · 使用start方法启动多线程

    缺点：已经继承了Thread类，无法继承其他类，不利于扩展

    注意：使用run相当于单线程普通类的普通方法，没有多线程的特点

 2. **实现Runnable接口，代替继承方法**

     1. 定义一个线程任务类  `class MyRunnableThread implements Runnable{}`

     2. 重写run方法

     3. 创建一个任务对象  ` Runnable target = new MyRunnable();`

     4. 将任务对象交给线程Thread，然后就通过start启动  ` Thread t = new Thread(target);  t.start();`

     附：Thread 构造器  

     | public Thread(String name)                 | 传统创建方法                     |
     | ------------------------------------------ | -------------------------------- |
     | public Thread(Runnable target)             | 封装对象成为线程对象             |
     | public Thread(Runnable target,String name) | 封装的同时，提供一个名字方便引用 |

     匿名内部类方式：

     ``` java
     //不再单独写类，直接创建匿名内部类
     Runnable target = new Runnable(){ 
         @override
         public void run() {
             // blablabla
         }
     }  
     // 进一步简化
     new Thread(Runnable() { @override } ).start();
     // 匿名内部类最简形式lambda表达式
     new Thread( () -> { @override } ).start();
     ```

     问题： 不能直接返回结果

 3. **JDK5.0 : Callable接口和FutureTask接口**

​	· 定义类实现Callable接口，重写Call方法来封装要做的事，注意申明执行后的数据类型<泛型>

​	· 用FutureTask把Callable对象封装成线程任务对象  [ FutureTask实现了Runnable接口，所以才能用Thread封装 ]

​	· Thread获得线程任务对象

​	· 调用start方法启动线程

​	· 线程执行后，可以通过FutureTask.get()来获得执行结果   [线程执行完成才会执行，否则会等待]

​	Callable接口是一个泛型接口！通过泛型决定返回的对象类型

​	get	可能会获得 [执行结果]，或者[异常结果]

4. **线程常用方法**

   | 方法                    | 作用                                                         |
   | ----------------------- | ------------------------------------------------------------ |
   | getName(),SetName()     | 对线程对象设置名字/获取名字  线程默认名Thread-索引(主线程为main) |
   | Thread.CurrentThread()  | 获取当前正在执行的线程 Thread t = ...                        |
   | Thread.sleep(long time) | 当前线程休眠指定毫秒后再继续执行                             |
   | run(),start()           | 线程要做的事和线程启动方法                                   |

#### 1.2线程安全问题和线程同步

​	同时调用且修改同一资源时可能出现不合事务逻辑的问题。

​	见UltiHM/Thread/com.thread.getDeposit代码所示的取钱问题

​	解决方案：线程同步

​	核心思想：  

​		· 加锁，对共享资源上锁，一个线程访问并确定释放后才能让下一个线程访问

​	线程同步方式：

​		· **方法一：同步代码块** —— 对一部分代码进行上锁

``` java
synchronized (同步锁对象) {   
	操作共享资源的代码(核心代码)	
}
```

​			要求：只要对于当前的线程们来说，是操作的同一个对象即可	

​			锁对象: 使用共享资源作为锁对象

​				·对于实例方法，使用this作为锁对象(操作的同一实例)

​				·对于静态方法，使用 字节码(类名.class)作为锁对象

​		· **方法二：同步方法** —— 直接上锁可能出现问题的方法

​			` 修饰符 synchronized 返回值类型 方法名称（参数列表） {方法体}`

​			底层原理：如果是个实例方法，使用this对方法这一块代码进行锁

​					如果是个静态方法，默认用类名.class 进行锁

​		· **方法三：Lock锁：JDK 5.0  **

​			` final Lock lock = new ReentrantLock();`  注： Lock是一个接口

​			比如在刚才的代码中，每个Account对象设置一个private的Lock对象，通过每个账户的锁对象进行上锁，解锁操作

​			实例方法lock(),unlock()，进行随时上下锁。   可以使用try/finally避免死锁

#### 1.3 线程通信

​	也就是线程间相互发送数据，通过共享一个数据的方式实现。

​	线程间会根据共享数据的情况决定执行内容，以及发送数据给其他线程。

​	常见的模型：

​		生产者与消费者模型

​	Object类的等待和唤醒方法：   以下方法必须使用 同步锁对象 进行调用。

| wait ()     | 让当前线程等待，释放锁，直到被notify或notifyAll |
| ----------- | ----------------------------------------------- |
| notify()    | 唤醒一个线程                                    |
| notifyAll() | 唤醒所有线程                                    |



#### 1.4 线程池*

​	复用线程的技术。

​	原理：复用的工作线程 -> 处理任务队列

​	代表线程池的ExecutorService接口  ——  一个实现类：ThreadPoolExecutor

​	创建线程池方法：  直接用实现类创建线程池

` ExecutorService es = new ThreadPoolExecutor(		3，			5，			6，			TimeUnit.SECONDS,` 

`new ArrayBlockingQueue< >(5)`		//new   5个位置的任务队列											

`Executors.defaultThreadFactory()`    // 使用默认线程工厂，是静态的

`new ThreadPoolExecutor.AbortPolicy()     );` 		//new 默认拒绝策略

​		线程池构造器的七个参数：

```java
	public ThreadPoolExecutor(int corePoolSize,  // 线程池的线程数量（核心线程数），必须大于0
          int maximumPoolSize,   // 指定线程的最大数量 ，一般是>=上者
          long keepAliveTime,	// 指定临时线程的最长持续时间
          TimeUnit unit,		// 指定存活时间的单位，秒、分、时、天，和上一条配合使用
          BlockingQueue<Runnable> workQueue, // 指定任务队列    不能为null
          ThreadFactory threadFactory,       // 指定用哪一个线程工厂来创建线程    不能为null
          RejectedExecutionHandler handler)		//指定线程忙，且任务队列满时，处理新来的任务     不能为null
```

​		注:  当核心线程忙，且任务队列满时 ，才会创建临时线程

​			当核心线程忙，任务队列满，且临时线程也忙时，才会开始拒绝任务

​		

​		ExcutorService 接口常用方法：

| void execute(Runnable command)     | 执行任务/命令，用于执行Runnable任务                          |
| ---------------------------------- | ------------------------------------------------------------ |
| Future<T> submit(Callable<T> task) | 执行任务并返回未来对象，获取线程执行结果，用于执行Callable任务 |
| void shutdown()                    | 在任务执行完毕后关闭线程池                                   |
| List<Runnable> shutdownNow()       | 立即关闭，停止当前任务，并且返回任务队列中的任务             |

​		例： `Future< T >  f1 = pool.submit(new Callable(n));`

​				

​		RejectedExecutionHandler handler  参数之  **任务拒绝策略**

| ThreadPoolExecutor.AbortPolicy         | **默认策略**，丢弃任务并抛出RejectedExecutionException异常 |
| -------------------------------------- | ---------------------------------------------------------- |
| ThreadPoolExecutor.DiscardPolicy       | 丢弃任务但不抛出异常——不推荐                               |
| ThreadPoolExecutor.DiscardOldestPolicy | 抛弃队列中等待了最久的任务，然后把任务加入队列             |
| ThreadPoolExecutor.CallerRunsPolicy    | 由主线程调用任务的Run方法，跳过线程池的处理                |

​	

#### 1.5 Executors工具类(也可实现线程池)

​	常用方法之创建线程池：  

| public static ExecutorService newCachedThreadPool()          | 任务越多线程越多，线程空闲后自动回收(不限制线程数量)         |
| ------------------------------------------------------------ | ------------------------------------------------------------ |
| public static ExecutorService newFixedThreadPool(int nThreads) | 创建固定数量线程，线程因异常挂掉后会自动补充(不限制任务队列长度) |
| public static ExecutorService newSingleThreadExecutor ()     | 单线程的线程池，线程挂掉后会重新创建(不限制任务队列长度)     |
| public static ScheduledExecutorService newScheduledThreadPool(int corePoolSize) | (不限制线程数量)（可以实现延时和周期调度——实现定时器）       |

​	注:底层也是基于线程池构造器创建的，建议使用线程池构造器直接创建，利于避免OOM异常

#### 1.6 补充：定时器，线程生命周期

​	定时器是一种用于控制任务延时调用，或者周期性调用的技术，比如用于闹钟，定时邮件。

​	实现方法：1. Timer 类  2. ScheduledExecutorService

​		1. Timer 类 ，直接创建定时器对象(定时器本身就是一个单线程)   **屁用没有！白学!**

​			` Timer t = new Timer();`

​			常用方法：

``` java
void schedule(TimerTask task, long delay , long period )  // delay表示时延，period表示周期
```

​		2. ScheduledExecutorService   可以理解为多线程的Timer

​			通过工具类Executors创建。` SceduledExecutorService pool = Executors.newScheduledThreadPool(size)`

​			调度方法：

``` java
public ScheduledFuture<?> scheduleAtFixedRate(Runnable command, long initialDelay, long period, TimeUnit unit)
    
pool.scheduleAtFixedRate( () -> { System.out.pritnln( " Thread.currentThread().getName()" + "输出123" ) } );
```

​	线程的生命周期：Java中，线程有六个状态(枚举定义)

​		NEW ， RUNNABLE , BLOCKED , WAITING , TIMED_WAITING , TERMINATED

​	<img src="C:\Users\osaxe\AppData\Roaming\Typora\typora-user-images\image-20230326180453164.png" alt="image-20230326180453164" style="zoom: 50%;" />

### 2 网络编程

#### 2.1 网络通信三要素

	##### 2.1.1 IP地址

​	常用命令：

``` bash
ipconfig  //查看本机IP
ping IP   //检查网络连通情况
```

​	IP地址操作工具类： class InetAddress

​	

| 名称                                             | 说明                              |
| ------------------------------------------------ | --------------------------------- |
| public static InetAddress getLocalHost()         | 返回本主机的地址对象              |
| public static InetAddress getByName(String host) | 通过域名/IP，得到对应的IP地址对象 |
| public static getHostName()                      | 得到IP地址的主机名                |
| public static getHostAddress()                   | 返回主机的IP地址                  |
| public boolean isReachable(int timeout)          | 尝试连通IP地址对应的主机          |

##### 2.1.2 端口

唯一标识计算机上运行的程序

端口号范围：0~65535 （16bit）

端口类型:

​	周知端口：0~1023，被预占用（比如HTTP占用80，FTP占用21）

​	注册端口：1024~49151,分配给用户进程（比如TomCat占用8080，My'SQL占用3306）

​	动态端口：49152~65535，一般不固定分配给某种进程，二是动态分配的

##### 2.1.3 网络通讯协议 TCP/IP标准

###### **UDP协议：**

​	UDP: User Datagram Protocol

​	数据包对象：DatagramPacket    

​	构造器和方法：

| public DatagramPacket(byte[] buf,int length,InetAddress address,int port) | 创建发送端的数据包对象         |
| ------------------------------------------------------------ | ------------------------------ |
| public DatagramPacket(byte[] buf,int length)                 | 创建接收端的数据包对象         |
| public int getLength()                                       | 获得实际接收的字节的数目       |
| public SocketAddress getSocketAddress()                      | 获得数据包的IP信息(包括端口号) |
| public int getPort()                                         | 获得端口                       |

​	通信管道对象：DatagramSocket

​	构造器和方法：

| public DatagramSocket()               | 创建一个通信管道对象 —— 发送端，系统会分配一个端口(也可指定) |
| ------------------------------------- | ------------------------------------------------------------ |
| public DatagramSocket(int port)       | 创建一个  接收端，需要指定端口号                             |
| public void send(DatagramPacket p)    | 发送数据                                                     |
| public void receive(DatagramPacket p) | 接收数据                                                     |

​	UDP多发多收：while嵌套

​	UDP广播/组播: 与当前所在网络的 所有主机/选定的一组主机 通信

​		发送端使用广播时，必须使用广播地址255.255.255.255，且指定端口9999

​		接收端只要在同一网络并将端口设置为9999即可。

​		发送端使用组播时，必须使用地址 224.0.0.0 ~ 239.255.255.255，且端口为9999

​		接收端必须使用joinGroup方法绑定同一组播IP对象，并将端口设置为9999即可。

​			DatagramSocket的子类 MulticastSocket 可以帮助绑定组播IP。

``` java
//接收端进行组播接收
	MulticastSocket socket = new MulticastSocket(9999);
	socket.joinGroup(InetAddress.getByName("组播IP地址"));  //jdk14已经淘汰
	socket.joinGroup(new InetSocketAddress(InetAddress.getByName("组播IP地址") , 9999 ) ， 
			NetworkInterface.getByInetAddress(InetAddress.getLocalHost()) ) ; //新方法
```

###### **TCP协议：**

​	
