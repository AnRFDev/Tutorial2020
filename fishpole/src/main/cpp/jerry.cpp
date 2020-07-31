//
// Created by RustFisher on 2020/5/31.
//

#include <jni.h>
#include <string>
#include <android/log.h>

#define tag "rustApp"

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


extern "C"
JNIEXPORT jstring JNICALL
Java_com_rustfisher_fishpole_worker_Jerry_reverseString(JNIEnv *env, jobject thiz, jstring input) {
    const char *inputPtr = env->GetStringUTFChars(input, NULL);
    int len1 = env->GetStringLength(input);
    int len2 = strlen(inputPtr);

    __android_log_print(ANDROID_LOG_INFO, tag, "[reverseString] input: %s", inputPtr);
    __android_log_print(ANDROID_LOG_INFO, tag, "[reverseString] GetStringLength: %d, strlen: %d",
                        len1, len2);

    char *out = new char[len1 + 1]{}; // 这里需要多一位

    __android_log_print(ANDROID_LOG_INFO, tag, "[reverseString] origin out: %s", out);

    for (int i = 0; i < len1; i++) {
        out[i] = inputPtr[len1 - i - 1];
    }

    __android_log_print(ANDROID_LOG_INFO, tag, "[reverseString] result: %s", out);
    jstring res = env->NewStringUTF(out);

    delete[] out;
    env->ReleaseStringChars(input, reinterpret_cast<const jchar *>(inputPtr));

    return res;
}