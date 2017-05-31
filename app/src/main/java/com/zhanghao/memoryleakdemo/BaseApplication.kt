package com.zhanghao.memoryleakdemo

import android.app.Application
import android.os.StrictMode
import com.squareup.leakcanary.LeakCanary

/**
 * Created by zhanghao on 2017/5/30.
 */
class BaseApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        if (LeakCanary.isInAnalyzerProcess(this))
            return
        enableStickMode()
        LeakCanary.install(this)
    }


    fun enableStickMode() {
        StrictMode.setThreadPolicy(
                StrictMode.ThreadPolicy.Builder()
                        .detectAll()
                        .penaltyLog()
                        .penaltyDeath()
                        .build()
        )
        StrictMode.setVmPolicy(StrictMode.VmPolicy.Builder()
                .detectActivityLeaks()
                .build()
        )
    }

}