package radhika.yusuf.id.mvvmkotlin

import android.app.Application
import android.content.Context

class MyApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        instance = this
    }


    companion object {
        lateinit var instance: MyApplication

        fun getContext(): Context = instance.applicationContext
    }
}