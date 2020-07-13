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









 s

















