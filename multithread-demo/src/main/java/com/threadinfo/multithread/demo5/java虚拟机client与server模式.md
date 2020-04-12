# JVM client模式和Server模式的区别
> 这里向大家描述一下JVM client模式和Server模式两者的区别和联系，JVM如果不显式指定是-Server模式还是-client模式，JVM能够根据下列原则进行自动判断（适用于Java5版本或者Java以上版本）。``
## JVM client模式和Server模式
> JVM Server模式与client模式启动，最主要的差别在于：-Server模式启动时，速度较慢，但是一旦运行起来后，性能将会有很大的提升。JVM如果不显式指定是-Server模式还是-client模式，JVM能够根据下列原则进行自动判断（适用于Java5版本或者Java以上版本）。
> 前段时间有个同事给我发了个java跟c++性能比较的文章，其中有个对比图引起了我的兴趣，意外的是，我感兴趣的不是java和c++的对比，而是java -Server模式和java -client模式的对比。从来没想到两者间的性能有如此巨大的差别。而在后来自己的亲身测试中发现确实如此。
## 对比图

!["效率对比图"](https://pic3.zhimg.com/80/v2-416da8c73d4b52618727c413e580b6ca_720w.jpg)

图中最显著的就是JVM client模式和Server模式关于method call的对比，那个差别不是一般的大，在后来的测试中发现，相差至少有10倍。

## 另外两个对比图：
![""](https://pic2.zhimg.com/80/v2-99ebec20e164f9f7ac3404760b444671_720w.jpg)
![""](https://pic3.zhimg.com/80/v2-e56f43392ef007e5d016ca721ef8b95a_720w.jpg)

> 总结:
JVM工作在Server模式可以大大提高性能，但应用的启动会比client模式慢大概10%。当该参数不指定时，虚拟机启动检测主机是否为服务器，如果是，则以Server模式启动，否则以client模式启动，J2SE5.0检测的根据是至少2个CPU和最低2GB内存。
当JVM用于启动GUI界面的交互应用时适合于使用client模式，当JVM用于运行服务器后台程序时建议用Server模式。
JVM在client模式默认-Xms是1M，-Xmx是64M；JVM在Server模式默认-Xms是128M，-Xmx是1024M。我们可以通过运行:java -version来查看jvm默认工作在什么模式。