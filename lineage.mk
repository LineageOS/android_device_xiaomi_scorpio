$(call inherit-product, device/xiaomi/scorpio/full_scorpio.mk)

# Inherit some common Lineage stuff.
$(call inherit-product, vendor/cm/config/common_full_phone.mk)

PRODUCT_NAME := lineage_scorpio
PRODUCT_BRAND := xiaomi
PRODUCT_MANUFACTURER := xiaomi
PRODUCT_DEVICE := scorpio

PRODUCT_GMS_CLIENTID_BASE := android-xiaomi
