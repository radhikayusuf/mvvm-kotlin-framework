package radhika.yusuf.id.mvvmkotlin.utils.extension

import android.app.Activity
import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.os.Handler
import android.support.design.widget.Snackbar
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentTransaction
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.Toast
import radhika.yusuf.id.mvvmkotlin.R
import radhika.yusuf.id.mvvmkotlin.utils.helper.ViewModelFactory

/**
 * @author radhikayusuf.
 */


fun <T : ViewModel> AppCompatActivity.obtainViewModel(viewModelClass: Class<T>) =
    ViewModelProviders.of(this, ViewModelFactory.getInstance(application)).get(viewModelClass)

fun <T : ViewModel> Fragment.obtainViewModel(viewModelClass: Class<T>) =
    ViewModelProviders.of(this, ViewModelFactory.getInstance(requireActivity().application)).get(viewModelClass)

fun AppCompatActivity.replaceFragmentInActivity(fragment: Fragment, frameId: Int) {
    supportFragmentManager.transact {
        replace(frameId, fragment)
    }
}

private inline fun FragmentManager.transact(action: FragmentTransaction.() -> Unit) {
    beginTransaction().apply {
        action()
    }.commit()
}


fun Context.showToast(message: Int, duration: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(this, message, duration).show()
}

fun Fragment.showToast(message: Int, duration: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(requireContext(), message, duration).show()
}

fun Context.showToast(message: String, duration: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(this, message, duration).show()
}

fun Fragment.showToast(message: String, duration: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(requireContext(), message, duration).show()
}

fun Activity.showToast(message: String, duration: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(this, message, duration).show()
}

fun View.showSnackBarRes(message: Int, duration: Int = Snackbar.LENGTH_SHORT) {
    Snackbar.make(this, message, duration).show()
}

fun View.showSnackBar(message: String, duration: Int = Snackbar.LENGTH_SHORT) {
    Snackbar.make(this, message, duration).show()
}

fun View.showCustomSnackBarRes(message: Int, millisDuration: Long = 5000, showAction: Boolean = true, actionMessageRes: Int = R.string.text_close) {
    showCustomSnackBar(context.getString(message), millisDuration, showAction, context.getString(actionMessageRes))
}

fun View.showCustomSnackBar(message: String, millisDuration: Long = 5000, showAction: Boolean = true, actionMessage: String = context.getString(R.string.text_close)) {
    Snackbar.make(this, message, Snackbar.LENGTH_INDEFINITE).apply {
        val runnable = Runnable {
            if (isShown) {
                dismiss()
            }
        }
        val handler = Handler().apply {
            postDelayed(runnable, millisDuration)
        }

        if (showAction) {
            setAction(actionMessage) {
                handler.removeCallbacks(runnable)
                runnable.run()
            }
        }

        show()
    }
}


