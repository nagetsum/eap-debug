#include "com_baeldung_jni_HelloWorldJNI.h"
#include "jni.h"
#include  "stdio.h"

JNIEXPORT void JNICALL Java_com_baeldung_jni_HelloWorldJNI_sayHello(JNIEnv* env, jclass clazz) {
  printf("Hello world\n");
  return;
}
