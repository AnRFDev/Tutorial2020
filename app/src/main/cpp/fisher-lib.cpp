//
// Created by RustFisher on 2020/5/28.
//
#include <jni.h>
#include <string>

extern "C" JNIEXPORT jstring JNICALL
Java_com_rustfisher_tutorial2020_cal_CalUtil_getMsg(JNIEnv *env, jobject thiz) {
    std::string hello = "欢迎来到Tutorial";
    return env->NewStringUTF(hello.c_str());
}

extern "C" JNIEXPORT jint JNICALL
Java_com_rustfisher_tutorial2020_cal_CalUtil_getNumber(JNIEnv *env, jobject thiz) {
    return 2020;
}