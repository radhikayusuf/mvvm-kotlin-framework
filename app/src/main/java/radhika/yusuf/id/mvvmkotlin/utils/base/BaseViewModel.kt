package radhika.yusuf.id.mvvmkotlin.utils.base

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.databinding.ObservableBoolean
import android.databinding.ObservableField
import radhika.yusuf.id.mvvmkotlin.data.source.Repository
import radhika.yusuf.id.mvvmkotlin.utils.helper.SingleLiveEvent
import radhika.yusuf.id.mvvmkotlin.utils.chocohelper.ChocoRepository
import radhika.yusuf.id.mvvmkotlin.utils.chocohelper.ChocoChips

/**
 * @author radhikayusuf.
 */

open class BaseViewModel(application: Application) : AndroidViewModel(application) {

    @ChocoRepository
    lateinit var mRepository: Repository

    val isRequesting = ObservableField(true)
    val showMessage = SingleLiveEvent<String>()
    val showMessageRes = SingleLiveEvent<Int>()

    open fun start(){
        ChocoChips.inject(this)
    }


}