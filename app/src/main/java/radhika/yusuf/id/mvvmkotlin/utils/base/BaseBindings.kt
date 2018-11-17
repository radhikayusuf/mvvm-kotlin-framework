package radhika.yusuf.id.mvvmkotlin.utils.base

import android.annotation.SuppressLint
import android.databinding.BindingAdapter
import android.support.v7.widget.RecyclerView
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import radhika.yusuf.id.mvvmkotlin.mvvm.home.HomeAdapter
import radhika.yusuf.id.mvvmkotlin.mvvm.home.HomeModel
import radhika.yusuf.id.mvvmkotlin.utils.constant.DefaultValue.GLIDE_FADE_ANIMATION_DURATION

object BaseBindings {


    @BindingAdapter("app:listDataHome")
    @JvmStatic
    fun setListDataHome(recyclerView: RecyclerView, data: List<HomeModel>?) {
        with(recyclerView.adapter as HomeAdapter) {
            if (data != null) {
                replaceData(data)
            }
        }
    }

    @SuppressLint("PrivateResource")
    @BindingAdapter("app:imageUrl")
    @JvmStatic
    fun setImageUrl(view: ImageView, imageUrl: String?) {
        Glide.with(view.context)
            .load(imageUrl)
            .transition(
                DrawableTransitionOptions.withCrossFade(
                    GLIDE_FADE_ANIMATION_DURATION
                ))
            .into(view)
    }

}