package radhika.yusuf.id.mvvmkotlin.utils.helper

import android.app.Application
import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import radhika.yusuf.id.mvvmkotlin.mvvm.home.HomeViewModel

/**
 * Created by radhikayusuf on 17/11/2018.
 */

class ViewModelFactory private constructor(
        private val mApplication: Application
) : ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>) =
            with(modelClass) {
                when {
                    isAssignableFrom(HomeViewModel::class.java) ->
                        HomeViewModel(mApplication)
                    else ->
                        throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
                }
            } as T

    companion object {

        @Volatile private var INSTANCE: ViewModelFactory? = null
        fun getInstance(mApplication: Application) =
                INSTANCE ?: synchronized(ViewModelFactory::class.java) {
                    INSTANCE ?: ViewModelFactory(mApplication)
                            .also { INSTANCE = it }
                }
    }
}