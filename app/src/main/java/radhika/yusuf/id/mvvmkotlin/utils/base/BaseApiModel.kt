package radhika.yusuf.id.mvvmkotlin.utils.base

import com.google.gson.annotations.SerializedName

/**
 * Created by radhikayusuf on 17/11/18.
 */

data class BaseApiModel<T>(
        @SerializedName("code") val code: Int,
        @SerializedName("message") val message: String,
        @SerializedName("data") val data: T? = null

)