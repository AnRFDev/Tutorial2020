//
// Created by RustFisher on 2020/5/31.
//

#include <jni.h>
#include <string>

extern "C"
JNIEXPORT jint JNICALL
Java_com_rustfisher_fishpole_worker_Jerry_addFish(JNIEnv *env, jobject thiz, jint a, jint b) {
    return a + b;
}

extern "C"
JNIEXPORT jfloat JNICALL
Java_com_rustfisher_fishpole_worker_Jerry_calFish(JNIEnv *env, jobject thiz, jfloat f1, jfloat f2) {
    return f1 * f2 * 1.1f; // Different with Tom ;)
}

extern "C"
JNIEXPORT jstring JNICALL
Java_com_rustfisher_fishpole_worker_Jerry_name(JNIEnv *env, jobject thiz) {
    std::string jerry = "Jerry";
    return env->NewStringUTF(jerry.c_str());
}