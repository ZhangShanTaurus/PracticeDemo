@echo off
echo =====restart scroll-demo====
adb shell am force-stop com.zss.scroll
adb shell am start -n com.zss.scroll/com.zss.scroll.MainActivity