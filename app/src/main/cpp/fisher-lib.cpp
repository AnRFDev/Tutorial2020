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

extern "C"
JNIEXPORT jstring JNICALL
Java_com_rustfisher_tutorial2020_ndk_util_FileUtils_nReadFile(JNIEnv *env, jobject thiz,
                                                              jstring filepath) {
    char *filepathPtr = const_cast<char *>(env->GetStringUTFChars(filepath, JNI_FALSE));
    FILE *file = fopen(filepathPtr, "r+");
    char myStr[128];
    if (file != nullptr) {
        char *readInPtr = fgets(myStr, 128, file);
        fclose(file);
        if (nullptr != readInPtr) {
            return env->NewStringUTF(myStr);
        }
        return env->NewStringUTF("JNI read file fail!");
    }
    return env->NewStringUTF("JNI read file fail!");
}

extern "C"
JNIEXPORT void JNICALL
Java_com_rustfisher_tutorial2020_ndk_util_FileUtils_nWrite(JNIEnv *env, jobject thiz,
                                                           jstring filepath, jstring msg) {
    char *filepathPtr = const_cast<char *>(env->GetStringUTFChars(filepath, NULL));
    FILE *file = fopen(filepathPtr, "w+");
    const char *nativeMsg = reinterpret_cast<const char *>(env->GetStringUTFChars(msg, NULL));

    if (file != nullptr) {
        fputs(nativeMsg, file);
        fflush(file);
        fclose(file);
    }
}