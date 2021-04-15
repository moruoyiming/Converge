#include <jni.h>
#include <string>
//JNI打印，NDK工具链里的liblog.so库
#include <android/log.h>

#define TAG "JNILOG"
#define LOGD(...) __android_log_print(ANDROID_LOG_DEBUG,TAG,__VA_ARGS__)
#define LOGE(...) __android_log_print(ANDROID_LOG_ERROR,TAG,__VA_ARGS__)


extern "C" JNIEXPORT jstring JNICALL
Java_com_example_converge_note_ndk_NdkActivity_stringFromJNI(
        JNIEnv *env,
        jobject /* this */) {
    std::string hello = "Hello from C++";
    return env->NewStringUTF(hello.c_str());
}

//必须采用c的编译方式，请看JNIEnv内部源码
extern "C"
JNIEXPORT //标记该方法可以被外部调用
jstring //Java 与 Natvie 转换使用
JNICALL //关键字 JNI标记

//JNIEnv * env JNI:的桥梁环境

//jobject thiz 谁调用该方法，该对象就是谁的实例
//jclass clazz 谁调用该方法，该class就是谁的class
com_example_converge_note_ndk_NdkActivity_getStringPWD(JNIEnv *env, jobject thiz) {
    //无论是C还是C++ 最终是调用到C的JNINativeInterface，所以必须采用C的方式extern "C"


//静态函数
}extern "C"
JNIEXPORT jstring JNICALL
Java_com_example_converge_note_ndk_NdkActivity_getStringPWD2(JNIEnv *env, jclass clazz) {
}

extern "C"
JNIEXPORT void JNICALL
Java_com_example_converge_note_ndk_NdkActivity_changeName(JNIEnv *env, jobject thiz) {

    //获取class
    jclass j_cls = env->GetObjectClass(thiz);
    //获取属性
    // GetFieldID(jclass clazz, const char* name, const char* sig)
    // class 属性名 属性签名
    jfieldID j_fid = env->GetFieldID(j_cls, "name", "Ljava/lang/String;");

    //转换工作
    jstring j_str = static_cast<jstring>(env->GetObjectField(thiz, j_fid));

    //打印字符串
    char *c_str = const_cast<char * >(env->GetStringUTFChars(j_str, NULL));
    LOGD("native :%s", c_str);
    jstring jName = env->NewStringUTF("Beyond");
    env->SetObjectField(thiz, j_fid, jName);
}

extern "C"
JNIEXPORT void JNICALL
Java_com_example_converge_note_ndk_NdkActivity_changeAge(JNIEnv *env, jclass clazz) {
    // TODO: implement changeAge()
    jfieldID j_fid = env->GetStaticFieldID(clazz, "age", "I");

    jint age = env->GetStaticIntField(clazz, j_fid);

    age += 1;
    env->SetStaticIntField(clazz, j_fid, age);
}


extern "C"
JNIEXPORT void JNICALL
Java_com_example_converge_note_ndk_NdkActivity_callAddMethod(JNIEnv *env, jobject clazz) {
    //调用java层add函数
    //获取class
    jclass j_cls = env->GetObjectClass(clazz);
    jmethodID j_mid = env->GetMethodID(j_cls, "add", "(II)I");
    //调用java函数
    jint sum = env->CallIntMethod(clazz, j_mid, 3, 3);
    LOGD("sum result:%d", sum);
}
//jint = int
//jstring = String
//jintArray = int[]
//jobjectArray = 引用类型对象[]
extern "C"
JNIEXPORT void JNICALL
Java_com_example_converge_note_ndk_NdkActivity_testArrayAction(JNIEnv *env, jobject thiz,
                                                  jint count,
                                                  jstring text_info,
                                                  jintArray ints,
                                                  jobjectArray strs) {
    //1.基本数据类型 jint count
    int countInt = count;
    LOGD("参数一 countInt:%d", countInt);
    const char *textInfo = env->GetStringUTFChars(text_info, NULL);
    LOGD("参数二 textInfo:%s", textInfo);

    //2.int数组转成 int*
    int *jintArray = env->GetIntArrayElements(ints, NULL);
    jsize size = env->GetArrayLength(ints);
    for (int i = 0; i < size; ++i) {
        *(jintArray + i) += 100;
        LOGD("参数三 int[]:%d", *jintArray + i);
    }

    //JNIEnv *env == 操纵杆 JNI层
    // 0:刷新Java数组 并释放C++层数组
    // JNI_COMMIT：只提交 只刷新Java数组，不释放C++层数组
    // JNI_ABORT: 只释放C++层数组
    env->ReleaseIntArrayElements(ints, jintArray, 0);


    //3. jobjectArray 代表是Java的引用类型数组
    jsize jsize1 = env->GetArrayLength(strs);
    for (int i = 0; i < jsize1; ++i) {
        jstring jobj = static_cast<jstring>(env->GetObjectArrayElement(strs, i));
        const char *jobjCharp = env->GetStringUTFChars(jobj, NULL);
        LOGD("参数四 strs[]:%s", jobjCharp);
        //释放jstring
        env->ReleaseStringUTFChars(jobj, jobjCharp);
    }
}

extern "C"
JNIEXPORT void JNICALL
Java_com_example_converge_note_ndk_NdkActivity_putObject(JNIEnv *env, jobject thiz, jobject student,
                                            jstring str) {
    const char *strChar = env->GetStringUTFChars(str, NULL);
    env->ReleaseStringUTFChars(str, strChar);
//    1.寻找类
    jclass studentClass = env->GetObjectClass(student);
//    jclass studentClass = env->FindClass("com/example/ndk/bean/Student");
//    2.Student类里面的函数规则 签名
    jmethodID setName = env->GetMethodID(studentClass, "setName", "(Ljava/lang/String;)V");
    jmethodID getName = env->GetMethodID(studentClass, "getName", "()Ljava/lang/String;");
    jmethodID showInfo = env->GetStaticMethodID(studentClass, "showInfo", "(Ljava/lang/String;)V");
    jmethodID setAge = env->GetMethodID(studentClass, "setAge", "(I)V");
//    3.调用setName
    jstring value = env->NewStringUTF("张三");
    env->CallVoidMethod(student, setName, value);
//    4.调用getName
    jstring getNameResult = static_cast<jstring>(env->CallObjectMethod(student, getName));
    const char *getNameValue = env->GetStringUTFChars(getNameResult, NULL);
    LOGD("getNameResult:%s", getNameValue);
//    5.调用showInfo
    jstring info = env->NewStringUTF("北望气");
    env->CallStaticVoidMethod(studentClass, showInfo, info);
//    6.调用setAge
    jint intValue = 23;
    env->CallVoidMethod(student, setAge, intValue);

}


// 局部引用  全局引用
extern "C"
JNIEXPORT void JNICALL
Java_com_example_converge_note_ndk_NdkActivity_insertObject(JNIEnv *env, jobject thiz) {
    //1.通过包名+类名的方式 拿到Student class
    const char *studentstr = "com/example/converge/note/ndk/Student";
    jclass studentClass = env->FindClass(studentstr);
    //2.通过student的class 实例化此Student对象 C++ new
    jobject studentObj = env->AllocObject(studentClass);//AllocObject 只实例化对象，不会调用对象的构造函数
    //3.函数规则
    jmethodID setName = env->GetMethodID(studentClass, "setName", "(Ljava/lang/String;)V");
    jmethodID setAge = env->GetMethodID(studentClass, "setAge", "(I)V");
    jstring nameValue = env->NewStringUTF("东方不败");
    env->CallVoidMethod(studentObj, setName, nameValue);
    env->CallVoidMethod(studentObj, setAge, 30);
//    env->NewObject();//NewObject 实例化对象，会调用对象的构造函数

    const char *personstr = "com/example/converge/note/ndk/Person";
    jclass personClass = env->FindClass(personstr);
    jobject personObj = env->AllocObject(personClass);
    jmethodID setStudent = env->GetMethodID(personClass, "setStudent",
                                            "(Lcom/example/converge/note/ndk/Student;)V");
    env->CallVoidMethod(personObj, setStudent, studentObj);
    //规范 释放
    env->DeleteLocalRef(studentClass);
    env->DeleteLocalRef(personClass);
    env->DeleteLocalRef(studentObj);
    env->DeleteLocalRef(personObj);


    //TODO 局部引用 jobject jclass jstring  【函数弹栈后，会自动释放】
}

jclass dogClass;//这个是局部引用，不是全局引用


extern "C"
JNIEXPORT void JNICALL
Java_com_example_converge_note_ndk_NdkActivity_testQuote(JNIEnv *env, jobject thiz) {
    if (NULL == dogClass) {
        const char *dogClassChar = "com/example/converge/note/ndk/Dog";
        //TODO 局部引用
//        dogClass = env->FindClass(dogClassChar);
        //TODO 升级全局引用， JNI函数结束 不释放dogClass引用。
        //类似 C++对象 new ，需要手动delete
        jclass temp = env->FindClass(dogClassChar);
        dogClass = static_cast<jclass>(env->NewGlobalRef(temp));//提升全局引用
//        释放class
        env->DeleteLocalRef(temp);
    }
    jmethodID init = env->GetMethodID(dogClass, "<init>", "()V");
    jobject dogObject = env->NewObject(dogClass, init);

    init = env->GetMethodID(dogClass, "<init>", "(I)V");
    dogObject = env->NewObject(dogClass, init, 100);

    init = env->GetMethodID(dogClass, "<init>", "(ILjava/lang/String;)V");
    jstring name = env->NewStringUTF("dsfsdfsd");
    dogObject = env->NewObject(dogClass, init, 100, name);

    env->DeleteLocalRef(dogObject);

}//JNI函数结束，会释放局部引用，dogClass虽然会被释放，但是还不等于NULL，只是一个悬空指针。所以再次调用函数，进不去if语句中

// 可以在这个类中声明，另外一个类去实现
extern int age;//声明age
extern void show();//声明函数


extern "C"
JNIEXPORT void JNICALL
Java_com_example_converge_note_ndk_NdkActivity_delQuote(JNIEnv *env, jobject thiz) {
    if (dogClass != NULL) {
        env->DeleteGlobalRef(dogClass);
        dogClass = NULL;//指向NULL 避免成为悬空指针
        LOGE("全局引用释放完毕，上面的按钮已经失去了全局引用，再次点击会报错！");
    }
    show();
}