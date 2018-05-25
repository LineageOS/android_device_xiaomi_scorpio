LOCAL_PATH := $(call my-dir)
include $(CLEAR_VARS)

LOCAL_SRC_FILES := $(call all-java-files-under, src)
LOCAL_CERTIFICATE := platform
LOCAL_PRIVILEGED_MODULE := true
LOCAL_PACKAGE_NAME := AudioCombiner

LOCAL_USE_AAPT2 := true

LOCAL_PROGUARD_FLAG_FILES := proguard.flags

LOCAL_MODULE_TAGS := optional

include $(BUILD_PACKAGE)
