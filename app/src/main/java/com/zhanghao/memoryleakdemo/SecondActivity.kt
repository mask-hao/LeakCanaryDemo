package com.zhanghao.memoryleakdemo

import android.os.Bundle
import android.os.Handler
import android.os.Message
import android.support.v7.app.AppCompatActivity
import android.util.Log



/**
 *
 * Created by zhanghao on 2017/5/30.
 */

class SecondActivity : AppCompatActivity(){



    inner  class MyHandler : Handler(){
        override fun handleMessage(msg: Message?) {
            super.handleMessage(msg)
            println(msg?.arg1)
            println(System.currentTimeMillis())
        }
    }



    internal var handler : Handler  = object : Handler(){
        override fun handleMessage(msg: Message?) {
            super.handleMessage(msg)
            println(msg?.arg1)
            println(System.currentTimeMillis())
        }
    }



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

         Thread{
             println(System.currentTimeMillis())
             Thread.sleep(10000)
             val message = Message()
             message.arg1=10
             handler.sendMessage(message)
         }.start()


    }


    override fun onDestroy() {
        super.onDestroy()
        Log.d("tag","destory")
        }

}
