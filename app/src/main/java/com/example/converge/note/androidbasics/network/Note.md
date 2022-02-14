1.连接（客户端，服务端）
2.读网络数据
3.写网络数据


BIO  阻塞IO  面向流
    ServerSocket 绑定 Socket  操作阻塞
NIO  多路复用  面向缓冲区  三大核心组件及关系   Selector Channel Buffer SelectionKey
    ServerSocketChannel SocketChannel  向Selector注册事件，关注客户端连接事件，Select检测到客户端连接事件时
    通知ServerSocketChannel 有客户端连接。三次握手 数据传输。SocketChannel在Selector注册事件，关注读事件。SocektChannel 与
    应用数据传输时增加中间件Buffer。

AIO  异步IO