ScreenLock-Android
==================

另外一种奇特的解锁方式的锁屏软件

#Alpha 4.0.0
    增加了锁屏界面启动的优化，启动速度更快
    增加了按键监听，禁止掉了其他不好的按键，比如home键
    任然存在问题，就是有时候可能会出现toast显示时机不对，貌似是因为lock掉了
    home按键屏蔽问题，解决方法，仿照LockLayer类的写法，添加了一个权限
    <uses-permission android:name="android.permission.SYSTEM_ALERT_WINDOW" />
    同时添加类LockLayer，最后添加lockLayer的逻辑代码lock() unlock()

#Alpha 3.0.0
    实现了几乎所有的功能，更改了UI和一些逻辑，增加了交互效果

#Alpha 2.0.1
    ----

#Alpha 2.0.0
    实现了最简单的框架，搭建了一个可以实现手势监听以及最后两个文件之间通过handler进行通信的
    一个过程，所以现在实现的功能是：
    1.gestureListener 实现手势监听并且通过handler将数据传递给Screen RelativeLayout里面进行处理
    2.RelativeLayout实现了根据获得的方向进行相关监听，下个版本要做的事情就是对于这种事件进行监听

# Beta 1.0
    实现了简单的锁屏界面，但是没有实现解锁逻辑
