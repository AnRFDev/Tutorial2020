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