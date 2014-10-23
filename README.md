ScreenLock-Android
==================

另外一种奇特的解锁方式的锁屏软件

#Alpha 2.0.1
    ----

#Alpha 2.0.0
    实现了最简单的框架，搭建了一个可以实现手势监听以及最后两个文件之间通过handler进行通信的
    一个过程，所以现在实现的功能是：
    1.gestureListener 实现手势监听并且通过handler将数据传递给Screen RelativeLayout里面进行处理
    2.RelativeLayout实现了根据获得的方向进行相关监听，下个版本要做的事情就是对于这种事件进行监听

# Beta 1.0
    实现了简单的锁屏界面，但是没有实现解锁逻辑
