#include <jni.h>
#include <hb.h>

#ifndef _Included_dev_poire_buzz4j_HarfBuzz
#define _Included_dev_poire_buzz4j_HarfBuzz
#ifdef __cplusplus
extern "C" {
#endif

JNIEXPORT jobjectArray JNICALL Java_dev_poire_buzz4j_HarfBuzz_shapeBufferGlyphs
  (JNIEnv *env, jclass clazz, jstring fontPath, jstring text)
  {
    // Convert Java types
    const char *filename = env->GetStringUTFChars(fontPath, nullptr);
    const char *textBuffer = env->GetStringUTFChars(text, nullptr);

    // Create HarfBuzz buffer
    hb_buffer_t *hb_buf;
    hb_buf = hb_buffer_create();
    hb_buffer_add_utf8(hb_buf, textBuffer, -1, 0, -1);
    hb_buffer_guess_segment_properties(hb_buf);

    // Load font
    hb_blob_t *blob = hb_blob_create_from_file_or_fail(filename);
    hb_face_t *face = hb_face_create(blob, 0);
    hb_font_t *font = hb_font_create(face);

    // Apply shape
    hb_shape(font, hb_buf, NULL, 0);

    // TODO
    // Build output array of glyphs
    char msg[60] = "{hatever";
    jobjectArray result;

    result = (jobjectArray) env->NewObjectArray(5,
         env->FindClass("java/lang/String"),
         env->NewStringUTF("test"));

    // Cleanup
    hb_buffer_destroy(hb_buf);
    hb_font_destroy(font);
    hb_face_destroy(face);
    hb_blob_destroy(blob);
    env->ReleaseStringUTFChars(text, textBuffer);

    return result;
  }

#ifdef __cplusplus
}
#endif
#endif
