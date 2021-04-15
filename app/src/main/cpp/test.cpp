#include <jni.h>
#include <string>
//JNI打印，NDK工具链里的liblog.so库
#include <android/log.h>

#define TAG "JNILOG"
#define LOGD(...) __android_log_print(ANDROID_LOG_DEBUG,TAG,__VA_ARGS__)
#define LOGE(...) __android_log_print(ANDROID_LOG_ERROR,TAG,__VA_ARGS__)

int age =99;

void show(){
    LOGD("show 函数调用");
}