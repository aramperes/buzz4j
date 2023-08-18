#include <jni.h>

#ifndef _Included_dev_poire_buzz4j_HarfBuzz
#define _Included_dev_poire_buzz4j_HarfBuzz
#ifdef __cplusplus
extern "C" {
#endif

JNIEXPORT void JNICALL Java_dev_poire_buzz4j_HarfBuzz_shapeBuffer
  (JNIEnv *env, jclass clazz, jstring fontPath, jbyteArray buffer)
  {
    jbyte *buffer_bytes = env->GetByteArrayElements(buffer, NULL);
    buffer_bytes[0] = 123;
    env->ReleaseByteArrayElements(buffer, buffer_bytes, 0);
  }

#ifdef __cplusplus
}
#endif
#endif
