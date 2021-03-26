#include <jni.h>
#include <string>
#include <android/log.h>

//
// Created by RustFisher on 2020/5/31.
//

#define tag "rustApp"

extern "C"
JNIEXPORT jstring JNICALL
Java_com_rustfisher_fishpole_worker_FisherTom_name(JNIEnv *env, jobject thiz) {
    std::string myName = "Tom";
    return env->NewStringUTF(myName.c_str());
}

extern "C"
JNIEXPORT jint JNICALL
Java_com_rustfisher_fishpole_worker_FisherTom_addFish(JNIEnv *env, jobject thiz, jint a, jint b) {
    return a + b;
}

extern "C"
JNIEXPORT jfloat JNICALL
Java_com_rustfisher_fishpole_worker_FisherTom_calFish(JNIEnv *env, jobject thiz, jfloat f1,
                                                      jfloat f2) {
    return f1 * f2;
}

extern "C"
JNIEXPORT jdouble JNICALL
Java_com_rustfisher_fishpole_worker_FisherTom_nAddMoney(JNIEnv *env, jobject thiz) {
    jclass tom_clz = env->GetObjectClass(thiz); // 获取到这个Java类
    jfieldID money_fid = env->GetFieldID(tom_clz, "money", "D"); // 获取到属性id
    jdouble money = env->GetDoubleField(thiz, money_fid); // 获取到当前值
    money = money * 1.2 + 4.2; // 简单的运算
    env->SetDoubleField(thiz, money_fid, money); // 把值存进去
    return money;
}

extern "C"
JNIEXPORT jint JNICALL
Java_com_rustfisher_fishpole_worker_FisherTom_nVisitStaticNumber1(JNIEnv *env, jobject thiz) {
    jclass cls = env->GetObjectClass(thiz);
    jfieldID n_fid = env->GetStaticFieldID(cls, "staticNumber1", "I");
    jint n1 = env->GetStaticIntField(cls, n_fid);
    __android_log_print(ANDROID_LOG_INFO, tag, "对象访问staticNumber1: %d", n1);
    return n1;
}

extern "C"
JNIEXPORT jint JNICALL
Java_com_rustfisher_fishpole_worker_FisherTom_nVisitStaticNumber2(JNIEnv *env, jclass clazz) {
    jfieldID n_fid = env->GetStaticFieldID(clazz, "staticNumber1", "I");
    jint n1 = env->GetStaticIntField(clazz, n_fid);
    __android_log_print(ANDROID_LOG_INFO, tag, "静态 ndk访问staticNumber1: %d", n1);
    return n1;
}