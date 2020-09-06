bsdiff与bspatch重新编译了一份，现在可用了，使用方式：
生成差分包： bsdiff oldfile newfile patchfile
合成：bspatch oldfile 合成后的输出文件 patchfile

如：bsdiff 1.apk 2.apk patch
对比版本1与版本2的apk，生成差分包patch

bspatch 1.apk 2.apk patch
使用差分包patch与1.apk合并，生成2.apk