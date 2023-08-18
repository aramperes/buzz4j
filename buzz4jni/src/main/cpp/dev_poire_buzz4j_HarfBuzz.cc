#include <jni.h>
#include <hb.h>

#ifndef _Included_dev_poire_buzz4j_HarfBuzz
#define _Included_dev_poire_buzz4j_HarfBuzz
#ifdef __cplusplus
extern "C" {
#endif

JNIEXPORT jintArray JNICALL Java_dev_poire_buzz4j_HarfBuzz_shapeStringGlyphs
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

    // Get glyph info and positions
    unsigned int glyph_count;
    hb_glyph_info_t * glyph_info = hb_buffer_get_glyph_infos(hb_buf, &glyph_count);
    hb_glyph_position_t *glyph_pos = hb_buffer_get_glyph_positions(hb_buf, &glyph_count);

    // Build an [n * 5] array to encode glyph IDs and positions
    jintArray result;
    result = (jintArray) env->NewIntArray(glyph_count * 5);
    for (unsigned int i = 0; i < glyph_count; i++) {
      env->SetIntArrayRegion(result, i * 5 + 0, 1, (const jint *) &glyph_info[i].codepoint);
      env->SetIntArrayRegion(result, i * 5 + 1, 1, (const jint *) &glyph_pos[i].x_advance);
      env->SetIntArrayRegion(result, i * 5 + 2, 1, (const jint *) &glyph_pos[i].y_advance);
      env->SetIntArrayRegion(result, i * 5 + 3, 1, (const jint *) &glyph_pos[i].x_offset);
      env->SetIntArrayRegion(result, i * 5 + 4, 1, (const jint *) &glyph_pos[i].y_offset);
    }

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
