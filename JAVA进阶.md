# JAVA进阶

**注意：** 之前的内容在石墨文档——JAVA表格中



### 1.线程Thread

线程(thread)是一个程序内部的一条执行路径。

我们之前启动程序执行后，main方法的执行其实就是一条单独的执行路径。

程序中如果只有一条执行路径，那么这个程序就是单线程的程序。

**多线程：**从软硬件上实现多条执行流程的技术

#### 1.1 多线程的实现及其方法

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

#### 1.2 线程安全问题和线程同步

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

​	注册端口：1024~49151,分配给用户进程（比如TomCat占用8080，MySQL占用3306）

​	动态端口：49152~65535，一般不固定分配给某种进程，并且是动态分配的

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

​	客户端：使用Socket类(接收数据的类似下方服务端所写)

​		1.创建Socket对象

​		2.通过Socket创建一个字节输出流，用于发送数据 ` OutputStream os = socket.getOutputStream();`

​		3.将低级的字节流包装成打印流 ` PrintStream ps = new PrintStream(os);`

​		4.发送消息，进行通信` ps.print();`   发送后记得  ` ps.flush();`

​		Socket类构造器：Socket(String host,int port)    根据服务器IP地址(若在本地，为127.0.0.1)，服务器端口号创建Socket对象

​	服务端：使用ServerSocket类 (发送数据的类似上方客户端所写)

​		1.注册端口  构造器：ServerSocket(int port)   根据服务端端口创建对象 

​		2.等待接收客户端的Socket连接  方法：public Socket accept(); ` Socket socket = serverSocket.accept();`

​		3.接收成功，得到Socket对象，通过这个对象创建 字节输入流 ` InputStream is = socket.getInputStream();`  

​		4.先将字节流转换成字符流，再将低级的字符流包装成 缓冲字符输入流 (因为缓冲流不能包装字节流，必须先转换成字符流)

​		 ` BufferedReader br = new BufferedReader(new InputStreamReader(is) );`

​		5.接收消息

​	多发多收：while嵌套,注意服务端不但要在接收处while嵌套，accept()也需要嵌套进行轮询连接。

​	***同时处理多客户端消息：多线程**

​		UDP没有建立连接，不会持续占用线程。而TCP在长连接中当前线程会持续占用。

​		改进服务端，实现多线程:

​			当 accpet() 接收到连接对象，创建一个子线程来处理。

``` java
public class ServerReaderThread extends Thread(){
    private Socket socket;
    public ServerReaderThread (Socket socket){
        this.socket = socket;
    }
    @override  //在run方法中执行服务端的3-5步（创建IO流，接收消息）
    public run(){
        InputStream is = socket.getInputStream();
        BufferedReader br = new BufferedReader(new InputStreamReader(is) );
        String msg;
        while( (msg = br.readLine()) != null ){
            System.out.printlb(msg);
        }
    }
}
```

​	**\*通过线程池进一步优化：**

​		思想：将新的tcp连接设置成Runnable任务，放入线程池任务队列，让线程池按设定自行执行；

``` java
// 创建一个静态的线程池对象
private static ExecutorService pool = new ThreadPoolExecutor(3, 5, 6, TimeUnit.SECONDS, new ArrayBlockingQueue<>(2), 
Executors.defaultThreadFactory(), new ThreadPoolExecutor.AbortPolicy());

//创建任务类，用于socket通信
public class ServerReaderRunnable implements Runnable{
    private Socket socket;  
    public ServerReaderRunnable(Socket socket){  //定义构造器
        this.socket = socket;
    }
    @override
    public void run(){
        // is,br,msg,sout;
    }
}

//accept() 执行成功后，获得了socket对象，以此建立对立任务交给线程池运行；
	···
	Socket socket = serverSocket.accept();
	Runnable target  = new ServerReaderRunnable(socket);
    pool.execute(target);  //交付给线程池
	···
```

​	即时通信（服务端作为中转，接收和推送消息）

​	客户端需要再占用一个线程来读取读消息；服务端需要用一个集合将所有的socket对象储存。

### 补：Junit单元测试

​	针对方法的正确性进行的测试

​	首先要导入Junit的jar包(IDEA自带)

​	测试方法需要用注解@test，必须是public void 无参数方法

``` java
@Test
public void testMethod(){
    ·····
    TestClass t1 = new TestClass;
    String rs = t1.Method();
    Assert.assertEquals("不符合的消息提示内容" , "expected String" , rs);
}
```

​	Junit常用注解：

| @Test        | 测试方法                                              |
| ------------ | ----------------------------------------------------- |
| @Before      | 该方法在每个‘测试方法’执行 之前 执行一次              |
| @After       | 该方法在每个‘测试方法’执行 之后 执行一次              |
| @BeforeClass | 修饰静态方法   在所有方法执行 之前 ，执行且仅执行一次 |
| @AfterClass  | 修饰静态方法   在所有方法执行 之后 ，执行且仅执行一次 |



### 3 反射*

​	对于任意一个Class类，在’运行时‘可以得到这个类的全部成分：构造器对象Constructor，成员变量对象Field，成员方法对象Method.

​	反射的第一步是得到编译后的Class类对象,然后就可以解析类中的全部成分。` Class c = HelloWorld.class;`

​		可以用Class的一个方法forName(全限制名：包名+类名)来获取Class对象` Class c = Class.forName("com.xxxx.xxxx.Student");`

​		或者直接使用类名.class  ` Class c = Student.class; `

​		或者在运行时阶段获得实例的类对象 ` Class c = Student.getClass();`

​		**注意! 三种方式得到的都是同一个类对象，不是各自不同的新的类对象！ 类的对象是通过new创建的，每次都是不同的对象**

​	获取类对象之后，就可以获取 三种对象(Constructor,Field,Method)

#### 3.1 获得构造器对象

​	获得构造器对象Constructor：

| Constructor<?>[] getConstructors()                           | 返回所有构造器对象的数组（只能拿public类型的构造器） |
| ------------------------------------------------------------ | ---------------------------------------------------- |
| Constructor<?>[] getDeclaredConstructors()                   | 返回所有构造器对象的数组，包括private的              |
| Constructor<T> getConstructor(Class<?>... parameterTypes)    | 返回单个构造器对象（只能拿public的）                 |
| Constructor<T> getDeclaredConstructor(Class<?>... parameterTypes) | 返回单个构造器对象，无论构造器的修饰符如何           |

​	方法3、4用法示意： ` Constructor cons = c.getConstructor(Sring.class , int.class);//不写就是无参构造器 ` (类类型的可变参数)	

​	Constructor类用于创建对象的方法：

| T newInstance(Object... initargs)       | 根据指定构造器创建对象（输入构造器参数）                     |
| --------------------------------------- | ------------------------------------------------------------ |
| public void setAccessible(boolean flag) | 参数设置为true，取消访问检查(无视private等限制)，进行暴力反射 |

​	` cons.setAccessible(True);        Student s = (student) cons.newInstance();`  //默认返回Object，需要强转

#### 3.2 获得成员变量对象

​	由static和final修饰的变量也可以获取。

| Field[] getDeclaredFields()         | 返回所有成员变量对象的数组，存在就能拿到 |
| ----------------------------------- | ---------------------------------------- |
| Field getDeclaredField(String name) | 返回单个成员变量对象，存在就能拿到       |

​	对成员变量进行赋值：

| void set(Object obj, Object value) | 为对象注入某个成员变量数据 |
| ---------------------------------- | -------------------------- |

​	首先要有一个对象才能进行赋值！  以3.1获得的Student实例 s 继续：

``` java
···
    cons.setAccessible(True); //暴力反射
	Student s = (student) cons.newInstance();  //默认返回Object，需要强转
	Field ageF = c.getDeclaredField("age");
	ageF.setAccessible(True); //暴力反射
	ageF.set(s , 18 ); // 实例 s 成员变量 age 被设置为 18
	// 同样也可以用这种方式取值
	int age = (int) ageF.get(s);  // 方法的返回类型为Object 所以需要强转
···
```

#### 3.3 获得成员方法对象

​	方法也是类似的(同样可以获取静态方法)：

| Method[] getDeclaredMethods()                                | 返回所有成员方法对象的数组，存在就能拿到 |
| ------------------------------------------------------------ | ---------------------------------------- |
| Method getDeclaredMethod(String name, Class<?>... parameterTypes) | 返回单个成员方法对象，存在就能拿到       |

​	` Method m1 = c.getDeclaredMethod("buy", String.class , int.class);`

​	触发执行成员方法：

| Object invoke(Object obj, Object... args) | obj为执行此方法的对象，后续参数为方法的形参，Object为返回值(void方法返回null) |
| ----------------------------------------- | ------------------------------------------------------------ |

``` java
··· 
    Student s = (student) cons.newInstance();
	Method m1 = c.getDeclaredMethod("buy");  //m1为void 无参方法
	Method m2 = c.getDeclaredMethod("buy", String.class , int.class); // m2为返回 boolean，两个参数的方法
	m1.setAccessible(ture);//暴力反射
	Object result1 = m1.invoke(s); // 通过实例s来执行方法s，rusult1值为null
	Object result2 = m2.invoke(s,"Food",2);// 返回值为true
···
```

#### 3.4 反射的作用

​	绕过编译阶段：

``` java
	ArrayList<Integer> list = new ArrayList<>();
	Class c = list.getClass();
	Method add = c.getDeclaredMethod("add",Object.class);
	boolean rs = (boolean) add.invoke(list，"String") // 向约束了Integer的ArrayList注入了String类型的参数	
	/* 注意：泛型仅仅在编译阶段有效，成为字节码文件后，泛型会被擦除 */
```

​	做通用框架的底层原理：（目前仅了解）

### 4 注解  @

​	注解 Annotation ,可以对类、方法、成员变量、构造器、参数等进行标注，然后进行”特殊处理“。

​	例如在 Junit框架中，只有标注了 **@test** 的方法会被当做测试方法执行，没有标记的则不会执行；

#### 4.1 自定义注解

``` java
public @interface 注解名称{
    public 属性类型 属性名() default 默认值; 
} 

public @interface MyAlbum{
    public String name();  // 专辑名
    public String[] composers();  //作曲家们
    double price(); //价格
}

@MyAlbum(name="B-DAY",composers = {"IKON","CAIXUNKUN"},price = 99.9)  // 注解类
public class AnnotationDemo{
    
    @MyAlbum(name="B-DAY",composers = {"IKON","CAIXUNKUN"},price = 99.9) //注解构造器
    public AnnotationDemo(){}
    
    ······
}

```

​	value() 为注解的特别属性，只有一项无默认值属性时，使用注解时可以省略value ` @MyAlbum("/delete")`

#### 4.2 元注解

​	元注解: 用于注解 注解的 注解。 用于定义自定义注解的位置

​	**@Target**： 约束自定义注解的使用场景

​	**@Retention** ：申明注解的生命周期

​	比如Junit框架中的@Test注解的元注解：

``` java
@Retention(RetentionPolicy.RUNTIME)	
@Target({ElementType.METHOD})	
public @interface Test {
    ···
}
```

​	元注解参数：	<img src="C:\Users\osaxe\AppData\Roaming\Typora\typora-user-images\image-20230402165219488.png" alt="image-20230402165219488" style="zoom:50%;" />

#### 4.3 注解解析

​	也即判断是否存在注解，存在则解析出内容

​	Annotation接口 ： 注解的顶级接口，所有注解都是它的实现类

​	AnnotatedElement接口 : 定义了与注解解析的相关方法

​	所有的类成分都实现了AnnotatedElement接口，他们都有解析注解的能力。

| Annotation[]  getDeclaredAnnotations()                       | 获得当前对象上使用的所有注解，返回注解数组。 |
| ------------------------------------------------------------ | -------------------------------------------- |
| T getDeclaredAnnotation(Class<T> annotationClass)            | 根据注解类型获得对应注解对象                 |
| boolean isAnnotationPresent(Class<Annotation> annotationClass) | 判断当前对象是否使用了指定的注解             |

​	注解解析的技巧：注解在哪个成分，则先拿哪个成分，再拿其上的注解。

```java
public void parseClass() {
        Class c = BookStore.class; // 获得类对象
        if (c.isAnnotationPresent(Book.class)) { // 判断该类是否存在这个注解
            Book book = (Book) c.getDeclaredAnnotation(Book.class);  //获取该注解对象,注意！不要使用多态，不便于获取内容
            System.out.println(book.value());
            System.out.println(book.price());
            System.out.println(Arrays.toString(book.authors()));
        }
    }
```

​	部分实现Junit框架：

​	获得类对象，提取全部方法，遍历并判断方法是否存在@MyTest注解，存在则执行方法	

### 5 动态代理(设计模式之一)

​	代理类：java.lang.reflect.Proxy

​	见代码，暂略

<img src="https://scm-imagehost-public-1301181944.cos.ap-chengdu.myqcloud.com/img/image-20230402200849385.png" alt="image-20230402200849385" style="zoom:67%;" />



### 6 XML

​	可扩展标记语言XML - eXtensible Markup Language

#### 6.1 XML语法

​	后缀名必须为 .xml

​	第一档必须为文档声明： ` <?xml version="1.0" encoding="UTF-8" ?>`

##### 6.1.1 标签(元素)规则：

- 标签由一对尖括号和合法标识符组成: ` <name> </name>` ，必须存在一个根标签(内容随意)，有且只能有一个。
- 标签必须成对出现，有开始，有结束: ` <name> </name>`
- 特殊的标签可以不成对，但是必须有结束标记，如 ` <br/>`
- 标签中可以定义属性，属性和标签名空格隔开,属性值必须用引号引起来 ` <name id = “1"> </name>` 

##### 6.1.2 其他组成内容

- 可以定义注释信息  ` <!- 注释内容 -->`  (Mono字体连号了)

- 可以使用 转义字体来表示特殊符号  

  > &lt; -> < 
  >
  > &gt; -> ＞
  >
  > &amp; -> &
  >
  > &apos; -> ’
  >
  > &quot; -> “
  >
  > 本段引用建议查看源码...md文档也同样支持，所以显示一样了

- 可以使用CDATA区 （字符数据区）` <![  CDATA[ ]   ]>`

##### 6.1.3 XML文档约束 DTD / schema

​	文档约束：限定标签和属性的书写方法的规范

​	DTD文档约束：

​		规范文件后缀必须是 .dtd，使用时在xml文档中导入该dtd文档即可。 ` <!DOCTYPE ···>`

​	schema文档约束：

​		相比DTD，可以约束文档的数据类型，规范文件本身也是一个xml文档，可以被其他文档约束。

​		schema的文档后缀必须是 .xsd (xml schema definition) ，使用时导入即可

#### 6.2 XML解析 -Dom4J

​	SAX和DOM是两种官方提供的解析方式。

基于DOM的技术框架Dom4J:<img src="https://scm-imagehost-public-1301181944.cos.ap-chengdu.myqcloud.com/img/image-20230403152536040.png" alt="image-20230403152536040" style="zoom:50%;" />

使用Dom4J解析XML文件：

 1. 创建一个Dom4J的解析器对象： ` SAXReader saxReader = new SAXReader();`

 2. 加载XML文件，获得其Document对象 ` Document document = saxReader.read(new FileInputStream("pathname\\src\\doc.xml"));`

    实际开发常用的写法 ` InputStream is = Dom4JDemo.class.gerResourceAsStream("/doc.xml");` 

    `Document document = saxReader.read(is);`

    该法会直接定位于src目录下寻找，不受模块名更改的影响。

 3. 获取根元素对象： ` Element root = document.getRootElement();`

 4. 获得子元素：<img src="https://scm-imagehost-public-1301181944.cos.ap-chengdu.myqcloud.com/img/image-20230403154859122.png" alt="image-20230403154859122" style="zoom:50%;" />

#### 6.3 XML检索——Xpath

​	如果只是需要检索某一项信息，无需先解析整个XML文档，使用Xpath。

​	Xpath是基于Dom4J设计的，还需要一个jaxen.jar

​	检索方法：

  1. 创建一个Dom4J解析器对象，然后获取Document对象

  2. 查询节点：

     > 绝对路径查找：
     >
     > ​	` List<Node> nameNodes = document.selectNodes("/contactList/contact/name");` 
     >
     > ​	这样就获取了所有该路径的子元素节点对象数组；
     >
     > 相对路径查找：    ./子元素/
     >
     > ​	` Element root = document.getRootElement();  root.selectNodes("./contact/name");`
     >
     > 全文检索：
     >
     > ​	//元素   在全文找该元素   ` document.selectNodes("//name");`
     >
     > ​	//元素1/元素2  在全文找元素1的下一级子元素的元素2  
     >
     > ​	//元素1//元素2  在全文找元素1的所有级子元素的元素2
     >
     > 属性查找：
     >
     > ​	//@属性名称   在全文检索属性对象 ` List<Node> nameNodes = document.selectNodes("@id");`
     >
     > ​	//元素[@属性名称]  在全文检索包含该属性的元素
     >
     > ​	//元素[@属性名称 = 值]  在全文检索包含该属性且值符合的元素
     >
     > 以上方法都可以换为 selectSingleNode ，只获得检索到的第一个

### 7 补充设计模式

#### 工厂模式

​	例如在线程池中，就是用工厂来创建的，而不是使用new的方式进行创建。

​	工厂模式提供了一种获取对象的方式。

​	解耦合！将创建对象的过程独立出来，便于维护。

#### 装饰模式

​	创建一个新类，包装原始类，从而在新类中提升原来类的功能。

​	不更改原类的基础，动态地扩展一个类的功能。例如动态代理

​	例如：<img src="https://scm-imagehost-public-1301181944.cos.ap-chengdu.myqcloud.com/img/image-20230403175032316.png" alt="image-20230403175032316" style="zoom:50%;" />

​	
