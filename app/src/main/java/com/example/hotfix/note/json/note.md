## 序列化

序列化 与 反序列化

序列化用途: 网络上、跨进程

1.Serializable

2.Parcelable

3.json、xml、protbuf 二进制数据



## 如何选择合理的序列化方案

通用性

强健性

可调试性/可读性

性能

可扩展性/兼容性

安全性/访问限制

## Serializable

Serializable为接口，只是个标识。
Externalizable 继承自 Serializable 。Externalizable也是接口。
增加两个 writeExternal(ObjectOutput out)      readExternal(ObjectInput in)

需要两个辅助类

ObjectOutputStream  ObjectInputStream

String类 已经是实现序列化的。

Serializable 网络数据传输使用

Parcelable 进程间数据通讯

Serializable需要注意的地方
多引用写入
子类实现序列化，父类不实现序列化/ 对象引用
类的演化
枚举类型
重写readObject,writeObject
单例模式的序列化问题/反射问题
序列化存储规则

Serializable 和 Parcelable 两者区别
1. 在使用内存方面，Parcelable比Serializable性能高，所以推荐使用Parcelable。
2. Serializable在序列化的时候会产生大量的临时变量，从而引起频繁的GC。
3. Parcelable不能使用在要将数据存储在磁盘上的情况，因为Parcelable不能很好的保证数据的持续性，
   在外界有变化的情况下，建议使用Serializable



面试相关
1. 反序列化后的对象，需要调用构造函数重新构造吗
   反序列化调用不会调用构造函数。以存储的二进制数据进行构造

2. 序列前的对象与序列化后的对象是什么关系？是("=="还是equal？是浅复制还是深复制？)
   序列化前和序列化后 是两个不同的对象，对象地址发生了改变。调用equal 和 == 返回true。是一个深复制。

3. Android里面为什么要设计出Bundle而不是直接用Map结构
   Bundle中是使用的Parcel打包数据。Parcel可以实现跨进程通讯。
   Bundle内部是由ArrayMap实现的，ArrayMap的内部实现是两个数组，一个int数组是存储对象数 据对应下标，一个对象数组
   保存key和value，内部使用二分法对key进行排序，所以在添加、删 除、查找数据的时候，都会使用二分法查找，只适合于小数
   据量操作，如果在数据量比较大的情况 下，那么它的性能将退化。而HashMap内部则是数组+链表结构，所以在数据量较少的时候，
   HashMap的Entry Array比ArrayMap占用更多的内存。因为使用Bundle的场景大多数为小数据 量，我没见过在两个Activity之
   间传递10个以上数据的场景，所以相比之下，在这种情况下使用 ArrayMap保存数据，在操作速度和内存占用上都具有优势，
   因此使用Bundle来传递数据，可以保 证更快的速度和更少的内存占用。 另外一个原因，则是在Android中如果使用Intent来携带数据的话，
   需要数据是基本类型或者是可 序列化类型，HashMap使用Serializable进行序列化，而Bundle则是使用Parcelable进行序列化。
   而在Android平台中，更推荐使用Parcelable实现序列化，虽然写法复杂，但是开销更小，所以为 了更加快速的进行数据的序列化和反序列化，
   系统封装了Bundle类，方便我们进行数据的传输。

4. SerialVersionID的作用是什么？
   版本控制

5. Android中Intent/Bundle的通信原理及大小限制
   大小限制  bundle 在zgote在创建进程的时候，分配了binder的内存大小。binder申请匿名内存有限制。
   binder在内核空间创建内存映射时，大小限制在 < 4M
   intent 1M限制
   Intent 中的 Bundle 是使用 Binder 机制进行数据传送的。能使用的 Binder 的缓冲区是有大小限 制的(有些手机是 2 M)，
   而一个进程默认有 16 个 Binder 线程，所以一个线程能占用的缓冲区 就更小了( 有人以前做过测试，大约一个线程可以占用 128 KB)。
   所以当你看到 The Binder transaction failed because it was too large 这类 TransactionTooLargeException 异常时，
   你应 该知道怎么解决了

6. 为何Intent不能直接在组件间传递对象而要通过序列化机制？
   startActivity（intent），activity启动流程要和AMS交互，需要跨进程通讯。只有把数据序列化后，传递。

7. 序列化与持久化的关系和区别是什么？
   序列化:跨进程传输数据时，需要使用序列化。
   持久化:数据的存储。
   Intent在启动其他组件时，会离开当前应用程序进程，进入ActivityManagerService进程 (intent.prepareToLeaveProcess())，
   这也就意味着，Intent所携带的数据要能够在不同进程间 传输。首先我们知道，Android是基于Linux系统，不同进程之间的java对象是无法传输，
   所以我 们此处要对对象进行序列化，从而实现对象在 应用程序进程 和 ActivityManagerService进程 之间 传输。
   而Parcel或者Serializable都可以将对象序列化，其中，Serializable使用方便，但性能不如Parcel 容器
   ，后者也是Android系统专门推出的用于进程间通信等的接口














