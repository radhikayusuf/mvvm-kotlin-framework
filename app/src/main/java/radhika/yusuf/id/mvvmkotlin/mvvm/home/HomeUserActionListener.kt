package radhika.yusuf.id.mvvmkotlin.mvvm.home;

import radhika.yusuf.id.mvvmkotlin.utils.base.BaseUserActionListener


interface HomeUserActionListener : BaseUserActionListener {

    fun onClickItem(data: HomeModel)

}