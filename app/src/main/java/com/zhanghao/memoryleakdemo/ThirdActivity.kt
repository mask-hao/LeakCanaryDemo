package com.zhanghao.memoryleakdemo
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.os.Message
import java.lang.ref.WeakReference

class ThirdActivity : AppCompatActivity() {

    companion object{
        private var handler:Handler?=null
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_thrid)
        handler = MyHandler(this)
        Thread(MyRunnable()).start()
    }


    private class MyHandler(thirdActivity: ThirdActivity) : Handler(Looper.getMainLooper()) {
        var activity : WeakReference<ThirdActivity>?=null
        override fun handleMessage(msg: Message?) {
            super.handleMessage(msg)
            if (activity?.get()!=null){
                println(System.currentTimeMillis())
                println(msg?.arg1)
            }
        }
        init {
            activity  = WeakReference(thirdActivity)
        }
    }


    private class MyRunnable : Runnable{
        override fun run() {
            println(System.currentTimeMillis())
            Thread.sleep(10000)
            val message = Message()
            message.arg1=10
            handler?.sendMessage(message)
        }
    }


}
