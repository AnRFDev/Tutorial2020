#include <jni.h>

//
// Created by Rust on 2020/6/19.
//

extern "C"
JNIEXPORT jchar JNICALL
Java_com_rustfisher_fishpole_worker_BaseParam_nChar(JNIEnv *env, jobject thiz) {
    return 'a';
}

extern "C"
JNIEXPORT jbyte JNICALL
Java_com_rustfisher_fishpole_worker_BaseParam_nByte(JNIEnv *env, jobject thiz) {
    unsigned char abc = 99;
    return abc;
}

extern "C"
JNIEXPORT jshort JNICALL
Java_com_rustfisher_fishpole_worker_BaseParam_nShort(JNIEnv *env, jobject thiz) {
    short s = 128;
    return s;
}

extern "C"
JNIEXPORT jint JNICALL
Java_com_rustfisher_fishpole_worker_BaseParam_nInt(JNIEnv *env, jobject thiz) {
    return 42;
}

extern "C"
JNIEXPORT jlong JNICALL
Java_com_rustfisher_fishpole_worker_BaseParam_nLong(JNIEnv *env, jobject thiz) {
    long res = 123456789;
    return res;
}

extern "C"
JNIEXPORT jfloat JNICALL
Java_com_rustfisher_fishpole_worker_BaseParam_nFloat(JNIEnv *env, jobject thiz) {
    return 3.14159F;
}

extern "C"
JNIEXPORT jdouble JNICALL
Java_com_rustfisher_fishpole_worker_BaseParam_nDouble(JNIEnv *env, jobject thiz) {
    return 3.141592658;
}

extern "C"
JNIEXPORT jboolean JNICALL
Java_com_rustfisher_fishpole_worker_BaseParam_nBool(JNIEnv *env, jobject thiz) {
    return JNI_FALSE;
}

extern "C"
JNIEXPORT jintArray JNICALL
Java_com_rustfisher_fishpole_worker_BaseParam_nGenIntArray(JNIEnv *env, jobject thiz) {
    int arr[] = {1, 2, 3, 4, 5, 6, 7, 8, 9, 0};
    int len = sizeof(arr) / sizeof(arr[0]);;
    jintArray resArr = env->NewIntArray(len);
    env->SetIntArrayRegion(resArr, 0, len, arr);
    return resArr;
}

extern "C"
JNIEXPORT void JNICALL
Java_com_rustfisher_fishpole_worker_BaseParam_nModifyIntArray(JNIEnv *env, jobject thiz,
                                                              jintArray input) {
    int len = env->GetArrayLength(input);
    int *ptr = env->GetIntArrayElements(input, JNI_FALSE);
    for (int i = 0; i < len; i++) {
        ptr[i] = i + len;
    }
    env->ReleaseIntArrayElements(input, ptr, JNI_COMMIT);
    delete ptr;
}

extern "C"
JNIEXPORT jobject JNICALL
Java_com_rustfisher_fishpole_worker_BaseParam_nGetCarp(JNIEnv *env, jobject thiz, jstring name,
                                                       jint age, jfloat weight, jboolean alive) {
    jclass carp_clz = env->FindClass("com/rustfisher/fishpole/data/Carp");
    jmethodID fun_construct = env->GetMethodID(carp_clz, "<init>", "(Ljava/lang/String;IFZ)V");
    return env->NewObject(carp_clz, fun_construct, name, age, weight, alive);
}