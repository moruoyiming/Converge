package com.example.hack;

/**
 * 如果一个类，引用的其他类都是在同一个dex文件中，类会被打上CLASS_ISPREVERIFIED标志
 * AntilazyLoad类会被打包成单独的hack.dex，这样当安装apk的时候，classes.dex内的类都会引用一个在不相同dex中的
 * AntilazyLoad类，防止了类被打上CLASS_ISPREVERIFIED的标志了，只要没被打上这个标志的类都可以进行打补丁操作。
 * 字节码插桩：在Java字节码Class中某些位置插入或修改一些代码。
 */
public class AntilazyLoad {


}
