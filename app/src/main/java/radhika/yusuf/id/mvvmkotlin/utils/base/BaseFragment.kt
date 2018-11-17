package radhika.yusuf.id.mvvmkotlin.utils.base

import android.arch.lifecycle.Observer
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.View
import radhika.yusuf.id.mvvmkotlin.utils.extension.*

/**
 * @author radhikayusuf.
 */

abstract class BaseFragment<T : BaseViewModel> : Fragment(), BaseNavigator {

    lateinit var mParentVM: T
    private var mMessageType = MESSAGE_TYPE_SNACK


    override fun onViewCreated(paramView: View, savedInstanceState: Bundle?) {
        super.onViewCreated(paramView, savedInstanceState)
        mParentVM = getViewModel().apply {
            showMessage.observe(this@BaseFragment, Observer {
                if (it != null) {
                    when (mMessageType) {
                        MESSAGE_TYPE_SNACK_CUSTOM -> {
                            view?.showCustomSnackBar(it)
                        }
                        MESSAGE_TYPE_SNACK -> {
                            view?.showSnackBar(it)
                        }
                        else -> {
                            requireContext().showToast(it)
                        }
                    }
                }
            })

            showMessageRes.observe(this@BaseFragment, Observer {
                if (it != null) {
                    when (mMessageType) {
                        MESSAGE_TYPE_SNACK_CUSTOM -> {
                            view?.showCustomSnackBarRes(it)
                        }
                        MESSAGE_TYPE_SNACK -> {
                            view?.showSnackBarRes(it)
                        }
                        else -> {
                            requireContext().showToast(it)
                        }
                    }
                }
            })
        }
        onCreateObserver(mParentVM)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setContentData()
        mMessageType = setMessageType()
        mParentVM.start()
    }


    override fun onDestroy() {
        super.onDestroy()
        onDestroyObserver(mParentVM)
    }


    abstract fun onCreateObserver(viewModel: T)
    abstract fun getViewModel(): T
    abstract fun setContentData()
    abstract fun setMessageType(): String
    abstract fun onDestroyObserver(viewModel: T)


    companion object {
        const val MESSAGE_TYPE_TOAST = "TOAST_TYPE"
        const val MESSAGE_TYPE_SNACK = "SNACK_TYPE"
        const val MESSAGE_TYPE_SNACK_CUSTOM = "SNACK_CUSTOM_TYPE"
    }

}