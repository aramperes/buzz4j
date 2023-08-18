#include <jni.h>
#include <hb.h>

#ifndef _Included_dev_poire_buzz4j_HarfBuzz
#define _Included_dev_poire_buzz4j_HarfBuzz
#ifdef __cplusplus
extern "C" {
#endif

JNIEXPORT void JNICALL Java_dev_poire_buzz4j_HarfBuzz_shapeBuffer
  (JNIEnv *env, jclass clazz, jstring fontPath, jbyteArray buffer)
  {
    // Convert Java types
    char *filename = env->GetStringUTFChars(fontPath, nullptr);
    jbyte *buffer_bytes = env->GetByteArrayElements(buffer, NULL);

    // Create HarfBuzz buffer
    hb_buffer_t *hb_buf;
    hb_buf = hb_buffer_create();
    hb_buffer_add_utf8(hb_buf, buffer_bytes, -1, 0, -1);
    hb_buffer_guess_segment_properties(hb_buf);

    // Load font
    hb_blob_t *blob = hb_blob_create_from_file_or_fail(filename);
    hb_face_t *face = hb_face_create(blob, 0);
    hb_font_t *font = hb_font_create(face);

    // Apply shape
    hb_shape(font, hb_buf, NULL, 0);

    // Update source buffer with new glyphs
    buffer_bytes[0] = 123;

    // Cleanup
    hb_buffer_destroy(hb_buf);
    hb_font_destroy(font);
    hb_face_destroy(face);
    hb_blob_destroy(blob);

    // Release buffer back to Java
    env->ReleaseByteArrayElements(buffer, buffer_bytes, 0);
  }

#ifdef __cplusplus
}
#endif
#endif
