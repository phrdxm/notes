### 分布式系统概要

#### 特点

* 组件分布在多台网络计算机上
* 组件之间仅仅通过**消息传递**来通信并协调行动

#### 单机系统5要素

* 输入设备
* 输出设备
* 运算器
* 控制器
* 存储器：
  * 内存（掉电丢失数据）
  * 外存（掉电不丢失数据）

#### 线程和进程的执行模式

##### 多线程

* 不进行任何交互
* 基于共享容器的交互模式
  * 线程安全容器
  * 线程不安全容器：加锁或写时复制（copy on write）
    * 写时复制：当向容器中写入元素时，先将容器复制，再在副本中写入，最后将原容器的引用指向副本。写时复制的好处是读的时候不用加锁。写时复制也是一种读写分离的思想。
* 基于事件的交互模式：线程A只有在某一事件发生时才能继续运行，而这一事件与线程B有关。这种模式下要注意避免死锁，可以通过调整多个锁的获取顺序，或原子性地获取所有锁。

##### 多进程

多进程和多线程的区别：
* 线程间可以共享内存，进程不可以。
* 多进程的情况下，单个进程出现问题可能会导致整体不可用。但是分布式系统可以看作是多机多进程的系统，如果处理得当，单个进程的问题不会影响整体。
* 进程间通信要复杂一些，涉及到序列化和反序列化。

#### 各种模式下的可用性分析

* 单进程：可用性最差，机器故障、OS问题或进程本身的问题都会导致功能的整体不可用。
* 单机多进程：机器故障和OS问题会导致功能的整体不可用，某个进程出现问题可能会导致部分功能不可用，取决于具体设计。
* 多机：可用性最好，即便是机器故障和OS问题，也能保证系统大体上可用。

### 网络通信基础

#### TCP/IP四层结构

* 应用层
* 传输层
* 网络层
* 链路层

#### 网络IO的实现方式

* BIO：也就是阻塞IO，当服务器端收到一个新的套接字，就要准备一个线程来专门处理，包括建立连接和读写，建立连接和读写操作也是阻塞的。

* NIO：也就是非阻塞IO，基于事件驱动思想，采用Reactor模式。NIO中有三个重要概念，分别是**Channel（通道）**，**Buffer（缓冲区）**和**Selector（选择器）**。
  * 通道类似于BIO中的流，不过流是单向的，通道是双向的。
  * 对通道进行读写只能通过缓冲区，缓冲区其实就是个数组，支持多种基本数据类型，对于网络IO，用的最多的还是字节缓冲区，也就是字节数组。关于缓冲区的几个概念：
    * capacity：数组长度。
    * position：当前游标位置。
    * limit：第一个不允许读写的位置，也就是说，该位置前的数据是有效的，该位置后的数据是无意义的。

    ```
    // Java NIO 向文件写数据示例
    File file = new File("data");
    try (FileOutputStream outputStream = new FileOutputStream(file)) {
        FileChannel channel = outputStream.getChannel();
        ByteBuffer buffer = ByteBuffer.allocate(64);
        buffer.put("java nio".getBytes());
        buffer.flip();
        channel.write(buffer);
        channel.close();
    }

    // Java NIO 从文件读数据示例
    File file = new File("data");
    try (FileInputStream inputStream = new FileInputStream(file)) {
        FileChannel channel = inputStream.getChannel();
        ByteBuffer buffer = ByteBuffer.allocate(64);
        channel.read(buffer);
        buffer.flip();
        // 对buffer进行操作
    }
    ```

  * 选择器的作用是定时访问（单线程轮询）所有注册的通道，将通道中发生的特定事件取出进行处理。
