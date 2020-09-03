//
// Created by jian on 2020/9/3.
//
#include <jni.h>
#include <string>
#include <fcntl.h>
#include <unistd.h>
#include <sys/stat.h>
#include <sys/mman.h>
#include <android/log.h>

extern "C"
JNIEXPORT jint JNICALL
Java_com_example_dexdiff_BsPathUtils_patch(JNIEnv *env, jclass clazz, jstring old_apk,
                                           jstring new_apk, jstring patch) {
    // TODO: implement patch()
}