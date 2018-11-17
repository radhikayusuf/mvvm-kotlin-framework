package radhika.yusuf.id.mvvmkotlin.data.source.remote

import com.google.gson.Gson
import io.reactivex.Observer
import io.reactivex.disposables.Disposable
import radhika.yusuf.id.mvvmkotlin.utils.base.BaseApiModel
import retrofit2.HttpException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

/**
 * Created by radhikayusuf on 17/11/18.
 */

abstract class ApiCallback<M> : Observer<M> {

    abstract fun onSuccess(model: M)

    abstract fun onFailure(code: Int, errorMessage: String)

    abstract fun onFinish()

    override fun onComplete() {
        onFinish()
    }

    override fun onNext(t: M) {
        onSuccess(t)
    }

    override fun onSubscribe(d: Disposable) {

    }

    override fun onError(e: Throwable) {
        e.printStackTrace()
        when (e) {
            is HttpException -> {
                val code = e.code()
                var msg = e.message()
                var baseDao: BaseApiModel<M>? = null
                try {
                    val body = e.response().errorBody()
                    baseDao = Gson().fromJson<BaseApiModel<M>>(body?.string()?:"", BaseApiModel::class.java)
                } catch (exception: Exception) {
                    onFailure(code, exception.message?:"Unknown error occured")
                }

                when (code) {
                    504 -> {
                        msg = baseDao?.message ?: "Error Response"
                    }
                    502, 404 -> {
                        msg = baseDao?.message ?: "Error Connect or Resource Not Found"
                    }
                    400 -> {
                        msg = baseDao?.message ?: "Bad Request"
                    }
                    401 -> {
                        msg = baseDao?.message ?: "Not Authorized"
                    }
                }

                onFailure(code, msg)
            }

            is UnknownHostException -> onFailure(-1, "Telah terjadi kesalahan ketika koneksi ke server: ${e.message}")
            is SocketTimeoutException -> onFailure(-1, "Telah terjadi kesalahan ketika koneksi ke server: ${e.message}")
            else -> onFailure(-1, e.message ?: "Unknown error occured")
        }

        onFinish()
    }
}
